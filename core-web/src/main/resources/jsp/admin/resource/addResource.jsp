
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../../../common/common.jsp"></jsp:include>
<style type="text/css">
</style>
<title>新增功能资源</title>
</head>
	<body>
		<div style="margin: 5px 10px;">
			<form id="addResourceForm" name="addResourceForm">
				<input id="id" name="id" value="${vo.id }" type="hidden">
				<input id="parentId" name="parentId" value="${parentId }" type="hidden">
				<table style="width: 100%">
					<colgroup>
						<col width="25%">
						<col width="75%">
					</colgroup>
					<tr>
						<td align="right">资源文件夹:</td>
						<td>${parentName}</td>
						
					</tr>
					<tr>
						<td align="right">名称:</td>
						<td>
							<input id="name" name="name" class="easyui-textbox" 
											 data-options="required	: true,
											 			   validType: 'length[1,20]'">
						</td>
					</tr>
					<tr>
						<td align="right">资源路径:</td>
						<td>
							<input id="url" name="url" class="easyui-textbox" 
											 data-options="required	: true,width:'100%'">
						</td>
					</tr>
					<tr>
						<td align="right">是否菜单项:</td>
						<td colspan="3">
							<input name="isMenu" type="radio" value="0" checked="checked">否
							<input name="isMenu" type="radio" value="1">是
							
						</td>
					</tr>
					<tr>
						<td align="right">是否配置成权限:</td>
						<td colspan="3">
							<input name="auth" type="radio" value="0" checked="checked">否
							<input name="auth" type="radio" value="1">是
							
						</td>
					</tr>
				</table>
			</form>
		</div>
		<script type="text/javascript">
			$(function() {
				var id = '${vo.id}';
				var name = '${vo.name}';
				var url = '${vo.url}';
				var isMenu = '${vo.isMenu}';
				var auth = '${vo.auth}';
				if(id!=null && id!='') {
					$('#addResourceForm').find('#name').textbox('setValue',name);
					$('#addResourceForm').find('#url').textbox('setValue', url);
					$('#addResourceForm').find('#isMenu').val(isMenu);
					$('#addResourceForm').find('#auth').val(auth);
				}
			});
		</script>
	</body>
</html>