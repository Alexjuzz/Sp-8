package Spring.cloud.api;


import Spring.cloud.api.products.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductsApiController {


    @Autowired
    private final ProductApiService service;

    @Autowired
    public ProductsApiController(ProductApiService service) {
        this.service = service;
    }

    @GetMapping("/getById/{id}")
    public Product getById(@PathVariable("id") Long id) {
        return service.getProduct(id);
    }

    @PostMapping(value = "/createProduct")
    public Product createPen(@RequestBody Product product) {
        return service.createPen(product);
    }


    @GetMapping(value = "/getAllProducts")
    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }

    @PostMapping(value = "/delProduct/{id}")
    public void delProduct(@PathVariable("id") Long id) {
        service.deleteProduct(id);
    }

}
