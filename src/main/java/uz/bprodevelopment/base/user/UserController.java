package uz.bprodevelopment.base.user;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.bprodevelopment.base.user.dtos.UserCreateDto;
import uz.bprodevelopment.base.user.dtos.UserUpdateDto;

import static uz.bprodevelopment.base.constant.BaseApiUrls.USER_URL;


@RestController
@RequiredArgsConstructor
@RequestMapping(USER_URL)
public class UserController {

    private final UserService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(
            @PathVariable(name = "id") Long id
    ) {
        return ResponseEntity.ok().body(service.getOne(id));
    }


    @GetMapping("/all")
    public ResponseEntity<?> getListAll(UserFilter filter) {
        return ResponseEntity.ok().body(service.getListAll(filter));
    }

    @GetMapping
    public ResponseEntity<?> getList(UserFilter filter) {
        return ResponseEntity.ok().body(service.getList(filter));
    }


    @PostMapping
    public ResponseEntity<?> save(
            @RequestBody UserCreateDto item
    ) {
        service.save(item);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> update(
            @RequestBody UserUpdateDto item
    ) {
        service.update(item);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add-role/{userId}/{roleId}")
    public ResponseEntity<?> addRole(
            @PathVariable Long userId,
            @PathVariable Long roleId
    ) {
        service.addRole(userId, roleId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/remove-role/{userId}/{roleId}")
    public ResponseEntity<?> removeRole(
            @PathVariable Long userId,
            @PathVariable Long roleId
    ) {
        service.removeRole(userId, roleId);
        return ResponseEntity.ok().build();
    }

}

