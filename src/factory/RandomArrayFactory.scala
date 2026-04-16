package factory

import scala.util.Random

object RandomArrayFactory extends ArrayFactory{
  def create(size: Int): Array[Int] = {
    val array_int: Array[Int] = new Array[Int](size)
    val random = new Random()
    for(i <- 0 until size){
      array_int(i) = random.nextInt(size + 1)
    }
    array_int
  }
}
