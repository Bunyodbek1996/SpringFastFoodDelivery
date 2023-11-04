package uz.bprodevelopment.app.order;

import jakarta.persistence.*;
import lombok.*;
import uz.bprodevelopment.app.order_item.OrderItem;
import uz.bprodevelopment.base.config.audit_config.AuditEntity;
import uz.bprodevelopment.base.user.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order extends AuditEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @Column(nullable = false)
    private LocalDateTime estimatedDeliveryTime;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status = OrderStatus.CREATED;

    @OneToMany(
            mappedBy = "order",
            cascade = {
                    CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH
            }
    )
    private List<OrderItem> orderItems = new ArrayList<>();

    private Double latitude;
    private Double longitude;

}
