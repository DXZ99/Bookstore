package edu.neu.test;

import edu.neu.pojo.User;
import edu.neu.service.UserService;
import edu.neu.service.impl.UserServiceImpl;
import org.junit.Test;

/**
 * @author Zhao Dongxiao
 * @create 2020-12-14 17:12
 */
public class UserServiceImplTest {
    UserService userService = new UserServiceImpl();

    @Test
    public void registerUser() {
        userService.registerUser(new User(null, "zky", "666666", "zky@qq.com"));
        userService.registerUser(new User(null, "yinting", "130130", "yinting@qq.com"));
    }

    @Test
    public void login() {
        userService.login(new User(null, "zdx", "123456", null));
    }

    @Test
    public void existUsername() {
        if (userService.existUsername("zdx112")) {
            System.out.println("用户名已存在！");
        } else {
            System.out.println("用户名可用！");
        }
    }
}