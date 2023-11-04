package uz.bprodevelopment.app.order.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.bprodevelopment.app.order_item.dtos.OrderItemCreationDto;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreationDto {

    private List<OrderItemCreationDto> orderItems;
    private Double latitude;
    private Double longitude;

}
