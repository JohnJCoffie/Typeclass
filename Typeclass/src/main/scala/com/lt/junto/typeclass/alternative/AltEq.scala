package com.lt.junto.typeclass.alternative

import com.lt.junto.typeclass.Eq

object AltEq {

  implicit lazy val doubleHasEq = new Eq[Double] {
    override def eq(a: Double, b: Double) = a.toInt == b.toInt
  }
}
