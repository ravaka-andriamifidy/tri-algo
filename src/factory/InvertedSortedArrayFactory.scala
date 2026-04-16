package factory

object InvertedSortedArrayFactory extends ArrayFactory{
  def create(size: Int): Array[Int] = {
    val array_int: Array[Int] = new Array[Int](size)
    for(i <- 0 until size){
      array_int(i) = size - (i + 1)
    }
    array_int
  }
}
