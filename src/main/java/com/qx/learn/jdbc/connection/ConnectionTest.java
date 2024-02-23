package com.qx.learn.jdbc.connection;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Driver;
import org.junit.Test;



/**
 * 获取连接的不同方式
 *
 */
public class ConnectionTest {

	// 方式一：不推荐使用
	@Test
	public void testConnection1() throws SQLException {
		Driver driver = new Driver();
		String url = "jdbc:mysql://localhost:3306/test_db";
		// 将用户名和密码封装在Properties中
		Properties info = new Properties();
		info.setProperty("user", "root");
		info.setProperty("password", "Qx");
		Connection conn = driver.connect(url, info);
		System.out.println(conn);
	}

	// 方式二：使用反射，也不推荐使用
	@Test
	public void testConnection2() throws Exception {
		// 1.获取Driver实现类对象：使用反射
		Class clazz = Class.forName("com.mysql.jdbc.Driver");
		Driver driver = (Driver) clazz.newInstance();
		// 2.提供要连接的数据库
		String url = "jdbc:mysql://localhost:3306/test_db";
		// 3.提供连接需要的用户名和密码
		Properties info = new Properties();
		info.setProperty("user", "root");
		info.setProperty("password", "Qx");
		// 4.获取连接
		Connection conn = driver.connect(url, info);
		System.out.println(conn);

	}

	// 方式三：使用DriverManager
	@Test
	public void testConnection3() throws Exception {
		// 1.获取Driver实现类的对象
		Class clazz = Class.forName("com.mysql.jdbc.Driver");
		Driver driver = (Driver) clazz.newInstance();

		// 2.提供另外三个连接的基本信息：
		String url = "jdbc:mysql://localhost:3306/test_db";
		String user = "root";
		String password = "Qx";
		// 注册驱动
		DriverManager.registerDriver(driver);
		// 获取连接
		Connection conn = DriverManager.getConnection(url, user, password);
		System.out.println(conn);
	}

	//方式四：将所需要的4个基本信息放在配置文件中，使用使用DriverManager注册驱动
	@Test
	public void getConnection4() throws Exception{
		
		//1.读取配置文件中的4个基本信息
		InputStream is = ConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
		Properties pros = new Properties();
		pros.load(is);
		String user = pros.getProperty("user");
		String password = pros.getProperty("password");
		String url = pros.getProperty("url");
		String driverClass = pros.getProperty("driverClass");
		Class clazz = Class.forName(driverClass);
		Driver driver = (Driver) clazz.newInstance();
		// 注册驱动
		DriverManager.registerDriver(driver);
		//3.获取连接
		Connection conn = DriverManager.getConnection(url, user, password);
		System.out.println(conn);
	}

	//方式五：与方法四想比，注册驱动写法不同，        最为常用
	@Test
	public void getConnection5() throws Exception{

		//1.读取配置文件中的4个基本信息
		InputStream is = ConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
		Properties pros = new Properties();
		pros.load(is);
		String user = pros.getProperty("user");
		String password = pros.getProperty("password");
		String url = pros.getProperty("url");
		String driverClass = pros.getProperty("driverClass");
		// 注册驱动,不需要接收返回值，因为我们只想用它的类加载动作
		Class.forName(driverClass);
		//3.获取连接
		Connection conn = DriverManager.getConnection(url, user, password);
		System.out.println(conn);
	}

}
