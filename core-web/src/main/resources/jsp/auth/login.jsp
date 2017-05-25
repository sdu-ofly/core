<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>登录界面</title>
	<link href="<%=request.getContextPath()%>/css/core/admin/login/vjpublic.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/css/core/admin/login/jqueryui.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/css/core/admin/login/vjpage.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/css/core/admin/login/config.css" rel="stylesheet" type="text/css" />
	<jsp:include page="../../common/common.jsp"></jsp:include>
	<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/core/admin/login/jquery-ui.min.js"></script> --%>
</head>

<body class="body_style1">
<div id="page5_jPanel1" class="Panel Panel_Null">
	<div id="page5_jPanel2" class="Panel Panel_Null">
		<div id="page5_jHtmlForm1_form">
			<input id="ctx" type="text" style="display: none;" value="<%=request.getContextPath()%>">
			<form id="loginForm" name="loginForm" method="post" action="">
				<table>
					<colgroup>
						<col width="25%">
						<col width="75%">
					</colgroup>
					<tr>
						<td align="right"”>帐号名：</td>
						<td>
							<input id="account" class="easyui-textbox" style="width:150px"
									 data-options="required	: true,
									 			   validType: 'length[1,20]'" >
						</td>
					</tr>
					<tr>
						<td align="right">密码：</td>
						<td>
							<input id="password" style="width:150px" class="easyui-passwordbox" 
									data-options="required	: true,
									 			  validType: 'length[1,20]'" >
						</td>
					</tr>
					<tr>
						<td>
						</td>
						<td>
							<span id="errorMsg" style="color: red;"></span>
						</td>
						
					</tr>
					<tr>
						<td colspan="2" align="center">
							<a href="javascript:void(0);" class="easyui-linkbutton" data-options="onClick:auth.login">登录</a>  
						</td>
					</tr>
					
				</table>
				
		</div>
		
		<div id="page5_jLabel3" class="text" >OFLY 后台管理系统</div></div></div>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/core/auth/auth.js"></script>
</body>
</html>