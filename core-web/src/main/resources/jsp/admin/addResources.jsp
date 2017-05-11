
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../../common/common.jsp"></jsp:include>
<style type="text/css">
</style>
<title>新增资源节点</title>
</head>
	<body>
		<div style="margin: 5px 10px;">
			<form id="resourceNodeForm" name="resourceNodeForm">
				<input id="parentId" name="parentId" value="${parentId}" type="hidden">
				<input id="id" name="id" value="${node.id}" type="hidden">
				<table style="width: 100%">
					<colgroup>
						<col width="20%">
						<col width="30%">
						<col width="20%">
						<col width="30%">
					</colgroup>
					<tr>
						<td align="right">父节点名称:</td>
						<td>${parentName}</td>
						<td align="right">名称:</td>
						<td>
							<input id="name" name="name" class="easyui-textbox" 
											 data-options="required	: true,
											 			   validType: 'length[1,20]'">
						</td>
					</tr>
				</table>
			</form>
		</div>
		<script type="text/javascript">
			$(function() {
				var name = '${node.name}';
				var id = '${node.id}';
				if(id!=null && id!='') {
					$('#resourceNodeForm').find('#name').textbox('setValue',name);
				}
			});
		</script>
	</body>
</html>