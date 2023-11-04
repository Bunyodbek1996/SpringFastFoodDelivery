package uz.bprodevelopment.base.user;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import uz.bprodevelopment.base.role.Role;
import uz.bprodevelopment.base.user.dtos.UserCreateDto;
import uz.bprodevelopment.base.user.dtos.UserDto;
import uz.bprodevelopment.base.user.dtos.UserUpdateDto;
import uz.bprodevelopment.base.util.CustomPage;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-04T14:05:51+0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8.1 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( user.getId() );
        userDto.setFullName( user.getFullName() );
        userDto.setUsername( user.getUsername() );
        userDto.setPhoneNumber( user.getPhoneNumber() );
        Set<Role> set = user.getRoles();
        if ( set != null ) {
            userDto.setRoles( new LinkedHashSet<Role>( set ) );
        }
        userDto.setEnabled( user.getEnabled() );

        return userDto;
    }

    @Override
    public User toEntity(UserCreateDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.fullName( userDto.getFullName() );
        user.username( userDto.getUsername() );
        user.phoneNumber( userDto.getPhoneNumber() );
        user.password( userDto.getPassword() );

        return user.build();
    }

    @Override
    public User toEntity(UserUpdateDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( userDto.getId() );
        user.fullName( userDto.getFullName() );
        user.username( userDto.getUsername() );
        user.phoneNumber( userDto.getPhoneNumber() );
        user.password( userDto.getPassword() );

        return user.build();
    }

    @Override
    public List<UserDto> toDtos(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( users.size() );
        for ( User user : users ) {
            list.add( toDto( user ) );
        }

        return list;
    }

    @Override
    public CustomPage<UserDto> toCustomPage(Page<User> page) {
        if ( page == null ) {
            return null;
        }

        CustomPage<UserDto> customPage = new CustomPage<UserDto>();

        customPage.setIsFirst( page.isFirst() );
        customPage.setIsLast( page.isLast() );
        if ( page.hasContent() ) {
            customPage.setContent( toDtos( page.getContent() ) );
        }
        customPage.setTotalPages( page.getTotalPages() );
        customPage.setTotalElements( page.getTotalElements() );

        customPage.setPageNumber( page.getNumber() + 1 );

        return customPage;
    }
}
