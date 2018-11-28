// jsp のランダム数を使用してお酒を作るゲーム（？）を実装するクラス
class RandomDrink {
    constructor (menuElements, drinkElement) {
        // HTML 要素（メニュー表用）
        this.menuElements = menuElements

        // HTML 要素（グラス色用）
        this.drinkElement = drinkElement

        // ソフトドリンクを混ぜたときに変化するカラーコードを格納する配列
        // カルピス、メロンソーダ、オレンジジュース、コーラの順で格納する
        // グラデーションの濃いほうが内側で薄いほうが外側
        this.mixedDrinkColorArray = [
            { name: 'カルピスサワー', className: 'calpis' },
            { name: 'メロンサワー', className: 'melon' },
            { name: 'オレンジサワー', className: 'orange' },
            { name: 'コーラサワー', className: 'cola' }
        ]

        // ランダムの値を設定する
        this.randomIndex = this.returnRandomIndexArray(2)
    }

    // 重複しないランダムな配列オブジェクトを返すメソッド
    returnRandomIndexArray (size) {
        // 今回は扱うお酒の個数が決まっているので静的な値から取り出す
        let array = [0, 1, 2, 3]
        let a = array.length;

        // シャッフルを行う
        while (a) {
            const j = Math.floor( Math.random() * a )
            const t = array[--a]
            array[a] = array[j]
            array[j] = t
        }

        console.log(array)
        // 2個分の値が格納されている配列を返す
        return array.slice(0, size)
    }

    // メニュー表を更新するメソッド
    createMenu () {
        const menuElementsArray = Array.from(this.menuElements, e => e)


        // メニュー名を変更する
        menuElementsArray.forEach((e, i) => {
            e.children[0].innerHTML = this.mixedDrinkColorArray[this.randomIndex[i]].name
        })
    }

    // グラスの液体色を変更するメソッド
    createDrink () {
        this.drinkElement.classList.add(this.mixedDrinkColorArray[this.randomIndex[0]].className)
    }
}

// DOM 操作
window.onload = () => {
    // DOM 要素を取得
    const menuElements = document.querySelectorAll('.target-children')
    const drinkElement = document.querySelector('.glass')

    // ランダムでお酒を生成するシステムを実行
    const rd = new RandomDrink(menuElements, drinkElement)
    rd.createMenu()
    rd.createDrink()


}