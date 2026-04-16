package factory

object ShuffleArrayFactory extends ArrayFactory{
  def create(size: Int): Array[Int] = {
    val array_int: Array[Int] = new Array[Int](size)
    for(i <- 0 until size){
      if(i % 2 == 0){
        array_int(i) =  i / 2
      }
      else{
        array_int(i) = size - (i / 2 + 1)
      }
    }
    array_int
  }
}