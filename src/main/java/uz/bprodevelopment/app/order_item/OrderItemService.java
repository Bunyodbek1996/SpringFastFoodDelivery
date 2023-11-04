package uz.bprodevelopment.app.order_item;


import uz.bprodevelopment.app.order_item.dtos.OrderItemCreationDto;
import uz.bprodevelopment.app.order_item.dtos.OrderItemDto;
import uz.bprodevelopment.app.order_item.dtos.OrderItemUpdationDto;
import uz.bprodevelopment.base.util.CustomPage;

import java.util.List;

public interface OrderItemService {

    OrderItemDto getOne(Long id);

    List<OrderItemDto> getListAll(OrderItemFilter filter);

    CustomPage<OrderItemDto> getList(OrderItemFilter filter);

    void save(OrderItemCreationDto dto);

    void update(OrderItemUpdationDto dto);

    void delete(Long id);

}
