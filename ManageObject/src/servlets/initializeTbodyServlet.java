package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.GetData;
import service.Param;
import myTools.JsonUtil;
import myTools.StringUtil;

@SuppressWarnings("serial")
public class initializeTbodyServlet extends HttpServlet {
    private GetData getData = new GetData();
	/**
	 * 此处接收前台传过来的数据和请求
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ajaxInitializeTable(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doGet(request, response);
	}
	
	@SuppressWarnings("unused")
	private void ajaxInitializeTable(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter pw = response.getWriter();
		String params = getRequestPayload(request);
		Param param = (Param) JsonUtil.JsonToList(params, Param.class);
		
		String objectName = param.getObjectName();
		String search = param.getSearch();
		
		// 分页查找，需判断是否有带查询条件
		ArrayList<Object> pagingList = new ArrayList<Object>();
		GetData getData = new GetData();
		StringUtil stringUtil = new StringUtil();

		ArrayList<Object> objects = new ArrayList<Object>();
        if(search != null && search != ""){
            objects.addAll(getData.search(objectName,search));
        } else if(objectName != null && objectName != "") {
            objects.addAll(getData.showAll(objectName));
        }
        if(objects.size() > param.getOffset() + param.getLimit()){
        	for (int i = param.getOffset(); i < param.getOffset() + param.getLimit(); i++) {
    			pagingList.add(objects.get(i));
    		}
        }else{
        	for (int i = param.getOffset(); i < objects.size(); i++) {
    			pagingList.add(objects.get(i));
    		}
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
