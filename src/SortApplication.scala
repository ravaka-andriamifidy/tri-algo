import Task5.executeSort
import factory.{AlmostSortedArrayFactory, InvertedSortedArrayFactory, RandomArrayFactory, ShuffleArrayFactory}
import sort.{SelectionSort, YSort}

import java.io.{File, PrintWriter}
import scala.collection.mutable.ArrayBuffer

class SortApplication {
  def displayArray(a: Array[Int]): Unit = {
    println(a.mkString("{ ", ", ", " }"))
  }

  def executeSort(size: Int, array: Array[Int], factoryName: String): Long = {
    val start = System.currentTimeMillis();
    var data = SelectionSort.sort(array);
    val end = System.currentTimeMillis();
    end-start
  }
}

object SortApplicationMain extends App {
  private val sort: SortApplication = new SortApplication()


  val factories = Array("Random", "Shuffle","Inverted", "AlmostSorted")
  val sortName = Array("YSort", "Selection")


  for(s <- sortName){
    val writer = new PrintWriter(new File(s"res/${s}_result.csv"))
    writer.write(s"taille,${factories.mkString(",")}\n")

    for (size <- 10000 to 100000 by 10000) {
      var execution_time: Long = 0
      var times: ArrayBuffer[String] = new ArrayBuffer[String](4)
      for(factory <- factories){
        var array: Array[Int] =
          if (factory == "RandomArrayFactory") RandomArrayFactory.create(size)
          else if (factory == "ShuffleArrayFactory")  ShuffleArrayFactory.create(size)
          else if (factory == "InvertedSortedArrayFactory")  InvertedSortedArrayFactory.create(size)
          else AlmostSortedArrayFactory.create(size)

        execution_time = executeSort(size, array, factory)
        times.append(execution_time.toString)
      }
      writer.write(s"$size,${times.mkString(",")}\n")
    }
    writer.close()
  }
}

