package myTools;

public class InputObject {
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
