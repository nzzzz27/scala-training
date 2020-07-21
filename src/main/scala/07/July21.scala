// Futureを使えるようにimport
import scala.concurrent.Future

// おまじないだと思って無視してください
import scala.concurrent.ExecutionContext.Implicits.global

object July21 {

  def main(args: Array[String]): Unit = {
    println("//--- Future Test ---")
    //println(futureTest1)
    println(futureTest2)
  
  

  }

  //--- Future Test ---
  
  //同期処理の例
  def futureTest1(): Unit =  {
    // Threadは処理の実行単位
    // 今回でいえば、この処理を1000ミリ秒止めるという処理になる
    (1 to 10).foreach { i =>
      Thread.sleep(1000)
      println(s"A: $i")
    }
  
    // 2秒ずつとめてから出力
    (11 to 20).foreach { i =>
      Thread.sleep(2000)
      println(s"B: $i")
    }
  }
  
  //非同期処理
  def futureTest2(): Unit = {

    Future {
      (1 to 10).foreach { i =>
        Thread.sleep(1000)
        println(s"C: $i")
      }
    }
  
    Future {
      (11 to 20).foreach { i =>
        Thread.sleep(2000)
        println(s"D: $i")
      }
    }
  }

}
