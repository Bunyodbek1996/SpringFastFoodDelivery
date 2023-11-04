package uz.bprodevelopment.app.category.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryCreationDto {

    @NotBlank(message = "name is required and can not be blank")
    private String name;

}
