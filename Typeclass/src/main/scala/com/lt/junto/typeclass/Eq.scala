package com.lt.junto.typeclass

trait Eq[A] {
  def eq(a:A, b:A):Boolean
  def neq(a:A, b:A):Boolean = !eq(a, b)
}

object Eq {
  /* Adapter methods */

  def apply[A](implicit eq: Eq[A]): Eq[A] = eq

  def eq[A](lhs:A, rhs:A)(implicit adapter: Eq[A]) = adapter.eq(lhs, rhs)

//  def neq[A: Eq](lhs:A, rhs:A) = implicitly[Eq[A]].neq(lhs, rhs)
  def neq[A: Eq](lhs:A, rhs:A) = Eq[A].neq(lhs, rhs)

  /* Default Typeclass instances, used when Typeclass author wants to provide default behavior. */
  implicit lazy val doubleHasEq = new Eq[Double] {
    override def eq(a: Double, b: Double) = a == b
  }
}

object ExtraImplicits {
  implicit def infixOps[T:Eq](lhs:T) = new EqOps(lhs)
}

class EqOps[T](lhs:T)(implicit e:Eq[T]) {
  def ====(rhs:T) = e.eq(lhs, rhs)
  def !===(rhs:T) = e.neq(lhs, rhs)
}
