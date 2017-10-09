package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 数据库操作
 */
public class DatabaseLink {
    private String dbName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private String databaseLocation = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=ObjectManage";
    String userName = "***";
    String password = "*******";
	private Connection connection;
	
	/**
	 * linker数据库连接对象的初始化
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public DatabaseLink() throws SQLException, ClassNotFoundException{
		Class.forName(dbName);
		connection = DriverManager.getConnection
				(databaseLocation ,userName,password);
	}

	public void close() throws SQLException {
		connection.close();
	}
	//查询
	public ResultSet selectDatabase(String selectStatement) throws SQLException{
		Statement statement = (Statement) connection.createStatement();
		ResultSet resultSet = statement.executeQuery(selectStatement);
		return resultSet;		
	}
	// SQL更新
    public int executeUpdateSQL(String sql, Object... args) {
        PreparedStatement pstmt = null;
        int counts = 0;
        try {
            pstmt = connection.prepareStatement(sql);
            for (int i = 1; i <= args.length; i++) {
                pstmt.setObject(i, args[i - 1]);
            }
            counts = pstmt.executeUpdate();
            return counts;
        } catch (SQLException e) {
            System.out.println("no重复!");
        } 
        return 0;
    }
    // 获取SQL查询语句的查询结果
    public ResultSet executeSelectSQL(String sql, Object... args) {
        PreparedStatement pstmt = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(sql);
            for (int i = 1; i <= args.length; i++) {
                pstmt.setObject(i, args[i - 1]);
            }
            rs = pstmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //close(conn, pstmt, rs);
        }
        return rs;
    }
}
