<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js">
</script>
</head>
<body>
	<a href="a.jsp">aaa</a>
	<br>
	<a href="b.jsp">bbb</a>
	<br>
	<a href="c.jsp">ccc</a>
	<br>
	<button id="showdb">查询数据库</button>
	<br>
	<div id="show" style="float: left;"></div>
	<div id="showsingle"></div>
</body>
</html>