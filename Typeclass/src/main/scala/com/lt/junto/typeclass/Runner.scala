package com.lt.junto.typeclass

object Runner extends App {
  import IntProcessor._
  import DoubleProcessor._
  import ExtraImplicits._
  val x = Eq.eq(2.0, 2.0)(Eq.doubleHasEq)
  val y = Processor.process(7)(IntProcessor.intProcessor)
  val z = Processor.process(7)
  val a = 7
  val s = a.process()
  val b = 8.8
  val t = b.process()
  println(x)
  println(y)
  println(z)
  println(s)
  println(t)
}
