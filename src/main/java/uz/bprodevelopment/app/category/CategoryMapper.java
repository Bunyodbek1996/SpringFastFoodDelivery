package uz.bprodevelopment.app.category;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import uz.bprodevelopment.app.category.dtos.CategoryCreationDto;
import uz.bprodevelopment.app.category.dtos.CategoryDto;
import uz.bprodevelopment.app.category.dtos.CategoryUpdationDto;
import uz.bprodevelopment.base.util.CustomPage;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto toDto(Category category);

    Category toEntity(CategoryCreationDto dto);

    Category toEntity(CategoryUpdationDto dto);

    List<CategoryDto> toDtos(List<Category> categories);

    @Mapping(target = "isFirst", source = "first")
    @Mapping(target = "isLast", source = "last")
    @Mapping(target = "pageNumber", expression = "java(page.getNumber() + 1)" )
    CustomPage<CategoryDto> toCustomPage(Page<Category> page);

}
