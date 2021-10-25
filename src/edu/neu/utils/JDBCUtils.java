package edu.neu.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Zhao Dongxiao
 * @create 2020-12-14 15:14
 */
public class JDBCUtils {

    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

    static {
        try {
            Properties properties = new Properties();
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            properties.load(is);
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取数据库连接池的连接
     *
     * @return 如果返回null则获取连接失败，有值就是获取连接成功
     */
    public static Connection getConnection() {
        Connection conn = threadLocal.get();
        if (conn == null) {
            try {
                conn = dataSource.getConnection(); //从数据库连接池中获取连接
                threadLocal.set(conn); //保存到ThreadLocal对象中，供后面的jdbc操作使用
                conn.setAutoCommit(false); //设置为手动提交事务
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    /**
     * 提交事务，并关闭连接，放回数据库连接池
     */
    public static void commitAndClose(){
        Connection connection = threadLocal.get();
        if (connection != null){
            //如果不等于空，说明之前使用过连接，操作过数据库
            try {
                connection.commit(); //提交事务
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close(); //关闭连接，释放资源
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        //一定要执行remove操作，否则出错(Tomcat底层使用了数据库连接池技术)
        threadLocal.remove();
    }

    /**
     * 回滚事务，并关闭连接，放回数据库连接池
     */
    public static void rollbackAndClose(){
        Connection connection = threadLocal.get();
        if (connection != null){
            //如果不等于空，说明之前使用过连接，操作过数据库
            try {
                connection.rollback(); //回滚事务
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close(); //关闭连接，释放资源
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        //一定要执行remove操作，否则出错(Tomcat底层使用了数据库连接池技术)
        threadLocal.remove();
    }




    /*
     * 关闭连接，放回数据库连接池
     *
     * @param conn

    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    */

}
