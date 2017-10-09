package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UpdateData;

@SuppressWarnings("serial")
public class createObjectServlet  extends HttpServlet {
	/**
	 * 此处接收前台传过来的数据和请求
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		  request.setCharacterEncoding("UTF-8");
	        String objName = request.getParameter("objectName");
	        if(objName == "" || objName == null){
	            return;
	        }
	        request.setAttribute("objectName",objName);
	        request.getRequestDispatcher("/views/createObject.jsp").forward(request,response);
	}
	@SuppressWarnings("null")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UpdateData up=new UpdateData();
		request.setCharacterEncoding("UTF-8");
        String objectName = request.getParameter("objectName");
        String[] inputs = request.getParameterValues("inputs[]");
        String[] options = request.getParameterValues("options[]");

        boolean result = up.create(objectName,inputs,options);

        response.getWriter().print(result);
	}
}
