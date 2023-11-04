package uz.bprodevelopment.app.order_item.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemUpdationDto {

    private Long id;
    private Integer quantity;

}
