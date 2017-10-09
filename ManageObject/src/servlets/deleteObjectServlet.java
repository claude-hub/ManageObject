package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UpdateData;

import java.io.IOException;

public class deleteObjectServlet extends HttpServlet{
    private UpdateData up = new UpdateData();

    public deleteObjectServlet() {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        String objectName = request.getParameter("objectName");
        int id = Integer.parseInt(request.getParameter("id"));
        boolean result = up.deleteObject(objectName,id);

        response.getWriter().print(result);
    }
}
