# tuple
- tuple(タプル)は、複数のフィールドをひとまとめにして扱うことができます。
- 複数型の組み合わせを、2〜22個作ることができる(Scalaの標準ライブラリで定義されているのが22まで)
- タプルのN番目の要素にアクセスするときは、`._N`と書く（Nは１スタート）
- タプルの分解にはmatch式を使うことが多い
- どの値が何番目に入っているか一目でわかりにくいため、複数人が関わる開発ではミスを生む原因になる。　　


使用例）

```
val sample1: (Int, Int, Int) = (10, 20, 30)

val sample2: (String, Int) = ("abc", 100)
```

## 使い方
###インスタンスの生成  
```
scala> val persons = ("Taro", "Jiro")
persons: (String, String) = (Taro,Jiro)
```

###パラメータ取得
tupleの要素がN個存在するとき、それぞれの要素にアクセスするには`tuple._N`
```
scala> persons._1
res0: String = Taro
```

###いろんな書き方
全てのタプルは、以下のようにも書ける。
```
Tuple4(1, 2, 3, 4)

Tuple(1, 2)
```


1つの要素からなるタプルは、明示すれば使える。
```
//同じ意味
Tuple1("apple")

Tuple("apple")
```


2つの要素からなるタプルはPairという特別なクラス（オブジェクト）がある。ので、以下の書き方もできる。
```
//同じ意味
(1, "apple")

1 -> "apple"

Pair(1, "apple")

Tuple2(1, "apple")

```  

3つの要素からなるタプルは、Tripleという特別なクラス（オブジェクトがある）。ので、以下の書き方もできる。
```
//同じ意味
(1, 2, 3)

Triple(1, 2, 3)

Tuple3(1, 2, 3)
```

参考サイト：http://www.ne.jp/asahi/hishidama/home/tech/scala/tuple.html  


# import文
- 2種類存在
  - single type: クラスやオブジェクトを直接インポートする
  - on demand  : パッケージ配下の全てのメンバをインポートする

```
import java.time.LocalTime
```
参照：

Javaのパッケージ  
Java Platform, Standard Edition8: 
https://docs.oracle.com/javase/jp/8/docs/api/overview-summary.html#overview.description

Scalaのパッケージ  
Scala Standard Library  
https://www.scala-lang.org/api/current/index.html


# case class
- 独自の型が持てる
- 便利なメソッドやコンパニオンオブジェクト(クラスと同名のオブジェクト)が自動的に生成され使えるようになる
- 不変なデータを作るのに適しています
- インスタンス化は不要（applyメゾットを標準保有しているため）
- フィールドは、自動的に`val`として宣言される


## 使い方
定義
```
//パラメータは、自動的にval（定数）として宣言される
//パラメータの上書きにはcopyメゾットが必要

case class クラス名(
  パラメータ名: 型
)

/*
 case class クラス名(
  フィールド/コンストラクタ
 ) 
*/
```

定義の例
```
//example1
case class Person(
  name: String, 
  age: Int
)
defined class Person

scala> val person = Person("Tanaka", 20)
person: Person = Person(Tanaka,20)


//example2
//デフォルト値の設定もできる
case class Person2(
  name: String = "Tanaka", 
  age : Int = 20
)
defined class Person

scala> val person2 = Person2()
person: Person2 = Person2(Tanaka,20)
```


パラメータ取得
```
scala> person.name
res0: String = Tanaka

scala> person.age
res1: Int = 20
```

## 自動生成される便利メゾットたち
- apply()
  - インスタンスの生成に暗黙的に使われている

- unapply()
  - return value: Option[A] / Option[(A, B)]
  - パラメータを取り出し、別のインスタンスを生成することのできるメソッド
```
//example1
  case class Person(
    name: String,
    age: Int
  )
  defined class Person

  scala> val person = Person("Tanaka", 20)
  person: Person = Person(Tanaka,20)

  scala> Person.unapply(person)
  res2: Option[(String, Int)] = Some((Tanaka,20))

//example2 
  case class Person ()
  val person = Person()

  Person.unapply(person) 
  true
```

- copy()
toString()
equals()
hashCode()



## クラスとの違い
class  
- 値が同じでも別インスタンスから、２つのインスタンスは`=`ではない。
- 同値性の変更には、javaのメゾットであるhashcodeとequalsをoverrideする必要がある。
- インスタンス化にはnewが必要

```
case class Person(
  name: String,
  age:  Int
)

val person = Person("Tanaka", 20)
```

case class
- 値が同じ別インスタンスは`=`になる。
- コンパニオンオブジェクトとしてapply()が標準定義されているので、newでインスタンスする必要がない



## 参考サイト
- https://docs.scala-lang.org/ja/tour/case-classes.html
- http://www.ne.jp/asahi/hishidama/home/tech/scala/class.html
- https://qiita.com/4245Ryomt/items/ae1468e634523c83d571
