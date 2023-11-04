package uz.bprodevelopment.base.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.bprodevelopment.base.role.Role;
import uz.bprodevelopment.base.role.RoleRepo;
import uz.bprodevelopment.base.user.dtos.UserCreateDto;
import uz.bprodevelopment.base.user.dtos.UserDto;
import uz.bprodevelopment.base.user.dtos.UserUpdateDto;
import uz.bprodevelopment.base.util.CustomPage;

import java.util.List;

import static uz.bprodevelopment.base.constant.Constants.ROLE_ADMIN;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepo repo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserDto getOne(Long id) {
        User user = repo.getReferenceById(id);
        return userMapper.toDto(user);
    }

    @Override
    public List<UserDto> getListAll(UserFilter filter) {

        UserSpec spec = new UserSpec();
        spec.addCriteria("fullName", ":", filter.getFullName());
        spec.addCriteria("username", ":", filter.getUsername());

        List<User> users = repo.findAll(spec.getSpecification(), filter.sorting());

        return userMapper.toDtos(users);
    }

    @Override
    public CustomPage<UserDto> getList(UserFilter filter) {

        UserSpec spec = new UserSpec();

        spec.addCriteria("fullName", ":", filter.getFullName());
        spec.addCriteria("username", ":", filter.getUsername());

        Page<User> page = repo.findAll(spec.getSpecification(), filter.pageable());

        return userMapper.toCustomPage(page);
    }


    @Override
    public void save(UserCreateDto item) {
        User user = userMapper.toEntity(item);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repo.save(user);
    }

    @Override
    @Transactional
    public void update(UserUpdateDto item) {
        User user = repo.getReferenceById(item.getId());
        user.setFullName(item.getFullName());
        user.setUsername(item.getUsername());
        if (item.getPassword() != null && !item.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(item.getPassword()));
        }
    }

    @Override
    @Transactional
    public void addRole(Long userId, Long roleId) {
        Role role = roleRepo.findByName(ROLE_ADMIN);
        if (roleId.intValue() == role.getId().intValue()){
            throw new RuntimeException("You can not add admin role to users");
        }
        User user = repo.getReferenceById(userId);
        user.getRoles().add(roleRepo.getReferenceById(roleId));
    }

    @Override
    @Transactional
    public void removeRole(Long userId, Long roleId) {
        User user = repo.getReferenceById(userId);
        user.getRoles().remove(roleRepo.getReferenceById(roleId));
    }

    @Override
    @Transactional
    public void enableUser(Long userId) {
        User user = repo.getReferenceById(userId);
        user.setEnabled(true);
    }

    @Override
    public void disableUser(Long userId) {
        User user = repo.getReferenceById(userId);
        user.setEnabled(false);
    }


}
