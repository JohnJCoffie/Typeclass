package com.lt.junto.typeclass

import scala.util.Try


trait Processor[A] {
  def process(a: A): Try[Unit]
}

object Processor {
  def apply[A](implicit processor: Processor[A]): Processor[A] = processor
  def process[A](input:A)(implicit adapter: Processor[A]) = adapter.process(input)
}

class ProcessorOps[A](a: A)(implicit processor: Processor[A]) {
  def process(): Try[Unit] = processor.process(a)
}

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
  implicit def infixOps[A:Processor](a:A) = new ProcessorOps(a)
}

class EqOps[T](lhs:T)(implicit e:Eq[T]) {
  def ====(rhs:T) = e.eq(lhs, rhs)
  def !===(rhs:T) = e.neq(lhs, rhs)
}
