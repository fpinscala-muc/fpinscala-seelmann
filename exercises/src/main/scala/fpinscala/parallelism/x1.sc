object x1 {

  def sum(ints: IndexedSeq[Int]): Int =
    if (ints.size <= 1)
      ints.headOption getOrElse 0
    else {
      val (l, r) = ints.splitAt(ints.length / 2)
      sum(l) + sum(r)
    }                                             //> sum: (ints: IndexedSeq[Int])Int

  sum(IndexedSeq(1, 2, 3, 4))                     //> res0: Int = 10

}