import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

object July22 {
  
  def main(args: Array[String]): Unit = {
    println("//--- Future Test ---")
    //println(futureTest1)      
    //println(futureTest2)      
    println(futureTest3)      

    println("//--- Future Question --")
    println(Database.main(1))
  }
  
  //--- Future Test ---
  def futureTest1() = {
    def future1(): Future[Int] =
      Future{
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
      
      //Future[Future[FUture[Unit]]]
      val result1 = future1 map { n =>
        future2(n) map { m =>
          future3(m)
        }
      }  
      //val res0: scala.concurrent.Future[scala.concurrent.Future[scala.concurrent.Future[Unit]]] = Future(<not completed>)


      //Future[Unit]
      val result2 = future1 flatMap { n =>
        future2(n) flatMap { m =>
          future3(m)
        }
      }
      // scala.concurrent.Future[Unit] = Future(<not completed>)
      

      //Future[Unit]
      val result3 = future1 flatMap { n =>
        future2(n) map { m =>
          future3(m)
        }
      }
    //scala.concurrent.Future[scala.concurrent.Future[Unit]] = Future(<not completed>)
    

    //例のごとく、for式に書き換えることができます。
    for {
      n <- future1()
      m <- future2(n)
      _ <- future3(m)
    } yield {
      Unit
    }
  }  

  def futureTest2() = {
    val future = Future {
      println("Futureの処理開始")
      Thread.sleep(3000)
      1 / 0
    } //Future(<not completed>): Future[Int]
    
    // 
    future recover {
      case e:Exception => println("エラー発生")
    }
    // 
    //{future recoverWith {
    //{  case e: Exception => Future.successful(2)
    //{}
  }

  def futureTest3() = {
    val future = Future {
      println("Futureの処理開始")
      Thread.sleep(3000)
      1 / 0
    }
   // future recover {
   //   case e:Exception => println("エラー発生")
   // }
    future recoverWith {
      case e: Exception => Future.successful(2)
    }
  }

  //--- Future Question ---
  /* 保育士バンクで実際に存在するモデル(を簡単にしたもの)を元に、与えられた施設のIDから法人を取得し、法人名を標準出力するメソッドを作成してください。 
   * 条件は以下です。
   *
   * mainというメソッドで園名を標準出力すること
   * mainメソッドにはbranchId: Longを引数として受け取る
   * クラス定義は以下の通り
   */
  case class Organization(
    id:    Long,   // 法人ID
    name:  String, // 法人名
    email: String  // 法人のEmailアドレス
  )
  // 法人(Organization)が所有している施設情報(保育園・幼稚園など)
  case class Branch (
    id:              Long,   // 施設ID
    organizationId:  Long,   // 法人ID
    name:            String  // 施設名
  )
  
  // Database情報
  object Database {
    // 施設のIDから、施設情報を取得する
    // 仮で、nextbeat保育園の情報を返却するような実装にしている
    def getBranchById(branchId: Long): Future[Branch] = 
      Future.successful(Branch(
        id             = 1,
        organizationId = 1,
        name           = "nextbeat保育園"
      ))
  
    // 法人のIDから、法人情報を取得する
    // 仮で、株式会社nextbeatの情報を返却するような実装にしている
    def getOrganizationById(organizationId: Long): Future[Organization] =
      Future.successful(Organization(
        id    = 1,
        name  = "株式会社nextbeat",
        email = "nextbeat.net"
      ))

    def main(branchId: Long) = {
      val branchInfo: Future[Branch] = getBranchById(branchId)
      //Future(Success(Branch(1,1,nextbeat保育園)))

      val orgInfo: Future[Organization] = getOrganizationById(branchId)
      //Future(Success(Organization(1,株式会社nextbeat,nextbeat.net)))

      branchInfo
    }
  }

  
}

