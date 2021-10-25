package edu.neu.service.impl;

import edu.neu.dao.UserDao;
import edu.neu.dao.impl.UserDaoImpl;
import edu.neu.pojo.User;
import edu.neu.service.UserService;

/**
 * @author Zhao Dongxiao
 * @create 2020-12-14 17:08
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public void registerUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean existUsername(String username) {
        if (userDao.queryUserByUsername(username) == null) {
            //说明没查到，表示可用
            return false;
        }
        return true;
    }
}
