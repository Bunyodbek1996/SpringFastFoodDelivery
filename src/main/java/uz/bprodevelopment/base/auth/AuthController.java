package uz.bprodevelopment.base.auth;


import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.bprodevelopment.base.auth.request.ChangePasswordRequest;
import uz.bprodevelopment.base.auth.request.LoginRequest;
import uz.bprodevelopment.base.auth.request.RegisterRequest;

import java.io.IOException;

import static uz.bprodevelopment.base.constant.BaseApiUrls.*;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping(REGISTER_URL)
    @SecurityRequirements()
    public ResponseEntity<?> register(
            @Valid @RequestBody RegisterRequest registerRequest
    ) {
        service.register(registerRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = REGISTER_URL + "/confirm/{token}", produces = MediaType.TEXT_HTML_VALUE)
    @SecurityRequirements()
    public String confirmRegistration(
            @PathVariable String token
    ) {
        return service.confirmRegistration(token);
    }

    @PostMapping(LOGIN_URL)
    @SecurityRequirements()
    public ResponseEntity<?> login(
            @RequestBody LoginRequest loginRequest
    ) {
        return ResponseEntity.ok(service.login(loginRequest));
    }


    @PostMapping(REFRESH_TOKEN_URL)
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }


    @PostMapping(CHANGE_PASSWORD_URL)
    public ResponseEntity<?> changePassword(
            @Valid @RequestBody ChangePasswordRequest request
    ){
        service.changePassword(request);
        return ResponseEntity.ok().build();
    }

}
