<%-- 
    Document   : Mypage
    Created on : 2019/01/31, 3:24:16
    Author     : O.Toya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>マイページ - MemoShare</title>
        
        <link href="https://fonts.googleapis.com/css?family=M+PLUS+1p" rel="stylesheet">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
        <link rel="stylesheet" href="./stylesheets/common.css">
        <link rel="stylesheet" href="./stylesheets/mypage.css">

        <script src="./javascripts/mypage.js"></script>
    </head>
    <body>
        <div class="warpper">
            <div class="menu">
                <span class="username">
                    こんにちは、テストアカウントさん
                </span>

                <div class="create_newroom_button">
                    <span>新規メモ部屋作成</span>
                </div>
            </div>

            <div class="content">
                <h1>マイページ</h1>

                <div class="memo_list_views">
                    <div class="myselfmemo_list">
                        <!-- メモデータが入る -->
                    </div>

                    <div class="room_list">
                        <h3>使用できるメモ部屋</h3>

                        <ul>
                            <li>
                                <span class="icon-users"><i class="fas fa-users"></i></span>
                                <span>あなたがリーダーの部屋です</span>
                            </li>
                            
                            <li>
                                <span class="icon-link"><i class="fas fa-link"></i></span>
                                <span>他のユーザーが作成した部屋です</span>
                            </li>
                        </ul>

                        <div>
                            <!-- 部屋データが入る -->
                        </div>
                    </div>
                </div>
            </div>

            <div class="create_room_layer">
                <div class="create_room_view">
                    <h3><i class="fas fa-plus-square"></i> 新規メモ部屋作成</h3>

                    <div>
                        <div>
                            <span><i class="fas fa-angle-right"></i> 部屋名</span>
                            <input id="room_name" type="text" placeholder="部屋名を入力してください。">
                        </div>

                        <div>
                            <span><i class="fas fa-angle-right"></i> 部屋の共有</span>
                            <select id="is_participation">
                                <option value="0" selected>しない</option>
                                <option value="1">する</option>
                            </select>
                        </div>                        
                    </div>

                    <div class="create_room_submit_button">
                        <div class="loading">
                            <i class="fas fa-spinner fa-spin"></i>                            
                        </div>
                        
                        <span>登録</span>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
