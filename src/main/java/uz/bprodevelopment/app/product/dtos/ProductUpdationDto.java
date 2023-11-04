package uz.bprodevelopment.app.product.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.bprodevelopment.app.category.Category;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdationDto {

    @NotNull(message = "id is required")
    private Long id;

    @NotBlank(message = "name is required and can not be blank")
    private String name;

    @NotNull(message = "price is required")
    private Double price;

    @NotNull(message = "categoryId is required")
    private Long categoryId;

    private String description;

}
