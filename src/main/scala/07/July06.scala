object July06 {

  def main(args: Array[String]): Unit = {
    println("//Test")
    println(sample(Some(Some(10))))
    println(sample2(Some(10)))
    println(sample3(Some(2)))
    println(sample4(Some(2)))


    println("//Option Question1")
    println(optionQuestion1(Some(1)))
    println(optionQuestion1(None))
    
    println("//Option Question2")
    println(optionQuestion2(Some(0)))
    println(optionQuestion2(None))

    println("//Option Question3")
    println(optionQuestion3())


    println("//Option Question4")
    println(optionQuestion4(Some(Some(2))))
    println(optionQuestion4(None))
    println(optionQuestion4(Some(None)))

    println("//Option Question4: flatMap + map")    
    println(optionQuestion4_2(Some(Some(2))))
    println(optionQuestion4_2(None))
    println(optionQuestion4_2(Some(None))) 

    
    println("//Option Question4: for")
    //println(optionQuestion4_3(Some(Some(2))))
    //println(optionQuestion4_3(None))
    //println(optionQuestion4_3(Some(None))) 

  }


  // 練習
  def sample(item: Option[Option[Any]]): Boolean = {
    item.isEmpty
    item.isDefined
  } 
 
  def sample2(num: Option[Int]) = {

    num.map(i => i * 2)

  }
  
  def sample3(num: Option[Int]) = {
  
    val option1 = Some(1)
    val option2 = Some(2)
    val option3 = Some(3)
  
    for {

       i1 <- option1
       i2 <- option2
       i3 <- option3
  
     } yield {
    
      i1 * i2 * i3

    }

  }

  def sample4(num: Option[Int]) = {
    
    val option1 = Some(1)
    val option2 = Some(2)
    val option3 = Some(3)

    option1 flatMap { i1 =>
      
      option2 flatMap { i2 =>
      
        option3 map { i3 =>
      
          i1 * i2 * i3
    
        }
      }
    }

  }





  // 問題1
  // Option[Int]型である、numが引数として渡されるメソッドoptionQuestion1があります。Someの場合はその値を、Noneの場合は０を返却するメソッドを、getOrElseメソッドを使って作成してください。
  def optionQuestion1(num: Option[Int]): Int = {
    num.getOrElse(0)
  }


  // 問題２
  // Option[Int]型である、numが引数として渡されるoptionQuestion2メソッドがあります。Someの場合はその値に3を足したSome[Int]を、Noneの場合はそのままNoneを返却するメソッドを、mapメソッドを使って作成してください。
  def optionQuestion2(num: Option[Int]): Option[Int] = {
    num.map(_ + 3)
  }


 // 問題３
 // val strOpt: Option[String] = Some("") を定義し、それに対してisEmpty, isDefined を実行した場合にどのような挙動をするか確かめてください。
  def optionQuestion3(): Unit = {
    val strOpt: Option[String] = Some("")

    println(s"isEmpty = ${strOpt.isEmpty}")
    println(s"isDefined = ${strOpt.isDefined}")  
  }
  

  // 問題４
  // Option[Option[Int]]型である、numが引数として渡されるoptionQuestion4メソッドがあります。Someの場合はその値を2倍したSome[Int]を、Noneの場合はそのままNoneを返却するメソッドを作成してください。
  def optionQuestion4(num: Option[Option[Int]]): Option[Int]  = {
    num.flatMap(i => if (i.isDefined) Some(i.get * 2) else None)
  }

  def optionQuestion4_2(num: Option[Option[Int]]): Option[Int] = {
    num flatMap(i => i map(_ * 2))
  }
  
  //def optionQuestion4_3(num: Option[Option[Int]]): Option[Int] = {
    
  //}



}
