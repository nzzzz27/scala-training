object July16 {
  def main(args: Array[String]): Unit = {
    println("//--- case class, tuple test ---")
    println(caseClasstest)

    println("//----case class, tuple ---")  
    println(s"case class, tuple Q1: $companyInfo")
    println(s"case class, tuple Q2: $companyInfo_phone")
    println(s"case class, tuple Q4: $nextbeat")
    println(s"case class, tuple Q5: $nextbeat2")

    println("//--- trait, abstract class Question ---")
    println(s"直方体: $rectangularVolume")
    println(s"四角錐: $fourSidedPyramidVolume")
    
    println("//--- Either, Try Test --")
    println(s"Either定義１: $leftEither, $rightEither")
    println(s"leftEither.left.get:  ${leftEither.left.get}")
    println(s"rightEither.left.getOrElse(0):  ${rightEither.left.getOrElse(0)}")
    println(s"rightEither.left.map(i => i):  ${rightEither.left.map(i => i)}")
    println(s"leftEither.right.getOrElse(0):  ${leftEither.right.getOrElse(0)}")
    println(s"leftEither.right.map(i => i):  ${leftEither.right.map(i => i)}")
    println(tom.name)

    println("//--- Either, Try Question --")
    println(s"eitherTryQuestion1_A: $eitherTryQuestion1_A")
    println(s"eitherTryQuestion1_B: $eitherTryQuestion1_B")

  }
  
  val caseClasstest = Company("nextbeat", "03-5423-6131", None)

  //---- case class, tuple  Questions ----
  // Q1 
  // 会社を表す値を持ったtupleを定義してください。
  // 会社名は nextbeat / 電話番号は 03-5423-6131 / 住所は 東京都渋谷区恵比寿4丁目9-10
  val companyInfo: (String, String, Option[String]) = ("nextbeat", "03-5423-6131", Some("東京都渋谷区恵比寿4丁目9-10"))
  
  // Q2
  // 1で定義したtupleから、電話番号の値が含まれる値を取得してください
  val companyInfo_phone: String = companyInfo._2
  
  // Q3
  // 会社を表すパラメータをもったCompanyというcase class を定義してください
  case class Company (
    name   : String, 
    phone  : String, 
    address: Option[String]
  )
  
  // Q4
  // 3で定義したCompanyクラスから、インスタンスを生成してください。
  // 会社名は nextbeat / 電話番号は 03-5423-6131 / 住所は 東京都渋谷区恵比寿4丁目9-10
  val nextbeat = Company("nextbeat", "03-5423-6131", Some("東京都渋谷区恵比寿4丁目9-10"))
  
  // Q5
  // 4で生成したインスタンスのcopyメソッドを使って会社名を置き換えてください。
  val nextbeat2 = nextbeat.copy(name = "ネクストビート")
  

  // --- trait, abstract class Question ---
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

  val rectangularVolume     : Int = Rectangular(8, 6, 6).calculate
  val fourSidedPyramidVolume: Int = FourSidedPyramid(8, 6, 6).calculate
  
  // --- Either test ---

  val leftEither : Either[String, Int] = Left("string") //Left(string)
  
  val rightEither: Either[String, Int] = Right(100) //Right(100)
  case class Person(name: String, age: Int)
  val tom = Person("Tom", 20)


  // --- Either, Try Questions ---
  //Q1
  /*
    エディタ、IDE、sbtなどを使わずに、以下の構文でのEitherの型を答えてください。ただし、Eitherのソースを読むのは可とします。
    
    Either[A, B]の、AとBをそれぞれ答えられたらOKです。
       
    定義
    case class User(id: UserId, name: String)
    case class ExceptionMessage(message: String)

    object LoginApplication {
      def login(id: UserId): Either[String, User] = {
        // implement
      }
    }
    
    回答対象コード
    // val loginRes = Either[String, User]型の値
    // 
    val loginRes = login(UserId(1))
    loginRes.left.map(s => ExceptionMessage(s)).right.map(u => u.id)
  */
  val eitherTryQuestion1_A: String = "A: "
  val eitherTryQuestion1_B: String = "B: "
  
  //loginRes.left         : NoSuchElementException
  //loginRes.left.map(s ~): Right()
  //ExceptionMessage(s).right.map(u ~):


}
