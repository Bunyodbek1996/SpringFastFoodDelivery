package uz.bprodevelopment.base.auth.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotNull(message = "fullName is required")
    private String fullName;

    @NotNull(message = "email is required")
    @Schema(example = "somename@gmail.com", description = "Email used as username in the application")
    private String username;

    @NotNull(message = "phoneNumber is required")
    @Schema(example = "+998 98 998 89 90")
    private String phoneNumber;

    @NotNull(message = "password is required")
    private String password;

    @NotNull(message = "passwordConfirmation is required")
    private String passwordConfirmation;

}
