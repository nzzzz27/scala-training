object July15 {
  def main(args: Array[String]): Unit = {
    //println(mapGroupByTest) 
    println(s"trait, abstract Question1-1: ${rectangular}")
  }

  //def mapGroupByTest = {
  //  val numsOpt = Seq(Some(2), Some(2),None, Some(9))
  //  val nums    = Seq(1,2,3,4,5,8)
  //  val res1    = numsOpt.groupBy(_.getOrElse(100))
  //  val res2    = nums.groupBy(_ % 2 == 0)
  //  val res1:  = HashMap(9 -> List(Some(9)), 2 -> List(Some(2), Some(2)), 100 -> List(None))
  //  val res2 = HashMap(false -> List(1, 3, 5), true -> List(2, 4, 8))

  //--------trait, abstract -----------

  abstract class Shape (val height: Int) {
    def calculate(): Int  
  }
  
  case class Rectangular (_height: Int, width: Int, depth: Int) extends Shape (
    height = _height
  ) {
    def calculate(): Int = {
      height * width * depth
    }
  }
  
  val rectangular = Rectangular(10, 20, 10).calculate
 
}
