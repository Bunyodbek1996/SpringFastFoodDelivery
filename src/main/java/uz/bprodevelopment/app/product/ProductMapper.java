package uz.bprodevelopment.app.product;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import uz.bprodevelopment.app.category.Category;
import uz.bprodevelopment.app.product.dtos.ProductCreationDto;
import uz.bprodevelopment.app.product.dtos.ProductDto;
import uz.bprodevelopment.app.product.dtos.ProductUpdationDto;
import uz.bprodevelopment.base.util.CustomPage;

import java.util.List;

@Mapper(componentModel = "spring", imports = { Category.class })
public interface ProductMapper {

    ProductDto toDto(Product product);

    @Mapping(target = "category", expression = "java(Category.builder().id(dto.getCategoryId()).build())")
    Product toEntity(ProductCreationDto dto);

    @Mapping(target = "category", expression = "java(Category.builder().id(dto.getCategoryId()).build())")
    Product toEntity(ProductUpdationDto dto);

    List<ProductDto> toDtos(List<Product> categories);

    @Mapping(target = "isFirst", source = "first")
    @Mapping(target = "isLast", source = "last")
    @Mapping(target = "pageNumber", expression = "java(page.getNumber() + 1)" )
    CustomPage<ProductDto> toCustomPage(Page<Product> page);

}
