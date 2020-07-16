# Either
- 「2つのうちどちらか」を1つの型で表すことのできるクラス
- 第一パラメータ（Left）に「エラー」、第二パラメータ（Right）に成功の値を返す
- Optionでは情報が足りず、エラーが代数的データ型で定められているときに使う
  - 代数的データ型：代数的データ型とはプログラミング、特に関数型プログラミングや型システムにおいて使われるデータ型である。それぞれの代数的データ型の値には、1個以上のコンストラクタがあり、各コンストラクタには0個以上の引数がある。

## 定義方法
### 変数
vがStringもしくはIntの値のどちらかであるということを定義している
```
val v: Either[String, Int]
```

### インスタンス
値がLeft, Rightのどちらの型であるかを指定し、その型で初期化を行います。
```
//LeftはString, RightはInt型でないとだめ
val leftEither: Either[String, Int] = Left("string")

val rightEither: Either[String, Int] = Right(100)
```

### 値の取得
- `.get`
getメソッドを直接呼び出すと、その値がない場合に例外が発生します。
```
val leftEither : Either[String, Int] = Left("string") 
val rightEither: Either[String, Int] = Right(100) 

//leftの値を取得
leftEither.left.get // -> string

//rightの値を取得
rightEither.right.get // -> 100
```

- `.getOrElse`
```
val leftEither : Either[String, Int] = Left("string") 
val rightEither: Either[String, Int] = Right(100) 

//leftの値を取得
leftEither.right.getOrElse("none") // -> none

//rightの値を取得
rightEither.left.getOrElse // -> 0
```

- `match式`
```
v match {
  case Left(str)  => println("Rightだから値がないよ")
  case Right(int) => println(s"入っている値は${int}だよ")
}
```

- `.map()`
扱うEither型の値によって、処理の結果が変化します。  
EitherがRightである場合、Rightに格納されている値に対して関数を適用。適用後の値をEither型で返します。

```
// Right型が存在するとき、値が変換されて返却される
val rightEither: Either[String, Int] = Right(100)
val after = rightEither.map(i => i * 10)

println(after) // Right(1000)


// Right型が存在しない時、エラーとなる
val leftEither: Either[String, Int] = Left("invalid parameter")
val after = leftEither.map(i => i * 10)

println(after) // Left("invalid parameter")


// Left型に適用したい場合
val leftEither: Either[String, Int] = Left("invalid parameter")
val after = leftEither.left.map(s => s"Error! - $s")

println(after) // Left("Error! - invalid parameter")
```

# Try 
- エラー時には例外を保持する
- SuccessとFailureの２つの値を持つ
- 失敗時の方はTrawableで固定されているため、型パラメータは一つ
- Tryで扱う例外は、そのほとんどがリカバリー可能な例外であると考えても問題ありません。扱い方はすごくシンプルで、「例外が発生しうる処理をTry型で初期化する」だけです。


#  例外について
## 例外とは
- 例外というのは、プログラムを実行する上で想定しない挙動を指します。
- 例外は、プログラム側でなんら処理しなかった場合、発生した時点でプログラムを強制終了します。
- エラー内容・サービスへの影響度にもよりますが、プログラムで想定・対処できるものに関しては基本的に例外を捕捉した上で、正常系に戻すことが多いです。逆に「データベースサーバーが稼働を停止している」というようなプログラムで正常に戻しても意味のないエラーに関しては、エラーのままプログラムを終了させる必要があります。
- 通常はgetOrElseメソッドなどを使うことが普通です
- Tryを使った例外処理はあくまでも「プログラム上意図しない挙動や要因により発生する例外を扱う際に利用する」ということを理解しておきましょう。 
- Tryを使う例
    - 外部APIへの接続
    - ユーザーからの意図しない入力


# 修飾子
## sealed
同一ファイル内からのみ継承可能なクラス/トレイトとして定義する

