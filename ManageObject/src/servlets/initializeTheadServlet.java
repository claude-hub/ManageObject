package servlets;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Clumn;

public class initializeTheadServlet extends HttpServlet {
	/**
	 * 此处接收前台传过来的数据和请求
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ajaxInitializeThead(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doGet(request, response);
	}
	
	@SuppressWarnings("unused")
	private void ajaxInitializeThead(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");	
		String object = request.getParameter("objectName");
		ArrayList<String> thead = getName(object);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(thead.toString());
	}
    //获取字段名
    public static ArrayList<String> getName(String objectName) {
    	ArrayList<String> name = new ArrayList<String>();
    	name.add(" {checkbox : true}" );
    	Class<?> clazz;
		try {
			clazz = Class.forName("entity." + objectName);
	    	Field fields[]= clazz.getDeclaredFields();
	    	for(Field field:fields){
	    		Clumn fieldLabel = field.getAnnotation(Clumn.class);
	    		name.add("{title: '"+fieldLabel.value()+"',field: '"+field.getName()+"'}");
	    	}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return name;
    }
}
