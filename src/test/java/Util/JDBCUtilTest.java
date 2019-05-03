package Util;

import java.sql.Connection;

import org.junit.Test;

import junit.framework.Assert;

public class JDBCUtilTest {
	@Test
	public void getConnectionTest() {
		Connection conn = null;
		try {
			conn = JDBCUtil.getConnection();
			Assert.assertNotNull(conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.close(null, conn);
		}
	}
}
