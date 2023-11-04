package uz.bprodevelopment.app.order_item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import uz.bprodevelopment.app.order.Order;
import uz.bprodevelopment.app.order.OrderStatus;
import uz.bprodevelopment.app.order_item.dtos.OrderItemCreationDto;
import uz.bprodevelopment.app.order_item.dtos.OrderItemDto;
import uz.bprodevelopment.app.order_item.dtos.OrderItemUpdationDto;
import uz.bprodevelopment.base.file_storage.FileStorageService;
import uz.bprodevelopment.base.util.CustomPage;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderItemServiceImpl implements OrderItemService {

    private final FileStorageService fileStorageService;
    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;

    @Override
    public OrderItemDto getOne(Long id) {
        OrderItem orderItem = orderItemRepository.getReferenceById(id);
        return orderItemMapper.toDto(orderItem);
    }

    @Override
    public List<OrderItemDto> getListAll(OrderItemFilter filter) {

        OrderItemSpecification spec = filter.retrieveSpecification();

        List<OrderItem> categories = orderItemRepository.findAll(spec.getSpecification(), filter.sorting());

        return orderItemMapper.toDtos(categories);
    }

    @Override
    public CustomPage<OrderItemDto> getList(OrderItemFilter filter) {

        OrderItemSpecification spec = filter.retrieveSpecification();

        Page<OrderItem> page = orderItemRepository.findAll(spec.getSpecification(), filter.pageable());

        return orderItemMapper.toCustomPage(page);
    }


    @Override
    public void save(OrderItemCreationDto dto) {
        OrderItem orderItem = orderItemMapper.toEntity(dto);
        Order order = orderItem.getOrder();
        if (!order.getStatus().equals(OrderStatus.CREATED)){
            throw new IllegalStateException("Order is not available for adding new item. Status: " + order.getStatus());
        }
        orderItemRepository.save(orderItem);
    }

    @Override
    public void update(OrderItemUpdationDto dto) {
        OrderItem orderItem = orderItemMapper.toEntity(dto);
        Order order = orderItem.getOrder();
        if (!order.getStatus().equals(OrderStatus.CREATED)){
            throw new IllegalStateException("Order is not available for updating new item. Status: " + order.getStatus());
        }
        orderItemRepository.save(orderItem);
    }


    @Override
    public void delete(Long id) {
        orderItemRepository.deleteById(id);
    }

}
