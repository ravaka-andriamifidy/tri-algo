package test

object AlmostSortedArrayFactory extends ArrayFactory{
  def create(size: Int): Array[Int] = {
    val array_int: Array[Int] = new Array[Int](size)
    for(i <- 0 until size by 2){
      array_int(i) = i
      array_int(i + 1) = if (i == 0) size - 1 else size - i
    }
    if(size % 2 != 0) array_int(size - 1) = (size - 1) / 2
    array_int
  }
}
