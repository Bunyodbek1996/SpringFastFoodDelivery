package uz.bprodevelopment.base.user;


import lombok.*;
import uz.bprodevelopment.base.spec.BaseFilter;


@Getter
@Setter
public class UserFilter extends BaseFilter {
    private String fullName;
    private String username;
}
