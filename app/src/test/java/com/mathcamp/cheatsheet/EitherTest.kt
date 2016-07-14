package com.mathcamp.cheatsheet

import io.kotlintest.specs.FreeSpec

/**
 * Created by bkase on 7/13/16.
 */

class EitherTest: FreeSpec() {
  init {
    "Either should be constructable left or right" - {
      val l = Either.Left<Int>(1)
      val r = Either.Right<String>("Hello")
      var x: Either<Int, String> = l
      x = r
    }

    "Either should have right-biased map" - {
      "it should ignore the left" - {
        val start: Either<Int, String> = Either.Left(5)
        val mapped = start.map { it + "!" }
        start shouldBe mapped
      }
      "it should transform right" - {
        val start: Either<Int, String> = Either.Right("hello")
        val mapped = start.map { it + "!" }
        mapped shouldBe Either.Right<String>("hello!")
      }
    }
  }
}
