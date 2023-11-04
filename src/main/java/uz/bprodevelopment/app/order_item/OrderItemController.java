package uz.bprodevelopment.app.order_item;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.bprodevelopment.app.order_item.dtos.OrderItemCreationDto;
import uz.bprodevelopment.app.order_item.dtos.OrderItemUpdationDto;

import static uz.bprodevelopment.app.util.ApiUrls.ORDER_ITEM_URL;


@RestController
@RequiredArgsConstructor
@RequestMapping(ORDER_ITEM_URL)
public class OrderItemController {

    private final OrderItemService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.getOne(id));
    }


    @GetMapping("/all")
    public ResponseEntity<?> getListAll(OrderItemFilter filter) {
        return ResponseEntity.ok().body(service.getListAll(filter));
    }

    @GetMapping
    public ResponseEntity<?> getList(OrderItemFilter filter) {
        return ResponseEntity.ok().body(service.getList(filter));
    }


    @PostMapping
    public ResponseEntity<?> save(
            @Valid @RequestBody OrderItemCreationDto item
    ) {
        service.save(item);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody OrderItemUpdationDto item) {
        service.update(item);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}

