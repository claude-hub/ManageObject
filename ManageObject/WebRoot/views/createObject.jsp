<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="myTools.ObjectManager" %>

<div class="modal-header">
    <button type="button" class="close" Data-dismiss="modal" aria-hidden="true">&times;</button>
    <h4 class="modal-title" id="myModalLabel">创建对象</h4>
</div>
<!--<form id="createObject" method="post" action="/ManageObject/servlets/createObjectServlet"> -->
	<div class="modal-body">
    	<%
        	String objName = (String)request.getAttribute("objectName");
        	Object[] annotations = ObjectManager.getAnnotations(objName).toArray();
        	Object[] fields = ObjectManager.getTips(objName).toArray();
       		for (int i = 0;i < fields.length;i++) {
    	%>
    	<input name="objectName" value="<%=objName%>" type="hidden"/>
    	<div class="form-group">
    		<div class="input-group">
        		<span class="input-group-addon" id="basic-addon1"><%=annotations[i].toString()%></span>
        		<input type="text" id="<%=fields[i].toString()%>" class="form-control input-item"  placeholder="<%=fields[i].toString()%>" >
    		</div>
    	</div>
    	<%}%>
	</div>
<!-- </form> -->
<div class="modal-footer">
    <button type="button" class="btn btn-primary" onclick="SubmitCreate()">提交</button>
    <button type="button" class="btn btn-default" Data-dismiss="modal">关闭</button>
</div>
