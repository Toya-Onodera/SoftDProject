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
    }

    // 迷路用の HTML を生成する
    createMazeHtml () {
        let mazeHtmlElement = ''

        for (let i = 0; i <= this.size; i++) {
            mazeHtmlElement += '<div class="gameViewRow">'

            for (let j = 0; j <= this.size; j++) {
                // 基準値の座標
                const mapIndex = (this.size + 1) * i + j
                mazeHtmlElement += (this.mazeMap[mapIndex] === 0) ? '<div class="gameViewColumn"></div>' : '<div class="gameViewColumn wall"></div>'
            }

            mazeHtmlElement += '</div>'
        }

        this.elm.innerHTML = mazeHtmlElement
    }
}

// DOM 操作
(() => {
    const mazeElement = document.getElementById('gameView')
    const rm = new RandomMaze(mazeElement, 30)
    rm.stickDownMaze()
    rm.createMazeHtml()
})()
