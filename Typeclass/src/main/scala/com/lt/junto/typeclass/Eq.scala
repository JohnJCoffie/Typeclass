package com.lt.junto.typeclass

trait Eq[A] {
  def eq(a:A, b:A):Boolean
  def neq(a:A, b:A):Boolean = !eq(a, b)
}


