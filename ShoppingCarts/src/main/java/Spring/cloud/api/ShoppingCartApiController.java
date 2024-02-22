package Spring.cloud.api;

import Spring.cloud.api.products.Product;
import Spring.cloud.api.shoppingcarts.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/basket")
public class ShoppingCartApiController {
    private final  ShoppingCartApiService service;

    @Autowired
    public ShoppingCartApiController(ShoppingCartApiService service){
        this.service = service;
    }
    @PostMapping("/")
    public ShoppingCart createBasket(@RequestBody ShoppingCart shoppingCart){
        return  service.createBasket(shoppingCart);
    }

    @DeleteMapping(value = "/del/{productId}")
    public ResponseEntity deleteProductInBasket(@RequestBody Long basketId, @PathVariable("productId") Long productId){
        if(service.delProductInBasket(basketId,productId)){
            return new ResponseEntity("Product: " + productId + "has removed." , HttpStatus.ACCEPTED);
        }else {
            return new ResponseEntity("Bad request",HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/addProduct/{basketId}")
    public ShoppingCart putProduct(@PathVariable("basketId") Long basketId, @RequestBody() Long productId){
        return service.putProductToBasket(basketId,productId);
    }

    @GetMapping("/getAllBasket")
    public List<ShoppingCart> getAllBasket(){
        
        return service.getAllBasket();
    }
}
