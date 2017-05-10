<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../../common/common.jsp"></jsp:include>
<title>资源</title>
</head>
<body>
	<table id="datagrid" class="easyui-datagrid"
		   data-options="fitColumns		: true,
						 rownumbers		: true,
						 fit			: true,
						 striped		: true,
						 pagination		: true,
						 loadMsg		: '查询中',
						 toolbar		: '#tb',
						 url			:'<%=request.getContextPath()%>/resources/queryList'">
		<thead>
	    <tr>
	        <th data-options="field:'resource',width:'20%',align:'left'">资源编码</th>
	        <th data-options="field:'name',width:'20%',align:'left'">资源名称</th>
	        <th data-options="field:'moduleName',width:'20%',align:'left'">模块名称</th>
	        <th data-options="field:'scopeCN',width:'10%',align:'center'">调用范围</th>
	        <th data-options="field:'statusCN',width:'10%',align:'center'">状态</th>
	        <th data-options="field:'act',width:'20%',align:'center'">操作</th>
	    </tr>
	    </thead>
	</table>
	<div id="tb">
		<div style="display: inline-block;">
			<span>查询条件:</span>
			<input id="condition" placeholder="sdadsa|dsda|asad">
		</div>
		<div style="display: inline-block;float: right;">
			<a id="addBtn" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a>
			<div class="split-line"></div>
			<a id="editBtn" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a>
			<div class="split-line"></div>
			<a id="deleteBtn" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true">删除</a>
		</div>
	</div>
</body>
</html>