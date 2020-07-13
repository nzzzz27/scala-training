object July13 {
  
  def main(args: Array[String]): Unit = {
    println("//Map Test")
    println(mapTest("みかん"))
    println(mapValuesTest())
    println(valuesTest())
    println(toMapTest())
    println(groupByTest())
  }
  

  // values for test methods
  val userMap = Map(1 -> "鈴木", 2 -> "佐藤", 3 -> "山田")

  def  mapTest(fruitName: String): Int = {
    val fruitsPriceMap: Map[String, Int] = Map("りんご" -> 100, "みかん" -> 80, "ぶどう" -> 300)
    fruitsPriceMap(fruitName)
  }
  
  def mapValuesTest(): Map[Int, String] = {
    //userMap.mapValues(_ + "さん")
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
  
  
  //--------- Map Questions ---------------
  //以下のような、果物の価格を表したMapがあります
  val fruits = Map("りんご" -> 100, "みかん" -> 80, "ぶどう" -> 300)

  //Q1
  //みかんの価格を取得してみましょう。
  def mapQuestion1(fruitsMap: Map[String, Int]): Int = {
    fruitsMap.
  }

}
