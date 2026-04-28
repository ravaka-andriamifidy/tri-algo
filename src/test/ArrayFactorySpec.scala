package test

import factory.{InvertedSortedArrayFactory, RandomArrayFactory, ShuffleArrayFactory}
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest._

class ArrayFactorySpec extends propspec.AnyPropSpec with TableDrivenPropertyChecks with Matchers {

  val factories =
    Table(
      "factories",
      RandomArrayFactory,
      InvertedSortedArrayFactory,
      ShuffleArrayFactory
    )

  val sizes =
    Table(
      "sizes",
      0,
      1,
      10,
      100,
      43334,
    )

  property("ArrayFactory should return an array of the right size") {
    forAll(factories) { f => {
      forAll(sizes) { s =>
        f.create(s).length should be(s)
      }
    }
    }
  }

   val invertedSortedArrayFactoryValues =
     Table(
       "invertedSortedArrayFactoryValues",
       Array(0),
       (1 to 0 by -1).toArray,
       (177 to 0 by -1).toArray
     )

  property("The InvertedSortedArrayFactory should create arrays that looks like") {
    forAll(invertedSortedArrayFactoryValues) { v =>
      InvertedSortedArrayFactory.create(v.length) should be (v)
    }
  }

  val shuffleArrayFactoryValues =
    Table(
      "ShuffleArrayFactoryValues",
      Array(0),
      Array(0, 1),
      Array(0, 3, 1, 2),
      Array(0, 4, 1, 3, 2),
      Array(0, 6, 1, 5, 2, 4, 3),
      Array(0,32,1,31,2,30,3,29,4,28,5,27,6,26,7,25,8,24,9,23,10,22,11,21,12,20,13,19,14,18,15,17,16)
    )

  property("The ShuffleArrayFactory should create arrays that looks like") {
    forAll(shuffleArrayFactoryValues) { v =>
      ShuffleArrayFactory.create(v.length) should be (v)
    }
  }

  val randomArrayFactorySizes =
    Table(
      "randomArrayFactorySizes",
      1,
      1550,
      1234,
      5678
    )

  property("The RandomArrayFactory should create arrays that looks like") {
    forAll(randomArrayFactorySizes) { s =>
      RandomArrayFactory.create(s).max should be <= s
    }
  }
}