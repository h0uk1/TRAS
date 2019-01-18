<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <!-- Custom styles for this template -->
    <link href="https://getbootstrap.com/docs/4.0/examples/checkout/form-validation.css" rel="stylesheet">
<title>Tras アカウント本登録</title>
</head>

<script>
	function CheckPassword(confirm){
		// 入力値取得
		var input1 = pass.value;
		var input2 = passRe.value;
		// パスワード比較
		if(input1 != input2){
			confirm.setCustomValidity("入力値が一致しません。");
		}else{
			confirm.setCustomValidity('');
		}
	}
</script>

<style>
      @media (min-width: 768px) {
        .container {
          max-width: 730px;
        }
      }
    </style>

<body>
<form action="CreateAccount" method="post">
<div class="container">
<div class="my-3 p-3 bg-white rounded box-shadow">
            <h4 class="mb-3">新規アカウント登録</h4>

				<div class="row">

					<div class="col-md-6 mb-3">
                        <label for="lastName">姓</label>
                        <input type="text" class="form-control" name="familyName" placeholder="" value="" required>
                        <div class="invalid-feedback">
                            Valid last name is required.
                        </div>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="firstName">名</label>
                        <input type="text" class="form-control" name="firstName" placeholder="" value="" required>
                        <div class="invalid-feedback">
                            Valid first name is required.
                        </div>
                    </div>

                </div>

                <div class="mb-3">
                    <label for="username">ユーザ名</label>
                    <div class="input-group">
                        <input type="text" class="form-control" name="userName" minlength="4" maxlength="20" placeholder="Username" required>
                        <div class="invalid-feedback" style="width: 100%;">
                            Your username is required.
                        </div>
                    </div>
                </div>
<%
	if(session.getAttribute("register")!=null && !(Boolean)session.getAttribute("register")) {
		out.println("<p>このユーザ名は既に使われています</p>");
	}
%>
				<div class="mb-3">
                    <label for="pass">パスワード</label>
                    <div class="input-group">
                        <input type="password" class="form-control" name="pass" id="pass" minlength="8" maxlength="16" placeholder="Password" required>
                    </div>
                </div>

                <div class="mb-3">
                    <label for="passRe">パスワードの確認</label>
                    <div class="input-group">
                        <input type="password" name="passRe" id="passRe" minlength="8" maxlength="16" class="form-control" placeholder="Password" required oninput="CheckPassword(this)"/>
                    </div>
                </div>

                    <div class="col-md-5 mb-3">
                        <label for="grade">学年</label>
						<select name="grade" class="custom-select d-block w-100">
							<option value="" selected>未設定</option>
							<option value="1年">1年</option>
							<option value="2年">2年</option>
							<option value="3年">3年</option>
							<option value="4年">4年</option>
						</select>
					</div>

					<div class="col-md-5 mb-3">
                        <label for="underGraduate">学部</label>
						<select name="underGraduate" class="custom-select d-block w-100">
							<option value=""  class="msg" selected >未設定</option>
							<option value="未来科学部">未来科学部</option>
							<option value="工学部">工学部</option>
							<option value="システムデザイン学部">システムデザイン学部</option>
							<option value="情報環境学部">情報環境学部</option>
							<option value="工学部第2部">工学部第2部</option>
						</select>
					</div>

					<div class="col-md-5 mb-3">
                        <label for="department">学科</label>
						<select name="department" class="custom-select d-block w-100">
							<option value="" class="msg" selected>学科を選択</option>
						    <option value="建築学科" data-val="未来科学部">建築学科</option>
      						<option value="情報メディア学科" data-val="未来科学部">情報メディア学科</option>
      						<option value="ロボット・メカトロニクス学科" data-val="未来科学部">ロボット・メカトロニクス学科</option>

      						<option value="電気電子工学科" data-val="工学部">電気電子工学科</option>
      						<option value="電子システム学科" data-val="工学部">電子システム学科</option>
      						<option value="応用化学科" data-val="工学部">応用化学科</option>
      						<option value="機械工学科" data-val="工学部">機械工学科</option>
      						<option value="先端機械工学科" data-val="工学部">先端機械工学科</option>
      						<option value="情報通信工学科" data-val="工学部">情報通信工学科</option>

      						<option value="情報システム工学科" data-val="システムデザイン学部">情報システム工学科</option>
      						<option value="デザイン工学科" data-val="システムデザイン学部">デザイン工学科</option>

      						<option value="ネットワーク・コンピュータ工学コース" data-val="情報環境学部">ネットワーク・コンピュータ工学コース</option>
      						<option value="デジタル情報工学コース" data-val="情報環境学部">デジタル情報工学コース</option>
      						<option value="建築デザインコース" data-val="情報環境学部">建築デザインコース</option>
      						<option value="コミュニケーション工学コース" data-val="情報環境学部">コミュニケーション工学コース</option>

      						<option value="電気電子工学科" data-val="工学部第2部">電気電子工学科</option>
      						<option value="機械工学科" data-val="工学部第2部">機械工学科</option>
      						<option value="情報通信工学科" data-val="工学部第2部">情報通信工学科</option>
						</select>
					</div>


					<div class="col-md-5 mb-3">
                        <label for="secretQuestion">秘密の質問</label>
						<select name="secretQuestion" class="custom-select d-block w-100">
							<option value="">質問を選択</option>
      						<option value="ペットの名前は？">ペットの名前は？</option>
      						<option value="母親の旧姓は？">母親の旧姓は？</option>
      						<option value="子供の頃のあだ名は？">子供の頃のあだ名は？</option>
      						<option value="好きな食べ物は？">好きな食べ物は？</option>
      						<option value="自分の出身地は？">自分の出身地は？</option>
						</select>
					</div>

					<div class="col-md-5 mb-3">
                        <label for="secretQuestion">秘密の質問</label>
						<input type="text" name="secretQuestionAnswer" class="form-control" maxlength="10" required placeholder="Username"/>
					</div>

					<button type="submit" name="Register" class="btn btn-primary btn-lg btn-block">確認する</button>
					</form>
			</div>
		</div>

					<footer class="my-5 pt-5 text-muted text-center text-small">
        				<p class="mb-1">&copy; 2017-2018 kenta</p>
					</footer>

</body>
</html>