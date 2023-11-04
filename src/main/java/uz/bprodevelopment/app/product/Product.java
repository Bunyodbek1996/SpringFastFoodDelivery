package uz.bprodevelopment.app.product;

import jakarta.persistence.*;
import lombok.*;
import uz.bprodevelopment.app.category.Category;
import uz.bprodevelopment.base.config.audit_config.AuditEntity;

import static jakarta.persistence.GenerationType.IDENTITY;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product extends AuditEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;
    private String imageHashCode;
    private Double price;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

}
