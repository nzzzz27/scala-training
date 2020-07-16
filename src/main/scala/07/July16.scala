object July16 {
  def main(args: Array[String]): Unit = {
    println("//--- case class, tuple test ---")
    //println(caseClasstest)

    println("//----case class, tuple ---")  
    println(s"case class, tuple Q1: $companyInfo")
    println(s"case class, tuple Q2: $companyInfo_phone")
    println(s"case class, tuple Q4: $nextbeat")
    println(s"case class, tuple Q5: $nextbeat2")

    println("//--- trait, abstract class Question ---")
    println(s"直方体: $rectangularVolume")

  }
  
  //--- case class tuple ---
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
  abstract class Shape(val height: Int) {
    def calculate(): Int
  }

  class Rectangular(height: Int, width: Int, depth: Int) extends Shape(height) {
    def calculate(): Int = {
      width * height * depth
    }  
  }

  val rectangularVolume = new Rectangular(10, 20, 30)
}
