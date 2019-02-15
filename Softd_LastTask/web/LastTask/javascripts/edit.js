document.addEventListener('DOMContentLoaded', () => {
    const url = window.location.search;
    const memo_id = url.slice(1).split('&');
    var editor;
    
    const toolbarOptions = [
        [{'size': ['small', false, 'large', 'huge']}],
        ['bold', 'italic', 'underline', 'strike'],
        ['link', 'image'],
        [{'list': 'ordered'}, {'list': 'bullet'}],
    ];

    var quill = new Quill('#editor', {
        modules: {
            toolbar: toolbarOptions
        },

        theme: 'snow'
    });

    fetch(`/Softd_LastTask/LoginCheck`)
    .then(response => response.json())
    .then(status => {
        if (typeof status.result === 'number') {
            editor = document.querySelector('.ql-editor');
            
            // ユーザ名表示
            const usernameElement = document.querySelector('.username');
            usernameElement.textContent = `こんにちは、${status.user_name}さん`;
            
            fetch(`/Softd_LastTask/MemoData?${memo_id[0]}`)
            .then(response => response.json())
            .then(dataStr => {
                const content = (dataStr[0].media === "null") ? dataStr[0].text : dataStr[0].media;
                editor.textContent = content;
            });
        }

        // ログインできていないならログイン画面へリダイレクト
        else {
            location.href = '/Softd_LastTask/LastTask/Login.jsp';
        }
    });
    
    const editMemoSaveButton = document.querySelector('.edit_memo_save_button');
    editMemoSaveButton.addEventListener('click', () => {
        editor.contentEditable = "false";
        const sendText = editor.textContent;
        
        fetch(`/Softd_LastTask/MemoData`,
            {
                method: 'POST',
                headers: new Headers({'Content-type': 'application/x-www-form-urlencoded'}),
                body: `${memo_id}&content=${sendText}`
            }
        )

        .then(response => response.text())
        .then(result => {
            if (result === 'success') {
                // 複数ページから Edit.jsp に飛んでくるため前のページに戻る
                location.href = document.referrer;
            }
        });
    });
});