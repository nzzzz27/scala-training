/* Scala応用問題
 * https://nextbeat-external.atlassian.net/wiki/spaces/DEVNEWEDU/pages/1258914223/2-3-1.+Scala
*/

object Aug03 {
  def main(args: Array[String]): Unit = {
    println("//--- Question 1 ---")
    question1(Some(1))
    //question1(Some(5))
    //question1(Some(10))
    //question1(None)
    
    println("//--- Question 1 別解 ---")
    question1_2(Some(1))
    //question1_2(Some(5))
    //question1_2(Some(10))
    //question1_2(None)

    println("//--- Question 2 ---")
    println(convertToString(Some(10))(question2))
    println(convertToString(None)(question2))

    println("//--- Question 3 ---") 
    println(question3(Seq(5, 2, 3, 8)))
    
    //println("//--- Question 3 別解 ---") 
    ////give up
    //println(question3_2(Seq(5, 2, 3, 8)))

    println("//--- Question 4 ---")
    println(question4(Seq(5, 2, 3, 8)))

    println("//--- Question 5 ---")
    println(question5(Seq(Some(1), None, Some(3), Some(5))))
    
    println("//--- Question 5 別解 ---")
    println(question5_2(Seq(Some(1), None, Some(3), Some(5))))

    println("//--- Question 6 ---")
    println(question6(Seq(3, 2, 1)))
  }
  
  // 8/3 14:00 ~
  /* Q1
   * Option[Int]型の numOptを引数として受け取り
     numOptがSomeでかつ値が10以上であれば、"A"
     numOptがSomeでかつ値が9以下、5以上であれば、"B"
     numOptがSomeでかつ値が4以下であれば"C"
     numOptがNoneであれば"D"
     を標準出力させるメソッドquestion1を作成してください
  */
  
  def question1(numOpt: Option[Int]): Unit = {
    if (numOpt.isEmpty) println("D")

    numOpt.map( _  match {
      case n if n >= 10 => println("A")
      case n if n <= 4  => println("C")
      case _            => println("B")
    }) 
  }

  // ~ 14:35 
  // 別解
  def question1_2(numOpt: Option[Int]): Unit = {
    numOpt match {
      case Some(n) if n >= 10 => println("A")
      case Some(n) if n <= 4  => println("C")
      case None               => println("D")
      case _                  => println("B")
    }
  }
  
  // 8/3 14:40 ~
  /*  Q2 
   *  Option[Int] => String に変換する関数 convと、Option[Int]型である numOpt を引数にとり、numOptを文字列にして返すメソッドconvertToStringがあります。convertToStringを使ってnumOpt: Option[Int]をStringに変換するメソッドquestion2を作成してください。
    
    numOptは以下の条件に従って変換させること
     1.numOptがSome()であれば、そのIntの値をStringに変換する
     2.numOptがNoneであれば、"空でした"というStringに変換する
  */
  def convertToString(numOpt: Option[Int])(conv: Option[Int] => String): String = conv(numOpt) 
  
  // ~ 15:10
  def question2(numOpt: Option[Int]):String = {
    numOpt match {
      case Some(num) => num.toString
      case None      => "空でした"
    }
  }
  
  //  8/3 15:13 ~  
  /*  Q3 
   *  Seq[Int]型であるnumsを引数として受け取り、配列の中身を昇順でソートするquesion3を作成してください
   */

  // ~ 15:23
  def question3(nums: Seq[Int]): Seq[Int] = {
    nums.sorted
  }
  
  // ~ 16:00 give up! 
  // 別解：n1とn2を比較して、n1>n2なら、n1をn2+1に配置する。n1が最後まで来たら、n2と他の数字の比較に移動
  //def question3_2(nums: Seq[Int]) = {
  //  def aSort(aNums: Seq[Int], result: Seq[Int]) = {
  //    nums match {
  //      case h :: _ if h > _.head => aSort(h, )
  //    }
  //  }
  //}
  
  // 8/3 16:00 ~ 
  /*  Q4 
   *  Seq[Int]型であるnumsを引数として受け取り、配列の中身を降順でソートするquesion4を作成してください。その際にreverseメソッドは使用しないこと
   */

  //  ~16:05
  def question4(nums: Seq[Int]): Seq[Int] = {
    val sortedNumSeq: Seq[Int] = nums.sorted 
    sortedNumSeq.foldLeft(Nil: Seq[Int])((acc, value) => value +: acc)
  } 
  
  //  16:10 ~
  /*  Q5 
   *  Seq[Option[Int]]型である、numOpsを引数にとりnumOpsの要素がSomeの場合は中の数字を、Noneの場合はリストから外したSeq[Int]を返すquestion5を作成してください
   */
  
  //  ~ 16:26 
  def question5(numOps: Seq[Option[Int]]): Seq[Int] = {
    numOps.collect{
      case Some(num) => num
    }
  }
  
  // ~ 16:36
  def question5_2(numOps: Seq[Option[Int]]): Seq[Int] = {
    numOps.flatMap(i => i)
  }
  
  // 16:40 ~ 
  /*  Q6
   *  Seq[Int]型である、numsを引数として渡され、各要素とその要素のindex(位置)をタプルで紐付けたSeq[(Int,Int)]を返すquestion6を作成してください
   */

  // 16: 55 
  def question6(nums: Seq[Int]): Seq[(Int,Int)] = {
    nums.zipWithIndex
  }

  //
  /* Q7 
   * Seq[Int]型であるnumsを引数として受け取り、numsの最大値のindex(添字)を返却するquestion7を作成してください
最大値が複数存在する場合は、そのうちいずれかが返却されているようになっていれば問題ない
  */
  
}


