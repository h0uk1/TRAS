<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
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

	<div class="container">
	<div class="row justify-content-center">
	<form action="AccountListAdmin" method="get">
		<div class="col-sm">
			<dt>学年（任意）</dt>
			<dd><select name="selectGrade">
				<option value="" selected>未設定</option>
				<option value="1年">1年</option>
				<option value="2年">2年</option>
				<option value="3年">3年</option>
				<option value="4年">4年</option>
			</select></dd>
		</div>

		<div class="col-sm">
			<dt>学部（任意）</dt>
			<dd><select name="selectUnderGraduate">
				<option value="" selected>未設定</option>
				<option value="未来科学部">未来科学部</option>
				<option value="工学部">工学部</option>
				<option value="システムデザイン学部">システムデザイン学部</option>
				<option value="情報環境学部">情報環境学部</option>
				<option value="工学部第2部">工学部第2部</option>
			</select></dd>
		</div>

		<div>
			<dt>学科（任意）</dt>
			<dd>
			   <select  name="selectDepartment" id="mirai" class="subbox">
			      <option value="">学科を選択</option>
			      <option value="建築学科">建築学科</option>
			      <option value="情報メディア学科">情報メディア学科</option>
			      <option value="ロボット・メカトロニクス学科">ロボット・メカトロニクス学科</option>
			   </select>
			    <select name="selectDepartment" id="engineering" class="subbox">
			      <option value="">学科を選択</option>
			      <option value="電気電子工学科">電気電子工学科</option>
			      <option value="電子システム学科">電子システム学科</option>
			      <option value="応用化学科">応用化学科</option>
			      <option value="機械工学科">機械工学科</option>
			      <option value="先端機械工学科">先端機械工学科</option>
			      <option value="情報通信工学科">情報通信工学科</option>
			   </select>
			   <select name="selectDepartment" id="systemdesign" class="subbox">
			      <option value="">学科を選択</option>
			      <option value="情報システム工学科">情報システム工学科</option>
			      <option value="デザイン工学科">デザイン工学科</option>
			   </select>
			   <select name="selectDepartment" id="johokankyo" class="subbox">
			      <option value="">学科を選択</option>
			      <option value="ネットワーク・コンピュータ工学コース">ネットワーク・コンピュータ工学コース</option>
			      <option value="デジタル情報工学コース">デジタル情報工学コース</option>
			      <option value="建築デザインコース">建築デザインコース</option>
			      <option value="コミュニケーション工学コース">コミュニケーション工学コース</option>
			   </select>
			   <select name="selectDepartment" id="engineering2" class="subbox">
			      <option value="">学科を選択</option>
			      <option value="電気電子工学科">電気電子工学科</option>
			      <option value="機械工学科">機械工学科</option>
			      <option value="情報通信工学科">情報通信工学科</option>
			   </select>
			</dd>
		</div>

		<div>
			<dt>ユーザ名</dt>
			<dd>
				<input type="text" name="inputUserName">
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
	ArrayList<Account> accountList = (ArrayList<Account>)request.getAttribute("accountList");
	for(Account account:accountList){
	    out.println("<div class=\"container\">");
	        out.println("<div class=\"row jumbotron\">");
            	out.println("<div>");
            		out.println("<p class=\"lead\">ユーザID："+ account.getUserID() +"</p>");
				out.println("</div>");
	            out.println("<div>");
	                out.println("<p class=\"lead\">ユーザ名："+ account.getUserName() +"</p>");
	            out.println("</div>");
            	out.println("<div>");
        			out.println("<p class=\"lead\">氏名："+ account.getName() +"</p>");
				out.println("</div>");
	            out.println("<div class=\"pull-right align-self-end\">");
	                 out.print("<a class=\"btn btn-lg btn-primary\" href=\"/kenta/AccountInfoAdmin?action="+ account.getUserID() +"\" role=\"button\">詳細 &raquo;</a>");
	            out.println("</div>");
	        out.println("</div>");
		out.println("</div>");
	}
%>


</body>
</html>