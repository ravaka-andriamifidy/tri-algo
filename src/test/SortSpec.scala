package test

import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest._

class SortSpec extends propspec.AnyPropSpec with TableDrivenPropertyChecks with Matchers with GivenWhenThen {
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

  property("Selection sort should sort arrays") {
    forAll(factories) { f => {
      forAll(sizes) { s =>
        val input = f.create(s)
        val reference = input.clone().sorted
        val output = SelectionSort.sort(input)
        output shouldEqual reference
      }
    }
    }
  }
  property("Selection sort should sort *in place*") {
    forAll(factories) { f => {
      forAll(sizes) { s =>
        val input = f.create(s)
        val output = SelectionSort.sort(input)
        input eq output shouldBe (true)
      }
    }
    }
  }
}
