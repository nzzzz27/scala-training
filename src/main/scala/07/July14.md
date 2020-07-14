# Map groupBy method
- keyを指定しない場合、Map内の要素数に合わせてkeyのナンバリングを自由でしてくれる。
```
  def mapQuestion5_4 = {
    numbers.groupBy(_ % 2)
  }
  //result
  Map(1 -> List(1, 3, 5), 0 -> List(2, 4))
```




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
### インスタンスの生成  
```
scala> val persons = ("Taro", "Jiro")
persons: (String, String) = (Taro,Jiro)
```

### パラメータ取得
tupleの要素がN個存在するとき、それぞれの要素にアクセスするには`tuple._N`
```
scala> persons._1
res0: String = Taro
```

### いろんな書き方
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




# case class
- 便利なメソッドやコンパニオンオブジェクト(クラスと同名のオブジェクト)が自動的に生成され使えるようになる
- 独自の型が持てる
- 不変なデータを作るのに適しています
- インスタンス化は不要（applyメゾットを標準保有しているため）
- フィールドは、自動的に`val`として宣言される
- classを継承できる
- abstractなどの修飾子は使えないし、継承もできない

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
  - return value: Option[A] / Option[(A, B)] / true
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
  - 既存のインスタンスから、copyメソッド内で渡された値を変更した、新たなインスタンスを生成
  - 別のインスタンスを生成しているだけで、既存のインスタンスの値は変更しない
```
scala> val person1 = Person("Tanaka", 20)
person1: Person = Person(Tanaka,20)

scala> val person2 = person1.copy(name = "Sato")
person2: Person = Person(Sato,20)
```

- toString()
  - case classを綺麗に整形されたString型に変換
```
person.toString()
> Person(Tanaka,20)
```

- equals()
  - インスタンス同士を比較して、すべての値が同一であればtrueが返ります。
  - case classでは、値が同じ別インスタンスは、true(equals)
  - classでは、値が同じ別インスタンスは、false(not equals)
```
scala> case class Person(name: String, age: Int)
defined class Person

scala> val person1 = Person("Tanaka", 20)
person1: Person = Person(Tanaka,20)

scala> val person2 = Person("Tanaka", 20)
person2: Person = Person(Tanaka,20)

scala> person1.equals(person2)
res2: Boolean = true
```

- hashCode()
  - 各フィールドの内容を元にハッシュ値を算出


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


# import文
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




# trait, abstract class
- 継承は、既存のクラスから、新しく作ったクラスに「変数定義」や「メソッド」などを引き継ぐこと
  - スーパークラス: 継承される既存のクラス（親）
  - サブクラス    : 継承した新しく作ったクラス(子)
- 子クラスで定義したメソッドやプロパティは親クラスから呼び出せません。


## 継承方法
- scalaでは、継承元の親を一つしか指定できない。ので、traitがある。
- classがtraitを継承することをmixinという
- 処理の共通化、抽象化をしたいときに使う
 - 抽象化: abstract修飾子。スーパークラスでは定義のみとし、メゾットなどの実装はサブクラスでさせる
- overrideキーワードを使うと、定義の上書きができる


### trait
  - クラスから、インスタンス化を省いた機能
  - パラメータを持つことができません
  - 複数のトレイトをmixinできる 
```
//sample1
trait Namable {
  //ここはフィールド
  val name: String
  def display(): Unit = println(name)
}

//sample2
trait sample 
```

### abstract class
- 修飾子の一つ
- このクラスでのメゾット実装を不可にする
- サブクラスでメゾットを実装する
```
//sample1
abstract sampleClass
```

#### 具体的な使用例
```
// 具体的な実装内容は持っていないが、これで定義してもcompileエラーにならない。
abstract class Person(
  name: String,
  age:  Int  
) {
  def greet(): Unit  
}

// Personを継承した、Japaneseを定義
case class Japanese(
  name: String,
  age:  Int  
) extends Person(name = name, age = age) {
  def greet(): Unit = {
    println("こんにちは")  
  }  
}

// Personを継承した、Americanを定義
// (アメリカの公用語が英語なので、Helloにしました。)
case class American(
  name: String,
  age:  Int  
) extends Person(name = name, age = age) {
  def greet(): Unit = {
    println("Hello")  
  }  
}
```



