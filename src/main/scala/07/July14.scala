object July14 {
  def main(args: Array[String]) = {
    println("//--- Map Question ---")
    println(mapQuestion5_2)
    println(mapQuestion5_3)

    println("//--- Tuple Test ---")
    println(s"tupleTest: ${1 -> "Tanaka" -> "student"}")

    println("//--- case calss Test ---")
    println(caseClassTest1)
    println(caseClassTest2)
    
    println("//--- case class & tuple Questions ---")
    println(s"caseClassTupleQuestion1: ${companyInfo}")
    println(s"caseClassTupleQuestion2: ${caseClassTupleQuestion2}")
    println(s"caseClassTupleQuestion3: コード参照")
    println(s"caseClassTupleQuestion4: コード参照")
    println(s"caseClassTupleQuestion5: ${caseClassTupleQuestion5}")
  }
  
  //Q5
  //以下のようなSeqがあります。
  val numbers = Seq(1,2,3,4,5)
  //これらを、以下のように2で割った余りでグループ分けしたMapに変えるには、どうしたらいいでしょうか？
  Map(
    1 -> Seq(1, 3, 5),
    0 -> Seq(2, 4)
  )
  def mapQuestion5_2: Map[Int, Seq[Int]] = {
    numbers.groupBy(num => if (num % 2 == 0) 0 else 1)       
  }

  def mapQuestion5_3: Map[Int, Seq[Int]] = {
    Map(  
      1 -> numbers.filter(_ % 2 == 1),
      0 -> numbers.filter(_ % 2 == 0)
    )
  }

  // --------- case class test ----------
  def caseClassTest1: Any = {
    case class Person (
      name: String,
      age : Int
    )
    
    val person = Person("Tanaka", 20)
  }

  def caseClassTest2 {
    // ① abstruct: サブクラスでメゾットを実装させる
    case class SNS
    
    // ② SNSクラスを承するケースクラスを作成
    case class Twitter(id: String, follow: Int, follower: Int)     extends SNS
    case class Line(id: String, friends: Int, group: Int)          extends SNS
    case class Facebook(id: String, friends: Int, company: String) extends SNS

    // ③ 引数（sns名）に合った内容を出力する関数
    // s""で囲っているから、戻り値の型はString
    // SNSクラス（を継承している）ケースクラス
    def summarySNS(sns: SNS): String = {
      sns match {
        case Twitter(id, follow, follower)   =>
          s"Twitter  -> ID: ${id}, フォロー: ${follow}人, フォロワー: ${follower}人"
        case Line(id, friends, group)        =>
          s"Line     -> ID: ${id}, 友達: ${friends}人, グループ数: ${group}"
        case Facebook(id, friends, company)  =>
          s"Facebook -> ID: ${id}, 友達: ${friends}人, 会社名: ${company}"
      }
    }
    
    // ④ ② で作成したケースクラスをインスタンス化
    val twitter  = Twitter("10000", 130, 75)
    val line     = Line("10000", 100, 20)
    val facebook = Facebook("10000", 300, "nextbeat")
    
    // ⑤ ④ のケースクラスを引数に入れて、関数を実行
    println(summarySNS(twitter))
    println(summarySNS(line))
    println(summarySNS(facebook))
  }
  
  // ------ case class & tuple Questions ------
  /* 「会社」を表す値を、tuple, case classを使って表現しましょう。
      会社には、
      1 会社名
      2 電話番号
      3 住所(任意項目) が含まれるように定義する。
   */
  
  // Q1
  // 1. 会社を表す値を持ったtupleを定義してください。
  val companyInfo: (String, String, String) = ("nextbeat", "03-5423-6131", "東京都渋谷区恵比寿4丁目9-10")
  
  //Q2
  //2. 1で定義したtupleから、電話番号の値が含まれる値を取得してください
  def caseClassTupleQuestion2: String = {
    companyInfo._2
  }

  //Q3
  //3. 会社を表すパラメータをもったCompanyというcase class を定義してください
  case class Company(
    name: String,
    phone: String, 
    address: String
  )

  //Q4
  //4. 3で定義したCompanyクラスから、インスタンスを生成してください。
  val nextbeatCompanyInfo = Company("nextbeat", "03-5423-6131", "東京都渋谷区恵比寿4丁目9-10")

  //Q5
  //5. 4で生成したインスタンスのcopyメソッドを使って会社名を置き換えてください。
  def caseClassTupleQuestion5 = {
    nextbeatCompanyInfo.copy(name = "ネクストビート") 
  }

}
