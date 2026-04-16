import test.ShuffleArrayFactory

trait Sort {
  def sort(a: Array[Int]): Array[Int]
}

object SelectionSort extends Sort{
  def sort(a: Array[Int]): Array[Int] = {
    for (i <- 0 until a.length - 1) {
      var minIndex = i
      for (j <- i + 1 until a.length) {
        if (a(j) < a(minIndex)) {
          minIndex = j
        }
      }
      if (minIndex != i) {
        val temp = a(i)
        a(i) = a(minIndex)
        a(minIndex) = temp
      }
    }
    a
  }
}

object Task3 extends  App{
  private val sort: SortApplication = new SortApplication()
  sort.displayArray(SelectionSort.sort(ShuffleArrayFactory.create(7)))
}