package cyc.services;

import cyc.dataModel.User;

import java.util.List;

public interface UserService {

    void addUser(User user);

    User getUserByUserId(String userId);

    User findByEmail(String email);

    List<User> getAllUsers();

    void createUser(User user);

    boolean testDatabaseConnection();
}
