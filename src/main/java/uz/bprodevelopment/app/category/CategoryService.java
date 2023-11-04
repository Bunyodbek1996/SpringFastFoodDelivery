package uz.bprodevelopment.app.category;


import uz.bprodevelopment.app.category.dtos.CategoryUpdationDto;
import uz.bprodevelopment.app.category.dtos.CategoryCreationDto;
import uz.bprodevelopment.app.category.dtos.CategoryDto;
import uz.bprodevelopment.base.util.CustomPage;

import java.util.List;

public interface CategoryService {

    CategoryDto getOne(Long id);

    List<CategoryDto> getListAll(CategoryFilter filter);

    CustomPage<CategoryDto> getList(CategoryFilter filter);

    void save(CategoryCreationDto item);

    void update(CategoryUpdationDto item);

    void delete(Long id);


}
