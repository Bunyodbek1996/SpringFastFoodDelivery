package uz.bprodevelopment.app.order_item;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import uz.bprodevelopment.app.order_item.dtos.OrderItemCreationDto;
import uz.bprodevelopment.app.order_item.dtos.OrderItemDto;
import uz.bprodevelopment.app.order_item.dtos.OrderItemUpdationDto;
import uz.bprodevelopment.app.product.Product;
import uz.bprodevelopment.app.product.ProductRepository;
import uz.bprodevelopment.base.util.CustomPage;

import java.util.List;

@Mapper(
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        componentModel = "spring",
        imports = { Product.class}
)
public abstract class OrderItemMapper {

    @Autowired
    protected ProductRepository productRepository;

    @Mapping(target = "totalPrice", expression = "java(orderItem.getPrice() * orderItem.getQuantity())" )
    public abstract OrderItemDto toDto(OrderItem orderItem);

    @Mapping(target = "product", expression = "java(Product.builder().id(dto.getProductId()).build())" )
    @Mapping(target = "price", expression = "java(productRepository.getReferenceById(dto.getProductId()).getPrice())" )
    public abstract OrderItem toEntity(OrderItemCreationDto dto);

    public abstract OrderItem toEntity(OrderItemUpdationDto dto);

    public abstract List<OrderItemDto> toDtos(List<OrderItem> list);

    @Mapping(target = "isFirst", source = "first")
    @Mapping(target = "isLast", source = "last")
    @Mapping(target = "pageNumber", expression = "java(page.getNumber() + 1)" )
    public abstract CustomPage<OrderItemDto> toCustomPage(Page<OrderItem> page);

}
