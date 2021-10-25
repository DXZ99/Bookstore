package edu.neu.service;

import edu.neu.pojo.User;

/**
 * @author Zhao Dongxiao
 * @create 2020-12-14 17:05
 */
public interface UserService {
    /**
     * 注册用户
     * @param user
     */
    public void registerUser(User user);

    /**
     * 登录
     * @param user
     * @return 返回null表示登录失败
     */
    public User login(User user);

    /**
     * 检查用户名是否可用
     * @param username
     * @return 返回true表示用户名已存在，false表示不存在即可用
     */
    public boolean existUsername(String username);
}
