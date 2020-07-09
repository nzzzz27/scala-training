object July09 {
  def main(args: Array[String]): Unit = {
    println("// Seq Question")
    println(seqQuestion1(numSeq))
    println(seqQuestion2(numSeq))
    println(seqQuestion3(numSeqSeq))

  } 
  
  val numSeq: Seq[Int] = Seq(1, 3, 5, 7, 9)
  val numSeqSeq: Seq[Seq[Int]] = Seq(Seq(1, 3, 5, 7, 9))

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
    //numSeqSeq.filter(num => num.exists(num2 => num2 % 3 == 0))
    numSeqSeq.filter(_.exists(_ % 3 == 0))
  }


}
