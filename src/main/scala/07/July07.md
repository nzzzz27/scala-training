# mutable & immutable
- コレクションクラスの全ては次のいずれかのパッケージのサブクラス
  - scala.collection
  - mutableのサブクラス
  - immutableのサブクラス
  - generic のサブクラス

- コレクションクラスのほとんど、可変性について3形態ある　
  - scala.collection
  - scala.collection.immutable 
  - scala.collection.mutable 
- 特定の値を書き換えるようなメソッドは その値を直接書き換えるのではなく、その値を書き換えた新たなインスタンスを生成する


## mutable
- 定義した値が変わることがある
- varはmutable


## immutable
- 定義した値が変わることがない
- valはimmutable


# Seq型
複数のデータを格納することができる配列


## メゾット一覧
- map()
  - 引数：関数
  - コレクションの値に変更を加えて、List型として返す

- flatten
  - 入れ子になったSeqの値を、一階層のSeqのまとめる

- find()
  - 引数：Booleanを返す関数
  - 返り値：Some or None
  - 注意：2番目以降の条件合致した値は無視されちゃう..
  - Seqの中身を先頭から順番に受け取って評価している

- filter()
  - 返り値：条件を満たす要素だけで構成されたコレクション or 空のコレクション

- collect { case式 }
　- 引数：case式
　- 返り値：case式にマッチした値のみを集めたコレクション型

- collectFirst{ case式 }
  - 引数：case式
  - 返り値：Some or None
  - 条件にマッチした最初の値だけをOption型で返す

- exists()
 - 引数：関数
 - 返り値：Boolean

- contains()
 - 引数：探したい値
 - 戻り値：Boolean

- empty
 - 返り値：自分と同じ型の空のList

- head
 - 返り値：コレクションの先頭の値 or 例外（エラー）

- headOption
 - 返り値：Some(コレクションの先頭) or None
 - Noneは、コレクションが空の場合

- last
 - 返り値：コレクションの末尾 or None
 - Noneは、コレクションが空の場合

- lastOption()
 - 返り値：Some(コレクションの末尾）or None
 - Noneは、コレクションが空の場合
