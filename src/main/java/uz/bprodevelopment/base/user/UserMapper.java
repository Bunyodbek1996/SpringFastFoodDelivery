package uz.bprodevelopment.base.user;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import uz.bprodevelopment.base.user.dtos.UserCreateDto;
import uz.bprodevelopment.base.user.dtos.UserDto;
import uz.bprodevelopment.base.user.dtos.UserUpdateDto;
import uz.bprodevelopment.base.util.CustomPage;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    User toEntity(UserCreateDto userDto);

    User toEntity(UserUpdateDto userDto);

    List<UserDto> toDtos(List<User> users);

    @Mapping(target = "isFirst", source = "first")
    @Mapping(target = "isLast", source = "last")
    @Mapping(target = "pageNumber", expression = "java(page.getNumber() + 1)" )
    CustomPage<UserDto> toCustomPage(Page<User> page);

}
