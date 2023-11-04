package uz.bprodevelopment.app.order_item;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import uz.bprodevelopment.app.order_item.dtos.OrderItemCreationDto;
import uz.bprodevelopment.app.order_item.dtos.OrderItemDto;
import uz.bprodevelopment.app.order_item.dtos.OrderItemUpdationDto;
import uz.bprodevelopment.app.product.Product;
import uz.bprodevelopment.base.util.CustomPage;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-04T14:15:06+0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8.1 (Amazon.com Inc.)"
)
@Component
public class OrderItemMapperImpl extends OrderItemMapper {

    @Override
    public OrderItemDto toDto(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }

        OrderItemDto orderItemDto = new OrderItemDto();

        orderItemDto.setId( orderItem.getId() );
        orderItemDto.setPrice( orderItem.getPrice() );
        orderItemDto.setQuantity( orderItem.getQuantity() );

        orderItemDto.setTotalPrice( orderItem.getPrice() * orderItem.getQuantity() );

        return orderItemDto;
    }

    @Override
    public OrderItem toEntity(OrderItemCreationDto dto) {
        if ( dto == null ) {
            return null;
        }

        OrderItem.OrderItemBuilder orderItem = OrderItem.builder();

        orderItem.quantity( dto.getQuantity() );

        orderItem.product( Product.builder().id(dto.getProductId()).build() );
        orderItem.price( productRepository.getReferenceById(dto.getProductId()).getPrice() );

        return orderItem.build();
    }

    @Override
    public OrderItem toEntity(OrderItemUpdationDto dto) {
        if ( dto == null ) {
            return null;
        }

        OrderItem.OrderItemBuilder orderItem = OrderItem.builder();

        orderItem.id( dto.getId() );
        orderItem.quantity( dto.getQuantity() );

        return orderItem.build();
    }

    @Override
    public List<OrderItemDto> toDtos(List<OrderItem> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderItemDto> list1 = new ArrayList<OrderItemDto>( list.size() );
        for ( OrderItem orderItem : list ) {
            list1.add( toDto( orderItem ) );
        }

        return list1;
    }

    @Override
    public CustomPage<OrderItemDto> toCustomPage(Page<OrderItem> page) {
        if ( page == null ) {
            return null;
        }

        CustomPage<OrderItemDto> customPage = new CustomPage<OrderItemDto>();

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
}
