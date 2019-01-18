<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tras admin</title>
</head>
<body>

        <h1>問い合わせ内容</h1>
			<p>ユーザ名：${sessionScope.accountInfo.userName}</p>
			<p>問い合わせ分類：${sessionScope.questionInfo.questionGroup}</p>
			<p>問い合わせ内容：${sessionScope.questionInfo.textBox}</p>

        <!--入力内容の修正-->
        <form action="QuestionInfoAdmin" method="post">
        <input type="submit" name="send" value="返信">返信する</button>
        </form>
        <!--入力内容の送信-->
        <form action="QuestionListAdmin" method="get">
        <input type="submit" name="cancel" value="キャンセル">キャンセル</button>
        </form>

</body>
</html>