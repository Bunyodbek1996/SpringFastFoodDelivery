package uz.bprodevelopment.app.product;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import uz.bprodevelopment.app.category.Category;
import uz.bprodevelopment.app.product.dtos.ProductCreationDto;
import uz.bprodevelopment.app.product.dtos.ProductDto;
import uz.bprodevelopment.app.product.dtos.ProductUpdationDto;
import uz.bprodevelopment.base.util.CustomPage;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-04T14:05:51+0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8.1 (Amazon.com Inc.)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDto toDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        productDto.setId( product.getId() );
        productDto.setName( product.getName() );
        productDto.setImageHashCode( product.getImageHashCode() );
        productDto.setPrice( product.getPrice() );
        productDto.setDescription( product.getDescription() );

        return productDto;
    }

    @Override
    public Product toEntity(ProductCreationDto dto) {
        if ( dto == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.name( dto.getName() );
        product.price( dto.getPrice() );
        product.description( dto.getDescription() );

        product.category( Category.builder().id(dto.getCategoryId()).build() );

        return product.build();
    }

    @Override
    public Product toEntity(ProductUpdationDto dto) {
        if ( dto == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.id( dto.getId() );
        product.name( dto.getName() );
        product.price( dto.getPrice() );
        product.description( dto.getDescription() );

        product.category( Category.builder().id(dto.getCategoryId()).build() );

        return product.build();
    }

    @Override
    public List<ProductDto> toDtos(List<Product> categories) {
        if ( categories == null ) {
            return null;
        }

        List<ProductDto> list = new ArrayList<ProductDto>( categories.size() );
        for ( Product product : categories ) {
            list.add( toDto( product ) );
        }

        return list;
    }

    @Override
    public CustomPage<ProductDto> toCustomPage(Page<Product> page) {
        if ( page == null ) {
            return null;
        }

        CustomPage<ProductDto> customPage = new CustomPage<ProductDto>();

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
