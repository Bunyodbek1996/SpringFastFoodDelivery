package uz.bprodevelopment.app.order;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import uz.bprodevelopment.app.order.dtos.OrderCreationDto;
import uz.bprodevelopment.app.order.dtos.OrderDto;
import uz.bprodevelopment.app.order.dtos.OrderUpdationDto;
import uz.bprodevelopment.app.order_item.OrderItemMapper;
import uz.bprodevelopment.app.product.Product;
import uz.bprodevelopment.base.util.AppUtils;
import uz.bprodevelopment.base.util.CustomPage;

import java.util.List;

@Mapper(componentModel = "spring",
        imports = { Product.class, AppUtils.class },
        uses = { OrderItemMapper.class }
)
public interface OrderMapper {

    @Mapping(target = "userPhoneNumber", expression = "java(order.getUser().getPhoneNumber())")
    @Mapping(target = "userFullName", expression  = "java(order.getUser().getFullName())")
    @Mapping(target = "estimatedDeliveryTime", source = "estimatedDeliveryTime", dateFormat = "dd.MM.yyyy HH:mm")
    OrderDto toDto(Order order);

    @Mapping(target = "user", expression = "java(AppUtils.getCurrentUser())" )
    Order toEntity(OrderCreationDto dto);

    Order toEntity(OrderUpdationDto dto);

    List<OrderDto> toDtos(List<Order> categories);

    @Mapping(target = "isFirst", source = "first")
    @Mapping(target = "isLast", source = "last")
    @Mapping(target = "pageNumber", expression = "java(page.getNumber() + 1)" )
    CustomPage<OrderDto> toCustomPage(Page<Order> page);

}
