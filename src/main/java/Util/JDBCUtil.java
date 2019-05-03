package Util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtil {

	public static String DRIVERNAME = null;
	public static String URL = null;
	public static String USER = null;
	public static String PASSWORD = null;

	public static Connection conn = null;

	static {
		try {
			// 通过当前类获取类加载器
			ClassLoader classLoader = JDBCUtil.class.getClassLoader();
			// 通过类加载器获取一个输入流
			InputStream in = classLoader.getResourceAsStream("db.properties");
			// 创建properties对象
			Properties props = new Properties();
			// 加载输入流
			props.load(in);
			// 获取相关参数
			DRIVERNAME = props.getProperty("drivername");
			URL = props.getProperty("url");
			USER = props.getProperty("user");
			PASSWORD = props.getProperty("password");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获取数据库连接
	 * 
	 * @return Connection - a connection to url
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception {
		if (conn != null) {
			return conn;
		}

		// 加载数据库驱动
		Class.forName(DRIVERNAME);
		// 获取数据库连接
		conn = DriverManager.getConnection(URL, USER, PASSWORD);

		return conn;
	}

	/**
	 * 关闭资源
	 * 
	 * @param stmt
	 * @param conn
	 */
	public static void close(Statement stmt, Connection conn) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 关闭资源，包括数据集
	 * 
	 * @param rs
	 * @param stmt
	 * @param conn
	 */
	public static void close(ResultSet rs, Statement stmt, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
