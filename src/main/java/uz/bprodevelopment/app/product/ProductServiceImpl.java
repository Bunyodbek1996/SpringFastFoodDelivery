package uz.bprodevelopment.app.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import uz.bprodevelopment.app.category.Category;
import uz.bprodevelopment.app.product.dtos.ProductCreationDto;
import uz.bprodevelopment.app.product.dtos.ProductDto;
import uz.bprodevelopment.app.product.dtos.ProductUpdationDto;
import uz.bprodevelopment.base.file_storage.FileStorage;
import uz.bprodevelopment.base.file_storage.FileStorageService;
import uz.bprodevelopment.base.util.CustomPage;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final FileStorageService fileStorageService;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductDto getOne(Long id) {
        Product product = productRepository.getReferenceById(id);
        return productMapper.toDto(product);
    }

    @Override
    public List<ProductDto> getListAll(ProductFilter filter) {

        ProductSpecification spec = filter.retrieveSpecification();

        List<Product> categories = productRepository.findAll(spec.getSpecification(), filter.sorting());

        return productMapper.toDtos(categories);
    }

    @Override
    public CustomPage<ProductDto> getList(ProductFilter filter) {

        ProductSpecification spec = filter.retrieveSpecification();

        Page<Product> page = productRepository.findAll(spec.getSpecification(), filter.pageable());

        return productMapper.toCustomPage(page);
    }

    @Override
    @Transactional
    public void save(String productJson, MultipartFile image) {
        ObjectMapper objectMapper = new ObjectMapper();
        ProductCreationDto itemDto;
        try {
            itemDto = objectMapper.readValue(productJson, ProductCreationDto.class);
            if (itemDto.getName().isBlank()) {
                throw new RuntimeException("name is required and can not be blank");
            }
            if (itemDto.getPrice() == null) {
                throw new RuntimeException("price is required");
            }
            if (itemDto.getCategoryId() == null) {
                throw new RuntimeException("categoryId is required");
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        FileStorage fileStorage = fileStorageService.save(image);

        Product product = productMapper.toEntity(itemDto);
        product.setImageHashCode(fileStorage.getFileHashId());

        productRepository.save(product);
    }

    @Override
    @Transactional
    public void update(ProductUpdationDto dto) {
        Product product = productRepository.getReferenceById(dto.getId());
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setCategory(Category.builder().id(dto.getCategoryId()).build());
        product.setDescription(dto.getDescription());
    }

    @Override
    @Transactional
    public void updateImage(Long productId, MultipartFile image) {

        Product product = productRepository.getReferenceById(productId);

        FileStorage oldImage = fileStorageService.get(product.getImageHashCode());
        oldImage.setDeletable(true);

        FileStorage newImage = fileStorageService.save(image);

        product.setImageHashCode(newImage.getFileHashId());
    }


    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

}
