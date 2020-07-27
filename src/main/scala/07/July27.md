# メモ
- Tryは、失敗値を見たい時によく用いられる
- forは、失敗値の場合の処理をスキップできる
- 持っている値が不明な時に、エラーを返す可能性のあるメゾットを使用しない

```
//例
val branchInfo: Try[Branch] = Database.getBranchById(branchId).value.get
```

- Await.readyは、処理が終わるまでスレッドが解放されない（同期処理）ので、なるべく使わない。  
（参照：[[Scala]Await.resultからFutureに乗り換える](https://qiita.com/mikene_koko/items/c956677693fbd2e86da6))  


## わからなかったこと
`Future(<not completed>)`が返ってきてしまう  
→ Futureの処理が未完了の状態で値を取得しているため。`Await`トレイトのメゾット(`onComplete`など)で、処理を待つ必要がある。  

例）
```
//施設ID
val bId: Long = 1L 

//法人IDを取得 ...A
val oIdA: Future[Long] = for {
  branch <- Database.getBranchById(bId)
} yield {
  branch.organizationId
}

//Awaitで処理終了を待つ ...B
val oIdB: Future[Long] = Await.ready(oIdFuture, Duration.Inf) 

//結果
def main() = {
  oIdA  //Future(<not completed>)

  oIdB  //Future(Success(1))
}
```

Future問題の考え方  
```
//共通
流れ
1. 施設IDから、法人IDを取得する … Branch
2. 法人IDにmatchする法人名を取得する … BranchとOrganization

getBranchById()の結果と、getOrganizationById()の結果の、idが合致するかどうか判定して、合致したらOrganizationのnameを返す、match式
```

```
//うまくいかなかった時
1. branchIdから、Branch case classのorganizationIdを取得 
2. branchIdから、Organization case classのidを取得 
3. match式で1と2の値がイコールかチェックし、イコールならSuccessに包んだOrganization.nameを返却
4. Successから中の値だけを取り出す

//コード
def main(branchId: Long): String = {
  //1
  val branchInfo: Try[Branch]       = Database.getBranchById(branchId).value.get
  
  //2
  val orgInfo   : Try[Organization] = Database.getOrganizationById(branchId).value.get
  
  //3
  val nurseryName: Try[String] = branchInfo.flatMap(b =>
      orgInfo.flatMap(o =>
          o.id match {
            case b.id => Success(b.name)
            case _    => Failure(throw new NoSuchElementException)
          }
      )
  )

  //4
  nurseryName.get
}

//よくなかった点
・Try型に変換する時は、失敗値を細かく確認したい時。この問題ではobject DatabaseのFutureはSuccessと決定しているので、.valueでOption[Try[T]]の使用は必要ない。（もっと良いメゾットがある）
・1, 2時点で、Futureの処理が完了しているかどうか不明な時点で、エラーを返す可能性のある.getメゾットを使っている。もし使うなら、getOrElse()がベター。
・Future型の値が持っているのが成功値か失敗値か気にしなくて良いのに、3のmatch式で値が等しいかどうかチェックするのが冗長
```

```
//うまくいった時
1. branchIdから、Branch case class内のorganizationIdを取得し、getOrganizationByIdメゾットの引数に代入。
2. 引数のorganizationIdと同じidを持つOrganization case classを取得。
3. Organization case classからnameを取得し、定数に代入。
4. Futureの処理完了を待ってから、値を出力

//コード
def main(branchId: Long) = {

  val nurseryNameFuture: Future[String] = for {
    //1
    branch <- Database.getBranchById(branchId) 
    //2
    organization <- Database.getOrganizationById(branch.organizationId)
  } yield {
    //3
    organization.name
  }
  
  //4
  val nurseryName: Unit =  nurseryNameFuture.onComplete {
    case Success(_) => println(nurseryNameFuture.value.get.get)
    case Failure(e) => println(e.getMessage)
  }

  nurseryName
}
```


