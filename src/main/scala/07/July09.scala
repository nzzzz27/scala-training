object July09 {
  def main(args: Array[String]): Unit = {
    println("// Seq Question")
    println(s"Question1 : ${seqQuestion1(numSeq)}")
    println(s"Question2 : ${seqQuestion2(numSeq)}")
    println(s"Question3 : ${seqQuestion3(numSeqSeq)}")
    println(s"Question4 : ${seqQuestion4(numSeq)}")
    println(s"Question5 : ${seqQuestion5(numOptSeq)}")
    println(s"Question6 : ${seqQuestion6(strSeq)}")
    println(s"Question7 : ${seqQuestion7(numSeq2)}")
  } 
  
  val numSeq: Seq[Int] = Seq(1, 2, 3, 4, 5, 6, 7, 8, 9)
  val numSeqSeq: Seq[Seq[Int]] = Seq(Seq(1, 3, 5, 7, 9), Seq(1, 2, 5, 7))
  val numOptSeq: Seq[Option[Int]] = Seq(Some(1), Some(0))
  val strSeq: Seq[String] = Seq("abc", "d", "ef")
  val numSeq2: Seq[Int] = Seq(1, 2, 300, 100, 3)

  // Question1
  // Seq[Int]型である、numSeqが引数として渡されるseqQuestion1メソッドがあります。numSeqの要素のうち、3の倍数の要素のみ0に変換する関数を実装してください。
  def seqQuestion1(numSeq: Seq[Int]): Seq[Int] = {
    numSeq.map(num => if (num % 3 == 0) 0 else num)
  }

  // Question2
  // Seq[Int]型である、numSeqが引数として渡されるseqQuestion2メソッドがあります。 numSeqのうち3の倍数のみを返すメソッドをfilterを使って作成してください。
  def seqQuestion2(numSeq: Seq[Int]): Seq[Int] = {
    numSeq.filter(num => num % 3 == 0)
  }
  
  // Question3 
  // Seq[Seq[Int]]型である、numSeqSeqが引数として渡されるseqQuestion3メソッドがあります。 numSeqSeqのうち3の倍数を含むSeqのみを返すメソッドをfilterとexistsを使って作成してください。
  def seqQuestion3(numSeqSeq: Seq[Seq[Int]]): Seq[Seq[Int]] = {
    numSeqSeq.filter(_.exists(_ % 3 == 0))
  }

  // Question4 
  // Seq[Int]型である、numSeqが引数として渡されるseqQuestion4メソッドがあります。 numSeqに偶数が含まれていればその最初の値を、含まれていない場合は-1を返すメソッドをfindを使って作成してください。
  def seqQuestion4(numSeq: Seq[Int]): Option[Int] = {
    numSeq.find(num => num % 2 == 0) 
  }
  
  //Question5
  //Seq[Option[Int]]型である、numOptSeqが引数として渡されるseqQuestion5メソッドがあります。 numOptSeqのうち0を含む最初のOption[Int]を返すメソッドをfindとcontainsを使って作成してください。
  def seqQuestion5(numOptSeq: Seq[Option[Int]]): Option[Option[Int]] = {
    numOptSeq.find(num => num.contains(0))
  }
  
  //Question6
  //Seq[String]型である、strSeqが引数として渡されるseqQuestion6メソッドがあります。 strSeqのうち文字列の長さが2以上であるものの末尾に"x”を追加し、それらのみを含むSeqを返すメソッドを、collectを使って作成してください。
  def seqQuestion6(strSeq: Seq[String]): Seq[String] = {
    strSeq.collect {
      case str if str.length > 2 => str + "x"
    } 
  }

  //Question7 
  //Seq[Int]型であるnumSeqが引数として渡されるseqQuestion7メソッドがあります。 numSeqのうち値が100以上である最初のIntを2倍した値を返すメソッドを作成してください。 100以上の値がない場合は0を返してください。 メソッドはcollectFirstを使って作成してください。
  //def seqQuestion7(numSeq: Seq[Int]): Int = {
    
  //}

}
