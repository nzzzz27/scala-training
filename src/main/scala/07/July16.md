object July16 {
  def main(args: Array[String]): Unit = {
    println("//--- case class, tuple Question")
    println(s"case class & tuple Q1: $companyInfo")
    println(s"case class & tuple Q2: $caseClassTupleQuestion2")
    println(s"case class & tuple Q3: $nextbeat")
    println(s"case class & tuple Q5: $nextbeat2")
  }
  
  // Q1
  // 会社を表す値を持ったtupleを定義してください。
  val companyInfo: (String, String, Option[String]) = ("nextbeat", "03-5423-6131", "東京都渋谷区恵比寿4丁目9-10")
  
  // Q2
  // 1で定義したtupleから、電話番号の値が含まれる値を取得してください
  def caseClassTupleQuestion2: String = {
      companyInfo._2
  }
  
  // Q3
  // 会社を表すパラメータをもったCompanyというcase class を定義してください
  case class Company {
      name   : String, 
      phone  : String,
      address: String
  }

  // Q4
  // 3で定義したCompanyクラスから、インスタンスを生成してください。
   val nextbeat = Company("nextbeat", "03-5423-6131", "東京都渋谷区恵比寿4丁目9-10")

  // Q5
  // 4で生成したインスタンスのcopyメソッドを使って会社名を置き換えてください。
  val nextbeat2 = nextbeat.copy("ネクストビート")
  
  

}
