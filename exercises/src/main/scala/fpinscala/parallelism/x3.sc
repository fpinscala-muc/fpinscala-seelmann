object x3 {

  object Par {
    def unit[A](a: => A): Par[A] = Par(a)
    def get[A](a: Par[A]): A = a.a
    def map2[A, B, C](a: Par[A], b: Par[B])(f: (A, B) => C): Par[C] =
      unit(f(get(a), get(b)))
  }
  case class Par[A](a: A) {}

  def sum(ints: IndexedSeq[Int]): Par[Int] =
    if (ints.size <= 1)
      Par.unit(ints.headOption getOrElse 0)
    else {
      val (l, r) = ints.splitAt(ints.length / 2)
      Par.map2(sum(l), sum(r))(_ + _)
    }                                             //> sum: (ints: IndexedSeq[Int])x3.Par[Int]

  Par.get(sum(IndexedSeq(1, 2, 3, 4)))            //> res0: Int = 10

}