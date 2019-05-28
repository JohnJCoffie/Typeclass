package com.lt.junto.typeclass

import com.lt.junto.typeclass.Mapper.{ClassA, ClassB, TypeA, TypeB}

import scala.util.{Failure, Try}

object Runner extends App {
  import IntProcessor._
  import DoubleProcessor._
  import ExtraImplicits._
  val x = Eq.eq(2.0, 2.0)(Eq.doubleHasEq)
  val y = Processor.process(7)(IntProcessor.intProcessor)
  val z = Processor.process(7)
  val a: Int = 7
  val s = a.process()
  val b = 8.8
  val t = b.process()
  implicit object IntProcessor2 extends Processor2[Int, String] {
      def process2(a: Int): Try[String] = Failure(new Exception("Kabooms"))
  }
  implicit object IntToStringMapper extends Mapper[Int, String] {
    def toDto(i: Int): String = i.toString
  }
import Processor2._
  val n = Processor2.process2(7.7)(DoubleProcessor2.doubleProcessor2)
  val o = Processor2.process2(7)
  val p: Try[String] = a.process2()
  println(x)
  println(y)
  println(z)
  println(s)
  println(t)
  println("---")
  println(n)
  println(p)


  import MyProcessor.Ops.IntStringProcessor
  val q: Try[String] = MyProcessor.process(a)
  println(q)

//  import Ops.ThingAThingBProcessor
  import MyProcessor.Ops.SomeThingAThingBProcessor
  import MyProcessor._
  val r1: Option[Thing] = MyProcessor.process(ThingA(a))
  val r2: Option[ThingB] = ThingA(a).process()
  println(r1)

//  implicit object DoubleToIntMapper extends Mapper[Double, Int] {
//    def toDto(i: Double): Int = i.toInt
//  }
//
//  import Mapper._
//
//  val s1: String = 42.toDto
//  println(s1)
//
//  val v: Double = 49.7
//  val s2: Int = v.toDto
//  println(s2)

  val a11 = ClassA("john")
  val a12 = ClassB("ann")

  a11 match {
    case zz: TypeA => println(zz.name1)
    case _ => println("bad")
  }

  a12 match {
    case zz: TypeB => println(zz.name2)
    case _ => println("bad")
  }

}
