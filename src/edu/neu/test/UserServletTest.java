package edu.neu.test;

import java.lang.reflect.Method;

/**
 * @author Zhao Dongxiao
 * @create 2020-12-21 17:11
 */
public class UserServletTest {
    public void login() {
        System.out.println("this is login");
    }

    public void regist() {
        System.out.println("this is regist");
    }

    public void updateUser() {
        System.out.println("this is updateUser");
    }

    public void updatePassword() {
        System.out.println("this is updatePassword");
    }

    public static void main(String[] args) {
        String action = "regist";
        try {
            //通过action鉴别字符串，获取相应方法的反射对象
            Method method = UserServletTest.class.getDeclaredMethod(action);
            //调用目标方法
            method.invoke(new UserServletTest());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
