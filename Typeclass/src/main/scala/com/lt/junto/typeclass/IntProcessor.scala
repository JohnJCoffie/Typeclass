package com.lt.junto.typeclass
import scala.util.{Failure, Success, Try}

object IntProcessor {
  implicit lazy val intProcessor = new Processor[Int] {
//    override def process(a: Int): Try[Unit] = Success(())
    override def process(a: Int): Try[Unit] = Failure(new Exception("Boom"))
  }

}

object DoubleProcessor {
  implicit lazy val doubleProcessor = new Processor[Double] {
    override def process(a: Double): Try[Unit] = Success(())
  }
}