class Free[F[_], A](implicit FUNC: Functor[F]) {
  def flatMap[B](f: A => Free[F, B]): Free[F, B] =
    this match {
      case Done(a) => f(a)
      case More(k) => More(FUNC.map(k)(_ flatMap f))
    }

  def map[B](f: A => B): Free[F, B] = flatMap(x => Done(f(x)))
}

case class Done[F[_] : Functor, A](a: A) extends Free[F, A]

case class More[F[_] : Functor, A](k: F[Free[F, A]]) extends Free[F, A]

/*
Free[KVS, Unit] = More(Put(k, v, Done(())))
Free[KVS, String] = More(Get(k, v => Done(v)))
Free[KVS, Unit] = More(Delete(k, Done(())))
 */