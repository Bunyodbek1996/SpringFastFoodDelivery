package uz.bprodevelopment.base.user.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.bprodevelopment.base.role.Role;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    Long id;
    private String fullName;
    private String username;
    private String phoneNumber;
    private Set<Role> roles = new HashSet<>();
    private Boolean enabled;

}
