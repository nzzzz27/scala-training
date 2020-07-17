object July16 {
  def main(args: Array[String]): Unit = {
    println("//--- case class, tuple test ---")
      println(caseClassTest)

    println("//----case class, tuple ---")  
    println(s"case class, tuple q1: $companyInfo")
    println(s"case class, tuple q2: $companyInfo_phone")
    println(s"case class, tuple q4: $nextbeat")
    println(s"case class, tuple q5: $nextbeat2")

    println("//--- trait, abstract class question ---")
    println(s"直方体: $rectangularvolume")
    println(s"四角錐: $foursidedpyramidvolume")
    
    println("//--- Either, try test --")
    println(s"Either定義１: $leftEither, $rightEither")
    println(s"leftEither.left.get:  ${leftEither.left.get}")
    println(s"rightEither.left.getOrElse(0):  ${rightEither.left.getOrElse(0)}")
    println(s"rightEither.left.map(i => i):  ${rightEither.left.map(i => i)}")
    println(s"leftEither.right.getOrElse(0):  ${leftEither.right.getOrElse(0)}")
    println(s"leftEither.right.map(i => i):  ${leftEither.right.map(i => i)}")
    println(s"leftEither.right.map(i => i):  ${leftEither.right.map(i => i)}")

    println("//--- Either, try question --")
    println(s"Eithertryquestion1_a: $Eithertryquestion1_a")
    println(s"Eithertryquestion1_b: $Eithertryquestion1_b")

  }
  
  val caseClassTest = Company("nextbeat", "03-5423-6131", None)

  //---- case class, tuple  questions ----
  // q1 
  // 会社を表す値を持ったtupleを定義してください。
  // 会社名は nextbeat / 電話番号は 03-5423-6131 / 住所は 東京都渋谷区恵比寿4丁目9-10
  val companyInfo: (String, String, Option[String]) = ("nextbeat", "03-5423-6131", Some("東京都渋谷区恵比寿4丁目9-10"))
  
  // q2
  // 1で定義したtupleから、電話番号の値が含まれる値を取得してください
  val companyInfo_phone: String = companyInfo._2
  
  // q3
  // 会社を表すパラメータをもったcompanyというcase class を定義してください
  case class Company (
    name   : String, 
    phone  : String, 
    address: Option[String]
  )
  
  // q4
  // 3で定義したcompanyクラスから、インスタンスを生成してください。
  // 会社名は nextbeat / 電話番号は 03-5423-6131 / 住所は 東京都渋谷区恵比寿4丁目9-10
  val nextbeat = Company("nextbeat", "03-5423-6131", Some("東京都渋谷区恵比寿4丁目9-10"))
  
  // q5
  // 4で生成したインスタンスのcopyメソッドを使って会社名を置き換えてください。
  val nextbeat2 = nextbeat.copy(name = "ネクストビート")
  

  // --- trait, abstract class question ---
  abstract class Shape(height: Int) {
    def calculate(): Int
  }

  case class Rectangular(height: Int, width: Int, depth: Int) extends Shape(height) {
    def calculate(): Int = {
      width * height * depth
    }  
  }
  
  case class FourSidedPyramid(height: Int, width: Int, depth: Int) extends Shape(height) {
    def calculate(): Int = {
      width * depth * height / 3
    }
  }

  val rectangularvolume     : Int = Rectangular(8, 6, 6).calculate
  val foursidedpyramidvolume: Int = FourSidedPyramid(8, 6, 6).calculate
  
  // --- Either test ---

  val leftEither : Either[String, Int] = Left("String") //left(String)
  
  val rightEither: Either[String, Int] = Right(100) //right(100)
  case class Person(name: String, age: Int)
  val tom = Person("tom", 20)


  // --- Either, try questions ---
  //q1
  /*
    エディタ、ide、sbtなどを使わずに、以下の構文でのEitherの型を答えてください。ただし、Eitherのソースを読むのは可とします。
    
    Either[a, b]の、aとbをそれぞれ答えられたらokです。
       
    定義
    case class user(id: userid, name: String)
    case class exceptionmessage(message: String)

    object loginapplication {
      def login(id: userid): Either[String, user] = {
        // implement
      }
    }
    
    回答対象コード
    // val loginres = Either[String, user]型の値
    // 
    val loginres = login(userid(1))
    loginres.left.map(s => exceptionmessage(s)).right.map(u => u.id)
  */
  val Eithertryquestion1_a: String = "a: "
  val Eithertryquestion1_b: String = "b: "
  
  //loginres.left         : nosuchelementexception
  //loginres.left.map(s ~): right()
  //exceptionmessage(s).right.map(u ~):


}
