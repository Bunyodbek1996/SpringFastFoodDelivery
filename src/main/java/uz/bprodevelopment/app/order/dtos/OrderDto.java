package uz.bprodevelopment.app.order.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.bprodevelopment.app.order.OrderStatus;
import uz.bprodevelopment.app.order_item.dtos.OrderItemDto;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long id;
    private String userPhoneNumber;
    private String userFullName;
    private OrderStatus status;
    private String estimatedDeliveryTime;

    private List<OrderItemDto> orderItems;

    private Double latitude;
    private Double longitude;


}
