<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../../../common/common.jsp"></jsp:include>
<title>帐号</title> 
</head>
<body style="width: 100%;height: 100%; margin: 0px">
	<form id="accountForm" name="accountForm">
	<input id="ctx" value="<%=request.getContextPath() %>" type="hidden">
	<div class="easyui-layout" data-options="border:false" style="width:100%;height:500px;">
		<div data-options="region:'west',split:true,border:false" style="width:50%;">
			<div class="easyui-layout" data-options="fit:true">
				<div data-options="region:'north',split:true,border:false" style="height:50%">
					<table id="accountGrid" class="easyui-datagrid" title="帐号管理"
						   data-options="pagination		: true,
										 border			: false,
										 pageSize		: 20,
										 fit			: true,
						   				 singleSelect	: true,
						   				 rownumbers		: true,
						   				 onClickRow		: account.accountClick,
						   				 toolbar		: '#tb',
						   				 url			: '/server/admin/account/queryAccountList'">  
						   				 <!-- 
						   				
						   				  --> 
					    <thead>   
					        <tr>   
					            <th data-options="field:'account',width:'50%'">帐号</th>   
					            <th data-options="field:'name',width:'50%'">用户名称</th>   
					        </tr>   
					    </thead>   
					</table>
					<div id="tb">
						<div style="display: inline-block;margin-left: 10px;">
							<span>查询条件:</span>
							<input id="accountCondition" name="value" class="easyui-textbox" 
											 data-options="prompt:'帐号名称|用户名称'">
							<a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true,onClick:account.queryAccountList">查询</a>
						</div>
						<div style="display: inline-block;float: right;margin-right: 10px;">
							<a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true,onClick:account.addAccount">新增</a>
							<div class="split-line"></div>
							<a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true,onClick:account.editAccount">修改</a>
							<div class="split-line"></div>
							<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true,onClick:account.deleteAccount">删除</a>
						</div>
					</div>
				</div>
				<div data-options="region:'center',border:false">
					<table id="relaRoleGrid" class="easyui-datagrid" title="关联角色"
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
					        <th data-options="field:'name'	,width:'20%',align:'left'">角色名称</th>
					        <th data-options="field:'value'	,width:'60%',align:'left'">角色编码</th>
					    </tr>
					    </thead>
					</table>
					<div id="tb1">
						<div style="display: inline-block;margin-left: 10px;">
							<span>查询条件:</span>
							<input id="accountRelaCondition" name="value" class="easyui-textbox" 
											 data-options="prompt:'角色名称|角色编码'">
							<a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">查询</a>
						</div>
						<div style="display: inline-block;float: right;margin-right: 10px;">
							<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true">删除</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div data-options="region:'center',border:false">
			<table id="unUseRoleGrid" class="easyui-datagrid" title="未分配角色"
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
			        <th data-options="field:'name'	,width:'20%',align:'left'">角色名称</th>
			        <th data-options="field:'value'	,width:'50%',align:'left'">角色编码</th>
			    </tr>
			    </thead>
			</table>
			<div id="tb2">
				<div style="display: inline-block;margin-left: 10px; height: 26px;">
					&nbsp;
				</div>
				<div style="display: inline-block;float: right;margin-right: 10px;">
					<a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
				</div>
			</div>
		</div>
	</div>
	</form>
	<script type="text/javascript" src="/server/js/core/admin/tree.js"></script>
	<script type="text/javascript" src="/server/js/core/admin/account.js"></script>
	<script type="text/javascript">
		$(function() {
		});
	</script>
</body>
</html>