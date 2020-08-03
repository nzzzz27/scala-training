# Scala 
## 見直しポイント
- インデントは揃っているか
  - 変数定義
  - 型の位置
  - =>の位置

- 後から見返してもわかりやすいコードか
```
//Not Good
numOpt match {
   case Some(n) if n >= 10 => println("A")
   case Some(n) if n <= 4  => println("C")
   case None               => println("D")
    case _                  => println("B")
}

//Good
numOpt match  {
  case Some(x) if(x >= 10)           => "a"
  case Some(y) if(y >=  5 && y <= 9) => "b"
  case Some(_)                       => "c"
  case None                          => "d"
}
``` 

## コレクション
### `.zipWidthIndex`
コレクションの中の値を、index番号付きのタプルで返却する

```
Seq(3, 2, 1).zipWithIndex

//結果
List((3,0), (2,1), (1,2))
```

### `[.distinct](https://github.com/scala/scala/blob/98384fdc53af3524fd6abcd04a509a168d130ae2/src/library/scala/collection/Seq.scala#L204)`
重複する値を削除

```
Seq(1, 2, 3, 1, 2, 3).distinct

//結果
List(1, 2, 3)
```

### `toSet`
Set型に変換する。Setの性質として、重複を許容しない。（重複は削除される）

```
Seq(1, 2, 3, 1, 2, 3).toSet

//結果
Set(1, 2, 3)
```
