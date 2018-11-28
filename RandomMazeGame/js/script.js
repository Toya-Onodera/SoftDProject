// jsp のランダム数を使用して迷路をすることができるゲームを実装するクラス
class RandomMaze {
    constructor (element, size) {
        // HTML 要素
        this.elm = element

        // 迷路の高さ (row) 幅 (column) を設定
        this.size = size

        // 迷路のマップデータを格納する
        // 「高さ * 幅」分の一次元配列を用意
        this.mazeMap = new Array(Math.pow(size + 1, 2))

        // 自分の座標
        this.myIndex = 0;

        // 矢印の方向を指定するクラスを格納する配列
        this.arrowClassArray = ['myAvatarArrowUp', 'myAvatarArrowDown', 'myAvatarArrowRight', 'myAvatarArrowLeft']
    }

    // 乱数を生成するメソッド min < max までの範囲
    // https://qiita.com/uto-usui/items/7193db237175ba15aaa3
    getRandomInt (min, max) {
        return Math.floor(Math.random() * (max - min + 1) + min)
    }

    // 迷路を生成する（棒倒し法で実装）
    stickDownMaze () {
        // 壁を伸ばす方向を指定する配列
        const go = [-this.size - 1, this.size + 1, -1, 1]

        // すべて 0 で初期化
        this.mazeMap.fill(0)

        // 壁を配置
        for (let i = 1; i < this.size; i += 2) {
            for (let j = 1; j < this.size; j += 2) {
                // 基準値の座標
                const mapIndex = (this.size + 1) * i + j

                // 基準の壁を配置
                this.mazeMap[mapIndex] = 1

                while (true) {
                    // 一番上のときだけ上に壁を生成することを許可
                    const wallPos = (i === 1) ? this.getRandomInt(0, 3) : this.getRandomInt(1, 3)

                    // 壁が生成できる状態のときのみ生成する
                    if (this.mazeMap[mapIndex + go[wallPos]] === 0) {
                        this.mazeMap[mapIndex + go[wallPos]] = 1
                        break
                    }
                }
            }
        }

        // 自分の位置を設定する
        this.mazeMap[this.myIndex] = 100;

        // ゴールを設定する
        this.mazeMap[Math.pow(this.size + 1, 2) - 1] = -100;
    }

    // 描画を行う前に行うメソッド
    reRenderInit () {
        this.elm.innerHTML = null
    }

    // 迷路用の HTML を生成する
    createMazeHtml () {
        // 一度初期化する
        this.reRenderInit()

        let mazeHtmlElement = ''

        for (let i = 0; i <= this.size; i++) {
            mazeHtmlElement += '<div class="gameViewRow">'

            for (let j = 0; j <= this.size; j++) {
                // 基準値の座標
                const mapIndex = (this.size + 1) * i + j
                mazeHtmlElement += (() => {
                    // 空白
                    if (this.mazeMap[mapIndex] === 0) {
                        return '<div class="gameViewColumn"></div>'
                    }

                    // 壁
                    else if (this.mazeMap[mapIndex] === 1) {
                        return '<div class="gameViewColumn wall"></div>'
                    }

                    // 自分
                    else if (this.mazeMap[mapIndex] === 100) {
                        return '<div class="gameViewColumn myAvatar"></div>'
                    }

                    // ゴール
                    else if (this.mazeMap[mapIndex] === -100) {
                        return '<div class="gameViewColumn goal"></div>'
                    }
                })()
            }

            mazeHtmlElement += '</div>'
        }

        this.elm.innerHTML = mazeHtmlElement
    }

    // 自分のアバターを移動させる
    moveAvatar () {
        const firstClassName = this.arrowClassArray[3]
        let step = 0;

        if (firstClassName === 'myAvatarArrowUp' && (this.myIndex - this.size) > 0) {
            step = -this.size - 1;
        }

        // TODO: 下の移動条件を直す
        else if (firstClassName === 'myAvatarArrowDown' && this.myIndex <= Math.pow(this.size + 1, 2)) {
            step = this.size + 1;
        }

        else if (firstClassName === 'myAvatarArrowRight' && this.myIndex % (this.size + 1) < this.size) {
            step = 1;
        }

        else if (firstClassName === 'myAvatarArrowLeft' && this.myIndex % (this.size + 1) !== 0) {
            step = -1;
        }

        // 自分の座標を更新
        if (this.mazeMap[this.myIndex + step] !== 1) {
            // 自分の座標をリセット
            this.mazeMap[this.myIndex] = 0

            // 座標を変更する
            this.myIndex += step

            // 変更後の座標に自分の座標をセット
            this.mazeMap[this.myIndex] = 100
        }

        // 再描画
        this.createMazeHtml()
    }

    // ゴール判定を行うメソッド
    isGoal () {
        return (this.myIndex === Math.pow(this.size + 1, 2) - 1)
    }

    // ランダムで矢印を変更するメソッド
    arrowChange () {
        const arrowSet = this.arrowClassArray.shift()
        this.elm.className = arrowSet
        this.arrowClassArray.push(arrowSet)
    }
}

// DOM 操作
window.onload = () => {
    // ゲームビューの要素
    const mazeElement = document.getElementById('gameView')

    const rm = new RandomMaze(mazeElement, 14)

    rm.stickDownMaze()
    rm.createMazeHtml()

    const tapLayerElement = document.getElementById('tapArea')
    tapLayerElement.addEventListener('click', () => {
        rm.moveAvatar()
    })

    // 矢印を変更する
    const arrowTimer = () => {
        if (!rm.isGoal()) {
            rm.arrowChange()
            setTimeout(arrowTimer, 700)
        }

        else {
            alert("ゴール！！")
            tapLayerElement.parentNode.removeChild(tapLayerElement)
        }
    }

    setTimeout(arrowTimer, 0)
}