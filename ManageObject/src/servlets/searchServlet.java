package servlets;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.GetData;
import service.Param;
import myTools.JsonUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class searchServlet extends HttpServlet{
    private GetData getData = new GetData();
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

		PrintWriter pw = response.getWriter();
		String params = getRequestPayload(request);
		Param param = (Param) JsonUtil.JsonToList(params, Param.class);
		
		String objectName = param.getObjectName();
		String search = param.getSearch();
		ArrayList<Object> pagingList = new ArrayList<Object>();
		ArrayList<Object> objects = new ArrayList<Object>();
		
        if(search != null && search != ""){
            objects.addAll(getData.search(objectName,search));
        } else if(objectName != null && objectName != "") {
            objects.addAll(getData.showAll(objectName));
        }
        for (int i = param.getOffset(); i < param.getOffset() + param.getLimit(); i++) {
			pagingList.add(objects.get(i));
		}
		// 换成json字符串
		String arrayListJson = JsonUtil.parseJson(pagingList);
		// 得到总记录数
		int total = objects.size();

		// 需要返回的数据有总记录数和行数据
		String json = "{\"total\":" + total + ",\"rows\":" + arrayListJson
				+ "}";
		pw.print(json);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
    	doGet(request, response);
    }
    
	private String getRequestPayload(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		try (BufferedReader reader = request.getReader();) {
			char[] buff = new char[1024];
			int len;
			while ((len = reader.read(buff)) != -1) {
				sb.append(buff, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}
