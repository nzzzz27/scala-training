# [Try](https://github.com/takapi327/learners-dictionary/blob/master/scala/summary/Either-Try.md#Try)
例外処理に使用する型。  
成功時にはSuccess, 失敗時にはFailureを返す。  
Failureの値の型は、Thorwableに限定されているため、型引数は１つしか取らない。


## Tryの定義
```
sealed abstract class Try[+T]

final case class Success[+T](value: T) extends Try[T]

final case class Failure[+T](exception: Throwable) extends Try[T]
```

## Tryの使い方
```
//Tryを使えるようにする
import scala.util.Try

//Successの場合
val tryString: Try[String]   = Try("ABC")  //Success(ABC)
val trySeq   : Try[Seq[Int]] = Try(Seq(1, 2, 3, 4))  //Success(List(1, 2, 3, 4))

//Failureの場合
val tryInt   : Try[Int] = Try(throw new RuntimeException("to be cought"))  //Failure(java.lang.RuntimeException: to be cought)
```

## [class Throwable](https://docs.oracle.com/javase/jp/7/api/java/lang/Throwable.html)
全てのエラー/例外のスーパークラス。  


# Either 
## `.fold(式)`
Rightの値に適用するとき、第一引数の処理、Leftの値に適用するとき第二引数の処理を適用して返却。  
```
Right(10).fold(
  b => "error",
  a => a * 10
)
```

## 実装例
例１）簡略化されたログイン実装の例
```
case class User(id: UserId, name: String)
case class ExceptionMessage(message: String)
object LoginApplication {
  def login(id: UserId): Either[String, User] = {
    // implement
  }
}

val loginRes = login(UserId(1))
loginRes.left.map(s => ExceptionMessage(s)).right.map(u => u.id)
```
 1. val loginRes = login(UserId(1))について
    loginは、Either[String, User]型。
    UerId(1)は、case class Userの引数。
 
 2. loginRes.left.map(s => ExceptionMessage(s)).right.map(u => u.id)
    
    loginResがRightの場合：
    loginRes.left.map(s => ExceptionMessage(s)) ... Right(値：User型)
    Rightに対して、.right.map(...をすると、Rightの中の値を加工した結果を返すので、User型

 3. loginResがLeftの場合 ：
    loginRes.left.map(s => ExceptionMessage(s) ... ExceptionMessage型
    Leftに対して、.right.map(...)をすると、mapの中の式は無視してLeftの値をそのまま返すので、ExceptionMessage型
     


# 定義のコードの読み方

例：`.fold`
```
def fold[C](fa: A => C, fb: B => C): C = this match {
  case Right(b) => fb(b)

  case Left(a)  => fa(a)
}
```
実際に使うときの書き方は、この部分。
```
def fold[C](fa: A => C, fb: B => C): C
```
上記の内部の動きを表しているのが、=以降のmatch式部分。


