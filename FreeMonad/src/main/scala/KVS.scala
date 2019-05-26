sealed trait KVS[A]

case class Put[A](key: String, value: String, a: A) extends KVS[A]

case class Get[A](key: String, h: String => A) extends KVS[A]

case class Delete[A](key: String, a: A) extends KVS[A]

