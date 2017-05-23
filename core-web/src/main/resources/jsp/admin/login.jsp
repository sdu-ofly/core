<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>登录界面</title>
	<link href="<%=request.getContextPath()%>/css/core/admin/login/vjpublic.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/css/core/admin/jqueryui.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/css/core/admin/login/vjpage.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/css/core/admin/login/config.css" rel="stylesheet" type="text/css" />
	<jsp:include page="../../common/common.jsp"></jsp:include>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/core/admin/login/jquery-ui.min.js"></script>
</head>

<body class="body_style1">
<div id="page5_jPanel1" class="Panel Panel_Null">
	<div id="page5_jPanel2" class="Panel Panel_Null">
		<div id="page5_jHtmlForm1_form">
			<form id="page5_jHtmlForm1" name="page5_jHtmlForm1" method="post" action="">
				<div id="page5_jLabel1" class="text">用户名：</div>
				<input type="text" class="Edit Edit_style1" value="" id="page5_jEdit1"/>
				<div id="page5_jLabel2" class="text">密码：</div>
				<input type="password" class="Edit Edit_style4" value="" id="page5_jEdit2"/>
				<div id="page5_jButton1" class="vjbutton vjbutton_c_style1 border_radius_3"><div class="vjbutton_txtR">登录</div></div>
		</div>
		
		<div id="page5_jLabel3" class="text">OFLY 后台管理系统</div></div></div>

</body>
</html>