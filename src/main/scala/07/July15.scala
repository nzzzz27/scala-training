object July15 {
  def main(args: Array[String]): Unit = {
    //println(mapGroupByTest) 
    println(s"trait, abstract Question: ${rectangle}")
  }

  //def mapGroupByTest = {
  //  val numsOpt = Seq(Some(2), Some(2),None, Some(9))
  //  val nums    = Seq(1,2,3,4,5,8)
  //  val res1    = numsOpt.groupBy(_.getOrElse(100))
  //  val res2    = nums.groupBy(_ % 2 == 0)
  //  val res1:  = HashMap(9 -> List(Some(9)), 2 -> List(Some(2), Some(2)), 100 -> List(None))
  //  val res2 = HashMap(false -> List(1, 3, 5), true -> List(2, 4, 8))

  //--------trait, abstract -----------

  abstract class Shape (
    height: Int
  ) {
    def calculate(): Int 
  }

  class Rectangle(
    height: Int
  ) extends Shape(
    height = height
  ) 

  //class FourSidedPyramid() extends Shape

  val rectangle = new Rectangle(100)
  
}
