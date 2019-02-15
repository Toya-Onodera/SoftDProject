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
        <title>メモを追加する - MemoShare</title>
        
        <link href="https://fonts.googleapis.com/css?family=M+PLUS+1p" rel="stylesheet">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
        <link href="https://cdn.quilljs.com/1.3.6/quill.snow.css" rel="stylesheet">
        <link rel="stylesheet" href="./stylesheets/common.css">
        <link rel="stylesheet" href="./stylesheets/edit.css">

        <script src="https://cdn.quilljs.com/1.3.6/quill.js"></script>
        <script src="./javascripts/new_memo.js"></script>
    </head>
    <body>
        <div class="warpper">
            <div class="menu">
                <span class="username">
                    こんにちは、テストアカウントさん
                </span>

                <div class="edit_memo_save_button">
                    <span><i class="far fa-save"></i> メモ保存</span>
                </div>
            </div>

            <div class="content">
                <h1>メモを追加する</h1>

                <div class="edit_view">
                    <div class="edit_content_view">
                        <h3>本文を追加</h3>

                        <div id="editor" class="edit_content">
                            <!-- 本文が入る -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
