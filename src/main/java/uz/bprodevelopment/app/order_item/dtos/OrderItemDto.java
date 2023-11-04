package uz.bprodevelopment.app.order_item.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {

    private Long id;
    private Double price;
    private Double totalPrice;
    private Integer quantity;
    private String productName;


}
