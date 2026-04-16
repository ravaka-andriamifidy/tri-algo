import scala.util.Random

trait ArrayFactory {
  def create(size: Int): Array[Int]
}

object RandomFactory extends ArrayFactory{
  def create(size: Int): Array[Int] = {
    val array_int: Array[Int] = new Array[Int](size)
    val random = new Random()
    for(i <- 0 until size){
      array_int(i) = random.nextInt(size + 1)
    }
  array_int
  }
}

object InvertedSortedArrayFactory extends ArrayFactory{
  def create(size: Int): Array[Int] = {
    val array_int: Array[Int] = new Array[Int](size)
    for(i <- 0 until size){
      array_int(i) = size - (i + 1)
    }
    array_int
  }
}

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


class SortApplication {
  def displayArray(a: Array[Int]): Unit = {
    println(a.mkString("{ ",", ", " }"))
  }
}

object Task1 extends  App{
  private val sort: SortApplication = new SortApplication()
  sort.displayArray(RandomFactory.create(4))
  sort.displayArray(InvertedSortedArrayFactory.create(4))
  sort.displayArray(ShuffleArrayFactory.create(7))
  sort.displayArray(AlmostSortedArrayFactory.create(4))
}
