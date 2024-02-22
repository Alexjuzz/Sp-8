package Spring.cloud.api.products;


import Spring.cloud.api.shoppingcarts.ShoppingCart;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



@Entity
@Getter
@Setter
@Table(name="products")
public  class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shoppingcart_id")
    private ShoppingCart shoppingcart;

}
