<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>
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
<title>Tras</title>
</head>
<body>



	<jsp:include page="/includeJSP/header.jsp">
		<jsp:param name="log" value="log" />
	</jsp:include>

	<h2>落札分</h2>
	<%
		ArrayList<ExhibitInfo> exhibitList = (ArrayList<ExhibitInfo>) session.getAttribute("exhibitList");
		Map<String, Integer> bidNumMap = (HashMap) request.getAttribute("bidNumMap");
		for (ExhibitInfo ei : exhibitList) {
			out.println("<div class=\"container\">");
			out.println("<div class=\"row jumbotron\">");
			out.println("<div>");
			out.println("<img src=\"" + ei.getImage() + "\" width=200 height=200>");
			out.println("</div>");
			out.println("<div>");
			out.println("<p class=\"lead\">教科書名：" + ei.getName() + "</p>");
			out.println("<p class=\"lead\">現在価格：" + ei.getPrice() + "</p>");
			out.println("<p class=\"lead\">終了日時：" + ei.getEndTime() + "</p>");
			out.println("<p class=\"lead\">入札数：" + bidNumMap.get(ei.getExhibitID()) + "</p>");
			out.println("</div>");
			out.println("<div class=\"pull-right align-self-end\">");
			out.print("<form action =\" TradeContact\" method=\"post\">");
			out.print("<a class=\"btn btn-lg btn-primary\" href=\"/kenta/AuctionEnd?action=" + ei.getExhibitID()
					+ "\" role=\"button\">取引詳細 &raquo;</a>");
			out.print("</form>");
			out.println("</div>");
			out.println("</div>");
			out.println("</div>");
		}
	%>


	<jsp:include page="/includeJSP/footer.jsp">
		<jsp:param name="log" value="log" />
	</jsp:include>


</body>
</html>