package Spring.cloud.api.orders;

import Spring.cloud.api.shoppingcarts.ShoppingCart;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
@Entity
@Getter
@Setter
@Table(name = "Orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "UniqID")
    private UUID uuid;
    @PrePersist
    private void prePersist(){
        this.uuid = UUID.randomUUID();
    }
//    @Column(name= "Basket")
//    private ShoppingCart basket;
}
