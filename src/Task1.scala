import factory.{AlmostSortedArrayFactory, InvertedSortedArrayFactory, ShuffleArrayFactory}

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

object ArrayTbleau extends ArrayFactory{
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
//implementation le trait arrayfactory qui retourne un tableaau de taille size 
//avec pourcentage une constante dans la classe exp 30%
object AlmostSortedArrayFactory extends ArrayFactory{
  private val MisplacedRatio: Double = 0.30

  def create(size: Int): Array[Int] = {
    val array_int: Array[Int] = (0 until size).toArray

    if (size < 2) {
      return array_int
    }

    val random = new Random()
    val misplacedCount = math.max(0, math.min(size, math.round(size * MisplacedRatio).toInt))

    if (misplacedCount < 2) {
      return array_int
    }

    val indexPool = (0 until size).toArray
    // Fisher-Yates partiel: les `misplacedCount` premiers indices deviennent une selection aleatoire sans repetition.
    for (i <- 0 until misplacedCount) {
      val j = i + random.nextInt(size - i)
      val tmp = indexPool(i)
      indexPool(i) = indexPool(j)
      indexPool(j) = tmp
    }

    val selected = indexPool.take(misplacedCount)
    val selectedValues = selected.map(array_int(_))

    // Rotation pour garantir que tous les indices selectionnes recoivent une valeur differente.
    for (i <- selected.indices) {
      val source = (i + 1) % misplacedCount
      array_int(selected(i)) = selectedValues(source)
    }

    array_int
  }
}
//tache8



class SortApplication {
  def displayArray(a: Array[Int]): Unit = {
    println(a.mkString("{ ", ", ", " }"))
  }
}

object Task1 extends App {
  private val sort: SortApplication = new SortApplication()
  sort.displayArray(RandomFactory.create(4))
  sort.displayArray(InvertedSortedArrayFactory.create(4))
  sort.displayArray(ShuffleArrayFactory.create(7))
  sort.displayArray(AlmostSortedArrayFactory.create(10))
}

