# trait, abstract class, case classの使い所
- 共通
  - 独自の型を作れる = Seqなど要素の型が一律でないといけない時に便利
  - 持つべき要素を強制的に共通化できる
  - 引数の左辺と右辺の名前が同じの場合は、「= 右辺」を省略できる（JSの変数みたいな）
  - 引数は、「引数名 = xxx」としたら表記は順不同でOK 

# memo
- trait
  - 振る舞いや見た目など、規格が決まっているもの
  - （基本的に）メゾットだけを持つイメージ
  - 例えば、USB（タイプやサイズなど規格が決まっている）

- abstract class
  - 要件からメゾットまでフレキシブルなもの
  - 例えば、電子レンジ（色、サイズ、Wなど決まっていない）
  - classなので、サブクラスは１つしか作れない

- case class
  - classの便利メゾットやnewが必要ないバージョン
  - 複数のサブクラスも作れる
  - classを継承した場合、case classで暗黙的位に.apply()でインスタンス化した値をclassに渡している


# class(July14~)
- クラスのメンバー
  - フィールド    ：そのデータが保持するべき変数(x, y)
  - メゾット      ：そのデータに対して可能な操作(distance)
  - コンストラクタ：データを作り出l（インスタンス化するとき）に呼び出される手続き
- プライマリコンストラクタ: クラスパラメータ + 実行される本体

```
class Point (val x: Int, val y: Int) {
  def distance() = {
    
  }
}

new Point(100, 100)
```

## フィールドの書き方
```
class Point(val x: Int, y: Int) {}


//フィールド宣言をせず、クラスパラメータをそのまま代入するやり方もある

class Point(vx: Int, vy: Int) {
  var x = vx,
  var y = vy
}

//フィールを宣言をvarで行った場合、初期値に_もOK。その方のデフォルト値になる。
class Point(var _: Int) // = 0
```

# case class(July14~)
- クラスパラメータは、自動的にvalとして宣言される
```
case class Point (x: Int, y: Int) 

Point(100, 100)
```


```
class ClassName (
 
  val 定数名 = 値
  var 変数名 = 値
 
)
```

# class, case classの継承
```
/* 
abstract class 抽象クラス (引数) {
  抽象メゾット 
}
*/

abstract class Shape (val height: Int) {
  def calculate(): Int
}

/* 
class 具象クラス(具象クラスの引数) {

} extends 抽象クラス (
  抽象クラスの引数 = 具象クラスの引数 
) {
} 
*/

/*

*/
class Rectangle (_height: Int) extends Shape (
  //Rectangleクラスの引数と同じ変数名なら、「height」だけでもOK
  height = _height
) {
  def calculate(): Int = {
    height * height
  }
}

val rectangle = new Rectangle(10).calculate()
```


