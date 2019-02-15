<%-- 
    Document   : Register
    Created on : 2019/01/31, 3:24:16
    Author     : O.Toya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ユーザー登録 - MemoShare</title>
        
        <link href="https://fonts.googleapis.com/css?family=M+PLUS+1p" rel="stylesheet">
        <link rel="stylesheet" href="./stylesheets/common.css">
        <link rel="stylesheet" href="./stylesheets/register.css">
        
        <script src="./javascripts/register.js"></script>
    </head>
    <body>
        <div class="warpper">
            <div class="warpper-layer">
                <div class="form-wrapper">
                    <h1>ユーザー登録</h1>
                    
                    <p>このシステムを利用するために、アカウントを登録してください。</p>

                    <form id="register-form" method="POST" action="#" accept-charset="UTF-8">
                        <div class="form-item">
                            <label for="email"></label>
                            <input id="email" type="email" name="email" required="required" placeholder="新規メールアドレス">
                        </div>

                        <div class="form-item">
                            <label for="username"></label>
                            <input id="username" type="text" name="username" required="required" placeholder="新規ユーザー名">
                        </div>
                        
                        <div class="form-item">
                            <label for="password"></label>
                            <input id="password" type="password" name="password" required="required" placeholder="新規パスワード">
                        </div>

                        <div class="button-panel">
                            <input type="submit" class="button" title="登録する" value="登録する"></input>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
