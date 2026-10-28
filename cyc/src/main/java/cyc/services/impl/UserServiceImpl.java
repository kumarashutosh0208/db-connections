package cyc.services.impl;

import cyc.commonDao.CommonDao;
import cyc.dataModel.User;
import cyc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Locale;

@Service
public class UserServiceImpl implements UserService {

    private static final String DEFAULT_ADMIN_EMAIL = "admin@cyc.com";
    private static final String DEFAULT_ADMIN_PASSWORD = "admin123";
    private static final String DEFAULT_ADMIN_ROLE = "ADMIN";

    @Autowired
    private CommonDao commonDao;

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostConstruct
    public void initializeDefaultAdmin() {
        Query query = new Query(Criteria.where("email").is(DEFAULT_ADMIN_EMAIL));
        User existingAdmin = mongoTemplate.findOne(query, User.class);

        if (existingAdmin == null) {
            User admin = new User();
            admin.setName("System Admin");
            admin.setEmail(DEFAULT_ADMIN_EMAIL);
            admin.setPassword(org.springframework.security.crypto.bcrypt.BCrypt.hashpw(DEFAULT_ADMIN_PASSWORD, org.springframework.security.crypto.bcrypt.BCrypt.gensalt()));
            admin.setRole(DEFAULT_ADMIN_ROLE);
            admin.setStatus("ACTIVE");
            admin.setMobileNumber("8400818928");
            commonDao.save(admin);
        }
    }

    @Override
    public void addUser(User user) {
        commonDao.save(user);
    }

    @Override
    public User getUserByUserId(String userId) {
        return mongoTemplate.findById(userId, User.class);
    }

    @Override
    public User findByEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return null;
        }
        return mongoTemplate.findOne(new Query(Criteria.where("email").is(email.trim())), User.class);
    }

    @Override
    public List<User> getAllUsers() {
        return mongoTemplate.findAll(User.class);
    }

    @Override
    public void createUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }

        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            user.setPassword("default@123");
        }

        user.setPassword(org.springframework.security.crypto.bcrypt.BCrypt.hashpw(user.getPassword(), org.springframework.security.crypto.bcrypt.BCrypt.gensalt()));

        if (user.getRole() == null || user.getRole().trim().isEmpty()) {
            user.setRole("USER");
        } else {
            user.setRole(user.getRole().trim().toUpperCase(Locale.ROOT));
        }

        if (user.getStatus() == null || user.getStatus().trim().isEmpty()) {
            user.setStatus("ACTIVE");
        }

        commonDao.save(user);
    }

    @Override
    public boolean testDatabaseConnection() {
        try {
            mongoTemplate.getDb().runCommand(new org.bson.Document("ping", 1));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
