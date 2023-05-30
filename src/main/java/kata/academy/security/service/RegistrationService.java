package kata.academy.security.service;

import kata.academy.security.model.Role;
import kata.academy.security.model.User;
import kata.academy.security.repository.RoleRepository;
import kata.academy.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class RegistrationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @Autowired
    public RegistrationService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public void register(User user) {
        user.setDepartment("NEW");
        user.setName("NEW");
        user.setSurname("NEW");
        user.setSalary(0);

        Role role = new Role(1L,"ROLE_USER");
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);

        user.setRoles(roleSet);
        role.setUsers(Collections.singleton(user)); // Установка связи между объектами Role и User

        userRepository.save(user);
    }
}
