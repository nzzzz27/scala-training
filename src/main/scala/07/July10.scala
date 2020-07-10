object July10 {
  def main(args: Array[String]): Unit = {
    println("//Seq Test")
    //println(s"seqQuestion11-test1: ${seqQestion11_test1(Seq(1, 2, 3, 4, 5))}")
    //println(s"seqQuestion11-test2: ${seqQestion11_test2(Seq(1, 2, 3, 4, 5))}")
    //println(s"seqQuestion11-test3: ${seqQestion11_test3(Seq(1, 2, 3, 4, 5))}")

    println("//Seq Question")
    println(s"seqQuestion7: ${seqQuestion7(Seq(1, 200, 3, 400))}")
    println(s"seqQuetion10_1-1: ${seqQuestion10(Seq(1, 2, 3))}")
    println(s"seqQuetion10_1-2: ${seqQuestion10(Seq(1, 2))}")
    println(s"seqQuetion10_1-3: ${seqQuestion10(Seq())}")
    println(s"seqQuetion10_2-1: ${seqQuestion10_2(Seq(1, 2, 3))}")
    println(s"seqQuetion10_2-2: ${seqQuestion10_2(Seq(1, 2))}")
    println(s"seqQuetion10_2-3: ${seqQuestion10_2(Seq())}")
    println(s"seqQuetion11: ${reverse(Seq(1, 2, 3, 4, 5))}")
    println(s"seqQuetion12_1: ${sum(Seq(1, 2, 3, 4, 5))}")
    println(s"seqQuetion12_2: ${sum(Seq())}")
    println(s"seqQuetion13: ${seqQuestion13(Seq(1, 2, 3, 4, 5))}")
    //println(s"seqQuetion13-2: ${seqQuestion13(Seq())}")
    println(s"seqQuetion14: ${seqQuestion14(Seq(3*4, 4*2, 4*1))}")
    println(s"seqQuetion15: ${seqQuestion15(Seq(3*4, 4*2, 4*1))}")
  }

  
  // -------------- Seq Test ---------------------- 

  def seqQestion11_test1(numSeq: Seq[Int]): Seq[Int] = {
    numSeq.foldLeft(Nil: List[Int])((acc, value) => {
      value +: acc
    })
  } // => success! 
  
  /*
  def seqQestion11_test2(numSeq: Seq[Int]): Seq[Int] = {
    numSeq.foldLeft(Nil)((acc, value) => {
      value +: acc
    })
  } // => type mismatch; found:List[Int], required:scala.collection.immutable.Nil.type  
  */
  
  /*
  def seqQestion11_test3(numSeq: Seq[Int]): Seq[Int] = {
    numSeq.foldLeft(List())((acc, value) => {
      value +: acc
    })
  } // => type mismatch; found List[Int], required List[Nothing]
  */

  // -------------- Seq Qquestions ------------------
  
  //Question7
  //Seq[Int]型であるnumSeqが引数として渡されるseqQuestion7メソッドがあります。 numSeqのうち値が100以上である最初のIntを2倍した値を返すメソッドを作成してください。 100以上の値がない場合は0を返してください。 メソッドはcollectFirstを使って作成してください。
  def seqQuestion7(numSeq: Seq[Int]): Int = {
    val result = numSeq.collectFirst {
      case i if i >= 100 => i * 2
    }
    result.getOrElse(0)
  }

  //Question10
  //Seq[Int]型である、numSeqが引数として渡されるseqQuestion10メソッドがあります。 numSeqの要素数が3以上の場合、最初の値と最後の値を足した値を返すメソッドを作成してください。 numSeqが要素が2以下、1以上の場合は0を、numSeqが空の場合は-1を返してください。 メソッドの作成にはmatch式と:+, +:などを用いてください。
  def seqQuestion10(numSeq: Seq[Int]): Int = {
    numSeq match {
      case x :: y :: z :: _  => x + numSeq.last
      case Nil               => -1
      case _                 => 0
    }
  }

  def seqQuestion10_2(numSeq: Seq[Int]): Int = {
    numSeq match {
      case x +: _ +: _ :+ y => x + y
      case Nil              => -1
      case _                => 0
    }
  }

  //Question11
  //foldLeftを用いて、Seqの要素を反転させる次のシグニチャを持ったメソッドreverseを実装してみましょう。
  def reverse(numSeq: Seq[Int]): Seq[Int] = {
    numSeq.foldLeft(Nil: List[Int])((acc, value) => value +: acc)
  }
  
  //Question12
  //Seqの全ての要素を掛け合わせるメソッドsumをfoldRightを用いて実装してみましょう。
  //Seqが空のときは1を返してみましょう。
  def sum(numSeq: Seq[Int]): Int = {
    numSeq.foldRight(1)((value, acc) => acc * value) 
  }
  
  //Question13
  //問題12でreduceを使ってSeqが空の場合、エラーを吐くようにしましょう。
  def seqQuestion13(numSeq: Seq[Int]): Int = {
    numSeq.reduce((acc, value) => acc * value) 
  }

  //Question14
  //Seq(3×4, 4×2, 4×1)の最小値を出力しましょう。
  def seqQuestion14(numSeq: Seq[Int]): Int = {
    numSeq.min
  }

  //Question15 
  //Seq(3×4, 4×2, 4×1)の最大値を出力しましょう。
  def seqQuestion15(numSeq: Seq[Int]): Int = {
    numSeq.max
  }


}

