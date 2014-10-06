object x2 {

  case class Par[A](a: A) {}
  object Par {
    def unit[A](a: => A): Par[A] = Par(a)
    def get[A](a: Par[A]): A = a.a
  }

  def sum(ints: IndexedSeq[Int]): Int =
    if (ints.size <= 1)
      ints.headOption getOrElse 0
    else {
      val (l, r) = ints.splitAt(ints.length / 2)
      val sumL: Par[Int] = Par.unit(sum(l))
      val sumR: Par[Int] = Par.unit(sum(r))
      Par.get(sumL) + Par.get(sumR)
    }                                             //> sum: (ints: IndexedSeq[Int])Int

  sum(IndexedSeq(1, 2, 3, 4))                     //> res0: Int = 10

}