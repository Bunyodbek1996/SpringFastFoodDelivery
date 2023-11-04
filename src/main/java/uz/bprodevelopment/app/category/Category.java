package uz.bprodevelopment.app.category;

import jakarta.persistence.*;
import lombok.*;
import uz.bprodevelopment.base.config.audit_config.AuditEntity;

import static jakarta.persistence.GenerationType.IDENTITY;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category extends AuditEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

}
