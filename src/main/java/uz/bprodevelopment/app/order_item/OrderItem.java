package uz.bprodevelopment.app.order_item;

import jakarta.persistence.*;
import lombok.*;
import uz.bprodevelopment.app.order.Order;
import uz.bprodevelopment.app.product.Product;
import uz.bprodevelopment.base.config.audit_config.AuditEntity;

import static jakarta.persistence.GenerationType.IDENTITY;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_items")
public class OrderItem extends AuditEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private Double price;

    private Integer quantity;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH
            }
    )
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    private Product product;

}
