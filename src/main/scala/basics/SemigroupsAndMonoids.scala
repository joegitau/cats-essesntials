package basics

/* uses scala 3

object SemigroupsAndMonoids extends App {
  // abstract implementation of Semigroups and Monoids + of course some extension methods

  trait AbstractSemigroup[T] {
    def combine(a: T, b: T): T
  }

  object AbstractSemigroup {
    def apply[T](using instance: AbstractSemigroup[T]): AbstractSemigroup[T] = instance
  }

  trait AbstractMonoid[T] extends AbstractSemigroup[T] {
    def empty: T
  }

  object AbstractMonoid {
    def apply[T](using instance: AbstractMonoid[T]): AbstractMonoid[T] = instance
  }

  private object IntInstances {
    given IntMonoid: AbstractMonoid[Int] with {
      override def combine(a: Int, b: Int): Int = a + b
      override def empty: Int = 0
    }
  }

  private object StringInstances {
    given StringMonoid: AbstractMonoid[String] with {
      override def combine(a: String, b: String): String = a + b
      override def empty: String = ""
    }
  }

  // extension methods
  object AbstractSemigroupSyntax {
    extension [T](a: T)
    @targetName("combine")
    def |+|(b: T)(using semigroup: AbstractSemigroup[T]): T = semigroup.combine(a, b)
  }

  // usage
  import AbstractSemigroupSyntax.*
  import IntInstances.given
  import StringInstances.given

  private def reduceCompact[T : AbstractSemigroup](ls: List[T]): T = ls.reduce(_ |+| _)

  println(reduceCompact("Foo " :: "Bar" :: Nil))
  println(reduceCompact(1 :: 2 :: 3 :: Nil))
}
*/
