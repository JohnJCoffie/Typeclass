package com.it.junto.typeclass

import com.lt.junto.typeclass.Eq
import org.scalatest.{FunSuite, Matchers}

class EqTest extends FunSuite with Matchers {
  test("Test two doubles for specified equality") {
    val expected = 1.0
    val actual = 1.0
    assert(Eq.eq(expected, actual)(Eq.doubleHasEq), s"$expected should be equal to $actual")
  }

  test("Test two doubles for default equality") {
    val expected = 1.0
    val actual = 1.0
    assert(Eq.eq(expected, actual), s"$expected should be equal to $actual")
  }

  test("Test two doubles for default equality with ====") {
    import com.lt.junto.typeclass.ExtraImplicits._
    val expected = 1.0
    val actual = 1.0
    assert(expected ==== actual, s"$expected should be equal to $actual")
  }

  test("Test two doubles for specified inequality") {
    val expected = 1.0
    val actual = 5.0
    assert(Eq.neq(expected, actual)(Eq.doubleHasEq), s"$expected should not be equal to $actual")
  }

  test("Test two doubles for default inequality") {
    val expected = 1.0
    val actual = 5.0
    assert(Eq.neq(expected, actual), s"$expected should not be equal to $actual")
  }

  test("Test two doubles for default inequality with !===") {
    import com.lt.junto.typeclass.ExtraImplicits._
    val expected = 1.0
    val actual = 5.0
    assert(expected !=== actual, s"$expected should not be equal to $actual")
  }
}
