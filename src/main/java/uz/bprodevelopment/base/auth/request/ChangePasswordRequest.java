package uz.bprodevelopment.base.auth.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordRequest {

    @NotNull(message = "oldPassword is required")
    private String currentPassword;

    @NotNull(message = "newPassword is required")
    private String newPassword;

    @NotNull(message = "newPasswordConf is required")
    private String newPasswordConf;

}
