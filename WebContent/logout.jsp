<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//删除登录标识
	session.removeAttribute("empLoginName");

	
%>

<!-- 重定向 ,top是最外层的窗口-->
<script>
	//最外层框架跳到登录页
	window.top.location.href = 'login.html';
</script>	