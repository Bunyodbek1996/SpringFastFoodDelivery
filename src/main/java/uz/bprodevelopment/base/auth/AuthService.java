package uz.bprodevelopment.base.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.bprodevelopment.base.auth.request.ChangePasswordRequest;
import uz.bprodevelopment.base.auth.request.LoginRequest;
import uz.bprodevelopment.base.auth.request.RegisterRequest;

import java.io.IOException;

public interface AuthService {

    void register(RegisterRequest registerRequest);

    String confirmRegistration(String token);

    AuthResponse login(LoginRequest loginRequest);

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

    void changePassword(ChangePasswordRequest request);

}
