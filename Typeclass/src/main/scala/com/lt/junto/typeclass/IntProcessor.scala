package com.lt.junto.typeclass
import scala.util.{Failure, Success, Try}

object IntProcessor {
  implicit lazy val intProcessor = new Processor[Int] {
    override def process(a: Int): Try[String] = Failure(new Exception("Boom"))
  }

}

object DoubleProcessor {
  implicit lazy val doubleProcessor = new Processor[Double] {
    override def process(a: Double): Try[String] = Success(("Good"))
  }
}


object DoubleProcessor2 {
  implicit lazy val doubleProcessor2 = new Processor2[Double, String] {
    override def process2(a: Double): Try[String] = Success("Good!")
  }
}
object IntString {

  implicit object IntToStringMapper extends Processor2[Int, String] {
    override def process2(i: Int): Try[String] = Success(i.toString)
  }

}


