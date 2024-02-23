package com.qx.learn.jdbc.statement;


import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

import org.junit.Test;

/**
 * 使用数据库操作对象Statement进行查询
 */
public class StatementTest {

    // 如何避免出现sql注入：只要用 PreparedStatement(从Statement扩展而来) 取代 Statement
    @Test
    public void testLogin() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("请输入用户名：");
        String user = scanner.nextLine();
        System.out.print("请输入密码：");
        String password = scanner.nextLine();
        String sql = "SELECT user,password FROM user_table WHERE user = '" + user + "' AND password = '" + password + "'";
        User returnUser = get(sql, User.class);
        if (returnUser != null) {
            System.out.println("登录成功");
        } else {
            System.out.println("用户名不存在或密码错误");
        }
    }

    // 使用Statement实现对数据表的查询操作
    public <T> T get(String sql, Class<T> clazz) {
        T t = null;

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            // 1.加载配置文件
            InputStream is = StatementTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
            Properties properties = new Properties();
            properties.load(is);
            // 2.读取配置信息
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            String url = properties.getProperty("url");
            String driverClass = properties.getProperty("driverClass");
            // 3.加载驱动
            Class.forName(driverClass);
            // 4.获取连接
            connection = DriverManager.getConnection(url, user, password);
            // 5.获取数据库操作对象Statement
            statement = connection.createStatement();              // createStatement()实例化Statement
            // 6.获得结果集
            resultSet = statement.executeQuery(sql);            // Statement的方法executeQuery(String sql)获得查询结果集

            // 获取结果集的元数据
            ResultSetMetaData data = resultSet.getMetaData();
            // 获取结果集的列数
            int columnCount = data.getColumnCount();
            if (resultSet.next()) {
                t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    // 1. 获取列名
                    String columnName = data.getColumnLabel(i + 1); //JDBC所有下标从1开始
                    // 2. 根据列名获取对应数据表中的数据
                    Object columnVal = resultSet.getObject(columnName);
                    // 3. 将数据表中得到的数据，封装进对象
                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(t, columnVal);
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
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

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

}
