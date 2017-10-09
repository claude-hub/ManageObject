<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>增删改查</title>
	<meta charset="utf-8" />
    <link href="css/bootstrap.css" rel="stylesheet" />
    <link href="css/bootstrap-table.css" rel="stylesheet" />
	<script src="js/jquery-2.2.3.min.js"></script>
</head>
<body>
<div class="panel panel-success" style="text-align: center;">
	<div class="panel-heading">
		<h3 class="panel-title">任意对象的增删改查</h3>
	</div>
</div>
<div style="width: 80%;margin: 0 auto;">
	<div class="dropdown">
    	<button type="button" class="btn dropdown-toggle" id="selectObject_bnt" data-toggle="dropdown">请选择...<span class="caret"></span></button>
<!--     	<button type="button" class="btn dropdown-toggle" id="selectObject_bnt" data-toggle="dropdown">student</button> -->
    	<ul class="dropdown-menu" role="menu" aria-labelledby="selectObject">
			<li><a onclick="selectObject(this)">student</a></li>
        	<li><a onclick="selectObject(this)">teacher</a></li>
    	</ul>
	</div>
    <div class="panel-body" style="padding-bottom:0px;">
        <div id="toolbar" class="btn-group">
            <button id="btn_add" type="button" class="btn btn-default" onclick="CreateObject()">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
            </button>
            <button id="btn_edit" type="button" class="btn btn-default"  onclick="EditObject()">
                <span class="glyphicon glyphicon-pencil" aria-hidden="true" ></span>修改
            </button>
            <button id="btn_delete" type="button" class="btn btn-default" onclick="DeleteObject()">
                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
            </button>
            <div class="row">
        		<div class="col-lg-4">
            		<div class="input-group">
               	 		<input id="search" type="text" class="form-control" placeholder="搜索...">
                		<span class="input-group-btn"><button class="btn btn-default" type="button" onclick="Search()">搜索</button></span>
            		</div>
        		</div>
    		</div>
        </div>
        <table id="tb_departments" data-reorderable-columns="true">
        
        </table>
    </div>
</div>
	<!-- 弹框 -->
    <div class="modal fade" id="objectModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
            </div>
        </div>
    </div>
    <!-- 修改 -->
<!--     <div class="right-click" id="click-menu"> -->
<!--         <input id="selected-item" type="hidden"> -->
<!--         <p>编辑对象</p> -->
<!--         <ul> -->
<!--             <li onclick="DeleteObject()">删除对象</li> -->
<!--             <li onclick="EditObject()">修改对象信息</li> -->
<!--         </ul> -->
<!--     </div> -->
    <script src="js/bootstrap.js"></script>
    <script src="js/bootstrap-table.js"></script>
    <script src="js/bootstrap-table-zh-CN.js"></script>
    <script src="js/index.js"></script>
</body>
</html>