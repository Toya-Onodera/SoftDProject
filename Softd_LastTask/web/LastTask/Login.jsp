<%-- 
    Document   : login
    Created on : 2019/01/31, 3:24:16
    Author     : O.Toya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ログイン - MemoShare</title>
        
        <link href="https://fonts.googleapis.com/css?family=M+PLUS+1p" rel="stylesheet">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
        <link rel="stylesheet" href="./stylesheets/common.css">
        <link rel="stylesheet" href="./stylesheets/login.css">
        
        <script src="./javascripts/login.js"></script>
    </head>
    <body>
        <div class="warpper">
            <div class="form-wrapper">
                <h1>ログイン</h1>
                
                <form id="login-form" method="POST" action="#" accept-charset="UTF-8">
                    <div class="form-item">
                        <label for="email"></label>
                        <input id="email" type="email" name="email" required="required" placeholder="メールアドレス">
                    </div>
                
                    <div class="form-item">
                        <label for="password"></label>
                        <input id="password" type="password" name="password" required="required" placeholder="パスワード">
                    </div>
                
                    <div class="button-panel">
                        <input type="submit" class="button" title="ログインする" value="ログインする">
                    </div>
                </form>
                
                <div class="form-footer">
                    <p><a href="./Register.jsp">アカウントを作成する</a></p>
                    <p><a href="#">パスワードを忘れてしまった場合はこちら</a></p>
                </div>
            </div>
        </div>
    </body>
</html>
