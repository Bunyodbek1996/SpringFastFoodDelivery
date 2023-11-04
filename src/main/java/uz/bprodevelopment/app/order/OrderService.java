package uz.bprodevelopment.app.order;

import uz.bprodevelopment.app.order.dtos.OrderCreationDto;
import uz.bprodevelopment.app.order.dtos.OrderDto;
import uz.bprodevelopment.app.order.dtos.OrderUpdationDto;
import uz.bprodevelopment.base.util.CustomPage;

import java.util.List;

public interface OrderService {

    OrderDto getOne(Long id);

    List<OrderDto> getListAll(OrderFilter filter);

    CustomPage<OrderDto> getList(OrderFilter filter);

    void save(OrderCreationDto dto);

    void update(OrderUpdationDto dto);

    void delete(Long id);

    void confirm(Long id);

    void sent(Long id);

    void cancel(Long id);

}
