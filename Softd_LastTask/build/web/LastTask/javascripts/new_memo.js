document.addEventListener('DOMContentLoaded', () => {
    const url = window.location.search;
    const id = url.slice(1).split('&');
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
        }

        // ログインできていないならログイン画面へリダイレクト
        else {
            location.href = '/Softd_LastTask/LastTask/Login.jsp';
        }
    });
    
    const editMemoSaveButton = document.querySelector('.edit_memo_save_button');
    editMemoSaveButton.addEventListener('click', () => {
        const sendText = editor.textContent;
        editor.contentEditable = "false";        
        
        fetch(`/Softd_LastTask/MemoDataChange`,
            {
                method: 'POST',
                headers: new Headers({'Content-type': 'application/x-www-form-urlencoded'}),
                body: `${id[0]}&${id[1]}&content=${sendText}`
            }
        )

        .then(response => response.text())
        .then(result => {
            if (result === "success") {
                location.href = `/Softd_LastTask/LastTask/RoomMemo.jsp?${id[0]}`;                
            }
            
            else {
                alert('保存に失敗しました。再度保存をお願いします。');
            }
        });
    });
});