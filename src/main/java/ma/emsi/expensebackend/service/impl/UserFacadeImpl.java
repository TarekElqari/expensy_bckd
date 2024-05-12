package ma.emsi.expensebackend.service.impl;

import ma.emsi.expensebackend.entity.User;
import ma.emsi.expensebackend.repository.UserRepository;
import ma.emsi.expensebackend.service.facade.UserFacade;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class UserFacadeImpl implements UserFacade {

    private final UserRepository userRepository;
    
    private static final Logger logger = LoggerFactory.getLogger(UserFacadeImpl.class);


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

    public Optional<User> getUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            logger.info("Found user with username: {} and id: {}", username, user.get().getId());
        } else {
            logger.info("No user found with username: {}", username);
        }
        return user;
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            logger.info("Found user with id: {}", userId);
        } else {
            logger.info("No user found with id: {}", userId);
        }
        return user;
    }
}
