package service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import myTools.DisplayObject;
import myTools.StringUtil;
import dao.DatabaseLink;
/**
 * 获取指定对象的数据
 */
public class GetData {
	private ArrayList<Object> arrayList = new ArrayList<Object>();
	private DatabaseLink databaseLinker;
	
//	private SpellOperateSql spellSql= new SpellOperateSql();
	private DatabaseLink _db;
	 // 显示表所有信息
    public List<Object> showAll(String objectName) {
    	try {
			_db= new DatabaseLink();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        ArrayList<Object> result = new ArrayList<Object>();
        try {
            String sql = "select * from " + objectName;
            ResultSet rs = null;
            rs = _db.executeSelectSQL(sql);
            if (rs != null) {
                result.addAll(DisplayObject.translateInfo(rs, Class.forName("entity." + objectName)));
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
			_db.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return result;
    }
    // 查找
    public ArrayList<Object> search(String objectName, String search) {
    	try {
			_db= new DatabaseLink();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        ArrayList<Object> result = new ArrayList<Object>();
        Class<?> clazz = null;
        ResultSet rs = null;
        try {
            clazz = Class.forName("entity." + objectName);
            String sql = "select * from " + objectName;

            rs = _db.executeSelectSQL(sql);

            result.addAll(searchAll(rs, clazz, search));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
     			_db.close();
     		} catch (SQLException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		}
        return result;
    }
    public ArrayList<Object> searchAll(ResultSet rs, Class<?> clazz,String search) {
        ArrayList<Object> result = new ArrayList<Object>();
        try {
            while (rs.next()) {
                Object obj = clazz.newInstance();
                int index = -1;
                for (Field field : clazz.getDeclaredFields()) {
                    String fieldName = field.getName();
                    String fieldValue = rs.getString(fieldName);
                    DisplayObject.setField(field, clazz, obj, fieldValue);

                    if (fieldValue.contains(search)) {
                        index++;
                    }
                }
                if (index >= 0) {
                    result.add(obj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return result;
    }
    // 根据ID查找
    public Object searchById(String objectName, int id) {
    	try {
			_db= new DatabaseLink();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        Object result = new Object();
        ResultSet rs = null;
        try {
            Class<?> clazz = Class.forName("entity." + objectName);
            String sql = "select * from " + objectName + " where no=?";

            rs = _db.executeSelectSQL(sql, id);

            if (rs != null) {
                result = DisplayObject.translateInfo(rs, clazz).get(0);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
     			_db.close();
     		} catch (SQLException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		}
        return result;
    }
}
