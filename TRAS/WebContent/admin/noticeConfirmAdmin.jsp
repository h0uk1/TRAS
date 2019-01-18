<%@ page language="java" contentType="text/html; charset=UTF8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
    <link rel="stylesheet" href="main.css">
<title>Tras admin</title>
</head>
    <body>

    <jsp:include page="/includeAdminJSP/headerAdmin.jsp">
	<jsp:param name="log" value="log"/>
	</jsp:include>


    <form action = "NoticeSendAdmin" method="post">
        <h1>全体通知送信フォーム</h1>
        <dl>

            <!--タイトルを入力-->
            <dt>タイトル</dt>
            <dd>${sessionScope.noticeAll.noticeTitle}</dd>

            <!--内容を入力-->
            <dt>内容</dt>
            <dd>${sessionScope.noticeAll.message}</dd>
        </dl>

		<p>この内容で送信しますか？</p>
        <!--入力内容の修正-->
        <button type="submit" name="edit" onclick="history.back()">修正する</button>

   		<!--送信-->
        <button type="submit" name="register">送信</button>
       </form>
    </body>
</html>