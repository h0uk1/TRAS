<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.*"%>
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
<jsp:param name="log" value="log"/>
</jsp:include>
<div>
    <div class="container">
        <div class="row justify-content-center">
                <div class="col-sm-3.5">
                	<%
                		ExhibitInfo exhibitInfo = (ExhibitInfo)session.getAttribute("exhibitInfo");
                		out.print("<img src=\""+ exhibitInfo.getImage()  +"\" width=600 class=\"img-thumbnail\">");
                	%>
                </div>
        </div>
	</div>

    <br>

    <div class="container">
		<div class="row justify-content-center">
			<div class="col-sm-3.5">
				<p>教科書名：${sessionScope.exhibitInfo.name}</p>
			</div>
			<div class="w-100"></div>
			<div class="col-sm-3.5">
			    <p>現在価格：${sessionScope.exhibitInfo.price}円</p>
			    <p>最高価格入札者：${requestScope.bidSuccessUser.userName}</p>		<!-- 最高価格入札者名を表示(bidSuccessUserID -->
			</div>
		</div>
    </div>

    <div class="container">
      <!-- Example row of columns -->
      <div class="row">
        <div class="col-md-6">
          <h2>商品詳細</h2>
          <p>教科書名：${sessionScope.exhibitInfo.name}</p>
          <p>現在価格：${sessionScope.exhibitInfo.price}円</p>
          <p>オークション終了日時：${sessionScope.exhibitInfo.endTime}</p>
          <p>品質状態：${sessionScope.exhibitInfo.textState}</p>
          <p>受け渡し日時：${sessionScope.exhibitInfo.tradeTime}</p>
          <p>受け渡し場所：${sessionScope.exhibitInfo.tradePlace}</p>
          <p>入札数：${requestScope.bidNum}</p>		<!-- 入札数を表示(bidNum) -->
        </div>
        <div class="col-md-6">
          <br>
          <p>商品説明：${sessionScope.exhibitInfo.description}</p>
          <p>出品者情報：（ユーザ名）${requestScope.exhibitUser.userName}</p>	<!-- 出品者情報(exhibiUuser) -->
          <p>            （学年）    ${requestScope.exhibitUser.grade}</p>
          <p>            （学部）    ${requestScope.exhibitUser.underGraduate}</p>
          <p>            （学科）    ${requestScope.exhibitUser.department}</p>
        </div>

      </div>
    </div>


	<div class="input-group">
	<form action=Bid method=post>
  		<input type="text"name="price" class="form-control w-25" placeholder="入札額を入力" aria-label="" aria-describedby="basic-addon1" required style="position:fixed; right:50px; bottom:100px;"/>
    	<button class="btn btn-success" type="submit" name="bid" style="position:fixed; right:50px; bottom:50px;">入札</button>
  	</form>
	</div>
</div>
	<jsp:include page="/includeJSP/footer.jsp">
	<jsp:param name="log" value="log"/>
	</jsp:include>

</body>
</html>