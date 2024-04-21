package ma.emsi.expensebackend.service.impl;

import ma.emsi.expensebackend.entity.User;
import ma.emsi.expensebackend.repository.UserRepository;
import ma.emsi.expensebackend.service.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private final UserRepository userRepository;

    public UserFacadeImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
