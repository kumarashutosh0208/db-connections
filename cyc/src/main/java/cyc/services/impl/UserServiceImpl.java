package cyc.services.impl;

import cyc.commonDao.CommonDao;
import cyc.dataModel.User;
import cyc.repository.UserRespository;
import cyc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private CommonDao commonDao;

    @Override
    public void addUser(User user) {
        commonDao.save(user);
    }
}
