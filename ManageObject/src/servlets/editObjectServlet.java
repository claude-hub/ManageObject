package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.GetData;
import service.UpdateData;


@SuppressWarnings("serial")
public class editObjectServlet  extends HttpServlet {
       private GetData getData = new GetData();
       private UpdateData up= new UpdateData();
	/**
	 * 此处接收前台传过来的数据和请求
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String objName = request.getParameter("objectName");
        int objId = Integer.parseInt(request.getParameter("objectId"));

        Object obj = getData.searchById(objName,objId);
        if(obj == null){
            return;
        }
        request.setAttribute("objectName",objName);
        request.setAttribute("object",obj);
        request.getRequestDispatcher("/views/editObject.jsp").forward(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");

        String objectName = request.getParameter("objectName");
        String[] inputs = request.getParameterValues("inputs[]");
        String[] options = request.getParameterValues("options[]");

        boolean result = up.update(objectName,inputs,options);

        response.getWriter().print(result);
    }
}
