object July07 {
    
    def main(args: Array[String]): Unit = {
      
      //test
      println("//Test: Option")
      println(sampleOption())
      println(sampleOption2())

      println("//Option Question4: for")
      println(optionQuestion4_3(Some(Some(2))))
      println(optionQuestion4_4(Some(Some(2))))


      println("//Test: Seq")
    }

  // ------- Option ----------------
    val option1 = Some(1)
    val option2 = Some(2)
    val option3 = Some(3)

  // Practice
    def sampleOption(): Option[Int] = {
       for {
         i1 <- option1
         i2 <- option2
         i3 <- option3
       } yield {
         i1 * i2 * i3
       }
     }
      
    def sampleOption2(): Unit = {
      option1 flatMap { i1 =>
         println(s"i1 ${i1}") //1
        option2 flatMap { i2 =>
          println(s"i2 ${i2}") //2
             option3 map { i3 =>
                println(s"i3 ${i3}") //3
                println(i1 * i2 * i3) //6
             }
         }
      }
    }
  
    // 問題４
    // Option[Option[Int]]型である、numが引数として渡されるoptionQuestion4メソッドがあります。Someの場合はその値を2倍したSome[Int]を、Noneの場合はそのままNoneを返却するメソッドを作成してください。
    def optionQuestion4_3(num: Option[Option[Int]]): Option[Int] = {
      for {
        i <- num.flatten
      } yield {
        i * 2
      }
  }
      // for yield でoptionとかを剥がして使う時は、for の中で値を取り出し、yieldで加工することが多い
      def optionQuestion4_4(num: Option[Option[Int]]): Option[Int] = {
      for {
        numOpt <- num
        i <- numOpt
      } yield {
        i * 2
      }
  }

}   

  // -------- Seq ----------------

  // test

