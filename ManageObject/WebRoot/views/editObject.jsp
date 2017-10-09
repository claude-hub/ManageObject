<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ page import="myTools.ObjectManager" %>

<div class="modal-header">
    <button type="button" class="close" Data-dismiss="modal" aria-hidden="true">&times;</button>
    <h4 class="modal-title" id="myModalLabel">修改对象</h4>
</div>

<div class="modal-body">
    <%
        Object obj = request.getAttribute("object");
        String objName = (String)request.getAttribute("objectName");
        Class<?> clazz = obj.getClass();

        Object[] annotations = ObjectManager.getAnnotations(objName).toArray();
        Object[] fields = ObjectManager.getTips(objName).toArray();
        Object[] values = ObjectManager.getFields(obj, clazz);

        for (int i = 0;i < fields.length;i++) {
    %>
    <div class="form-group">
    <div class="input-group">
        <span class="input-group-addon" id="basic-addon1"><%=annotations[i].toString()%></span>
        <input type="text" class="form-control input-item" id="<%=fields[i].toString()%>" value="<%=values[i].toString()%>" placeholder="<%=fields[i].toString()%>" <%=fields[i].toString() == "idNo" ? "disabled" : ""%> aria-describedby="basic-addon1">
    </div>
    </div>
    <%}%>
</div>
<div class="modal-footer">
    <button type="button" class="btn btn-primary" onclick="SubmitEdit()">确认修改</button>
    <button type="button" class="btn btn-default" Data-dismiss="modal">关闭</button>
</div>
