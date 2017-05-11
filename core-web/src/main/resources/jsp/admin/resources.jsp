
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../../common/common.jsp"></jsp:include>
<style type="text/css">
.panel-header {
    border-top: none;
    border-left: none;
    padding-right: 6px;
}
.layout-body {
    border: none;
}
</style>
<title>资源</title>
</head>
	<input id="ctx" value="<%=request.getContextPath()%>" type="hidden">
	<body class="easyui-layout" style="width:100%;height: 100%;border:none;">
		<div data-options="region:'west',split:true" style="width:220px;">
			<ul id="tree" class="ztree" style="height: inherit;padding: 0px;"></ul>
		</div>
		<div data-options="region:'center'">
			<table id="datagrid" class="easyui-datagrid"
				   data-options="fitColumns		: true,
								 rownumbers		: true,
								 fit			: true,
								 striped		: true,
								 pagination		: true,
								 border			: false,
								 pageSize		: 20,
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
				<div style="display: inline-block;margin-left: 10px;">
					<span>查询条件:</span>
					<input id="condition" placeholder="sdadsa|dsda|asad">
					<a id="queryBtn" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">查询</a>
				</div>
				<div style="display: inline-block;float: right;margin-right: 10px;">
					<a id="addBtn" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a>
					<div class="split-line"></div>
					<a id="editBtn" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a>
					<div class="split-line"></div>
					<a id="deleteBtn" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true">删除</a>
				</div>
			</div>
			<div id="mm" class="easyui-menu">
			</div>
		</div>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/core/admin/tree.js"></script>
		<script type="text/javascript">
			$(function() {
				tree.initResourcesTree("tree");
			});
		</script>
	</body>
</html>