
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
