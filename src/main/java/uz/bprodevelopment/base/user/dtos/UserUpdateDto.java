package uz.bprodevelopment.base.user.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDto {

    private Long id;
    private String fullName;
    private String username;
    private String phoneNumber;
    private String password;

}
