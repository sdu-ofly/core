<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../../common/common.jsp"></jsp:include>
<title>Admin</title>
<style type="text/css">
	.tabs-header,.tabs-panels {
	border: none;
}
</style>
</head>
<input id="ctx" value="<%=request.getContextPath()%>" type="hidden">
<body class="easyui-layout" style="width:100%;height: 100%;">
	<div data-options="region:'north'" style="height:120px;background: url(../img/admin/banner.jpg)">
	</div>
	<div data-options="region:'west',split:true" title="导航" style="width:220px;">
		<ul id="tree" class="ztree"></ul>
	</div>
	<div data-options="region:'center'">
		<div id="admin_tabs" class="easyui-tabs" data-options = "fit:true">
			
		</div>
	</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/core/admin/tree.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/core/admin/admin.js"></script>
<script type="text/javascript">
$(function() {
	tree.initAdminNavigation("tree",{
		onDblClick	: admin.treeDblClick
	});
});
</script>
</body>
</html>