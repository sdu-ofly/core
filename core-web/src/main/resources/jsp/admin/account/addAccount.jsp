
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../../../common/common.jsp"></jsp:include>
<style type="text/css">
</style>
<title>新增帐号</title>
</head>
	<body>
		<div style="margin: 5px 10px;">
			<form id="addAccountForm" name="addAccountForm">
				<input id="id" name="id" value="${account.id}" type="hidden">
				<table style="width: 100%">
					<colgroup>
						<col width="20%">
						<col width="30%">
						<col width="20%">
						<col width="30%">
					</colgroup>
					<tr>
						<td align="right">帐号:</td>
						<td>
							<input id="account" name="account" class="easyui-textbox" 
											 data-options="required	: true,
											 			   validType: 'length[1,20]'">
						</td>
						<td align="right">用户名称:</td>
						<td>
							<input id="name" name="name" class="easyui-textbox" 
											 data-options="required	: true,
											 			   validType: 'length[1,20]'">
						</td>
					</tr>
					<tr>
						<td align="right">密码:</td>
						<td>
							<input id="password" name="password" class="easyui-passwordbox" 
											 data-options="required	: true,
											 			   validType: 'length[0,20]'">
						</td>
						<td align="right">密码确认:</td>
						<td>
							<input id="passwordCheck" name="passwordCheck" class="easyui-passwordbox" 
											 data-options="required	: true,
											 			   validType: 'length[0,20]'">
						</td>
					</tr>
				</table>
			</form>
		</div>
		<script type="text/javascript">
			$(function() {
				var name = '${account.name}';
				var id = '${account.id}';
				var accountName = '${account.account}';
				if(id!=null && id!='') {
					$('#addAccountForm').find('#name').textbox('setValue',name);
					$('#addAccountForm').find('#account').textbox('setValue',accountName);
 					$('#addAccountForm').find('#account').textbox({
						readonly	: true
					});
 					$('#addAccountForm').find('#password').passwordbox({
 						required	: false
					});
 					$('#addAccountForm').find('#passwordCheck').passwordbox({
 						required	: false
					});
				}
			});
		</script>
	</body>
</html>