package com.qx.learn.jdbc.connectionPool;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * 不使用数据库连接池，每次都通过 DriverManager 获取新连接，用完直接抛弃断开，连接的利用率太低，太浪费。对于数据库服务器来说，压力太大了。我们数据库服务器和 Java 程序对连接数也无法控制，很容易导致数据库服务器崩溃。
 * <p>
 * 使用连接池：
 * 我们可以建立一个连接池，这个池中可以容纳一定数量的连接对象，一开始我们可以先替用户先创建好一些连接对象，等用户要拿连接对象时，就直接从池中拿，不用新建了，这样也可以节省时间。然后用户用完后，放回去，别人可以接着用。
 * 可以提高连接的使用率。当池中的现有的连接都用完了，那么连接池可以向服务器申请新的连接放到池中。
 * 直到池中的连接达到“最大连接数”，就不能在申请新的连接了，如果没有拿到连接的用户只能等待
 * <p>
 * JDBC 的数据库连接池使用 javax.sql.DataSource 接口进行规范，所有的第三方连接池都实现此接口，自行添加具体实现。
 */
public class ConnectionPoolTest {
    @Test
    public void testSoftCoding() throws Exception
    {
        // 1. 读取外部配置的文件 Properties
        Properties properties = new Properties();

        // src 下的文件, 可以使用类加载器提供的方法
        InputStream inputStream = ConnectionPoolTest.class.getClassLoader().getResourceAsStream("druid.properties");
        properties.load(inputStream);

        // 2. 使用连接池的工具类的工场模式, 创建连接池
        DataSource druidDataSource = DruidDataSourceFactory.createDataSource(properties);
        Connection connection = druidDataSource.getConnection();
        connection.close();
    }
}
