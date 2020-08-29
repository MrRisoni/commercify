SELECT p.id FROM products p
JOIN
( SELECT product_id FROM product_attributes_values
   WHERE attribute_id=9 AND value_boolean = 1
) AS filtr0 ON filtr0.product_id=p.id
JOIN
( SELECT product_id FROM product_attributes_values
 WHERE attribute_id=7
  AND value_numeric >= -1.0 AND value_numeric <=17.0
  ) AS filtr1 ON filtr1.product_id=p.id
WHERE p.shop_id = 2
AND p.category_id = 5
AND p.visible = 1
AND p.active =1
AND p.price >=0
AND p.price <=1500
---------------------------------
SELECT pav.value,
pca.code,
COUNT(pav.id)
FROM product_category_attributes pca
JOIN product_attributes_values pav ON pav.attribute_id = pca.id
WHERE pca.shop_id =2
AND pca.isGrouppable = 1
AND pca.isString =1
GROUP BY pav.value

---------------------------------
SELECT pca.id,
pca.code,
pav.value_boolean,
COUNT(pav.id)
FROM product_category_attributes pca
JOIN product_attributes_values pav ON pav.attribute_id = pca.id
WHERE pca.shop_id =2
AND pca.isGrouppable = 1
AND pca.isBoolean =1
GROUP BY pca.code,pav.value_boolean
--------------------------------------------



