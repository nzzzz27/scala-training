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
//._2 = tupleの２番目の値 = 値段
//._2が同一のtupleごとに、Mapの中でグループ化している

val fruits = Map("りんご" -> 100, "みかん" -> 80, "ぶどう" -> 300)

fruits.map(v => v._1 + "は" + v._2 + "円です")

//result
res0: scala.collection.immutable.Iterable[String] = List(りんごは100円です, みかんは80円です, ぶどうは300円です)
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
