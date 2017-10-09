package myTools;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DisplayObject {
	// 获取所有属性
	public static Object[] getFields(Object obj, Class<?> clazz) {
		Field[] fields = clazz.getDeclaredFields();
		Method[] methods = clazz.getMethods();
		Object[] values = new Object[fields.length];
		try {
			for (int i = 0; i < fields.length; i++) {
				String setMethod = "get" + fields[i].getName();
				for (Method method : methods) {
					if (method.getName().toLowerCase()
							.equals(setMethod.toLowerCase())) {
						values[i] = method.invoke(obj);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return values;
	}
	
    // 输出基本信息
    public static ArrayList<Object> translateInfo(ResultSet rs, Class<?> clazz) {
        ArrayList<Object> result = new ArrayList<Object>();
        try {
            while (rs.next()) {
                Object obj = clazz.newInstance();
                for (Field field : clazz.getDeclaredFields()) {
                    String fieldName = field.getName();
                    String fieldValue = rs.getString(fieldName);
                    setField(field, clazz, obj, fieldValue);
                }
                result.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
 // 设置属性
 	public static void setField(Field field, Class<?> clazz, Object obj,String input)
 			throws IllegalAccessException, IllegalArgumentException,
 			InvocationTargetException {
 		Method[] methods = clazz.getMethods();
 		String setMethod = "set" + field.getName();
 		for (Method method : methods) {
 			if (method.getName().equalsIgnoreCase(setMethod)) {
 				Object args = getCheckedInput(field.getType().getSimpleName(),input);

 				method.invoke(obj, args);
 			}
 		}
 	}
 // 获取经过校验的输入
 	public static Object getCheckedInput(String type,String input) {
 		Object obj = null;
 		try {
 			if(type.equalsIgnoreCase("int")){
 				obj = Integer.parseInt(input);
 			}else if (type.equalsIgnoreCase("String")) {
 				obj = input;
 			} else if (type.equalsIgnoreCase("Integer")) {
 				obj = Integer.valueOf(input);
 			} else if (type.equalsIgnoreCase("Boolean")) {
 				obj = input.equalsIgnoreCase("1") ? true : false;
 			} else if (type.equalsIgnoreCase("double")) {
 				obj = Double.valueOf(input);
 			}
 		} catch (Exception e) {
 			return null;
 		}
 		return obj;
 	}
}
