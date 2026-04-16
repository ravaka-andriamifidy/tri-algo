package factory

trait ArrayFactory {
  def create(size: Int): Array[Int]
}
