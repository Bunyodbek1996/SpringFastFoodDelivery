package uz.bprodevelopment.base.user;


import uz.bprodevelopment.base.user.dtos.UserDto;
import uz.bprodevelopment.base.user.dtos.UserCreateDto;
import uz.bprodevelopment.base.user.dtos.UserUpdateDto;
import uz.bprodevelopment.base.util.CustomPage;

import java.util.List;

public interface UserService {

    UserDto getOne(Long id);

    List<UserDto> getListAll(UserFilter filter);

    CustomPage<UserDto> getList(UserFilter filter);

    void save(UserCreateDto item);

    void update(UserUpdateDto item);

    void addRole(Long userId, Long roleId);

    void removeRole(Long userId, Long roleId);

    void enableUser(Long userId);

    void disableUser(Long userId);

}
