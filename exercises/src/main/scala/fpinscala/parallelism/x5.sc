package fpinscala.parallelism

import Par._

object x5 {

  val es = null                                   //> es  : Null = null

  // 7.4.1 The law of mapping

  map(unit(1))(_ + 1)(es) == unit(2)(es)          //> res0: Boolean = true

  val x = 1                                       //> x  : Int = 1
  def f(a: Int): Int = (a + 1)                    //> f: (a: Int)Int
  map(unit(x))(f)(es) == unit(f(x))(es)           //> res1: Boolean = true

  def id[A](a: A): A = (a)                        //> id: [A](a: A)A
  map(unit(x))(id)(es) == unit(id(x))(es)         //> res2: Boolean = true
  map(unit(x))(id)(es) == unit(x)(es)             //> res3: Boolean = true
  val y = unit(x)                                 //> y  : java.util.concurrent.ExecutorService => java.util.concurrent.Future[Int
                                                  //| ] = <function1>
  map(y)(id)(es) == y(es)                         //> res4: Boolean = true

  map(unit(x))(f)(es) == unit(f(x))(es)           //> res5: Boolean = true

}