<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <!-- Custom styles for this template -->
    <link href="https://getbootstrap.com/docs/4.0/examples/sign-in/signin.css" rel="stylesheet">

<title>Tras</title>
</head>

<style>
      @media (min-width: 768px) {
        .container {
          max-width: 730px;
        }
      }
</style>

<body>
<div class="container">
<div class="my-3 p-3 bg-white rounded box-shadow">
		<h1 class="border-bottom border-gray pb-2 mb-0">アカウント情報確認画面</h1>

		<div class="border-bottom border-gray">
                <h4>氏名</h4>
                	<p>${sessionScope.account.name}</p>
        </div>

		<div class="border-bottom border-gray">
                <h3>ユーザ名</h3>
                	<p>${sessionScope.account.userName}</p>
        </div>

		<div class="border-bottom border-gray">
                <h3>パスワード</h3>
                	<p>${sessionScope.account.pass}</p>
        </div>

		<div class="border-bottom border-gray">
                <h3>学年（任意）</h3>
                	<p>${sessionScope.account.grade}</p>
        </div>

		<div class="border-bottom border-gray">
                <h3>学部（任意）</h3>
                	<p>${sessionScope.account.underGraduate}</p>
        </div>


		<div class="border-bottom border-gray">
                <h3>学科（任意）</h3>
                	<p>${sessionScope.account.department}</p>
        </div>


		<div class="border-bottom border-gray">
                <h3>秘密の質問</h3>
                	<p>${sessionScope.account.secretQuestion}</p>
        </div>


        <div class="border-bottom border-gray">
                <h3>秘密の質問の答え</h3>
                	<p>${sessionScope.account.secretQuestionAnswer} </p>
        </div>

		<div class="row">
		<form action="CreateAccount" method="get">
			<div class="btn-group">
			<input type="submit" name="edit" class="btn btn-primary btn-lg" value="編集" onClick="history.back()"/>
			</div>
		</form>
		<form action="CreateAccountComp" method="post">
		<div class="btn-group">
			<input type="submit" name="register" class="btn btn-primary btn-lg btn-block" value="登録" />
		</div>
		</form>
		</div>
	</div>
</div>

</body>
</html>