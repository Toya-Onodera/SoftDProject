<%-- 
    Document   : RoomMemo
    Created on : 2019/01/31, 3:24:16
    Author     : O.Toya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>部屋メモ一覧 - MemoShare</title>
        
        <link href="https://fonts.googleapis.com/css?family=M+PLUS+1p" rel="stylesheet">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
        <link rel="stylesheet" href="./stylesheets/common.css">
        <link rel="stylesheet" href="./stylesheets/room_memo.css">

        <script src="./javascripts/room_memo.js"></script>
    </head>
    <body>
        <div class="warpper">
            <div class="menu">
                <span class="username">
                    こんにちは、テストアカウントさん
                </span>

                <div class="create_newroom_button">
                    <a href="#">新規メモ作成</a>
                </div>
            </div>

            <div class="content">
                <h1>部屋メモ一覧</h1>

                <div class="memo_list_views">
                    <div class="myselfmemo_list">
                        <!-- 部屋内のメモデータが入る -->
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
