document.addEventListener('DOMContentLoaded', () => {
    const loginButton = document.querySelector('#login-form');
    loginButton.addEventListener('submit', e => {
        e.preventDefault();
        
        const email = document.querySelector('#email').value;
        const password = document.querySelector('#password').value;
        
        fetch(`/Softd_LastTask/LoginCheck`,
            {
                method: 'POST',
                headers: new Headers({'Content-type': 'application/x-www-form-urlencoded'}),
                body: `email=${email}&password=${password}`
            }
        )

        .then(response => response.json())
        .then(status => {
            if (typeof status.result === 'string') {
                location.href = status.result;
            }
            
            else {
                alert('ログインに失敗しました。メールアドレスかパスワードが間違っている可能性があります。');
            }
        });
    });
});

