object x4 {

  case class Par[A](a: A) {}
  object Par {
    def unit[A](a: A): Par[A] = Par(a)
    def lazyUnit[A](a: => A): Par[A] = fork(unit(a))
    def run[A](parA: Par[A]): A = parA.a
    def map2[A, B, C](a: Par[A], b: Par[B])(f: (A, B) => C): Par[C] = unit(f(run(a), run(b)))
    def fork[A](a: => Par[A]): Par[A] = a
    
  }

  def sum(ints: IndexedSeq[Int]): Par[Int] =
    if (ints.length <= 1)
      Par.unit(ints.headOption getOrElse 0)
    else {
      val (l, r) = ints.splitAt(ints.length / 2)
      Par.map2(Par.fork(sum(l)), Par.fork(sum(r)))(_ + _)
    }                                             //> sum: (ints: IndexedSeq[Int])x4.Par[Int]

  Par.run(sum(IndexedSeq(1, 2, 3, 4)))            //> res0: Int = 10
  
}