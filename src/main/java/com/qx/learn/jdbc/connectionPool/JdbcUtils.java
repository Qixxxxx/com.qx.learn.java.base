package com.qx.learn.jdbc.connectionPool;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Properties;

public class JdbcUtils {

    private JdbcUtils() {}

    private static final DataSource dataSource;
    // 使用ThreadLocal解决并发问题，让同一个线程获取同一个connection
    private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    /**
     * 对外提供连接的方法
     */
    public static Connection getConnection() throws SQLException {
        Connection connection = threadLocal.get();
        // 第一次没有
        if (connection == null) {
            connection = dataSource.getConnection();
            threadLocal.set(connection);
        }

        return connection;

    }

    /**
     * 对外提供释放资源方法
     */
    public static void freeConnection(Statement statement, ResultSet resultSet) throws SQLException {
        Connection connection = threadLocal.get();

        if (connection != null) {
            try {
                threadLocal.remove();    // 清空线程本地变量数据
                connection.setAutoCommit(true);    // 事务状态回归 false
                connection.close();    // 回收到连接池
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    static {
        // 初始化连接池对象
        Properties properties = new Properties();
        InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
