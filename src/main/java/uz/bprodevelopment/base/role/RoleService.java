package uz.bprodevelopment.base.role;


import java.util.List;

public interface RoleService {

    Role getRoleByName(String name);
    Role getRoleById(Long id);
    List<Role> getRoles();

    Role saveRole(Role role);

}
