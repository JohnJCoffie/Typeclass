package com.lt.junto.typeclass

import scala.util.Try

trait Processor2[-A,+B] {
  def process2(a: A): Try[B]
}

trait Processor[A] {
  def process(a: A): Try[String]
}

object Processor {
  def apply[A](implicit processor: Processor[A]): Processor[A] = processor
  def process[A](input:A)(implicit adapter: Processor[A]): Try[String] = adapter.process(input)
}

class ProcessorOps[A](a: A)(implicit processor: Processor[A]) {
  def process(): Try[String] = processor.process(a)
}

trait Eq[A] {
  def eq(a:A, b:A):Boolean
  def neq(a:A, b:A):Boolean = !eq(a, b)
}

object Eq {
  /* Adapter methods */

  def apply[A](implicit eq: Eq[A]): Eq[A] = eq

  def eq[A](lhs:A, rhs:A)(implicit adapter: Eq[A]) = adapter.eq(lhs, rhs)

  def neq[A: Eq](lhs:A, rhs:A) = Eq[A].neq(lhs, rhs)

  /* Default Typeclass instances, used when Typeclass author wants to provide default behavior. */
  implicit lazy val doubleHasEq = new Eq[Double] {
    override def eq(a: Double, b: Double) = a == b
  }
}
//  def neq[A: Eq](lhs:A, rhs:A) = implicitly[Eq[A]].neq(lhs, rhs)

object ExtraImplicits {
  implicit def infixOps[T:Eq](lhs:T) = new EqOps(lhs)
  implicit def infixOps[A:Processor](a:A) = new ProcessorOps(a)
}


class EqOps[T](lhs:T)(implicit e:Eq[T]) {
  def ====(rhs:T) = e.eq(lhs, rhs)
  def !===(rhs:T) = e.neq(lhs, rhs)
}

object Processor2 {
  def apply[A,B](implicit processor2: Processor2[A,B]): Processor2[A,B] = processor2
  def   process2[A,B](input:A)(implicit adapter: Processor2[A,B]): Try[B] = implicitly[Processor2[A,B]].process2(input)//adapter.process2(input)

  implicit class Processor2Ops[A,B](a: A)(implicit processor2: Processor2[A,B]) { //NEEDED for implicit conversion
    def process2(): Try[B] = Processor2[A,B].process2(a)
  }
}


trait Mapper[T, D] {
  def toDto(i: T): D
}

object Mapper {
  def map[T, D](i: T)(implicit mapper: Mapper[T, D]): D = implicitly[Mapper[T, D]].toDto(i)

  def apply[T, D](implicit mapper: Mapper[T, D]): Mapper[T, D] = mapper

  implicit def optionMapper[T, D](implicit mapper: Mapper[T, D]): Mapper[Option[T], Option[D]] = {
    new Mapper[Option[T], Option[D]] {
      override def toDto(i: Option[T]): Option[D] = i.map(mapper.toDto)
    }
  }

  implicit def seqMapper[T, D](implicit mapper: Mapper[T, D]): Mapper[Seq[T], Seq[D]] = {
    new Mapper[Seq[T], Seq[D]] {
      override def toDto(i: Seq[T]): Seq[D] = i.map(mapper.toDto)
    }
  }

  implicit class MapperOps1[T, D](a: T)(implicit m: Mapper[T, D]) {
    def toDto = Mapper[T, D].toDto(a)
  }
  /*
  // Neither works
  implicit class MapperOps1[T,D](a: T) {
    def toDto = Mapper[T,D].toDto(a)
  }
  implicit class MapperOps2[T,D](a: T) {
    def toDto(implicit mapper: Mapper[T,D]) = Mapper[T,D].toDto(a)
  }
  implicit class MapperOps3[T,D](a: T) {
    def toDto[D](implicit mapper: Mapper[T,D]): D Mapper[T,D].toDto(a)
  }

   */

  trait TypeA {
    def name1: String
  }

  trait TypeB {
    def name2: String
  }

  case class ClassA(name1: String) extends TypeA
  case class ClassB(name2: String) extends TypeB

}