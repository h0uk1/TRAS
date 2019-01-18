<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tras admin</title>
</head>
<body>

<form action="AccountDeleteComp" method=post>
	<p>アカウントを削除しますか？</p>
	<button class="btn-default" type="submit">削除</button>
</form>
<%Account account = (Account)session.getAttribute("targetAccount"); %>
<a class="btn-danger" href="/kenta/AccountInfoAdmin?action=<%=account.getUserID()%>">キャンセル</a>

</body>
</html>