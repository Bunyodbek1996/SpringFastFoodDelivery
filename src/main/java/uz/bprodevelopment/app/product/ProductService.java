package uz.bprodevelopment.app.product;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.multipart.MultipartFile;
import uz.bprodevelopment.app.product.dtos.ProductCreationDto;
import uz.bprodevelopment.app.product.dtos.ProductDto;
import uz.bprodevelopment.app.product.dtos.ProductUpdationDto;
import uz.bprodevelopment.base.util.CustomPage;

import java.util.List;

public interface ProductService {

    ProductDto getOne(Long id);

    List<ProductDto> getListAll(ProductFilter filter);

    CustomPage<ProductDto> getList(ProductFilter filter);

    void save(String productJson, MultipartFile image);

    void update(ProductUpdationDto dto);

    void updateImage(Long productId, MultipartFile image);

    void delete(Long id);

}
