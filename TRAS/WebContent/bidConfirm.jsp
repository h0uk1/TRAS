<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.*"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<title>Tras 入札確認画面</title>
</head>

<body>
<div class="container">
<div class="my-3 p-3 bg-white rounded box-shadow">
	<h2 class="text-center">入札確認画面</h2>

<%
	ExhibitInfo exhibitInfo = (ExhibitInfo) session.getAttribute("exhibitInfo");
	out.println("<p>");
	out.println("<h4 class=\"text-center\">現在価格：");
	out.println(exhibitInfo.getPrice());
	out.println("円</h4>");
	out.println("</p>");
%>
	<h4 class="text-center">入札価格：${sessionScope.bidPrice}円</h4>

	<div class="mx-auto">

	<form action="BidComp" method="post">
		<button type="submit" name="bid" class="btn btn-primary btn-lg">入札する</button>
	</form>

<%
	out.print("<a class=\"btn btn-primary btn-lg\" href=\"/kenta/Auction?action=" + exhibitInfo.getExhibitID() + "\" role=\"button\" onClick=\"history.back()\">キャンセル</a>");
%>
	</div>
</div>
</div>

</body>
</html>