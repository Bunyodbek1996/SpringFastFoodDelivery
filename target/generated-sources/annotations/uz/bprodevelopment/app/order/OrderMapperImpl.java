package uz.bprodevelopment.app.order;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import uz.bprodevelopment.app.order.dtos.OrderCreationDto;
import uz.bprodevelopment.app.order.dtos.OrderDto;
import uz.bprodevelopment.app.order.dtos.OrderUpdationDto;
import uz.bprodevelopment.app.order_item.OrderItem;
import uz.bprodevelopment.app.order_item.OrderItemMapper;
import uz.bprodevelopment.app.order_item.dtos.OrderItemCreationDto;
import uz.bprodevelopment.app.product.Product;
import uz.bprodevelopment.base.util.AppUtils;
import uz.bprodevelopment.base.util.CustomPage;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-04T14:05:51+0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8.1 (Amazon.com Inc.)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Autowired
    private OrderItemMapper orderItemMapper;
    private final DateTimeFormatter dateTimeFormatter_dd_MM_yyyy_HH_mm_12071769242 = DateTimeFormatter.ofPattern( "dd.MM.yyyy HH:mm" );

    @Override
    public OrderDto toDto(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDto orderDto = new OrderDto();

        if ( order.getEstimatedDeliveryTime() != null ) {
            orderDto.setEstimatedDeliveryTime( dateTimeFormatter_dd_MM_yyyy_HH_mm_12071769242.format( order.getEstimatedDeliveryTime() ) );
        }
        orderDto.setId( order.getId() );
        orderDto.setStatus( order.getStatus() );
        orderDto.setOrderItems( orderItemMapper.toDtos( order.getOrderItems() ) );
        orderDto.setLatitude( order.getLatitude() );
        orderDto.setLongitude( order.getLongitude() );

        orderDto.setUserPhoneNumber( order.getUser().getPhoneNumber() );
        orderDto.setUserFullName( order.getUser().getFullName() );

        return orderDto;
    }

    @Override
    public Order toEntity(OrderCreationDto dto) {
        if ( dto == null ) {
            return null;
        }

        Order.OrderBuilder order = Order.builder();

        order.orderItems( orderItemCreationDtoListToOrderItemList( dto.getOrderItems() ) );
        order.latitude( dto.getLatitude() );
        order.longitude( dto.getLongitude() );

        order.user( AppUtils.getCurrentUser() );

        return order.build();
    }

    @Override
    public Order toEntity(OrderUpdationDto dto) {
        if ( dto == null ) {
            return null;
        }

        Order.OrderBuilder order = Order.builder();

        order.latitude( dto.getLatitude() );
        order.longitude( dto.getLongitude() );

        return order.build();
    }

    @Override
    public List<OrderDto> toDtos(List<Order> categories) {
        if ( categories == null ) {
            return null;
        }

        List<OrderDto> list = new ArrayList<OrderDto>( categories.size() );
        for ( Order order : categories ) {
            list.add( toDto( order ) );
        }

        return list;
    }

    @Override
    public CustomPage<OrderDto> toCustomPage(Page<Order> page) {
        if ( page == null ) {
            return null;
        }

        CustomPage<OrderDto> customPage = new CustomPage<OrderDto>();

        customPage.setIsFirst( page.isFirst() );
        customPage.setIsLast( page.isLast() );
        if ( page.hasContent() ) {
            customPage.setContent( toDtos( page.getContent() ) );
        }
        customPage.setTotalPages( page.getTotalPages() );
        customPage.setTotalElements( page.getTotalElements() );

        customPage.setPageNumber( page.getNumber() + 1 );

        return customPage;
    }

    protected List<OrderItem> orderItemCreationDtoListToOrderItemList(List<OrderItemCreationDto> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderItem> list1 = new ArrayList<OrderItem>( list.size() );
        for ( OrderItemCreationDto orderItemCreationDto : list ) {
            list1.add( orderItemMapper.toEntity( orderItemCreationDto ) );
        }

        return list1;
    }
}
