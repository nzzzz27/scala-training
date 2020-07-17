# Either, Try 
```
// for式の中の最後の値が返却される
def eitherTryTest1 = {
  val a1: Either[String, Int] = Right(10)
  val a2: Either[String, Int] = Left("None")

  for {
     val1 <- a1
     val2 <- a2
  } yield {
     println(val1) 
  }

}
```

```
//Int * Stringは不可能なので、例外（Left"None")が返ってくる

def eitherTryTest1 = {
  val a1: Either[String, Int] = Right(10)
  val a2: Either[String, Int] = Left("None")

  for {
     val1 <- a1
     val2 <- a2
  } yield {
     val1 * val2
  }

}
```
