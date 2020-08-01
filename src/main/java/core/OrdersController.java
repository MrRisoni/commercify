package core;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import models.Order;
import models.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pojos.BasketAdd;
import spring_repos.OrderRepository;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Optional;

@CrossOrigin
@RestController
public class OrdersController {

    @Autowired
    OrderRepository ordRepo;

    @PostMapping(value = "/api/order/add_basket",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public String addToBasket(@RequestBody Object postData, HttpSession session) {
        try {
            session.setAttribute("foo", "bar");
            System.out.println(postData);
            Gson g = new Gson();
            BasketAdd kalathi = new Gson().fromJson(g.toJson(postData), BasketAdd.class);
            System.out.println("Deserialize");
            System.out.println(kalathi.getFoodId());
            System.out.println(kalathi.getIngredients().size());
            System.out.println(session.getAttribute("foo"));
            return "ok " + session.getAttribute("totalPrice");
        }
        catch (Exception ex) {
            return ex.getMessage();
        }
    }


    @RequestMapping(value=  "/api/order/show" , method = RequestMethod.GET)
    public String getOrderDetails() {

        try {
            Optional<Order> fetchedOrder = ordRepo.findById(1L);
            Order returnedOrder = fetchedOrder.orElse(null);

            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
            return mapper.writerWithView(View.IOrder.class).writeValueAsString(returnedOrder);
        }
        catch (Exception ex){
            return ex.getMessage();
        }
    }

}
