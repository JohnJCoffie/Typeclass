//package com.it.junto.typeclass
//
//import com.lt.junto.typeclass.Eq
//import org.scalatest.{FunSuite, Matchers}
//
//class EqTest extends FunSuite with Matchers {
//  test("Test two doubles for specified equality") {
//    val expected = 1.0
//    val actual = 1.0
//    assert(Eq.eq(expected, actual)(Eq.doubleHasEq), s"$expected should be equal to $actual")
//  }
//
//  test("Test two doubles for default equality") {
//    val expected = 1.0
//    val actual = 1.0
//    assert(Eq.eq(expected, actual), s"$expected should be equal to $actual")
//  }
//
//  test("Test two doubles for default equality with ====") {
//    import com.lt.junto.typeclass.ExtraImplicits._
//    val expected = 1.0
//    val actual = 1.0
//    assert(expected ==== actual, s"$expected should be equal to $actual")
//  }
//
//  test("Test two doubles for whole number equality with ====") {
//    import com.lt.junto.typeclass.ExtraImplicits._
//    import com.lt.junto.typeclass.alternative.AltEq.doubleHasEq
//    val expected = 1.0
//    val actual = 1.1
//    assert(expected ==== actual, s"$expected should be whole number equal to $actual")
//  }
//
//  test("Test two doubles for specified inequality") {
//    val expected = 1.0
//    val actual = 5.0
//    assert(Eq.neq(expected, actual)(Eq.doubleHasEq), s"$expected should not be equal to $actual")
//  }
//
//  test("Test two doubles for default inequality") {
//    val expected = 1.0
//    val actual = 5.0
//    assert(Eq.neq(expected, actual), s"$expected should not be equal to $actual")
//  }
//
//  test("Test two doubles for ???default inequality with !===") {
//    import com.lt.junto.typeclass.ExtraImplicits._
//    val expected = 1.0
//    val actual = 5.0
//    assert(expected !=== actual, s"$expected should not be equal to $actual")
//  }
//
//  test("Test two doubles for default inequality with !===") {
//    import com.lt.junto.typeclass.ExtraImplicits._
//    import com.lt.junto.typeclass.alternative.AltEq.doubleHasEq
//    val expected = 1.0
//    val actual = 2.1
//    assert(expected !=== actual, s"$expected should not be equal to $actual")
//  }
//
//  test("Test two employees are equal") {
//    import org.joda.time.DateTime
//    import org.joda.time.format.DateTimeFormat
//    import com.lt.junto.typeclass.ExtraImplicits._
//    import com.lt.junto.typeclass.department._
//
//    val janeDoe = Employee("Jane Doe", "Human Resources", 12001, DateTime.parse("07-06-2013", DateTimeFormat.forPattern("MM-dd-yyyy")))
//    val janeSmith = janeDoe.copy(name = "Jane Smith")
//
//    assert(janeDoe != janeSmith)
//    assert(janeDoe ==== janeSmith)
//  }
//}
