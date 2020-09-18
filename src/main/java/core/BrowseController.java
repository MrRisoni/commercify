package core;

import com.google.gson.Gson;
import dto.AttributeGroupBool;
import dto.AttributeGroupString;
import dto.RangeValues;
import dto.SimpleProduct;
import entity.*;
import entity.product.ProductAttributesValues;
import entity.product.ProductCategoryAttributes;
import entity.product.Products;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pojo.BrowsePojo;
import pojo.ProductFilterPojo;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class BrowseController {

    public List<String> joinSqlAttributeFiltering = new ArrayList<>();
    public int filtrCounter = 0;


    private Subquery buildSubQry(CriteriaQuery criteriaQry, CriteriaBuilder builder, Long shopId, Long categoryId, List<ProductFilterPojo> filtra,String CritName) {
        Subquery<Products> subQuery = criteriaQry.subquery(Products.class);
        Root<Products> subRoot = subQuery.from(Products.class);
        CollectionJoin<ProductAttributesValues, Products> joinWithAttributeValues = subRoot.joinCollection("productAttributesValuesCollection", JoinType.INNER);
        Predicate[] subQryPredicates;

        System.out.println("###############################################");


        String smallSQL = "";
        ProductFilterPojo filtro;
        System.out.println("CONSTRUCTING PREDICATES FOR " + CritName + " |  SIZE IS " + filtra.size());

        if (filtra.size() == 1) {
            filtro = filtra.get(0);

            subQryPredicates = new Predicate[3];
            subQryPredicates[0] = builder.equal(subRoot.get("shopKey"), shopId);
            subQryPredicates[1] = builder.equal(subRoot.get("categoryKey"), categoryId);


            smallSQL = "SELECT product_id FROM product_attributes_values WHERE attribute_id=" + filtro.getAttributeId();

            if (filtro.getType().equals("bool")) {
                subQryPredicates[2] = builder.and(builder.equal(joinWithAttributeValues.get("valueBoolean"), 1),
                        builder.equal(joinWithAttributeValues.get("attributeKey"), filtro.getAttributeId()));

                smallSQL += " AND value_boolean = 1";
            } else if (filtro.getType().equals("range")) {
                subQryPredicates[2] = builder.and(builder.between(joinWithAttributeValues.get("valueNumeric"), filtro.getFrom(), filtro.getTo()),
                        builder.equal(joinWithAttributeValues.get("attributeKey"), filtro.getAttributeId()));

                smallSQL += " AND value_numeric >= " + String.valueOf(filtro.getFrom()) + " AND value_numeric <=" + String.valueOf(filtro.getTo());

            } else if (filtro.getType().equals("str")) {
                subQryPredicates[2] =builder.and(builder.equal(joinWithAttributeValues.get("value"), filtro.getValueStr()),
                        builder.equal(joinWithAttributeValues.get("attributeKey"), filtro.getAttributeId()));

                smallSQL += " AND value = '" + filtro.getValueStr()  +"' ";

            } else {
                System.out.println("UNKNOWN FILTER TYPE " + filtro.getType());
            }
            System.out.println("TYPE " + filtro.getType());
        }
        else {
            // OR
            // AND it may have many values ....
            subQryPredicates = new Predicate[3];

            subQryPredicates[0] = builder.equal(subRoot.get("shopKey"), shopId);
            subQryPredicates[1] = builder.equal(subRoot.get("categoryKey"), categoryId);

            Predicate[] miniPredicates = new Predicate[filtra.size()];

            smallSQL = "SELECT product_id FROM product_attributes_values WHERE attribute_id=" + filtra.get(0).getAttributeId();
            List<String> miniSQLS = new ArrayList<>();

            System.out.println("MINI PREDICATES LEN " + filtra.size());
            for (int q=0;q< miniPredicates.length;q++)
            {
                System.out.println("Q IS " + q);

                // for str
                miniPredicates[q] = builder.and(builder.equal(joinWithAttributeValues.get("value"), filtra.get(q).getValueStr()),
                        builder.equal(joinWithAttributeValues.get("attributeKey"),filtra.get(q).getAttributeId()));

                miniSQLS.add(" value = '" + filtra.get(q).getValueStr()  +"' ");
            }


            subQryPredicates[2] = builder.or(miniPredicates);
            smallSQL += " AND ( " + String.join(" OR ",miniSQLS) + " ) ";
        }

        String filterId = "filtr" + String.valueOf(this.filtrCounter);
        this.joinSqlAttributeFiltering.add("JOIN ( " + smallSQL + " ) AS " + filterId + " ON " + filterId + ".product_id = p.id ");

        System.out.println("SMALL SQL");
        System.out.println(smallSQL);
        this.filtrCounter++;



        subQuery.select(subRoot)
                .where(subQryPredicates);

        System.out.println("END OF SUBFUNCTION!");
        return subQuery;

    }

    @PostMapping(value = "/api/browse/shop/{shopId}/category/{categoryId}",
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public HashMap<String, Object> getProductsWithCriteriaBuilder(@RequestBody Object filterCriteria,
                                                                  @PathVariable Long shopId,@PathVariable Long categoryId) {
        try {

            this.joinSqlAttributeFiltering.clear();
            this.filtrCounter = 0;


            EntityManager em = HibernateUtil.getEM();
            Gson g = new Gson();
            BrowsePojo searchCriteriaPojo = new Gson().fromJson(g.toJson(filterCriteria), BrowsePojo.class);
            HashMap<String, RangeValues> ranges = new HashMap<>();
            HashMap<String, Object> rsp = new HashMap<>();

            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<SimpleProduct> criteriaQry = builder.createQuery(SimpleProduct.class);
            Root<Products> rootProduct = criteriaQry.from(Products.class);

            // do not select all columns!!!
            int predicatesLen = 4; // category and shop, active, visible
            if (searchCriteriaPojo.getMinPrice() > -1) {
                predicatesLen++;
            }
            if (searchCriteriaPojo.getMaxPrice() > -1) {
                predicatesLen++;
            }

            HashMap<String, List<ProductFilterPojo>> synolo = this.groupByFilterAttribute(searchCriteriaPojo.getFilters());


            predicatesLen = 6 + synolo.size();
            System.out.println("TOTAL PREDICATES SIZE " + predicatesLen);

            Predicate[] ProductPredicates = new Predicate[predicatesLen];
            ProductPredicates[0] = builder.equal(rootProduct.get("shopKey"), shopId);
            ProductPredicates[1] = builder.equal(rootProduct.get("categoryKey"), categoryId);
            ProductPredicates[2] = builder.equal(rootProduct.get("active"), 1);
            ProductPredicates[3] = builder.equal(rootProduct.get("visible"), 1);

            ProductPredicates[4] = builder.ge(rootProduct.get("price"), searchCriteriaPojo.getMinPrice());
            ProductPredicates[5] = builder.le(rootProduct.get("price"), searchCriteriaPojo.getMaxPrice());


            int z = 6;
            Iterator iterator = synolo.entrySet().iterator();
            while (iterator.hasNext()) {
                System.out.println("Z is " + z);
                Map.Entry xyz = (Map.Entry) iterator.next();
                List<ProductFilterPojo> filtrouli = (List<ProductFilterPojo>) xyz.getValue();
                System.out.println("GET PREDICATES FOR " + xyz.getKey());
                ProductPredicates[z] =  builder.in(rootProduct.get("id")).value(buildSubQry(criteriaQry, builder, shopId, categoryId, filtrouli,xyz.getKey().toString()));
                z++;
                System.out.println("NEXT predicate!");
            }


            String finalSQL = " SELECT COUNT(p.id) AS totalProducts FROM products p ";
            finalSQL += String.join(" ", this.joinSqlAttributeFiltering);
            finalSQL += "  WHERE p.shop_id = " + String.valueOf(shopId);
            finalSQL += " AND p.category_id = " + String.valueOf(categoryId);
            finalSQL += " AND p.visible = 1 AND p.active =1 ";
            finalSQL += " AND p.price >=0 AND p.price <=1500 ";

            // finalSQL will be used to get total products and pages ,ie only for pagination


            switch (searchCriteriaPojo.getOrderBy().getOrderBy()) {
                case "price":
                    criteriaQry.orderBy(builder.asc(rootProduct.get("price")));
                    break;
                case "best_seller": // best seller
                    criteriaQry.orderBy(builder.desc(rootProduct.get("totalOrders")));
                    break;
                case "popularity":
                    criteriaQry.orderBy(builder.desc(rootProduct.get("total_clicks")));
                    break;
                case "avg_rating":
                    criteriaQry.orderBy(builder.desc(rootProduct.get("avgRating")));
                    break;
                case "new_products":
                    criteriaQry.orderBy(builder.desc(rootProduct.get("created")));
                    break;
                default:
                    criteriaQry.orderBy(builder.asc(rootProduct.get("price")));
                    break;
            }

            System.out.println("EXECURING QRY");
            // FINAL QUERY to show products
            List<SimpleProduct> criteriaResult = em.createQuery(
                    criteriaQry.multiselect(rootProduct.get("id"),
                            rootProduct.get("code"),
                            rootProduct.get("title"),
                            rootProduct.get("thumbnailUrl"),
                            rootProduct.get("price"),
                            rootProduct.get("avgRating"))
                            .where(ProductPredicates)
            ).getResultList();
            System.out.println("QRY EXECUTED!");

            BigInteger totalProducts = (BigInteger) em.createNativeQuery(finalSQL).getSingleResult();

            System.out.println("PER PAGE " + searchCriteriaPojo.getCurrentPage());
            BigInteger totalPages = totalProducts.divide(searchCriteriaPojo.getPerPage());

            BigInteger remainder = totalPages.remainder(searchCriteriaPojo.getPerPage());
            if (remainder.compareTo(BigInteger.valueOf(0L)) >=0) {
               totalPages = totalPages.add(BigInteger.valueOf(1L));
            }

            rsp.put("products",criteriaResult);
            rsp.put("resCount", totalProducts);
            rsp.put("totalPages", totalPages);

            rsp.put("finalSQL", finalSQL);
            System.out.println("---------------------------");
            System.out.println(finalSQL);

            return rsp;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private HashMap<String, List<ProductFilterPojo>> groupByFilterAttribute(List<ProductFilterPojo> filterVals) {
        HashMap<String, List<ProductFilterPojo>> synolo = new HashMap<>();
        Set<String> uniqueCodes = new HashSet<>();

        uniqueCodes = filterVals.stream().map(fitlr -> {
            return fitlr.getAttributeCode();
        }).collect(Collectors.toSet());

        for (String uni : uniqueCodes) {
            System.out.println("UNIK CODE " + uni);
            synolo.put(uni, filterVals.stream().filter(valItm -> {
                        return valItm.getAttributeCode().equals(uni);
                    }
            ).collect(Collectors.toList()));
        }

        return synolo;
    }




}
