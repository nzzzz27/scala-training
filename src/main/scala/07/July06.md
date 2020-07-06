# sbt 
- scala用のビルドツール


# Option

- 型の一種
- return Some[T] or None
- 使用例：「存在しない」「条件に合うものを発見できない」を探すとき


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


## isDifined method
- Optionの有無をBooleanで返す 
- Some[T]の場合にtrue


## map method
- 関数（式）を引数に取り、SomeかNoneを返す
- 値が存在する場合、Some()の中に関数結果を入れて返す
- 引数は、Optionの内包する型と同一である必要がある
- 返り値は任意の型


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
-  引数は、A => Option[B] の型
-  返り値の型は、Option[B] 


### mapとflatMapの違い
引数が、A => Option[B] のとき

mapの返り値：Option[Option[B]] 
flatMapの返り値:Option[B] 



