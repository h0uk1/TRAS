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
<title>Tras admin</title>
</head>
<script type="text/javascript">
function submit(){
	document.exhibitList.submit();
}
</script>

<body>

<jsp:include page="/includeAdminJSP/headerAdmin.jsp">
<jsp:param name="log" value="log"/>
</jsp:include>

<h1>${sessionScope.targetAccount.userName}の出品リスト</h1>

<form action="ExhibitListAdmin" method="post" name="exhibitList">
	<input type="radio" name="exhibitList" value="exhibitList" onClick="submit()">出品中
	<input type="radio" name="exhibitList" value="exhibitEndList" checked="checked">出品終了
</form>


<%
	ArrayList<ExhibitInfo> userExhibitList = (ArrayList<ExhibitInfo>)session.getAttribute("userExhibitList");
	Map<String,Integer> bidNumMap = (HashMap)request.getAttribute("bidNumMap");
	for(ExhibitInfo ei:userExhibitList){
	    out.println("<div class=\"container\">");
	        out.println("<div class=\"row jumbotron\">");
	            out.println("<div>");
	                out.println("<img src=\"" + ei.getImage() + "\" width=200 height=200>");
	            out.println("</div>");
	            out.println("<div>");
	                out.println("<p class=\"lead\">教科書名："+ ei.getName() +"</p>");
	                out.println("<p class=\"lead\">現在価格："+ ei.getPrice() +"</p>");
	                out.println("<p class=\"lead\">終了日時："+ ei.getEndTime() +"</p>");
	                out.println("<p class=\"lead\">入札数："+ bidNumMap.get(ei.getExhibitID()) +"</p>");
	            out.println("</div>");
	            out.println("<div class=\"pull-right align-self-end\">");
	            out.print("<a class=\"btn btn-lg btn-primary\" href=\"/kenta/ExhibitDelete?action="+ ei.getExhibitID()+"\" role=\"button\">削除 &raquo;</a>");
                out.print("<a class=\"btn btn-lg btn-primary\" href=\"/kenta/ExhibitEdit?action="+ ei.getExhibitID() +"\" role=\"button\">編集 &raquo;</a>");
	                 out.print("<a class=\"btn btn-lg btn-primary\" href=\"/kenta/AuctionEnd?action="+ ei.getExhibitID() +"\" role=\"button\">取引詳細 &raquo;</a>");
	            out.println("</div>");
	        out.println("</div>");
		out.println("</div>");
	}
%>
</body>
</html>