package Spring.cloud.repository;

import Spring.cloud.api.shoppingcarts.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartsRepository extends JpaRepository<ShoppingCart, Long> {
}
