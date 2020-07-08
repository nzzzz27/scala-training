# Seq メゾット一覧（July07の続き）

- init
  - 返り値：末尾を除いたコレクション or 例外（空の場合）

- tail
  - 返り値：先頭を除いたコレクション or 例外（空の場合）


- 中置演算子
  - +:
    - 左辺を右辺のコレクション先頭に追加
    - 例　　　：値 +: List or Seq or Nil
    - 具体例１：4 +: Seq(1, 2, 3) =  List(4, 1, 2, 3)
    - 具体例２：Seq(10) +: Seq(20) = List(List(10), 20)

  - :+
    - 右辺を、左辺のコレクションの末尾に追加
    - 例　　　：List or Seq or Nil :+ 値
    - 具体例１：Seq(1, 2, 3) :+ 4 = List(1, 2, 3, 4)
    - 具体例２：Seq(10, 20, 30) :+ Nil = List(10, 20, 30, List())

  - ++
   `- 左右の辺のコレクションを連結
    - コレクション ++ コレクション

  - Nil
     - = List()
     - Nil +: List(1, 2) = List(List(), 1, 2)

- foldLeft(initial value)((accumulator, value) => expression )
  - 具体例１：Seq(1, 2, 3, 4).foldLeft(0: Int)((acc: Int, n: Int) => acc + n)
  - 一番初めは、accumulator = initial value
  - value = コレクション内の値１つずつ
  - ①expressionの結果がaccumulatorに代入される　②次のコレクション内の値を用いてexpressionの処理をする

- foldRight(initial value)((accumulator, value) => expression )
  - foldLeftの逆

- reduce((accumulator, value) => expression )
  - 処理結果 or 例外

- min
  - 返り値：最小の値 or 例外
  - "a" < "あ"
  - "b" < "あ"
  - StringとIntの混合リストに対してはエラーとなる

- max
  - 返り値：最大の値 or 例外
  - "a" < "あ"
  - "b" < "あ"
  - StringとIntの混合リストに対してはエラーとなる

- match式
  - 条件にあう値を取り出す
