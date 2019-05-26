package com.lt.junto.typeclass.department

//import org.joda.time.DateTime

case class Employee(name: String, department: String, id: Long/*, hiredDate: DateTime*/)

object Employee {
  /* Default Typeclass instances, used when Typeclass author wants to provide default behavior. */
  import com.lt.junto.typeclass.Eq

  implicit lazy val employeeEntityEq = new Eq[Employee] {
    override def eq(a:Employee, b: Employee) = a.id == b.id
  }
}
