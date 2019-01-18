<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tras admin</title>
</head>
<body>
		<form action = "NoticeReplyAdmin" method="post">
        <h1>個人通知送信フォーム</h1>
        <dl>

            <!--タイトルを入力-->
            <dt>タイトル</dt>
            <dd>${sessionScope.notice.noticeGroup}</dd>

            <!--内容を入力-->
            <dt>内容</dt>
            <dd>${sessionScope.notice.message}</dd>
        </dl>

        <!--入力内容の確認-->
        <button type="submit" name="edit" onClick="history.back()">修正</button>

        <!--入力内容キャンセル-->
        <button type="submit" name="register">送信</button>
       	</form>
</body>
</html>