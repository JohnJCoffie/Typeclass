package com.lt.junto.typeclass

trait Eq[A] {
  def eq(a:A, b:A):Boolean
  def neq(a:A, b:A):Boolean = !eq(a, b)
}

object Eq {
  def eq[A](lhs:A, rhs:A)(implicit adapter: Eq[A]) = adapter.eq(lhs, rhs)
  def neq[A: Eq](lhs:A, rhs:A) = implicitly[Eq[A]].neq(lhs, rhs)

  implicit lazy val doubleHasEq = new Eq[Double] {
    override def eq(a: Double, b: Double) = a == b
  }
}
