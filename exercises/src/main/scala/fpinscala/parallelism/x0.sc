object x0 {

  def sum(ints: Seq[Int]): Int =
    ints.foldLeft(0)((a, b) => a + b)             //> sum: (ints: Seq[Int])Int
    
  sum(Seq(1, 2, 3, 4))                            //> res0: Int = 10

}