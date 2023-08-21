package basics

object TypeClasses extends App {
  case class Person(name: String, age: Int)

  // 1: define type class as a trait
  trait JsonSerializer[T] {
    def toJson(value: T): String
  }

  // 2: implement type class Instances
  implicit object StringSerializer extends JsonSerializer[String] {
     override def toJson(value: String): String = value
  }

  implicit object IntSerializer extends JsonSerializer[Int] {
    override def toJson(value: Int): String = value.toString
  }

  implicit object PersonSerializer extends JsonSerializer[Person] {
    override def toJson(value: Person): String =
      s"""
         |{ "name": ${value.name}, "age": ${value.age} }
         |""".stripMargin.trim
  }

  // usage
  private def asJsonList[T](ls: List[T])(implicit serializer: JsonSerializer[T]): String = {
    ls.map(l => serializer.toJson(l))
      .mkString("[ ", ", ", " ]")
  }

  // test
  val peeps = Person("Mason", 6) :: Person("Maya", 5) :: Nil
  println(asJsonList(peeps))

}

/**
 * Type classes are a powerful concept in functional programming that allow you to define
 * behavior or operations for a set of types in a generic and extensible way.
 *
 * They provide a mechanism for adding functionality to existing types without modifying their original definitions.
 *
 * Type classes are not classes in the traditional object-oriented sense;
 * they are more like interfaces or contracts that define a set of operations that types must support.
*/
