# [Future[T]](https://github.com/scala/scala/blob/v2.13.3/src/library/scala/concurrent/Future.scala#L98)
Future[T]は、非同期処理に利用する型。  
引数として渡した式を非同期に評価し、その評価値をFuture型で包んで返す。 

成功の場合：式の評価値をFuture型に包んで返す。  
失敗の場合：例外型の値を保持する。  


## 同期処理を非同期処理に書き換え
1秒ごとに1~10の値を出力し、その後2秒ごとに11~20の値を出力する。  
```
def run(): Unit =  {
  
  //処理A
  // Threadは処理の実行単位
  // 今回でいえば、この処理を1000ミリ秒止めるという処理になる
  (1 to 10).foreach { i =>
    Thread.sleep(1000)
    println(i)
  }
  
  //処理B
  // 2秒ずつとめてから出力
  (11 to 20).foreach { i =>
    Thread.sleep(2000)
    println(i)
  }
}

//結果
A: 1
A: 2
A: 3
A: 4
A: 5
A: 6
A: 7
A: 8
A: 9
A: 10
B: 11
B: 12
B: 13
B: 14
B: 15
B: 16
B: 17
B: 18
B: 19
B: 20
```

### 非同期処理  
処理Aは1秒ごと、処理Bは2秒ごとに、値を出力する。処理は同時に行われる。
```
// Futureを使えるようにimport
import scala.concurrent.Future

// おまじないだと思って無視してください
import scala.concurrent.ExecutionContext.Implicits.global

def run(): Unit = {

  Future {
    (1 to 10).foreach { i =>
      Thread.sleep(1000)
      println(i)
    }
  }

  Future {
    (11 to 20).foreach { i =>
      Thread.sleep(2000)
      println(i)
    }
  }
}

//結果
C: 1
D: 11
C: 2
C: 3
D: 12
C: 4
C: 5
D: 13
C: 6
C: 7
D: 14
C: 8
```


使用例
```
import ExecutionContext.Implicits.global
val s = "Hello"
val f: Future[String] = Future {
  s + " future!"
}
f foreach {
  msg => println(msg)
}
```
 
## ExecutionContext

ExecutionContextが持つ、２つの抽象メゾットをもつ。  
```
def execute(runnable: Runnable): Unit

def reportFailure(@deprecatedName('t) cause: Throwable): Unit
```

Futureに定義されている関数は、実際に非同期処理の実行（excure）とエラー処理（reportFailure）を、暗黙のパラメータとして渡されたExecutionContxt型の値に委譲している。  
つまり、Futureで定義された関数は、ExecutionContextの値の実装によって、どのようにexcuteまたはreportFailureされるかが決定される。  

