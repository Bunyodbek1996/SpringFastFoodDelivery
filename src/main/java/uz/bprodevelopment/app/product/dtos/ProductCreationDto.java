package uz.bprodevelopment.app.product.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreationDto {

    @NotBlank(message = "name is required and can not be blank")
    private String name;

    @NotNull(message = "price is required")
    private Double price;

    @NotNull(message = "categoryId is required")
    private Long categoryId;

    private String description;

}
