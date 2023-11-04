package uz.bprodevelopment.base.role;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static uz.bprodevelopment.base.constant.Constants.ROLE_ADMIN;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {

    private final RoleRepo roleRepo;


    @Override
    public Role saveRole(Role role) {
        return roleRepo.save(role);
    }


    @Override
    public Role getRoleByName(String name) {
        return roleRepo.findByName(name);
    }

    @Override
    public Role getRoleById(Long id) {
        Optional<Role> optionalRole = roleRepo.findById(id);
        return optionalRole.orElse(null);
    }

    @Override
    public List<Role> getRoles() {
        List<Role> roles = roleRepo.findAll();
        roles.removeIf(role -> role.getName().equals(ROLE_ADMIN));
        return roleRepo.findAll();
    }

}
