package uz.bprodevelopment.app.product;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.bprodevelopment.app.product.dtos.ProductUpdationDto;

import static uz.bprodevelopment.app.util.ApiUrls.PRODUCT_URL;


@RestController
@RequiredArgsConstructor
@RequestMapping(PRODUCT_URL)
public class ProductController {

    private final ProductService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.getOne(id));
    }


    @GetMapping("/all")
    public ResponseEntity<?> getListAll(ProductFilter filter) {
        return ResponseEntity.ok().body(service.getListAll(filter));
    }

    @GetMapping
    public ResponseEntity<?> getList(ProductFilter filter) {
        return ResponseEntity.ok().body(service.getList(filter));
    }


    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> save(
            @RequestPart("image") MultipartFile image,
            @RequestParam("productJson") String productJson
    ) {
        service.save(productJson, image);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody ProductUpdationDto item) {
        service.update(item);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/change-image/{productId}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> updateImage(
            @RequestPart("image") MultipartFile image,
            @PathVariable Long productId
    ) {
        service.updateImage(productId, image);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}

