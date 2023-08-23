package basics

import cats.Semigroup
import cats.implicits.catsSyntaxSemigroup

object Semigroups extends App {
  // Semigroups combine elements of the same type

  def combineInts(ls: List[Int]): Int = ls.reduce(Semigroup.combine[Int] )
  def combineStrings(ls: List[String]): String = ls.reduce(Semigroup.combine[String])

  println(combineInts(4 :: 3 :: Nil))
  println(combineStrings("I am " :: "a " :: "big fan!" :: Nil))

  // generalised combine
  def combineAnything[T](ls: List[T])(implicit instance: Semigroup[T]): T = ls.reduce(instance.combine)
  println(combineAnything("hello " :: "Scala" :: Nil))

  case class Expense(id: Int, amount: Double)
  implicit val expenseSemiGroup: Semigroup[Expense] = Semigroup.instance[Expense]((e1, e2) => {
    Expense(Math.max(e1.id, e2.id), e1.amount + e2.amount)
  })

  println(combineAnything(Expense(123455, 11.00) :: Expense(8724526, 45.88) :: Nil))

  // extension methods: |+|
  val expenses: Expense = Expense(1, 99) |+| Expense(2, 56) |+| Expense(3, 100)
  println(expenses)

  // summarised method - uses TypeContext instead of implicitly defining the implicit Semigroup -> T : Semigroup
  def reduceList[T : Semigroup](ls: List[T]): T = ls.reduce(_ |+| _)
  println(reduceList(Expense(4, 76) :: Expense(5, 98) :: Nil))

}

/**
 * Semigroups provide a simple and general way to combine values in an associative manner.
 * They are particularly useful when you need to aggregate or merge values in data processing or concurrent programming.

 * Semigroups are a building block for more advanced concepts like Monoids, which are semigroups with an identity element.
 * They promote modularity and composability by allowing you to define operations separately from the data they operate on.
 *
 * Being "associative" refers to a property of a binary operation where the grouping of operations doesn't affect the final result.
 * In other words, when an operation is associative, you can change the order in which you apply the operations to a set of elements,
 * and the result will remain the same.
 */
