package uz.bprodevelopment.app.category;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.bprodevelopment.app.category.dtos.CategoryUpdationDto;
import uz.bprodevelopment.app.category.dtos.CategoryCreationDto;

import static uz.bprodevelopment.app.util.ApiUrls.CATEGORY_URL;


@RestController
@RequiredArgsConstructor
@RequestMapping(CATEGORY_URL)
public class CategoryController {

    private final CategoryService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.getOne(id));
    }


    @GetMapping("/all")
    public ResponseEntity<?> getListAll(CategoryFilter filter) {
        return ResponseEntity.ok().body(service.getListAll(filter));
    }

    @GetMapping
    public ResponseEntity<?> getList(CategoryFilter filter) {
        return ResponseEntity.ok().body(service.getList(filter));
    }


    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody CategoryCreationDto item) {
        service.save(item);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody CategoryUpdationDto item) {
        service.update(item);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }


}

