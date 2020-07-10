
# Seq 
- 空リストの書き方は複数あり、型指定もできる。
```memo:memo
val l1: List[Int] = Nil
val l2 = Nil:List[Int]
val l3 = List.empty[Int]
val l4 = List[Int]()`
```
- 型指定しない場合、List()はList[Nothing]型の戻り値を指定する
- 型指定しない場合、Nilはscala.collection.immutable.Nil.typeの戻り値を指定する


# Map
- 連想配列（keyとvalueのペア）
- 基本の書き方
```memo: memo
Map(1 -> "鈴木", 2 -> "佐藤", 3 -> "山田")
```

## Mapメゾット一覧
  - .empty
    - 空のMapを作る
    - 指定した型をもつMap: Map.empty[Int, String]

  - Map + (key -> value)
    - Mapに要素を追加する
    - Map + (キー -> 値)

  - Map ++ Map
    - Map同士を連結
    - Map(key -> value) ++ Map(key -> value)

  - Map - key
    - Mapの要素を削除

  - .get(key)
    - return value：Some(value) or None
  
  - getOrElse(key, initial value)
    - return value: value or initial value

 
