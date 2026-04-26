import java.io.{File, PrintWriter}
import factory.{AlmostSortedArrayFactory, InvertedSortedArrayFactory, RandomArrayFactory, ShuffleArrayFactory}
import sort.SelectionSort

import scala.collection.mutable.ArrayBuffer

object Task5 extends App{
  def executeSort(size: Int, array: Array[Int], factoryName: String): Long = {
    val start = System.currentTimeMillis();
    var data = SelectionSort.sort(array);
    val end = System.currentTimeMillis();

    end-start
  }

  val writer = new PrintWriter(new File("res/results.csv"))
  val factories = Array("Random", "Shuffle","Inverted", "AlmostSorted")
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
