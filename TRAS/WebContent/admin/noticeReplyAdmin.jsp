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


    <form action = "NoticeReplyAdmin" method="post">
        <h1>個人通知送信フォーム</h1>
        <dl>

            <!--タイトルを入力-->
            <dt>タイトル</dt>
            <dd><textarea name="noticeTitle" required ></textarea></dd>

            <!--内容を入力-->
            <dt>内容</dt>
            <dd><textarea name="noticeContent" required></textarea></dd>
        </dl>

        <!--入力内容の確認-->
        <button type="submit" name="confirm">確認</button>
        </form>
		<form action = "NoticeReplyAdmin" method="post">
        <!--入力内容キャンセル-->
        <button type="submit" name="cancel">キャンセル</button>
       </form>
    </body>
</html>