package edu.neu.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

/**
 * 把Map中的值注入到对应的JavaBean属性中
 *
 * @author Zhao Dongxiao
 * @create 2020-12-21 18:03
 */
public class WebUtils {
    public static <T> T copyParamToBean(T bean, Map beanValue) {
        try {
            //把所有请求的参数都注入到user对象中
            BeanUtils.populate(bean, beanValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    public static int parseInt(String strInt, int defaultValue) {
        try {
            return Integer.parseInt(strInt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }
}
