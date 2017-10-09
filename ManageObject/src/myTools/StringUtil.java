package myTools;


public class StringUtil {
	public static String firstCharToUp(String str){
		char ch[]=str.toCharArray();
		char ch1=Character.toUpperCase(ch[0]);
		ch[0]=ch1;
		String s=new String(ch);
		return s;
	}
}
