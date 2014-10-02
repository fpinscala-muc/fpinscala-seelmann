package fpinscala.parallelism

import Par.Par
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

object y {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  /////////////////////////////
  
	val unit: Par[Int] = Par.unit(1)          //> unit  : java.util.concurrent.ExecutorService => java.util.concurrent.Future[
                                                  //| Int] = <function1>
  unit(null).get()                                //> res0: Int = 1
  
  
  
  
  /////////////////////////////
  
  val es = Executors.newCachedThreadPool()        //> es  : java.util.concurrent.ExecutorService = java.util.concurrent.ThreadPool
                                                  //| Executor@62787c64[Running, pool size = 0, active threads = 0, queued tasks =
                                                  //|  0, completed tasks = 0]

  val future1: Future[Int] = unit(es)             //> future1  : java.util.concurrent.Future[Int] = UnitFuture(1)

  /////////////////////////////

  val future: Future[Int] = Par.run(es)(unit)     //> future  : java.util.concurrent.Future[Int] = UnitFuture(1)
  val result: Int = future.get()                  //> result  : Int = 1

  /////////////////////////////

  val res: Par[Int] = Par.fork(unit)              //> res  : java.util.concurrent.ExecutorService => java.util.concurrent.Future[I
                                                  //| nt] = <function1>
  /////////////////////////////

  def sum(ints: IndexedSeq[Int]): Par[Int] =
    if (ints.length <= 1)
      Par.unit(ints.headOption getOrElse 0)
    else {
      val (l, r) = ints.splitAt(ints.length / 2)
      Par.fork(Par.map2(Par.fork(sum(l)), Par.fork(sum(r)))(_ + _))
    }                                             //> sum: (ints: IndexedSeq[Int])java.util.concurrent.ExecutorService => java.uti
                                                  //| l.concurrent.Future[Int]

	sum(IndexedSeq(1,2,3,4))(es) get          //> res1: Int = 10

  /////////////////////////////

	val s = Par.sortPar(Par.unit(List(4,3,2,1)))
                                                  //> s  : java.util.concurrent.ExecutorService => java.util.concurrent.Future[Li
                                                  //| st[Int]] = <function1>
  Par.run(es)(s)                                  //> res2: java.util.concurrent.Future[List[Int]] = UnitFuture(List(1, 2, 3, 4))
                                                  //| 

  es.shutdown()
}