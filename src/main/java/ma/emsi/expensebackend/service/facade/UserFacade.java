package ma.emsi.expensebackend.service.facade;

import ma.emsi.expensebackend.entity.User;
public interface UserFacade {
        User saveUser(User user);
        void deleteUser(Long userId);
        User updateUser(User user);
}


