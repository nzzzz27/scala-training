import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Success, Failure, Try}
import scala.util.Try
import scala.concurrent.duration._

object July27 {

  def main(args: Array[String]): Unit = {
    println("--- Future Question ---")
    println(GetNurseryName(1L).main)

    println("//最終コード")
    main(1L)
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
  }
  
  /*  ここから回答
      出力するもの：法人名
      
      流れ
      1. 施設IDから、法人IDを取得する … Branch
      2. 法人IDにmatchする法人名を取得する … BranchとOrganization 
      
      Mainメゾットに用意するもの
      ・戻り値： String 
      ・getBranchById()の結果と、getOrganizationById()の結果の、idが合致するかどうか判定して、合致したらOrganizationのnameを返す、match式
   */
  

  //--- 没コード ---
  case class GetNurseryName(branchId: Long) {
    val bId: Long = branchId 
    
    val oIdFuture: Future[Long] = for {
      branch <- Database.getBranchById(bId)
    } yield {
      branch.organizationId
    }
    
    val oId: Future[Long] = Await.ready(oIdFuture, Duration.Inf)  
    
    def main() = {
      oIdFuture  //Future(<not completed>)
      oId  //Future(Success(1))
    }
  }

  //最終的なコード
  def main(branchId: Long) = {
    val nurseryNameFuture: Future[String] = for {
      branch <- Database.getBranchById(branchId) 
      organization <- Database.getOrganizationById(branch.organizationId)
    } yield {
      organization.name
    }

    val nurseryName: Unit =  nurseryNameFuture.onComplete {
      case Success(_) => println(nurseryNameFuture.value.get.get)
      case Failure(e) => println(e.getMessage)
    }

    nurseryName
  }
  
}
