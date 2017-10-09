var TableHead;
$(function() {
	//初始化Table
	initializeTable();
});
function initializeTable() {
	var object = $("#selectObject_bnt").text();
	if(object=="请选择...")
		return false;
    $.ajax({
        type: 'get',
        url:'/ManageObject/servlets/initializeTheadServlet',
        async:false,//同步
        data: {"objectName": object},
        success: function (data) {
	         TableHead = eval(data);
	     	// 1.初始化Table
	     	var oTable = new TableInit();
	     	oTable.Init();
        },
        error: function (data) {
            alert('获取对象信息错误', 1);
        }
    });
}
var TableInit = function() {
	var oTableInit = new Object();
	// 初始化Table
	oTableInit.Init = function() {
		$("#tb_departments").bootstrapTable('destroy'); 
		$('#tb_departments').bootstrapTable({
			url : '/ManageObject/servlets/initializeTbodyServlet', // 请求后台的URL（*）
			method : 'post', // 请求方式（*）
			toolbar : '#toolbar', // 工具按钮用哪个容器
			striped : true, // 是否显示行间隔色
			cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			pagination : true, // 是否显示分页（*）
			sortable : false, // 是否启用排序
			sortOrder : "asc", // 排序方式
			queryParams : oTableInit.queryParams,// 传递参数（*）
			sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
			pageNumber : 1, // 初始化加载第一页，默认第一页
			pageSize : 10, // 每页的记录行数（*）
			pageList : [ 10, 25, 50, 100 ], // 可供选择的每页的行数（*）
			search : false, // 是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
			strictSearch : true,
			showColumns : true, // 是否显示所有的列
			showRefresh : true, // 是否显示刷新按钮
			minimumCountColumns : 2, // 最少允许的列数
			clickToSelect : true, // 是否启用点击选中行
			height : 500, // 行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
			uniqueId : "ID", // 每一行的唯一标识，一般为主键列
			showToggle : true, // 是否显示详细视图和列表视图的切换按钮
			cardView : false, // 是否显示详细视图
			detailView : false, // 是否显示父子表
			columns :TableHead,//表头
		});
	};
	// 得到查询的参数
	oTableInit.queryParams = function(params) {
		var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			limit : params.limit, // 页面大小
			offset : params.offset, // 页码
			objectName : $("#selectObject_bnt").text(),
			search:$("#search").val(),
		};
		return temp;
	};
	return oTableInit;
};
function selectObject(event) {
    var object = $(event).text();
    $("#selectObject_bnt").text(object);
	 initializeTable();
}
function CreateObject() {
	var object = $("#selectObject_bnt").text();
    if (object == "" || /\s+/g.test(object)) {
        return false;
    }
    $.ajax({
        type: 'get',
		url : '/ManageObject/servlets/createObjectServlet', 
        data: {"objectName": object},
        success: function (data) {
            $(".modal-content").html(data);
            $(function () {
                $('#objectModal').modal({
                    keyboard: true
                })
            });
        },
        error: function (data) {
            OpenTip("未知错误发生了", 2);
        }
    });
}
function SubmitCreate() {
    var inputs = new Array();
    var options = new Array();
    var object = $("#selectObject_bnt").text();

    $(".modal-body .input-item").each(function(){
        options.push(this.id);
        inputs.push($(this).val());
    });

    $.ajax({
       type:'post',
        url:'/ManageObject/servlets/createObjectServlet',
        data:{"objectName":object,"inputs":inputs,"options":options},
        success:function(data){
            if(data == "true"){
        		$("#tb_departments").bootstrapTable('refresh');
                $('#objectModal').modal('hide');
                alert("添加成功!!!");
                return true;
            }
        },
        error:function(data){
            OpenTip("未知错误发生了!",3);
        }
    });
}


function EditObject() {
    var object = $("#selectObject_bnt").text();  
    var row=$.map($("#tb_departments").bootstrapTable('getSelections'),function(row){
    	return row ;
    	});
    if(row.length>1||row.length<=0){
    	alert("请选择一行数据修改！");
    	return false;
    }
    var objectId = row[0].no;
    $.ajax({
        type: 'get',
        url: '/ManageObject/servlets/editObjectServlet',
        data: {"objectName": object,"objectId":objectId},
        success: function (data) {
            $(".modal-content").html(data);
            $(function () {
                $('#objectModal').modal({
                    keyboard: true
                })
            });
        },
        error: function (data) {
        	alert("错误发生!!!");
        }
    });
}
function SubmitEdit() {
    var inputs = new Array();
    var options = new Array();
    var object = $("#selectObject_bnt").text();

    $(".modal-body .input-item").each(function(){
        options.push(this.id);
        inputs.push($(this).val());
    });

    $.ajax({
        type:'post',
        url:'/ManageObject/servlets/editObjectServlet',
        data:{"objectName":object,"inputs":inputs,"options":options},
        success:function(data){
            if(data == "true"){
                $('#objectModal').modal('hide');
        		$("#tb_departments").bootstrapTable('refresh');
                alert("修改成功!!!");
                return true;
            }
        },
        error:function(data){
        	  alert("错误发生了!!!");
        }
    });
}
function DeleteObject() {
    var object = $("#selectObject_bnt").text();
    var row=$.map($("#tb_departments").bootstrapTable('getSelections'),function(row){
    	return row ;
    	});
    if(row.length>1||row.length<=0){
    	alert("请选择一行数据修改！");
    	return false;
    }
    var objectId = row[0].no;
    $.ajax({
        type: 'post',
        url: '/ManageObject/servlets/deleteObjectServlet',
        data: {"objectName": object, "id": objectId},
        success: function (data) {
            if (data == "true") {
        		$("#tb_departments").bootstrapTable('refresh');
                alert("删除成功!!!");
            } else {
            	alert("删除失败!!!");
            }
        },
        error: function (data) {
        		alert("错误发生!!!");
        }
    });
}

function Search() {
  	// 1.初始化Table
 	var oTable = new TableInit();
 	oTable.Init();
};