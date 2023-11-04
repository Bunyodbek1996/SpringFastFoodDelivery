package uz.bprodevelopment.app.category;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.bprodevelopment.app.category.dtos.CategoryDto;
import uz.bprodevelopment.app.category.dtos.CategoryUpdationDto;
import uz.bprodevelopment.app.category.dtos.CategoryCreationDto;
import uz.bprodevelopment.base.util.CustomPage;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDto getOne(Long id) {
        Category category = categoryRepository.getReferenceById(id);
        return categoryMapper.toDto(category);
    }

    @Override
    public List<CategoryDto> getListAll(CategoryFilter filter) {

        CategorySpecification spec = filter.retrieveSpecification();

        List<Category> categories = categoryRepository.findAll(spec.getSpecification(), filter.sorting());

        return categoryMapper.toDtos(categories);
    }

    @Override
    public CustomPage<CategoryDto> getList(CategoryFilter filter) {

        CategorySpecification spec = filter.retrieveSpecification();

        Page<Category> page = categoryRepository.findAll(spec.getSpecification(), filter.pageable());

        return categoryMapper.toCustomPage(page);
    }


    @Override
    public void save(CategoryCreationDto item) {
        Category category = categoryMapper.toEntity(item);
        categoryRepository.save(category);
    }

    @Override
    public void update(CategoryUpdationDto item) {
        categoryRepository.save(categoryMapper.toEntity(item));
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }


}
