# Future(~ July21)
## メゾット
### Futureの合成
Futureの処理を連結させる。  

メゾットの例文に使う関数。  
いずれの例でも、１つ１つのFutureの処理を待つので、合計3秒かかる。
```
def future1(): Future[Int] =
  Future{
    Thread.sleep(1000)
    2 * 2
  }

def future2(i: Int): Future[Int] =
  Future {
    Thread.sleep(1000)
    i * 2
  }

def future3(i: Int): Future[Unit] =
  Future {
    Thread.sleep(1000)
    println(i * 2)
  }
```

#### `.map()`
```
//Future[Future[Future[Unit]]]型
future1 map { n =>
  future2(n) map { m =>
    future3(m)
  }
}
```

#### `.flatMap`
```
//Future[Unit]型
future1 flatMap { n =>
  future2(n) flatMap { m =>
    future3(m)
  }
}
```

#### `.flatMap + .map`
```
//Future[Future[Unit]]
future1 flatMap { n =>
  future2(n) map { m =>
    future3(m)
  }
}
```


#### `for {} yield {}`
```
//Future[Unit]
for {
  n <- future1()
  m <- future2(n)
  _ <- future3(m)
} yield {
  Unit
}
```

## 例外処理
### `.recover`
元のFutureが成功した時：  
同一の結果をもつ新たなFuture型の値を返す  

元のFutureが失敗した時：  
失敗したFutureを成功したFutureに変換する。  
失敗したFutureが持つThrowableに対してrecoverの 例外処理の引数として渡された部分関数を適用した値を「成功時に値として」返している。  

```
val future = Future {
  println("Futureの処理開始")
  Thread.sleep(3000)
  1 / 0
}

future recover {
  case e:Exception => println("エラー発生")
}
```



### `.recoverWith`

元のFutureが成功した時：  
同一の結果を持つ新たなFuture型の値を返す  

元のFutureが失敗した時：  
失敗したFutureを成功したFutureに変換する。  
失敗したFutureが持つ失敗値Throwableに対し、recoverWithの引数として渡された部分関数を適用した値を元に「精巧ないしは失敗したFuture型の値」を返す。  

```
val future = Future {
  println("Futureの処理開始")
  Thread.sleep(3000)
  1 / 0
}

future recoverWith {
  case e: Exception => Future.successful(2)
}
```
