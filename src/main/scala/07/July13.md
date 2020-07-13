# Map (July10~)
- 検索したいキーワードなどをKeyにしておくと検索する際の計算が1回( O(1) )で高速ですが、条件に合致した値を探すために最大でN回の処理を行わないといけません。


## Map methods list(July10~)
- .contains(key)
  - return value: true / false
  - 指定したキーがMapに存在するかどうか

- .isDefinedAt(key)
  - return value: true / false
  - 指定したキーがMapに存在するかどうか

- .mapValues()
  - return value: 値を変更した新しいMap

- keys
  - return value: Set(key)
  - Mapに含まれるキーの一覧を取得

- values
  - return value: Iterable[String] 
  - Mapに含まれる値の一覧を取得

- filterKeys(抽出条件)
  - return value: Map

- toMap
  - return value: Map[key, val]
  - タプル2つのSeqからMapに変換
  - SeqをMapに変換　など
  - 以下使用例

```
val userTupleSeq = Seq((1, "鈴木"), (2, "佐藤"), (3, "山田"))

userTupleSeq.toMap

//result
Map(1 -> 鈴木, 2 -> 佐藤, 3 -> 山田)
```
  
- groupBy(関数)
  - return value: Map
  - 関数 f の適用結果が同じキーとなる要素をグループ化した Map  
  - 以下使用例

```
val numbers = Seq(1,2,3,4,5)

//num         : Seqの中の値一つずつ
//case式      : グループ分けの条件
//case式の結果: keyを作成
def mapQuestion5_2(): Map[Int, Seq[Int]] = {
   numbers.groupBy(num => {
     num match {
       case num if num % 2 == 0 => 0
       case _                   => 1
     }
   })
}

//result
Map(1 -> List(1, 3, 5), 0 -> List(2, 4))
```


# Iterable[Int]について
ref: https://docs.scala-lang.org/ja/overviews/collections/trait-iterable.html  
- 反復可能 (Iterable トレイトはコレクション階層の上から2番目に位置する。
- このトレイトの全メソッドは、コレクション内の要素を1つずつ返す抽象メソッド iterator に基づいている。
- Iterable では、Traversable トレイトの foreach メソッドも iteratorに基づいて実装されている。

# tuple 
ref: https://docs.scala-lang.org/ja/tour/tuples.html  
- tuple(タプル)とは、複数のフィールドをひとまとめにして扱うことができるもの

# Map and tuple
- Mapの「->」は実はタプルを作るためのもの
- 以下は同じ意味

```
Map("りんご" -> 100, "みかん" -> 80, "ぶどう" -> 300)

Map(("りんご", 100), ("みかん", 80), ("ぶどう", 300))
```
