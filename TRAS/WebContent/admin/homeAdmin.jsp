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
	<form action="HomeAdmin" method="post">
		<div class="col-sm">
			<dt>教科書名</dt>
			<dd>
				<input type="text" name="textName" minlength="0" maxlength="30">
			</dd>
		</div>

		<div class="col-sm">
			<dt>価格</dt>
			<dd>
				<select name="price">
					<%
						if (request.getAttribute("selectPrice") != null) {
							out.print("<option value=未設定 selected>Sorted:" + request.getAttribute("selectPrice") + "</option>");
						} else {
							out.print("<option value=未設定 selected>Sorted:―</option>");
						}
					%>
					<option value=未設定>―</option>
					<option value="1000未満">1000円未満</option>
					<option value="1000以上2000未満">1000円以上2000円未満</option>
					<option value="2000以上3000未満">2000円以上3000円未満</option>
					<option value="3000以上4000未満">3000円以上4000円未満</option>
					<option value="4000以上5000未満">4000円以上5000円未満</option>
					<option value="5000以上">5000円以上</option>
				</select>
			</dd>
		</div>


		<div class="col-sm">
			<dt>取引状態</dt>
			<dd>
				<select name="tradeState">
					<%
						if (request.getAttribute("selectTradeState") != null) {
							out.print("<option value="+ request.getAttribute("selectTradeState") +" selected>Sorted:" + request.getAttribute("selectTradeState") + "</option>");
						} else {
							out.print("<option value=\"オークション中\" selected>Sorted:―</option>");
						}
					%>
					<option value="オークション中">―</option>
					<option value="オークション中">オークション中</option>
					<option value="オークション終了">オークション終了</option>
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


	<!-- 出品情報の表示 -->
	<%
	int rowNumA =0;
	int rowNumB =-2;
		if (session.getAttribute("exhibitList") != null) {
			ArrayList<ExhibitInfo> exhibitList = (ArrayList<ExhibitInfo>) session.getAttribute("exhibitList");
			if (exhibitList == null) {
				System.out.println("ShowExhibitList : error");
				exhibitList = new ArrayList<ExhibitInfo>();

			} else {
				System.out.println("ShowExhibitList : success");
				for (ExhibitInfo ei : exhibitList) {
					boolean a = false;
					boolean b = false;
					if(rowNumA%3==0)a=true;
					if(rowNumB%3==0)b=true;
					if(a==true){out.print("<div class=\"container-fluid\"><div class=\"row justify-content-around no-gutters\">");}
						if(ei.getTradeState().equals("オークション中")){
							out.println("<div class=\"col-sm-3 border border-dark\"><a href=\"/kenta/AuctionAdmin?action="+ ei.getExhibitID() +"\" style=\"display:block;width:100%;height:100%; bgcolor:#ffffff;\">");
						}else if(ei.getTradeState().equals("オークション終了")){
							out.println("<div class=\"col-sm-3 border border-dark\"><a href=\"/kenta/AuctionEndAdmin?action="+ ei.getExhibitID() +"\" style=\"display:block;width:100%;height:100%; bgcolor:#ffffff;\">");
						}
							out.println("<table>");
								out.println("<tr>");
									out.println("<td><img src=" + "\"" + ei.getImage() + "\"" + " width=\"300\" height=\"300\" class=\"img-thumbnail\"></td>");
								out.println("</tr>");

								out.println("<tr>");
									out.println("<td>" + ei.getName() + "</td>");
								out.println("</tr>");

								out.println("<tr>");
									out.println("<td>" + ei.getPrice() + "円</td>");
								out.println("</tr>");
							out.println("</table>");
						out.println("</a></div>");
					if(b==true){out.println("</div></div><br><br>");}
					rowNumA++;
					rowNumB++;
				}
			}
		}
	%>

</body>
</html>