package com.it.junto.typeclass

import org.scalatest.{FunSuite, Matchers}

class EqTest extends FunSuite with Matchers {
  test("Placeholder") {
    val expected = 1L
    val actual = 1L
    actual shouldBe expected
  }
}
