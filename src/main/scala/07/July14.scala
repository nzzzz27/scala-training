object July14 {
  def main(args: Array[String]) = {
    println("//--- Map Question ---")
    println(mapQuestion5_2)

    println("//--- Tuple Test ---")
    println(s"tupleTest: ${1 -> "Tanaka" -> "student"}")

    println("//--- case calss Test ---")
    println(caseClassTest1)
    println(caseClassTest2)
  }
  
  //Q5
  //以下のようなSeqがあります。
    val numbers = Seq(1,2,3,4,5)
  //これらを、以下のように2で割った余りでグループ分けしたMapに変えるには、どうしたらいいでしょうか？
  Map(
    1 -> Seq(1, 3, 5),
    0 -> Seq(2, 4)
  )
  def mapQuestion5_2 = {
    numbers.groupBy(num => if (num % 2 == 0) 0 else 1)       
  }

  // --------- case class test ----------
  def caseClassTest1: Any = {
    case class Person (
      name: String,
      age: Int
    )
    
    val person = Person("Tanaka", 20)
  }

  def caseClassTest2 {
    abstract class SNS
      case class Twitter(id: String, follow: Int, follower: Int)     extends SNS
      case class Line(id: String, friends: Int, group: Int)          extends SNS
      case class Facebook(id: String, friends: Int, company: String) extends SNS
      
      
      def summarySNS(sns: SNS): String = {
        sns match {
          case Twitter(id, follow, follower) =>
            s"Twitter  -> ID: ${id}, フォロー: ${follow}人, フォロワー: ${follower}人"
          case Line(id, friends, group) =>
            s"Line     -> ID: ${id}, 友達: ${friends}人, グループ数: ${group}"
          case Facebook(id, friends, company)  =>
            s"Facebook -> ID: ${id}, 友達: ${friends}人, 会社名: ${company}"
        }
      }
      val twitter  = Twitter("10000", 130, 75)
      val line     = Line("10000", 100, 20)
      val facebook = Facebook("10000", 300, "nextbeat")
      
      println(summarySNS(twitter))
      println(summarySNS(line))
      println(summarySNS(facebook))
  }
  

}
