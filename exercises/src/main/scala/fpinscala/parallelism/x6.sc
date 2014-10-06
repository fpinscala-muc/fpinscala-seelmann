package fpinscala.parallelism

import java.util.concurrent.Executors
import Par._

object x6 {

  val es = Executors.newCachedThreadPool()        //> es  : java.util.concurrent.ExecutorService = java.util.concurrent.ThreadPool
                                                  //| Executor@5c331fd8[Running, pool size = 0, active threads = 0, queued tasks =
                                                  //|  0, completed tasks = 0]

  // 7.4.2 The law of forking

  val x = unit(1)                                 //> x  : java.util.concurrent.ExecutorService => java.util.concurrent.Future[Int
                                                  //| ] = <function1>

  fork(x)(es).get == x(es).get                    //> ### fork Thread[main,5,main]
                                                  //| res0: Boolean = true

  // 7.4.3 Breaking the law: a subtle bug

  val fixed = Executors.newFixedThreadPool(2)     //> fixed  : java.util.concurrent.ExecutorService = java.util.concurrent.ThreadP
                                                  //| oolExecutor@10867c21[Running, pool size = 0, active threads = 0, queued task
                                                  //| s = 0, completed tasks = 0]

  fork(fork(x))(fixed).get == x(fixed).get        //> ### fork Thread[main,5,main]
                                                  //| ### fork Thread[pool-2-thread-1,5,main]
                                                  //| res1: Boolean = true

  es.shutdown()
  fixed.shutdown()
}