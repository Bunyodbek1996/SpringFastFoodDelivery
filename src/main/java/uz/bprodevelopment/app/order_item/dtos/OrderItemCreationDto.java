package uz.bprodevelopment.app.order_item.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemCreationDto {

    private Integer quantity;
    private Long productId;

}
