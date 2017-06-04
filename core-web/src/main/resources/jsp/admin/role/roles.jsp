<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../../../common/common.jsp"></jsp:include>
<title>角色</title>
</head>
<body style="width: 100%;height: 100%; margin: 0px">
	<form id="roleForm" name="roleForm">
	<input id="ctx" value="<%=request.getContextPath()%>" type="hidden">
	<div class="easyui-layout" data-options="border:false" style="width:100%;height:500px;">
		<div data-options="region:'west',split:true,border:false" style="width:50%;">
			<div class="easyui-layout" data-options="fit:true">
				<div data-options="region:'north',split:true,border:false" style="height:50%">
					<table id="roleGrid" class="easyui-datagrid" title="角色管理"
						   data-options="pagination		: true,
										 border			: false,
										 pageSize		: 20,
										 fit			: true,
						   				 singleSelect	: true,
						   				 rownumbers		: true,
						   				 onClickRow		: roles.roleClick,
						   				 toolbar		: '#tb',
						   				 url			: '<%=request.getContextPath()%>/admin/roles/queryRoleList'">   
					    <thead>   
					        <tr>   
					            <th data-options="field:'name',width:'50%'">角色名称</th>   
					            <th data-options="field:'value',width:'50%'">角色编码</th>   
					        </tr>   
					    </thead>   
					</table>
					<div id="tb">
						<div style="display: inline-block;margin-left: 10px;">
							<span>查询条件:</span>
							<!-- <input id="condition" placeholder="角色名称|角色编码"> -->
							<input id="roleCondition" name="value" class="easyui-textbox" 
											 data-options="prompt:'角色名称|角色编码'">
							<a id="queryBtn" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true,onClick:roles.queryRoles">查询</a>
						</div>
						<div style="display: inline-block;float: right;margin-right: 10px;">
							<a id="addBtn" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true,onClick:roles.addRole">新增</a>
							<div class="split-line"></div>
							<a id="editBtn" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true,onClick:roles.editRole">修改</a>
							<div class="split-line"></div>
							<a id="deleteBtn" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true,onClick:roles.deleteRole">删除</a>
						</div>
					</div>
				</div>
				<div data-options="region:'center',border:false">
					<table id="relaResourceGrid" class="easyui-datagrid" title="关联资源"
						   data-options="fitColumns		: true,
										 rownumbers		: true,
										 fit			: true,
										 striped		: true,
										 pagination		: true,
										 border			: false,
										 pageSize		: 20,
										 toolbar		: '#tb1'">
						<thead>
					    <tr>
					    	<th data-options="field:'ck'	,width:'5%',checkbox:true"></th>
					        <th data-options="field:'name'	,width:'20%',align:'left'">资源名称</th>
					        <th data-options="field:'url'	,width:'55%',align:'left'">资源路径</th>
					        <th data-options="field:'isMenu',width:'10%',align:'center',formatter:roles.isFlagFormat">菜单项</th>
					        <th data-options="field:'auth',width:'10%',align:'center',formatter:roles.isFlagFormat">权限项</th>
					    </tr>
					    </thead>
					</table>
					<div id="tb1">
						<div style="display: inline-block;margin-left: 10px;">
							<span>查询条件:</span>
							<!-- <input id="condition" placeholder="资源名称|资源路径"> -->
							<input id="roleRelaCondition" name="value" class="easyui-textbox" 
											 data-options="prompt:'资源名称|资源路径'">
							<a id="queryBtn" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true,onClick:roles.queryRelaResouece">查询</a>
						</div>
						<div style="display: inline-block;float: right;margin-right: 10px;">
							<a id="deleteBtn" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true,onClick:roles.deleteRelaResource">删除</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div data-options="region:'center',border:false">
			<div class="easyui-layout" data-options="fit:true">
				<div data-options="region:'west',split:true,border:false" style="width:20%">
					<ul id="tree" class="ztree" style="height: inherit;padding: 0px;"></ul>
				</div>
				<div data-options="region:'center',border:false">
					<table id="unUseResourceGrid" class="easyui-datagrid" title="未使用资源"
						   data-options="fitColumns		: true,
										 rownumbers		: true,
										 fit			: true,
										 striped		: true,
										 pagination		: true,
										 border			: false,
										 pageSize		: 20,
										 toolbar		: '#tb2'">
						<thead>
					    <tr>
					    	<th data-options="field:'ck'	,width:'5%',checkbox:true"></th>
					        <th data-options="field:'name'	,width:'20%',align:'left'">资源名称</th>
					        <th data-options="field:'url'	,width:'45%',align:'left'">资源路径</th>
					        <th data-options="field:'isMenu',width:'15%',align:'center',formatter:roles.isFlagFormat">菜单项</th>
					        <th data-options="field:'auth',width:'15%',align:'center',formatter:roles.isFlagFormat">权限项</th>
					    </tr>
					    </thead>
					</table>
					<div id="tb2">
						<div style="display: inline-block;margin-left: 10px; height: 26px;">
							&nbsp;
						</div>
						<div style="display: inline-block;float: right;margin-right: 10px;">
							<a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true,onClick:roles.addRelaResource">添加</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</form>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/core/admin/tree.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/core/admin/roles.js"></script>
	<script type="text/javascript">
		$(function() {
			tree.initResourcesTree("tree",{
				resourcesDblClick	: roles.resourcesDblClick
			});
		});
	</script>
</body>
</html>