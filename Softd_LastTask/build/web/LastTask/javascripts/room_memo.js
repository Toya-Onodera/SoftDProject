document.addEventListener('DOMContentLoaded', () => {
    fetch(`/Softd_LastTask/LoginCheck`)
    .then(response => response.json())
    .then(status => {
        if (typeof status.result === 'number') {
            const url = window.location.search;
            const room_id = url.slice(1).split('&');
            const my_id = status.result;
            
            // ユーザ名表示
            const usernameElement = document.querySelector('.username');
            usernameElement.textContent = `こんにちは、${status.user_name}さん`;
            
            fetch(`/Softd_LastTask/RoomMemoData?${room_id[0]}`)            
            .then(response => response.json())
            .then(roomMemoData => {
                // 新規メモ作成ボタンで部屋を特定できるようにする
                const createNewroomButtonLink = document.querySelector('.create_newroom_button > a');
                createNewroomButtonLink.href = `/Softd_LastTask/LastTask/NewMemo.jsp?${room_id[0]}&account_id=${status.result}`;
                
                // メモ一覧部分
                const myselfmemoListElement = document.querySelector('.myselfmemo_list');                
                var myselfmemoElementStr = `<h3>${roomMemoData.title}</h3>`;
                
                console.log(roomMemoData)
                
                roomMemoData.memo.forEach(e => {
                console.log(e)
                    const content = (e.media === "null") ? e.text : `<img src="${e.media}">`;
                    const date = (e.updated_at === "null") ? e.created_at : e.updated_at;
                    
                    // 型が違うので等価演算子を使用
                    // TODO: 時間があったら直す？
                    const edit = (e.account_id == my_id) ?
                            `<div>
                                <span class="edit_button">
                                    <a href="/Softd_LastTask/LastTask/Edit.jsp?id=${e.id}">
                                        <i class="far fa-edit"></i> 編集
                                    </a>
                                </span>

                                <span class="delete_button">
                                    <span data-memoId="${e.id}">
                                        <i class="far fa-trash-alt"></i> 削除
                                    </span>
                                </span>
                            </div>`
                            : "";
                    
                    const myselfmemoTemplate = 
                            `<div class="myselfmemo">
                                <span class="text">${content}</span>

                                <div class="info">
                                    <span class="date">${date}</span>
                    
                                    ${edit}
                                </div>
                            </div>`;
                    
                    myselfmemoElementStr += myselfmemoTemplate;
                });
                
                
                myselfmemoListElement.innerHTML = myselfmemoElementStr;
                
                document.querySelectorAll('.delete_button > span').forEach(e => {
                    e.addEventListener('click', event => {
                        const deleteCheckFlg = confirm('このメモを削除しますか？');

                        if (deleteCheckFlg) {
                            const deleteMemoId = event.target.getAttribute('data-memoId');
                            
                            fetch(`/Softd_LastTask/MemoDataChange?memo_id=${deleteMemoId}`)
                            .then(response => response.text())
                            .then(result => {
                                if (result === "success") {
                                    location.reload();
                                }

                                else {
                                    alert('削除に失敗しました。時間を空けて再度お試しください。');
                                }
                            });
                        }
                    });
                });
            });
        }

        // ログインできていないならログイン画面へリダイレクト
        else {
            location.href = '/Softd_LastTask/LastTask/Login.jsp';
        }
    });
});