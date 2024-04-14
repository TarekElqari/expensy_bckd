package ma.emsi.expensebackend.service.impl;

import jakarta.transaction.Transactional;
import ma.emsi.expensebackend.entity.Role;
import ma.emsi.expensebackend.entity.User;
import ma.emsi.expensebackend.repository.RoleRepository;
import ma.emsi.expensebackend.repository.UserRepository;
import ma.emsi.expensebackend.service.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserFacadeImpl implements UserFacade {


    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final RoleRepository roleRepository;

    public UserFacadeImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User saveUser(User user) {
        Role role1 = new Role("ROLE_USER");
        Role role2 = new Role("ROLE_ADMIN");
        roleRepository.save(role1);
        roleRepository.save(role2);
        User u = new User();
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        u.setEmail(user.getEmail());
        List<Role> roles = new ArrayList<>();
        roles.add(role1);
        u.setRoles(roles);
        userRepository.save(u);
        return u;
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }
    public List<User> getAll() {
        return userRepository.findAll();
    }

}
