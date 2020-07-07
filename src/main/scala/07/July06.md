# sbt 
scala用のビルドツール



# 関数とメゾットの違い

メゾット：defで始まる構文
関数　　：第一級オブジェクト。オブジェクトとして扱えるので、「変数に代入する」「メゾットや関数の引数として渡す」「メゾットや関数の戻り値として返す」もOK

参考：
https://sites.google.com/site/scalajp/home/documentation/scala-by-example/chapter5
http://www.ne.jp/asahi/hishidama/home/tech/scala/function.html


# Option

- 型の一種
- return Some[T] or None
- 使用例：「存在しない」「条件に合うものを発見できない」を探すとき



## メゾット一覧

-　get
  -　返り値：値
  -  注意　：Opitionの値がNoneの場合は例外が発生するため使用しない

-　getOrElse(引数)
  -　返り値：Optionの値 or 引数の値

-　isEmpty 
  -　返り値：true or false
  -　メモ　：Option[Option[Int]]にも使える。（参照：sample)

- isDefined
  -  返り値：true or false
  -　メモ　：Option[Option[Int]]にも使える。（参照：sample)


- map()
  -　返り値：Some or None
　-　引数　：関数      ex) i => i * 2
　-　仮引数には、mapの返り値（つまりOption型の値 or None)が代入されている
  -  Option[Option[Int]]が渡された場合、Option[Option[int]]で返す


- flatten
  -  返り値：Some or None
  -  Option[Option[Int]]に対して使う

- flatMap()
  -  返り値：Some or None
  -  引数　：関数　　ex) A => Option[B]
  -  Option[Option[Int]]が渡された場合、Option[Int]で返す


## メゾットのいろんな書き方

val intval: Option[Int] = Some(5)
intval.map(i => i * 2)
intval.map(i => {i * 2})
intval.map(_ * 2)
intVal map(i => i * 2)





## get method

- Option型からその包まれた値を取得するメソッド
- 値が存在しない場合は、例外が発生する
- 確実に値が存在すると保証できる場合にのみ使う
- 値が存在しない可能性が存在しない可能性がある時は、getOrElseメゾットを使う



## getOrElse method

- Optionの値が存在している場合(Someの場合)はその値を返却し、存在していない場合(Noneの場合)は、引数として受け取った値を返却



## isEmpty method

- Optionの有無をBooleanで返す
- Noneの場合はtrue
- Some("")のときは、false



## isDefined method

- Optionの有無をBooleanで返す 
- Some[T]の場合にtrue
- Some("")のときは、true



## map method

-　引数：関数（式）
-　返り値：SomeかNone
-  引数は、Optionの内包する型と同一である必要がある
-  返り値は任意の型


```
scala> val intval: Option[Int] = Some(5)
val intval: Option[Int] = Some(5)

// いずれも同じ意味
scala> intval.map(i => i * 2)
scala> intval.map(i => {i * 2})
scala> intval.map(_ * 2)

val res1: Option[Int] = Some(10)
```


## flatten method

- 返り値：Some / None
- Option[Option[A]] のように Option型が入れ子になっている場合に、 平滑化するためのメソッド
- Option が入れ子になるケースは map が受け取る引数が A => Option[B] のような場合に起こり得ます

```
// Some(Some(3))
scala> val intVal: Option[Option[Int]] = Some(Some(3))
val intVal: Option[Option[Int]] = Some(Some(3))

scala> intVal.flatten
val res6: Option[Int] = Some(3)


// Some(Some(None))
scala> val intVal: Option[Option[Int]] = Some(None)
val intVal: Option[Option[Int]] = Some(None)

scala> intVal.flatten
val res8: Option[Int] = None
```


## flatMap method

-  map + flatten 
-  引数：A => Option[B]
-  返り値：Option[B] 



### mapとflatMapの違い

引数が、A => Option[B] のとき

mapの返り値：Option[Option[B]] 
flatMapの返り値:Option[B] 


##
in many situations, you can ignore the difference between functions and methods and just think of them as the same thing, but occasionally you may run into a situation in which the difference matters. 



