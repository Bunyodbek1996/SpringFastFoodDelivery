package uz.bprodevelopment.base.auth;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.bprodevelopment.base.auth.confirmation_token.ConfirmationToken;
import uz.bprodevelopment.base.auth.confirmation_token.ConfirmationTokenService;
import uz.bprodevelopment.base.auth.request.ChangePasswordRequest;
import uz.bprodevelopment.base.auth.request.LoginRequest;
import uz.bprodevelopment.base.auth.request.RegisterRequest;
import uz.bprodevelopment.base.config.jwt_config.JwtService;
import uz.bprodevelopment.base.email.EmailSenderService;
import uz.bprodevelopment.base.role.Role;
import uz.bprodevelopment.base.role.RoleRepo;
import uz.bprodevelopment.base.user.User;
import uz.bprodevelopment.base.user.UserRepo;
import uz.bprodevelopment.base.util.AppUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Set;

import static uz.bprodevelopment.base.constant.BaseApiUrls.REGISTER_URL;
import static uz.bprodevelopment.base.constant.Constants.ROLE_USER;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSenderService emailSenderService;

    @Value("${spring.mail.confirm.address}")
    private String mailConfirmAddress;

    @Override
    @Transactional
    public void register(RegisterRequest registerRequest) {

        if (!registerRequest.getPassword().equals(registerRequest.getPasswordConfirmation())) {
            throw new RuntimeException("passwords are not the same");
        }

        Role roleUser = roleRepo.findByName(ROLE_USER);

        User user = User.builder()
                .fullName(registerRequest.getFullName())
                .username(registerRequest.getUsername())
                .phoneNumber(registerRequest.getPhoneNumber())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .roles(Set.of(roleUser))
                .build();

        userRepo.save(user);

        String token = confirmationTokenService.createConfirmationToken(user);

        String link = mailConfirmAddress + REGISTER_URL + "/confirm/" + token;

        emailSenderService.sendConfirmationLink(
                registerRequest.getUsername(),
                registerRequest.getFullName(),
                link
        );

    }

    @Override
    @Transactional
    public String confirmRegistration(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token);

        if (confirmationToken.getConfirmedAt() != null) {
            return buildHtmlResponse("Email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiredAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            return buildHtmlResponse("Confirmation token is expired");
        }

        confirmationToken.setConfirmedAt(LocalDateTime.now());
        confirmationToken.getUser().setEnabled(true);

        return buildHtmlResponse("Congratulations, You confirmed successfully");
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        User user = userRepo.findByUsername(loginRequest.getUsername())
                .orElseThrow();

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .user(user)
                .build();
    }

    @Override
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {

        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }

        final String token = authHeader.substring(7);
        final String username = jwtService.extractUsername(token);

        if (username != null) {
            User user = this.userRepo.findByUsername(username)
                    .orElseThrow();
            if (jwtService.isTokenValid(token, user)) {
                var accessToken = jwtService.generateAccessToken(user);
                var refreshToken = jwtService.generateRefreshToken(user);
                var authResponse = AuthResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .user(user)
                        .build();
                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

    @Override
    @Transactional
    public void changePassword(ChangePasswordRequest request) {
        User user = AppUtils.getCurrentUser();

        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new RuntimeException("Current password is incorrect");
        }

        if (!request.getNewPassword().equals(request.getNewPasswordConf())) {
            throw new RuntimeException("Confirmation password is incorrect");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
    }

    private String buildHtmlResponse(String message) {
        return "<div style=\"display: flex;align-items: center;justify-content: center;height: 100vh;\">"
                + "<h1 style=\"font-size:60px\">" + message + "</h1>"
                + "</div>";
    }
}
