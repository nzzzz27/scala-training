import scala.util.{Try, Success, Failure}
object July20 {

  def main(args: Array[String]): Unit = {
    println("//--- Either, Try Test ---")
    println(tryString)
    println(trySeq)
    println(tryInt)
    println(eitherTest)

    println("//--- Either, Try Q1 ---")
    println("A: ExceptionMessage型")
    println("B: User型")

    println("//--- Either, Try Q2 ---")
    println(
      """
      出力結果は「()」(= Unit型)となる。処理の流れは、
      1. val1, val3はInt型、val2はString型となりyield内の式が計算不可能となるため、定数vには「Left('Not Found')」が代入される。
      2. Either型のforeachの定義により、Leftにforeachを適用した場合は「空の値()」を返すため。
      """.stripMargin)

    println("//--- Either, Try Q3 ---")
    println(eitherTryQuestion3)

    println("//--- Either, Try Q4 ---")
    println("メゾットを適用する対象がLeftの場合第一引数の処理を適用、Rightの場合は第二引数の処理を適用して返却する。")

    println("//--- Either, Try Q5 ---")
    println("メゾットを適用する対象がFailureの場合Noneを、Successの場合はSome()を返す。")
  }

  // Either, Try Test 
  
  val tryString: Try[String]   = Try("ABC")
  val trySeq   : Try[Seq[Int]] = Try(Seq(1, 2, 3, 4))
  val tryInt   : Try[Int] = Try(throw new RuntimeException("to be cought"))  
  
  val eitherTest = Right(10).fold(
    b => "error",
    a => a * 2
  )
  
  def sample(a: Int, b: Int): Int = a + b
  sample(1, 2)

//    def fold(fa: (Int, Int) => String, fb: Int => String): String = ???
//  
//    val a = 1
//    val b = 2
//   
//    fold(
//      (a: Int, b: Int) => {
//        a.toString + b.toString
//      },
//      (b: Int) => {
//        a.toString
//      }
//    )
  
  // Eitherの発展編
  case class User(name: String)
  case class LoginError(message: String)
  
  object LoginService {
    // 実際の処理は今回は割愛
    def login(name: String, password: String): Either[LoginError, User] = ???
  }
  
  // Either, Try Questions 
  
  /* Q1
   * エディタ、IDE、sbtなどを使わずに、以下の構文でのEitherの型を答えてください。ただし、Eitherのソースを読むのは可とします。
     Either[A, B]の、AとBをそれぞれ答えられたらOKです。

     定義
     ```
     case class User(id: UserId, name: String)
     case class ExceptionMessage(message: String)
     object LoginApplication {
       def login(id: UserId): Either[String, User] = {
         // implement
       }
     }
     ```
     回答対象コード
     ```
     val loginRes = login(UserId(1))
     loginRes.left.map(s => ExceptionMessage(s)).right.map(u => u.id)
     ```
    */
    
    /* メモ
     * 
     * 1. val loginRes = login(UserId(1))について
     *    loginは、Either[String, User]型。
     *    UerId(1)は、case class Userの引数。
     * 
     * 2. loginRes.left.map(s => ExceptionMessage(s)).right.map(u => u.id)
     *    
     *    loginResがRightの場合：
     *    loginRes.left.map(s => ExceptionMessage(s)) ... Right(値：User型)
     *    Rightに対して、.right.map(...をすると、Rightの中の値を加工した結果を返すので、User型
     *
     *    loginResがLeftの場合 ：
     *    loginRes.left.map(s => ExceptionMessage(s) ... ExceptionMessage型
     *    Leftに対して、.right.map(...)をすると、mapの中の式は無視してLeftの値をそのまま返すので、ExceptionMessage型
     *     
    */
  
   /* Q2 
    * 以下の結果がどうなるか、説明してください。
    * ```
    * val either1: Either[String, Int] = Right(200)
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
      ```
    */
    
   /* メモ
    * 
    * val v = for {
    *    val1 <- either1
    *    val2 <- either2
    *    val3 <- either3
    *  } yield {
    *    //Right(200) * Left("Not Found") * Right(100)
    *    val1 * val2 * val3
    * }
    *
    * //val v = Left("Not Found")
    *
    * v.foreach(i => println(i))
    *
    * //Either型のforeachの定義
    * def foreach[U](f: B => U): Unit = this match {
    *   case Right(b) => f(b)
    *   case _        =>
    * }
    * 
    */
    
    /* Q3 
     * Try[Int]を、Either[String, Int]に変換するコードを書いてください。
     * Eitherの左辺には、Tryが持っている Throwable.getMessageの値を渡してください。
     * Try[Int]の定義で１行、変換で１行の計２行記述してください。
    */
    
    def eitherTryQuestion3(): Either[String, Int] = {
      val tryInt: Try[Int] = Try(100)
      tryInt.toEither.left.map(s => s.getMessage)
    }
    
    /* Q4 
     * Eitherに定義されている以下のメソッドが、どういう処理をしているのか、講師に説明してください。
     * ```
     * def fold[C](fa: A => C, fb: B => C): C = this match {
          case Right(b) => fb(b)
          case Left(a)  => fa(a)
       }
       ```
    *
    */
    
   /* Q5 
    * Try.toOptionがどういう実装になっているか説明してください。
   */
}

