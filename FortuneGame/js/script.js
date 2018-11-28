// サイトの読み込みが終了したら実行される
window.onload = () => {
    // 操作したい DOM を取得
    let drawButtonElm = document.querySelector('.draw-button')
    let fortuneElm = document.querySelector('.fortune-main')

    // .draw-button がクリックされたら実行される
    drawButtonElm.addEventListener('click', () => {
        // おみくじを表示
        fortuneElm.classList.add('fortune-main-show')

        // ボタンを削除
        drawButtonElm.parentNode.removeChild(drawButtonElm)
    })

    // 運勢のテキスト
    const fortune = ['大吉', '中吉', '小吉', '末吉', '吉', '凶']

    // 運勢の説明テキスト
    const fortuneDocument = [
        'コツコツと続けてきた努力が開花 偶然も味方して絶好のタイミング<br>アドバイス … 前回と違うやり方をする<br>ポイント … 店名が長い店<br>パーソン … メガネが似合う人',
        '迷っていたことに挑戦 来年につながる一歩に<br>ポイント … 電子書籍',
        '年末イベントで良い情報が 早く動き始めると得しそう<br>ポイント … イチョウ並木',
        '諦めない姿勢で粘り勝ち 謙虚な心でチャレンジを<br>ポイント … ひざ掛け',
        '用事に追われてアタフタ 流れに任せてどっしりと<br>ポイント … 茶色の靴',
        '新しいことへの挑戦でつまずきそう 簡単そうに見えてもしっかり準備を<br>アドバイス … 先輩に相談する<br>パーソン … 寒いのが苦手な人<br>ポイント … エコバッグ<br>おまじない … 友達にあめをあげる<br>メニュー … 豚のしょうが焼き'
    ]

    // ランダムの数字を設定 0 ~ 6
    const randomIndex = Math.floor(Math.random() * (6 - 0 + 1) + 0)

    // 運勢を設定
    let luckyElm = document.querySelector('.lucky')
    luckyElm.innerHTML = fortune[randomIndex]

    // 運勢ごとの文章を設定
    let documentElm = document.querySelector('.document')
    documentElm.innerHTML = fortuneDocument[randomIndex]
}