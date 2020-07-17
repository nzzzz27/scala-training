object July17 {
  def main(args: Array[String]): Unit = {
    println("Either, Try Test")
    println(values.foreach(i => println(i))
    
    println("//--- Either,Try Q1 回答 ---")
    println(s"Aの型は: $eitherTryQ1_A")
    println(s"Bの型は: $eitherTryQ1_B")

    //println("//--- Either,Try Q2 回答 ---")
    //println("説明:" + eitherTryQ2)
  }
  
  // --- either, Try Test ---
  val rightA: Either[String, Int] = Right(10)
  val leftB : Either[String, Int] = Left("あああ")

  val values = for {
    val1 <- rightA 
    val2 <- leftB
  } yield {
    val1 * val2
  }
  
  // -- Either, Try Questions ---

  /*
     エディタ、IDE、sbtなどを使わずに、以下の構文でのEitherの型を答えてください。ただし、Eitherのソースを読むのは可とします。
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
  */

  /*
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
  
  val eitherTryQ1_A = ""
  val eitherTryQ1_B = "User型"
  

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

  /*
    //メモ
    val v = for {
      val1 <- either1
      val2 <- either2
      val3 <- either3
    } yield {
      //Right(200) * Left("Not Found") * Right(100)
      val1 * val2 * val3
    }
    
    //v = Left("Not Found") 

    v.foreach(i => println(i))
    //i = ()
  */
  
 val eitherTryQ2: String = "出力結果は「()」(= Unit型)となる。処理の流れは、1. val1, val3はInt型、val2はString型となりyield内の式が計算不可能となるため、定数vには「Left('Not Found')」が代入される。2. 定数vの値をforeach" 

}
