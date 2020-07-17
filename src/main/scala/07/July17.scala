object July17 {
  def main(args: Array[String]): Unit = {
    println(s"eitherTryQ1_A: $eitherTryQ1_A")
    println(s"eitherTryQ1_B: $eitherTryQ1_B")

    println(val loginRes = login(UserId(1)))
  }
  case class User(id: UserId, name: String)
case class ExceptionMessage(message: String)
object LoginApplication {
  def login(id: UserId): Either[String, User] = {
    // implement 
  }
} 
  // -- Either, Try Questions ---

  /*
   * エディタ、IDE、sbtなどを使わずに、以下の構文でのEitherの型を答えてください。ただし、Eitherのソースを読むのは可とします。
     Either[A, B]の、AとBをそれぞれ答えられたらOKです。

     //定義
     case class User(id: UserId, name: String)
     case class ExceptionMessage(message: String)
     
     object LoginApplication {
       def login(id: UserId): Either[String, User] = {
         // implement
       }
     }
     
     //回答対象コード
     val loginRes = login(UserId(1))
     loginRes.left.map(s => ExceptionMessage(s)).right.map(u => u.id)

     //メモ
     login(UserId(1)) ... loginはEither型。UserIdはcase class User。
     loginRes.left.map(s => ExceptionMessage(s)) ... 
  */
  
  val eitherTryQ1_A: String = ""
  val eitherTryQ1_B: String = ""
}
