document.addEventListener('DOMContentLoaded', () => {
    const createPageButton = document.querySelector('.create_newroom_button');
    const createRoomLayer = document.querySelector('.create_room_layer');
    const createRoomView = document.querySelector('.create_room_view');
    const createRoomSubmitButton = document.querySelector('.create_room_submit_button');
    const loadingView = document.querySelector('.loading');
    
    var my_id = null;

    // 新規メモ部屋作成ビューを表示する（フェードイン）
    createPageButton.addEventListener('click', () => {
        createRoomLayer.className = 'create_room_layer create_room_layer_show';
    });

    // 新規メモ部屋作成ビューを非表示にする（フェードアウト）
    createRoomLayer.addEventListener('click', () => {
        createRoomLayer.className = 'create_room_layer create_room_layer_hidden';

        setTimeout(() => {
            createRoomLayer.className = 'create_room_layer';
        }, 300);
    }, false);

    // .create_room_layer のみクリックできるようにバブリング防止
    createRoomView.addEventListener('click', e => e.stopPropagation());
    
    // 新規部屋の追加を行う
    createRoomSubmitButton.addEventListener('click', () => {
        const roomName = document.querySelector('#room_name').value;
        
        if (roomName === "") {
            alert("部屋名を入力してください");
            return false;
        }
        
        const isParticipation = document.querySelector('#is_participation').value;
        
        // ちょっと強引に…
        loadingView.style.display = 'flex';
        
        fetch(`/Softd_LastTask/MyPageData`,
            {
                method: 'POST',
                headers: new Headers({'Content-type': 'application/x-www-form-urlencoded'}),
                body: `my_id=${my_id}&room_name=${roomName}&is_participation=${isParticipation}`
            }
        )
        .then(response => response.text())
        .then(result => {
            if (result === "success") {
                location.reload();
            }
    
            else {
                alert('登録に失敗しました。時間を空けて再度お試しください。');
            }
        });
    });
    
    // ログイン確認後、自分のデータを表示する
    fetch(`/Softd_LastTask/LoginCheck`)
    .then(response => response.json())
    .then(status => {
    console.log(status)
        if (typeof status.result === 'number') {
            my_id = status.result;
            
            // ユーザ名表示
            const usernameElement = document.querySelector('.username');
            usernameElement.textContent = `こんにちは、${status.user_name}さん`;
            
            // メモデータを取得する
            fetch(`/Softd_LastTask/MyPageData?id=${my_id}`)
            .then(response => response.json())
            .then(dataStr => {
                // メモ一覧部分
                const myselfmemoListElement = document.querySelector('.myselfmemo_list');
                let myselfmemoElementStr = '<h3>自分のメモ</h3>';
                
                dataStr.memo.forEach(e => {
                    const id = e.id;
                    const content = (e.media === "null") ? e.text : `<img src="${e.media}">`;
                    const date = (e.updated_at === "null") ? e.created_at : e.updated_at;
                    
                    const myselfmemoTemplate = 
                    `<div class="myselfmemo">
                        <span class="text">
                            ${content}
                        </span>

                        <div class="info">
                            <span class="date">${date}</span>

                            <div>
                                <span class="edit_button">
                                    <a href="/Softd_LastTask/LastTask/Edit.jsp?id=${id}"><i class="far fa-edit"></i> 編集</a>
                                </span>

                                <span class="delete_button">
                                    <span data-memoId="${id}"><i class="far fa-trash-alt"></i> 削除</span>
                                </span>
                            </div>
                        </div>
                    </div>`;
                    
                    myselfmemoElementStr += myselfmemoTemplate;
                });
                
                myselfmemoListElement.innerHTML = myselfmemoElementStr;
                
                // 部屋一覧部分
                const roomListElement = document.querySelector('.room_list > div');                
                let roomListElementtStr = '';
                
                dataStr.room.forEach(e => {
                    // 型が違うので等価演算子を使用
                    // TODO: 時間があったら直す
                    const isMakeYourOwn = (e.account_id == my_id);
                    const roomTemplate = 
                    `<a href="/Softd_LastTask/LastTask/RoomMemo.jsp?room_id=${e.id}" class="room" data-isMakeYourOwn="${isMakeYourOwn}">
                        <span class="name">${e.title}</span>
                        <span class="user">${e.name} さん</span>
                    </a>`;
                    
                    roomListElementtStr += roomTemplate;
                });
                
                roomListElement.innerHTML = roomListElementtStr;
                
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