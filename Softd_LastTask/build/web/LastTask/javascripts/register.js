document.addEventListener('DOMContentLoaded', () => {
    const registerButton = document.querySelector('#register-form');
    registerButton.addEventListener('submit', e => {
        e.preventDefault();
        
        const email = document.querySelector('#email').value;
        const password = document.querySelector('#password').value;
        const username = document.querySelector('#username').value;
        
        fetch(`/Softd_LastTask/Register`,
            {
                method: 'POST',
                headers: new Headers({'Content-type': 'application/x-www-form-urlencoded'}),
                body: `email=${email}&password=${password}&username=${username}`
            }
        )

        .then(response => response.json())
        .then(status => {
            console.log(status)
            if (typeof status.result === 'string') {
                location.href = status.result;
            }
            
            else {
                alert('アカウントの作成に失敗しました。メールアドレスが重複している可能性があります。');
            }
        });
    });
});
