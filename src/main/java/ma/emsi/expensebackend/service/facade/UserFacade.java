package ma.emsi.expensebackend.service.facade;

import ma.emsi.expensebackend.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserFacade {
    User saveUser(User user);
    void deleteUser(Long userId);
    User updateUser(User user);
    List<User> getAll(); 
    Optional<User> getUserByUsername(String username);
}
