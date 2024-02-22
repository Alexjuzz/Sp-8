package Spring.cloud.api;

import Spring.cloud.api.products.Product;
import Spring.cloud.api.shoppingcarts.ShoppingCart;
import Spring.cloud.repository.ShoppingCartsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class ShoppingCartApiService {
        private final ShoppingCartsRepository repository;
        private final RestTemplate restTemplate;

        @Autowired
        public ShoppingCartApiService (ShoppingCartsRepository repository,RestTemplate restTemplate){
            this.repository = repository;
            this.restTemplate = restTemplate;
        }

    @Transactional
    public ShoppingCart putProductToBasket(Long id, Long  productId) {
        ShoppingCart currentBasket = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ShoppingCart not found for id: " + id));
        String productApi = "http://localhost:8082/api/products/getById/" + productId;
        Product product = restTemplate.getForObject(productApi, Product.class);
        currentBasket.getProductList().add(product);
        System.out.println(currentBasket.getProductList());
        return repository.save(currentBasket);

    }
    public boolean delProductInBasket(Long basketId, Long productId){
        ShoppingCart currentBasket = repository.findById(basketId)
                .orElseThrow(() -> new EntityNotFoundException("ShoppingCart not found for id: " + basketId));
        Product productToRemove = currentBasket.getProductList().stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Product not found for id: " + basketId));

        if (productToRemove != null) {
            currentBasket.getProductList().remove(productToRemove);
            repository.save(currentBasket);
            return true;
        }
        return false;
    }


    public ShoppingCart createBasket(ShoppingCart shoppingCart) {
            ShoppingCart basket = new ShoppingCart();
            return repository.save(basket);
    }

    public List<ShoppingCart> getAllBasket() {
            return repository.findAll();
    }
}
