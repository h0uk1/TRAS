<%@ page language="java" contentType="text/html; charset=UTF8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
    <link rel="stylesheet" href="main.css">
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <!-- Custom styles for this template -->
    <link href="https://getbootstrap.com/docs/4.0/examples/sign-in/signin.css" rel="stylesheet">
<title>Tras</title>
    </head>
    <body class="text-center">
    <div class="container">
	<div class="my-3 p-3 bg-white rounded box-shadow">
    <h1>アカウントを作成しました！</h1>
    <form action="CreateAccountComp" method="post">
       <input type="submit" name="toLogin" class="btn btn-lg btn-primary" value="ログイン画面へ" />
    </form>
    </div>
    </div>
    </body>
</html>