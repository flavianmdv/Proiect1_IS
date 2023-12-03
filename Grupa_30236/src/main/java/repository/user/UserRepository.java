package repository.user;

import model.User;
import model.validator.Notification;

import java.util.*;

public interface UserRepository {

    List<User> findAll();

    Notification<User> findByUsernameAndPassword(String username, String password);

    boolean save(User user);

    void removeAll();

    void deleteById(Long id);

    void updateEmployee(Long id, String username, Long roleId);
    boolean existsByUsername(String username);
}
