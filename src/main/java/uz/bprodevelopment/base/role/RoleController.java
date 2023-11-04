package uz.bprodevelopment.base.role;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.bprodevelopment.base.user.UserFilter;
import uz.bprodevelopment.base.user.UserService;
import uz.bprodevelopment.base.user.dtos.UserCreateDto;
import uz.bprodevelopment.base.user.dtos.UserUpdateDto;

import static uz.bprodevelopment.base.constant.BaseApiUrls.ROLE_URL;
import static uz.bprodevelopment.base.constant.BaseApiUrls.USER_URL;


@RestController
@RequiredArgsConstructor
@RequestMapping(ROLE_URL)
public class RoleController {

    private final RoleService service;

    @GetMapping
    public ResponseEntity<?> getListAll() {
        return ResponseEntity.ok().body(service.getRoles());
    }

}

