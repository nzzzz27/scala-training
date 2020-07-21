# [Future[T]](https://github.com/scala/scala/blob/v2.13.3/src/library/scala/concurrent/Future.scala#L98)
Future[T]は、非同期処理に利用する型。  
引数として渡した式を非同期に評価し、その評価値をFuture型で包んで返す。 

成功の場合：式の評価値をFuture型に包んで返す。  
失敗の場合：例外型の値を保持する。  

その他特性として以下がある。  

- Future には 1回だけ代入することができるという重要な特性がある。
- 一度 Future オブジェクトが値もしくは例外を持つと、実質不変となり、それが上書きされることは絶対に無い。


## 同期処理を非同期処理に書き換え
1秒ごとに1〜10の値を出力し、その後2秒ごとに11〜20の値を出力する。  

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
Futureを使うときに、合わせてimportするが、闇雲にimportしてはいけないので注意。  
```
//非推奨：scalaが標準で提供しているExecutionContext
import scala.concurrent.ExecutionContext.Implicits.global


//推奨：implicit ec: ExecutionContextをメゾットまたはコンストラクタのクラスパラメータに追加する。
@Singleton
class UserController @Inject()(
  cc: MessagesControllerComponents
)(implicit ec: ExecutionContext) 
  extends MessagesAbstractController(cc){
```

ExecutionContextは、２つの抽象メゾットをもつ。  
```
//非同期処理の実行
def execute(runnable: Runnable): Unit

//エラー処理
def reportFailure(@deprecatedName('t) cause: Throwable): Unit
```

Futureに定義されている関数は、実際に非同期処理の実行（excute）とエラー処理（reportFailure）を、暗黙のパラメータとして渡されたExecutionContext型の値に委譲している。  
つまり、Futureで定義された関数は、ExecutionContextの値の実装によって、どのようにexcuteまたはreportFailureされるかが決定される。  


### ExecutionContextの動きのイメージ
![例１](https://user-images.githubusercontent.com/57429437/81898909-95712300-95f4-11ea-8419-ea128c1b3cec.png)

> 例えば10個の重いタスクがあったとして、それぞれのタスクに対し new Thread & start (スレッド立ち上げ) して処理した場合は当然次のように10個のスレッドが立ち上がる。 
> しかし、この実装は以下のような問題点がある
>
> ・タスク数分スレッドを立ち上げるためメモリ/CPU資源の無駄になる 
> ・しかも暇しているスレッドがいる

> このようにタスク(Runnableといったほうが適切か)を「いい感じ((ExecutionContextの実装による)」にスレッドに分配するのがExecutionContextの役目だ。 
> 一般には「一定数を最大数とするスレッドプールを持っており、空いてるスレッドを利用してRunnableを処理してくれる」と考えればいいのではないだろうか。


ExecutionContextは、executeメソッドに渡される[Runnable](https://docs.oracle.com/javase/7/docs/api/java/lang/Runnable.html)を実行するときに非同期でなければいけない。  
特別な場合に同期処理としてExecutionContextが実行される場合もあるが、それは明示的かつExecutionContextの同期的実行が安全でなければいけない。  

Future.onCompleteなどのAPIには、コールバックと暗黙的なExexutionContextが必要になる。暗黙的なExecutionContextは、コールバックの実行のために必要となる。  

実装者は、どの部分にexecutionポリシーを適用したいか、慎重に考えなけらばいけない。
1アプリケーションにつき１箇所、もしくは論理的に関連している１コードセクションに１つが理想。　　

推奨方法としては、`implicit ec: ExecutionContext`をメゾットかクラスコンストラクターパラメータに追加すること。  

追加する例：
```
@Singleton
class UserController @Inject()(
  cc: MessagesControllerComponents
)(implicit ec: ExecutionContext) <- ココ
  extends MessagesAbstractController(cc){
```

## メゾット
### 初期化
処理の式に加えて、ExcecutionContextも必要。
```
val future = Future(1)
future: scala.concurrent.Future[Int] = Future(Success(1))

val future = Future("hoge")
future: scala.concurrent.Future[String] = Future(Success(hoge))
```

### `.successful()`/`.faild()`
実行済みのFutureを生成することができる。  
successfulはすでにある値をFutureが成功した場合の値として包み、faieldはすでにあるThrowableを継承した値をFutureが失敗した場合の値として包んで返す。  

failedの引数には、[Throwable](https://docs.oracle.com/javase/jp/8/docs/api/java/lang/Throwable.html)を継承したもののみが渡せる。  

これらの関数はFuture型で包むだけなので、何かの処理を非同期で行うわけではない。

```
val success = Future.successful(2)
val failed  = Future.failed(throw new java.util.NosuchElementExecption)
```

### `.isCompleted`
非同期処理が完了したかどうかを、Booleanで返す。  
```
  val msg = "hello"
  val f: Future[String] = Future {
    Thread.sleep(1000)
    msg * 5
  }

  f.isCompleted // false
```


### `Await`
値を取得するには、処理の終了を待つ必要がある。Futureの処理が終了するまで待つには、`Await`を使う。  

#### `.Await.ready()`
Future が完了するまで待機するがその結果を取得しないことができる。   
同様に、このメソッドを呼んだ時に Future が失敗したとしても例外は投げられない。 
第二引数で、処理の待ち時間の制限指定ができる。下記の場合は、[Duration](https://www.scala-lang.org/api/2.12.3/scala/concurrent/duration/Duration$.html#Inf:scala.concurrent.duration.Duration.Infinite).Inf = duration infinite。  
```
val future = Future {
  Thread.sleep(1000)
  2 * 2
}

//Await.readyの第二引数は待ち時間を表す。Infはinfinityのことで、永遠に待つ。  
val result = Await.ready(future, Duration.Inf)

result //Future(Success(4))
```



## 参考サイト
[FutureとPromise](https://docs.scala-lang.org/ja/overviews/core/futures.html)  
[Scala ExecutionContextって何 / Futureはスレッド立ち上げじゃないよ](https://mashi.hatenablog.com/entry/2014/11/24/010417)  

