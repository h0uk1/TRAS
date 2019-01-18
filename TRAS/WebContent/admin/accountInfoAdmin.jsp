<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tras admin</title>
</head>
<body>


<jsp:include page="/includeAdminJSP/headerAdmin.jsp">
<jsp:param name="log" value="log"/>
</jsp:include>
	<h1>${sessionScope.targetAccount.userName}のアカウント情報</h1>

	氏名：${sessionScope.targetAccount.name}<br>
	ユーザ名：${sessionScope.targetAccount.userName}<br>
	学年：${sessionScope.targetAccount.grade}<br>
	学部：${sessionScope.targetAccount.underGraduate}<br>
	学科：${sessionScope.targetAccount.department}<br>


	<form action="AccountDelete" method="post">
		<button type="submit" name="delete">アカウント削除</button>
	</form>
		<form action="BitSuccessListAdmin" method="get">
		<button type="submit" name="bid">落札分</button>
	</form>
		<form action="ExhibitListAdmin" method="get">
		<button type="submit" name="exhibit">出品状況</button>
	</form>
</body>
</html>