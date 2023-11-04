package uz.bprodevelopment.app.category;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import uz.bprodevelopment.app.category.dtos.CategoryCreationDto;
import uz.bprodevelopment.app.category.dtos.CategoryDto;
import uz.bprodevelopment.app.category.dtos.CategoryUpdationDto;
import uz.bprodevelopment.base.util.CustomPage;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-04T14:05:51+0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8.1 (Amazon.com Inc.)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryDto toDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setId( category.getId() );
        categoryDto.setName( category.getName() );

        return categoryDto;
    }

    @Override
    public Category toEntity(CategoryCreationDto dto) {
        if ( dto == null ) {
            return null;
        }

        Category.CategoryBuilder category = Category.builder();

        category.name( dto.getName() );

        return category.build();
    }

    @Override
    public Category toEntity(CategoryUpdationDto dto) {
        if ( dto == null ) {
            return null;
        }

        Category.CategoryBuilder category = Category.builder();

        category.id( dto.getId() );
        category.name( dto.getName() );

        return category.build();
    }

    @Override
    public List<CategoryDto> toDtos(List<Category> categories) {
        if ( categories == null ) {
            return null;
        }

        List<CategoryDto> list = new ArrayList<CategoryDto>( categories.size() );
        for ( Category category : categories ) {
            list.add( toDto( category ) );
        }

        return list;
    }

    @Override
    public CustomPage<CategoryDto> toCustomPage(Page<Category> page) {
        if ( page == null ) {
            return null;
        }

        CustomPage<CategoryDto> customPage = new CustomPage<CategoryDto>();

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
