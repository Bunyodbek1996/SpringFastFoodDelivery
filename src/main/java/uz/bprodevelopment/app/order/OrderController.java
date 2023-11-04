package uz.bprodevelopment.app.order;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.bprodevelopment.app.order.dtos.OrderCreationDto;
import uz.bprodevelopment.app.order.dtos.OrderUpdationDto;

import static uz.bprodevelopment.app.util.ApiUrls.ORDER_URL;


@RestController
@RequiredArgsConstructor
@RequestMapping(ORDER_URL)
public class OrderController {

    private final OrderService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.getOne(id));
    }


    @GetMapping("/all")
    public ResponseEntity<?> getListAll(OrderFilter filter) {
        return ResponseEntity.ok().body(service.getListAll(filter));
    }

    @GetMapping
    public ResponseEntity<?> getList(OrderFilter filter) {
        return ResponseEntity.ok().body(service.getList(filter));
    }


    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody OrderCreationDto item) {
        service.save(item);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody OrderUpdationDto item) {
        service.update(item);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/confirm/{orderId}")
    public ResponseEntity<?> confirm(@PathVariable Long orderId) {
        service.confirm(orderId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/send/{orderId}")
    public ResponseEntity<?> send(@PathVariable Long orderId) {
        service.sent(orderId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/cancel/{orderId}")
    public ResponseEntity<?> cancel(@PathVariable Long orderId) {
        service.cancel(orderId);
        return ResponseEntity.ok().build();
    }
}

