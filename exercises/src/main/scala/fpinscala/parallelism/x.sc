object x {

	def sum71(ints: IndexedSeq[Int]): Int =
	  if (ints.size <= 1)
	    ints.headOption getOrElse 0
		else {
		  val (l,r) = ints.splitAt(ints.length/2)
		  sum71(l) + sum71(r)
	  }                                       //> sum71: (ints: IndexedSeq[Int])Int

	sum71(IndexedSeq(1,2,3,4))                //> res0: Int = 10
  
  trait Par72[A]
  case class Par72c[A](a: A) extends Par72[A]
  object Par72 {
    def unit[A](a: => A): Par72[A] = Par72c(a)
    def get[A](a: Par72[A]): A = ???
  }
  
	def sum72(ints: IndexedSeq[Int]): Int =
	  if (ints.size <= 1)
	    ints.headOption getOrElse 0
		else {
		  val (l,r) = ints.splitAt(ints.length/2)
      val sumL: Par72[Int] = Par72.unit(sum72(l))
      val sumR: Par72[Int] = Par72.unit(sum72(r))
      Par72.get(sumL) + Par72.get(sumR)
	  }                                       //> sum72: (ints: IndexedSeq[Int])Int

	sum72(IndexedSeq(1,2,3,4))                //> scala.NotImplementedError: an implementation is missing
                                                  //| 	at scala.Predef$.$qmark$qmark$qmark(Predef.scala:252)
                                                  //| 	at x$$anonfun$main$1$Par72$3$.get(x.scala:17)
                                                  //| 	at x$$anonfun$main$1.x$$anonfun$$sum72$1(x.scala:27)
                                                  //| 	at x$$anonfun$main$1$$anonfun$1.apply$mcI$sp(x.scala:25)
                                                  //| 	at x$$anonfun$main$1$$anonfun$1.apply(x.scala:25)
                                                  //| 	at x$$anonfun$main$1$$anonfun$1.apply(x.scala:25)
                                                  //| 	at x$$anonfun$main$1$Par72$3$.unit(x.scala:16)
                                                  //| 	at x$$anonfun$main$1.x$$anonfun$$sum72$1(x.scala:25)
                                                  //| 	at x$$anonfun$main$1.apply$mcV$sp(x.scala:30)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.$execute(Wor
                                                  //| ksheetSupport.scala:75)
                                                  //| 	at x$.main(x.scala:1)
                                                  //| 	at x.main(x.scala)
  
  
  
}