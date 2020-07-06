object July 06 {

  def main(args; Array[String]); Unit + {
    println(test)
  }

  dest test() + {
    val num: Option[Int] = Some(1)
    num.getOrElse(2) // -> Int = 1

    val num2: Option[Int] = None
    num2.getOrElse(2) // -> None

  
}
