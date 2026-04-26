package factory

import scala.util.Random

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
