package uz.bprodevelopment.app.category.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryUpdationDto {

    @NotNull(message = "id is required")
    private Long id;

    @NotBlank(message = "name is required and can not be blank")
    private String name;

}
