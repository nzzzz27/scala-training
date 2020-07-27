# future(~ july21)
## メゾット
### futureの合成
futureの処理を連結させる。  

メゾットの例文に使う関数。  
いずれの例でも、１つ１つのfutureの処理を待つので、合計3秒かかる。
```
def future1(): future[int] =
  future{
    thread.sleep(1000)
    2 * 2
  }

def future2(i: int): future[int] =
  future {
    thread.sleep(1000)
    i * 2
  }

def future3(i: int): future[unit] =
  future {
    thread.sleep(1000)
    println(i * 2)
  }
```

#### `.map()`
```
//future[future[future[unit]]]型
future1 map { n =>
  future2(n) map { m =>
    future3(m)
  }
}
```

#### `.flatmap`
```
//future[unit]型
future1 flatmap { n =>
  future2(n) flatmap { m =>
    future3(m)
  }
}
```

#### `.flatmap + .map`
```
//future[future[unit]]
future1 flatmap { n =>
  future2(n) map { m =>
    future3(m)
  }
}
```


#### `for {} yield {}`
```
//future[unit]
for {
  n <- future1()
  m <- future2(n)
  _ <- future3(m)
} yield {
  unit
}
```

## 例外処理
### `.recover`
元のfutureが成功した時：  
同一の結果をもつ新たなfuture型の値を返す  

元のfutureが失敗した時：  
失敗したfutureを成功したfutureに変換する。  
失敗したfutureが持つthrowableに対してrecoverの 例外処理の引数として渡された部分関数を適用した値を「成功時に値として」返している。  

```
val future = future {
  println("futureの処理開始")
  thread.sleep(3000)
  1 / 0
}

future recover {
  case e:exception => println("エラー発生")
}
```



### `.recoverwith`

元のfutureが成功した時：  
同一の結果を持つ新たなfuture型の値を返す  

元のfutureが失敗した時：  
失敗したfutureを成功したfutureに変換する。  
失敗したfutureが持つ失敗値throwableに対し、recoverwithの引数として渡された部分関数を適用した値を元に「精巧ないしは失敗したfuture型の値」を返す。  

```
val future = future {
  println("futureの処理開始")
  thread.sleep(3000)
  1 / 0
}

future recoverwith {
  case e: exception => future.successful(2)
}
```


# futureの問題用のメモ
## mainメゾットに用意するもの
・戻り値： string 
・getbranchbyid()の結果と、getorganizationbyid()の結果の、idが合致するかどうか判定して、合致したらorganizationのnameを返す、match式

## 問題のコードが実際に使われる時の流れ 
・ユーザーが法人idを入力  
・dbから、与えられた法人idに合致するorganizationとbranchを探す  
	・合致するものがあれば、success。法人id以外のデータもdatabaseオブジェクトに入れる。  
	・合致しなければ、failureを返却  
→それで今は、法人idが合致した想定  

## メモ
実装  
・１つ目のfutureがsuccessなら、次のfutureがsuccessかどうかチェック。両方successだったら、idの値がmatchするか調べる。  
（successかどうかわかってからmatch式する）  
→　これだと、future、match式それぞれで例外を設定しなければいけない  

・２つのfutureをmapで１つにまとめて、その後にそれぞれのid値がmatchするか調べる。  
（successかどうか調べるのとmatchを同時に行う）  
→　例外の設定が一回で済むから、こっち?  
