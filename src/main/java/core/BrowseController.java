package core;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class BrowseController {


    @RequestMapping(value=  "/api/category/{categoryId}" , method = RequestMethod.GET)
    public String getProducts(@PathVariable Long categoryId)
    {
        try {
            // get manufacturers
            return null;
        }
        catch (Exception ex)
        {
            return ex.getMessage();
        }
    }
}
