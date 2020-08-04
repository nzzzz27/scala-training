/* Scala 応用問題
 * https://nextbeat-external.atlassian.net/wiki/spaces/DEVNEWEDU/pages/1258914223/2-3-1.+Scala
*/

//ExecutionContextを使えるように。非推奨。（次の見出し参照）
import scala.concurrent.ExecutionContext.Implicits.global

//Success, Failureを使えるようにする
import scala.util.{Failure, Success}

//期間の設定
import scala.concurrent.duration._

object Aug04 {
  def main(args: Array[String]): Unit = {
    println("//--- Question 11 ---")
    val monica: Person = Person(1L, "Monica", Some("female"))
    val tracy : Person = Person(2L, "Tracy", Some("female"))
    val justin: Person = Person(3L, "Justic", Some("male"))
    println(question11(Seq(monica, tracy, justin)))

    println("//--- Question12 ---")
    println(question12("1,2,3,4,hello"))

    println("//--- Question13 ---")
    println(question13(Future(Some("nextbeat"))))
    println(question13(Future(None)))

  }
  
  //  10:45 ~
  /*  Q11 
      Seq[Person]型であるpeopleを引数として受け取り、全てのgenderをNoneにするquestion11を作成してください
  */
  
  // ~ 11:00
  // Userの定義
  case class Person(id: Long, name: String, gender: Option[String])
  
  def question11(people: Seq[Person]): Seq[Person] = {
    people.map(_.copy(gender = None))    
  }
  
  // 11:03 ~
  /*  Q12
   *  , 区切りで生成された文字列numStrを引数として受け取り、 ,で区切られた各文字が数字であればSome[Int], 数字でなければNoneにして返すメソッドquestion12を作成してください
      例: "1,2,3,4,hello" → Seq(Some(1),Some(2),Some(3),Some(4),None)
  */
  
 // ~ 11:28 / 12:20 ~ 
  
  //うまくいかない
  //def question12(numstr:string): any = {
  //  
  //  val stroptseq: seq[option[string]] = numstr.split(",").map(str => some(str))
  //  
  //  for (
  //    stropt <- stroptseq
  //  ) yield {
  //    stropt.map(str => try {
  //      str.toint 
  //    } catch {
  //      case ex: numberformatexception => none
  //    })
  //  }
  //  
  //}
    
  // ~ 17:40 成功
  def question12(numStr:String): Seq[Option[Int]] = {
    
    for (
      str <- numStr.split(",")
    ) yield {
      try {
        Some(str.toInt)
      } catch {
        case ex: NumberFormatException => None
      }
    }

    //別解１
    //numStr.split(",").map(x =>
    //  Try(x.toInt).toOption
    //)

    //別解２
    //numStr.split(",").map(_.toIntOption)
    
  }
  /*  Q13 
   *  Future[Option[String]]型であるfOpsを引数として受け取り中の結果がSomeであればその文字列を、Noneであれば "なし" と標準出力させるquestion13を作成してください
  */
  //def question13(fOps: Future[Option[String]]): Future[Unit] = {
  //  
  //  //Future型の値が確定していない
  //  fOps match {
  //    case Some(_) => Future(_)
  //    case None      => Future("なし")
  //  }    
  //  
  //} 

}
