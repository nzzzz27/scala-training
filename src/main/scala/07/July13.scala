object July13 {
  
  def main(args: Array[String]): Unit = {
    println("//-------Map Test---------")
    println(mapTest("みかん"))
    println(mapValuesTest())
    println(valuesTest())
    println(toMapTest())
    println(groupByTest())
    println(groupByTest2())
    println(groupByTest3())
    
    println("//-------Map Question---------")
    println(s"mapQuestion1: ${mapQuestion1()}")
    println(s"mapQuestion2: ${mapQuestion2()}")
    println(s"mapQuestion2_2: ${mapQuestion2_2()}")
    println(s"mapQuestion3: ${mapQuestion3()}")
    println(s"mapQuestion3_2: ${mapQuestion3_2()}")
    println(s"mapQuestion4: ${mapQuestion4()}")
    println(s"mapQuestion5: ${mapQuestion5()}")
    println(s"mapQuestion5_2: ${mapQuestion5_2()}")
  }
  

  //-------- Map Test -------------
  val userMap = Map(1 -> "鈴木", 2 -> "佐藤", 3 -> "山田")

  def mapTest(fruitName: String): Int = {
    val fruitsPriceMap: Map[String, Int] = Map("りんご" -> 100, "みかん" -> 80, "ぶどう" -> 300)
    fruitsPriceMap(fruitName)
  }
  
  def mapValuesTest(): Map[Int, String] = {
    userMap.mapValues(i => "Mr/Ms." + i)
  }
  
  def valuesTest(): Iterable[String] = {
    userMap.values
  }
  
  def filterKeysTest(): Map[Int, String] = {
    userMap.filterKeys(_ % 2 == 0) 
  } 

  def toMapTest(): Map[Int, String] = {
    val userTupleSeq = Seq((1, "鈴木"), (2, "佐藤"), (3, "山田"))
    userTupleSeq.toMap 
    //result: Map(1 -> 鈴木, 2 -> 佐藤, 3 -> 山田) 
  }
  
  def groupByTest() = {
    val fruitsTupleSeq = Seq(("りんご", 200),("イチゴ", 200),("ぶどう", 300),("なし", 400),("バナナ", 300),("モモ", 400))
    
    //._2 = tupleの２番目の値 = 値段
    //._2が同一のtupleごとに、Mapの中でグループ化している
    fruitsTupleSeq.groupBy(value => value._2)

    /* result
    Map(200 -> List((りんご,200), (イチゴ,200)), 400 -> List((なし,400),
 (モモ,400)), 300 -> List((ぶどう,300), (バナナ,300)))
    */
  }

  def groupByTest2() = {
    case class Article(
       id: Long,   // ID
       title: String,   // タイトル 
       body: String   // 本文
    )
    val articles = Seq(
       Article(301, 
         "出産祝いでおめでとうを伝えるとき。メールやカードでの伝え方", "友だちの場合やビジネスの場合の文例"),
       Article(122, "2歳の子どもが激しい人見知りをするとき。固まる理由や対応について", "習い事のときや男性に人見知りをするときの様子"),
       Article(530, "1歳の幼児食。野菜を食べてほしいときの献立例やレシピとは", "冷凍ストックやレトルトを使って時短に")
    )  
    articles.groupBy(_.id)

    /*
    Map(122 -> List(Article(122,2歳の子どもが激しい人見知りをするとき。固まる理由や対応について,習い事のときや男性に人見知りをするときの様子)), 301 -> List(Article(301,出産祝いでおめでとうを伝えるとき。メールやカードでの伝え方,友だちの場合やビジネスの場合の文例)), 530 -> List(Article(530,1歳の幼児食。野菜を食べてほしいときの献立例やレシピとは,冷凍ストックやレトルトを使って時短に)))
    */
  }
  
  def groupByTest3() = {
    val numbers = Seq(1, 2, 3, 4, 5)
    //numbers.groupBy(i => i)
    //result: Map(5 -> List(5), 1 -> List(1), ....)
    
    numbers.groupBy(num => {
      num match {
        case num if num % 2 == 0 => 0
        case _ => 1
      }
    })
    /*
    numには、Seqの中の値一つずつが入っている。
    case式で、Mapのkeyを決定
    */

  }
  
  //--------- Map Questions ---------------
  //
  //以下のような、果物の価格を表したMapがあります
  val fruits = Map("りんご" -> 100, "みかん" -> 80, "ぶどう" -> 300)

  //Q1
  //みかんの価格を取得してみましょう。
  def mapQuestion1(): Int = {
    fruits("みかん")
  }

  //Q2
  //Mapに存在しない「"いちご"」を指定してgetメソッドで価格を取得しようとしたとき、どうなるかやってみてください。
  //このように、存在しなかった場合は「0」というデフォルト値を返すようにするにはどうすればいいでしょうか？
  def mapQuestion2(): Option[Int] = {
    fruits.get("いちご")
  }

  def mapQuestion2_2(): Int = {
    fruits.getOrElse("いちご", 0)
  }

  //Q3
  //fruitsの中に、特定のキーが存在するかを調べるにはどんな方法がありますか？また、それを使って「ぶどう」と「いちご」の存在判定をしてみてください。

  val grapesStr    : String = "ぶどう"
  val strawberryStr: String = "いちご"
  
  def mapQuestion3(): Boolean = {
    fruits.contains(grapesStr)
  }

  def mapQuestion3_2(): Boolean = {
    fruits.isDefinedAt(strawberryStr)
  }
  
  //Q4
  //Mapの各値に「円」をつけたMapに変換してみてください。具体的には、以下のようなMapにしてください。
  def mapQuestion4(): Map[String,String] = {
    fruits.mapValues(value => value + "円")
  }

  //Q5
  //以下のようなSeqがあります。
  
  val numbers = Seq(1,2,3,4,5)
  /*
  これらを、以下のように2で割った余りでグループ分けしたMapに変えるには、どうしたらいいでしょうか？
  
  Map(
    1 -> Seq(1, 3, 5),
    0 -> Seq(2, 4)
  )
  */
  def mapQuestion5(): Map[Int, Seq[Int]] = {   
    val oddSeq: Seq[Int] = numbers.collect {
      case num if num % 2 == 1 => num 
    }
    val evenSeq: Seq[Int] = numbers.collect {
      case num if num % 2 == 0 => num
    }
    Map(1 -> oddSeq, 0 -> evenSeq)
  }

  def mapQuestion5_2(): Map[Int, Seq[Int]] = {
    numbers.groupBy(num => {
      num match {
        case num if num % 2 == 0 => 0
        case _                   => 1
      }
    })
  }
  
}
