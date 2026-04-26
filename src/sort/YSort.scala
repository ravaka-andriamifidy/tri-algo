package sort

 object YSort extends Sort {
   def sort(array: Array[Int]): Array[Int] = {
      if (array.length > 1) {
        val size_left = array.length / 2;
        val size_right = array.length - size_left

        var array_left = new Array[Int](size_left)
        var array_right = new Array[Int](size_right)

        // fill the left array
        for (j <- 0 until size_left) {
          array_left(j)= array(j)
        }

        // fill the right array
        for (i <- size_left until size_left + size_right) {
          array_right(i - size_left) = array(i)
        }

        // recursive on right and left array
        array_left = sort(array_left)
        array_right = sort(array_right)

        var index, index_left, index_right = 0;

        while ( array_left.length != index_left && array_right.length != index_right) {
          if (array_left(index_left) <= array_right(index_right)) {
            array(index) = array_left(index_left)
            index += 1;
            index_left += 1
          } else {
            array(index) = array_right(index_right)
            index += 1
            index_right += 1
          }
        }

        while (array_left.length != index_left) {
          array(index) = array_left(index_left)
          index += 1
          index_left += 1
        }
        
        while ( array_right.length != index_right) {
          array(index) = array_right(index_right)
          index += 1
          index_right += 1
      }
    }
    array
  }
}
