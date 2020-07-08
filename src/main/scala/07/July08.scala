
object July08 {

  def main(args: Array[String]): Unit = {
    println("// Test")
    println(foldLeftSample())
    println(foldRightSample())
    println(reduceSample())

    println("//Seq Question")
    println()
  }
  
  // common values
  val numSeq1 = Seq(1, 2, 3)

  def foldLeftSample(): Int = {
    numSeq1.foldLeft(0)((acc, value) => 
      acc + value //6
      //acc // 0
      //value // 3
    )
  }

  def foldRightSample(): Int = {
    numSeq1.foldRight(0)((value, acc) => 
      value + acc //6
    )
  }

  def reduceSample(): Int = {
    numSeq1.reduce((acc, value) => 
      acc + value // 6
    ) 
  }
  
  //問題
  def seqQuestion1(numSeq: Seq[Int]): Seq[Int] = {

  }

}
