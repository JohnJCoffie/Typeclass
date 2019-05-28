package com.lt.junto.typeclass

import scala.util.{Failure, Success, Try}

/*TODO: Things to understand
1.) What does -A do that just using A doesn't?
2.) What does +B do that just using B doesn't?
3.) In MyProcessor object's def process(): does implicitly[....] and adapter.process(...) work behave the same?
4.) What does MyProcessor object's implicit class MyProcessorOps[A,B] add to the functionality?
*/

trait MyProcessor[A, B, C[_]] {
  def process(a: A): C[B]
}


object MyProcessor {
  def apply[A, B, C[_]](implicit processor: MyProcessor[A, B, C]): MyProcessor[A, B, C] = processor

  def process[A, B, C[_]](input: A)(implicit adapter: MyProcessor[A, B, C]): C[B] = {
    //    implicitly[MyProcessor[A,B]].process(input)
    adapter.process(input)
  }

  implicit class MyProcessorOps[A, B, C[_]](a: A)(implicit processor: MyProcessor[A, B, C]) {
    def process(): C[B] = MyProcessor[A, B, C].process(a)
  }

  object Ops {

    implicit object IntStringProcessor extends MyProcessor[Int, String, Try] {
      def process(a: Int): Try[String] = Failure(new Exception("My Kaboom"))
    }

    implicit object ThingThingProcessor extends MyProcessor[Thing, Thing, Try] {
      override def process(a: Thing): Try[Thing] = Success(ThingB("Good"))
    }

    implicit object ThingAThingBProcessor extends MyProcessor[ThingA, ThingB, Try] {
      override def process(a: ThingA): Try[ThingB] = Success(ThingB(a.i.toString))
    }

    implicit object SomeThingAThingBProcessor extends MyProcessor[ThingA, ThingB, Option] {
      override def process(a: ThingA): Option[ThingB] = Some(ThingB(a.i.toString))
    }

  }

}


sealed trait Thing

case class ThingA(i: Int) extends Thing

case class ThingB(s: String) extends Thing

//case class ThingSubA(override val i: Int, j: Int) extends ThingA(i)
//case class ThingSubB(override val s: String, t: Int) extends ThingB(s)