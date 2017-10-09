package service;

import java.sql.SQLException;

import dao.DatabaseLink;


public class UpdateData {
	private DatabaseLink _db;
	 // 增加
    public boolean create(String objectName, Object[] inputs, Object[] options) {
    	try {
			_db= new DatabaseLink();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        Class<?> clazz = null;
        try {
            clazz = Class.forName("entity." + objectName);
            // 拼接创建Object的SQL语言
            String sql = CreateObjectSQLString(objectName, options);

            // 写入数据库
            int count = _db.executeUpdateSQL(sql, inputs);

            return count > 0 ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    // 拼接添加SQL语句
    public String CreateObjectSQLString(String objName, Object[] options) {
        String sql = "insert into " + objName + "(";

        for (int i = 0; i < options.length; i++) {
            sql += options[i].toString() + ",";
        }

        sql = sql.substring(0, sql.length() - 1);
        sql += ") values(";

        for (int i = 0; i < options.length; i++) {
            sql += "?,";
        }

        sql = sql.substring(0, sql.length() - 1);
        sql = sql + ");";

        return sql;
    }
 // 修改
    public boolean update(String objectName, Object[] inputs, Object[] options) {
    	try {
			_db= new DatabaseLink();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        int id = 0;
        Class<?> clazz = null;
        try {
            clazz = Class.forName("entity." + objectName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        // 修改值列表

        String sqlCenter = "";

        // 拼接更新部分SQL语句
        for (int i = 0; i < options.length; i++) {
            sqlCenter += options[i].toString() + "=?,";
            if (options[i].toString().equals("no")) {
                id = Integer.parseInt(inputs[i].toString());
            }
        }

        sqlCenter = sqlCenter.substring(0, sqlCenter.length() - 1);

        String sql = "update " + objectName + " set " + sqlCenter
                + " where no=" + id;

        // 对数据库进行操作
        int count = _db.executeUpdateSQL(sql, inputs);
        try {
			_db.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return count > 0 ? true : false;
    }
    // 删除
    public boolean deleteObject(String objectName, int id) {
    	try {
			_db= new DatabaseLink();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
            String sql = "delete from " + objectName + " where no=?";
            int count = _db.executeUpdateSQL(sql, id);
            try {
    			_db.close();
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
            return count > 0 ? true : false;
        } catch (Exception e) {
            return false;
        }
    }
}
