package uz.bprodevelopment.app.order;

import com.google.common.util.concurrent.AtomicDouble;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.bprodevelopment.app.order.dtos.OrderCreationDto;
import uz.bprodevelopment.app.order.dtos.OrderDto;
import uz.bprodevelopment.app.order.dtos.OrderUpdationDto;
import uz.bprodevelopment.app.order_item.OrderItemRepository;
import uz.bprodevelopment.base.util.AppUtils;
import uz.bprodevelopment.base.util.CustomPage;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderMapper orderMapper;

    @Value("${cafe.latitude}")
    private Double cafeLatitude;

    @Value("${cafe.longitude}")
    private Double cafeLongitude;

    @Override
    public OrderDto getOne(Long id) {
        Order order = orderRepository.getReferenceById(id);
        return orderMapper.toDto(order);
    }

    @Override
    public List<OrderDto> getListAll(OrderFilter filter) {

        OrderSpecification spec = filter.retrieveSpecification();

        List<Order> categories = orderRepository.findAll(spec.getSpecification(), filter.sorting());

        return orderMapper.toDtos(categories);
    }

    @Override
    public CustomPage<OrderDto> getList(OrderFilter filter) {

        OrderSpecification spec = filter.retrieveSpecification();

        Page<Order> page = orderRepository.findAll(spec.getSpecification(), filter.pageable());

        return orderMapper.toCustomPage(page);
    }

    @Override
    @Transactional
    public void save(OrderCreationDto dto) {
        Order order = orderMapper.toEntity(dto);

        AtomicDouble estimatedDeliveryTime = new AtomicDouble(0);

        // I am going to retrieve Orders which is currently cooking
        List<Order> cookingOrders = orderRepository.findAllByStatus(OrderStatus.CONFIRMED);
        cookingOrders.add(order);

        // For every food preparing time is 1.25 minutes according to your documentation
        // So I considered all of them here
        cookingOrders.forEach(ord -> {
            ord.getOrderItems().forEach(orderItem -> {
                estimatedDeliveryTime.updateAndGet(v -> v + orderItem.getQuantity() * 1.25);
            });
        });

        // In here I considered between two locations
        double distance = AppUtils.distanceBetweenLocations(
                cafeLatitude, cafeLongitude, dto.getLatitude(), dto.getLongitude()
        );

        // cooking time + delivering time
        estimatedDeliveryTime.updateAndGet(v -> v + (distance * 3));

        order.setEstimatedDeliveryTime(LocalDateTime.now().plusMinutes(estimatedDeliveryTime.intValue()));

        orderRepository.save(order);
        order.getOrderItems().forEach(orderItem -> orderItem.setOrder(order));
        orderItemRepository.saveAll(order.getOrderItems());
    }

    @Override
    @Transactional
    public void update(OrderUpdationDto dto) {
        Order order = orderMapper.toEntity(dto);

        Double oldLat = order.getLatitude();
        Double oldLon = order.getLongitude();
        double oldDistance = AppUtils.distanceBetweenLocations(cafeLatitude, cafeLongitude, oldLat, oldLon);
        double newDistance = AppUtils.distanceBetweenLocations(cafeLatitude, cafeLongitude, dto.getLatitude(), dto.getLongitude());
        double difference = newDistance - oldDistance;

        LocalDateTime estimatedDeliveryTime = order.getEstimatedDeliveryTime().plusMinutes((long) (difference * 3));
        order.setEstimatedDeliveryTime(estimatedDeliveryTime);
    }


    @Override
    public void delete(Long id) {
        Order order = orderRepository.getReferenceById(id);
        if (!(order.getStatus().equals(OrderStatus.CANCELLED) || order.getStatus().equals(OrderStatus.CREATED))) {
            throw new RuntimeException("You can not delete this order for it status is not available for deleting");
        }
        orderRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void confirm(Long id) {
        Order order = orderRepository.getReferenceById(id);
        if (!order.getStatus().equals(OrderStatus.CREATED)) {
            throw new RuntimeException("You can not confirm this order for it status is not available for confirming");
        }
        order.setStatus(OrderStatus.CONFIRMED);
    }

    @Override
    @Transactional
    public void sent(Long id) {
        Order order = orderRepository.getReferenceById(id);
        if (!order.getStatus().equals(OrderStatus.CONFIRMED)) {
            throw new RuntimeException("You can not send this order for it status is not available for sending");
        }
        order.setStatus(OrderStatus.SENT);
    }

    @Override
    @Transactional
    public void cancel(Long id) {
        Order order = orderRepository.getReferenceById(id);
        if (!order.getStatus().equals(OrderStatus.CREATED)) {
            throw new RuntimeException("You can not cancel this order for it status is not available for cancelling");
        }
        order.setStatus(OrderStatus.CANCELLED);
    }

}
