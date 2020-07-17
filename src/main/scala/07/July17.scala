object July17 {
  def main(args: Array[String]): Unit = {
    println("Either, Try Test")
    println(s"eitherTryTest1: $eitherTryTest1")

    println(s"eitherTryQ1_A: $eitherTryQ1_A")
    println(s"eitherTryQ1_B: $eitherTryQ1_B")
    println(s"eitherTryQ2: $eitherTryQ2")
  }
  
  // --- either, Try Test ---
  def eitherTryTest1 = {
    val a1: Either[String, Int] = Right(10)
    val a2: Either[String, Int] = Left("None")

    for {
      val1 <- a1 
      val2 <- a2
    } yield {
      println(val1 * val2)
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
     - login(UserId(1))
       loginはEither[String, User]型。UserId(1)はcase class User。 
       UserId(1)と値があるから、Right(User型)となる。
                   // = Right(1)
        = login(UserId(1)) = Right(User)
          = User(id = 1, name = ???)
      
     - loginRes.left.map(s => ExceptionMessage(s))
                   // = ExceptionMessage(NoSuchException)
        = Right(User).map(s => ExceptionMessage(s)) 
          = Right(User).map(User => ExceptionMessage(User))

     - ExceptionMessage(User).right
        = Right(User)

     - loginRes.left.map(s => ExceptionMessage(s)).right.map(u => 
                   // u = NoSuchException
        = u は、Right(User) 

     - .right.map(u => u.id)
          // NoSuchException.id ?????
        = Right(User).id 
          = 1
  */
  
  val eitherTryQ1_A: String = "String (NoSuchException)"
  val eitherTryQ1_B: String = "User"
  

  /*
  Q2 
  以下の結果がどうなるか、説明してください。
  val either1: Either[String, Int] = Right(200)
  val either2: Either[String, Int] = Left("Not Found")
  val either3: Either[String, Int] = Right(100)
  
  val v = for {
    val1 <- either1
    val2 <- either2
    val3 <- either3
  } yield {
    val1 * val2 * val3
  }
  
  v.foreach(i => println(i))
  */
  
 val eitherTryQ2: String = "" 

}
