package edu.neu.test;

import edu.neu.dao.UserDao;
import edu.neu.dao.impl.UserDaoImpl;
import edu.neu.pojo.User;
import org.junit.Test;

/**
 * @author Zhao Dongxiao
 * @create 2020-12-14 16:37
 */
public class UserDaoTest {
    private UserDao userDao = new UserDaoImpl();

    @Test
    public void queryUserByUsername() {
        if (userDao.queryUserByUsername("admin123") == null) {
            System.out.println("用户名可用");
        } else {
            System.out.println("用户名已存在");
        }
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        if (userDao.queryUserByUsernameAndPassword("admin", "admin") == null) {
            System.out.println("用户名或密码错误，登录失败！");
        } else {
            System.out.println("登录成功！");
        }
    }

    @Test
    public void saveUser() {
        System.out.println(userDao.saveUser(new User(null, "zdx", "123456", "zdx@qq.com")));
    }
}