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
<title>Tras admin</title>
</head>
<body>
<jsp:include page="/includeAdminJSP/headerAdmin.jsp">
<jsp:param name="log" value="log"/>
</jsp:include>

<div class="container">
	<div class="row justify-content-center">
		<form action="QuestionListAdmin" method="get">
			<div class="col-sm">
				<dt>分類</dt>
				<dd>
					<select name="selectQuestion">
						<%
							if (request.getAttribute("selectQuestion") != null) {
								out.print("<option value=未設定 selected>Sorted:" + request.getAttribute("selectQuestion") + "</option>");
							} else {
								out.print("<option value=未設定 selected>Sorted:―</option>");
							}
						%>
						<option value=未設定>―</option>
						<option value="商品に関すること">商品に関すること</option>
						<option value="システムに関すること">システムに関すること</option>
						<option value="通報">通報</option>
					</select>
				</dd>
			</div>

			<div>
				<dd>
					<button type="submit" name="serch">検索</button>
				</dd>
			</div>
		</form>
	</div>
</div>

<%
	ArrayList<Question_m> questionList = (ArrayList<Question_m>)request.getAttribute("questionList");
	for(Question_m qi:questionList){
	    out.println("<div class=\"container\">");
	        out.println("<div class=\"row jumbotron\">");
	            out.println("<div>");
	                out.println("<p class=\"lead\">ユーザID："+ qi.getUserID() +"</p>");
	                out.println("<p class=\"lead\">問い合わせ分類："+ qi.getQuestionGroup() +"</p>");
	                out.println("<p class=\"lead\">問い合わせ内容："+ qi.getTextBox() +"</p>");
	            out.println("</div>");
	            out.println("<div class=\"pull-right align-self-end\">");
	                 out.print("<a class=\"btn btn-lg btn-primary\" href=\"/kenta/QuestionInfoAdmin?action="+ qi.getQuestionID() +"\" role=\"button\">問い合わせ詳細 &raquo;</a>");
	            out.println("</div>");
	        out.println("</div>");
		out.println("</div>");
	}
%>

</body>
</html>