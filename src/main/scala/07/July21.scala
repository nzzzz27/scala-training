import java.util.Date


// Futureを使えるようにimport
import scala.concurrent._
import scala.util.{Failure, Random, Success}

// おまじないだと思って無視してください
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

object July21 {

  def main(args: Array[String]): Unit = {
    println("//--- Future Test ---")

    println("%tY-%<tm-%<td %<tH:%<tM:%<tS %<tZ %<tz" format new Date) 

    //println(futureTest1)
    //println(futureTest2)
    println(futureTest3)    
    println(futureTest4)    
    println(futureTest5)   
    
    println("%tY-%<tm-%<td %<tH:%<tM:%<tS %<tZ %<tz" format new Date) 

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
  
  //Futureのメゾット
  def futureTest3() = {
    val future = Future {
      Thread.sleep(1000)
      2 * 2
    }
    
    //Await.readyの第二引数は待ち時間を表す。Infはinfinityのことで、永遠に待つ。  
    val result = Await.ready(future, Duration.Inf)
    //result //Future(Success(4))
    //result.value  //Some(Success(4)): Option[Try[T]] 
    //result.value.get //Success(4): Try[T]
    
    result.value.get match {
      case Success(v) => println(v)
      case Failure(e) => println(e.getMessage)
    }


  }

  def futureTest4() = {
    val future = Future {
      println("Futureの処理開始")
      
      Thread.sleep(3000)
      println("処理完了")
    }
    
    future.onComplete {
      case Success(v) => println("成功")
      case Failure(e) => println("エラー発生" + e)
    }
  }
  
  def futureTest5() = {
    def future1(): Future[Int] = 
      Future {
        Thread.sleep(1000)
        2 * 2
      }
    
    def future2(i: Int): Future[Int] =  
      Future {
        Thread.sleep(1000)
        i * 2
      }
    
    def future3(i: Int): Future[Unit] =
      Future {
        Thread.sleep(1000)  
        println(i * 2)
      }
    
    // 出力されるが、戻り値がFuture[Future[Future[Unit]]]になってしまうので書き換える
    val result = future1 map { n =>
      //n = 2 * 2
      future2(n) map { m =>
        //m = 4 * 2
        future3(m)
      }
    }
    result 
    
    // flatMapを使うことでFutureの入れ子をなくすことができた
    // この場合、戻り値がFuture[Unit]になる
    //future1 flatMap { n =>
    //  future2(n) flatMap { m =>
    //    future3(m)
    //  }
    //}
  }


}

