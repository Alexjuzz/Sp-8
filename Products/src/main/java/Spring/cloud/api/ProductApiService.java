package Spring.cloud.api;

import Spring.cloud.api.products.Product;
import Spring.cloud.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductApiService {
    @Autowired
    private ProductRepository repository;

    public Product createPen(Product product){
        Product pen = new Product();
        pen.setName(product.getName());
        pen.setDescription(product.getDescription());
        return repository.save(pen);
    }

    public Product getProduct(Long id){
        return repository.findById(id).orElse(null);
    }

    public List<Product> getAllProducts(){
        return repository.findAll();
    }
    public void deleteProduct(Long id){
        repository.deleteById(id);
    }

}
