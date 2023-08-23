package basics

import cats.Monoid
import cats.syntax.monoid._

object Monoids extends App {
  // Monoids extend Semigroup - and as such are used to combine values of the same type while at
  // the same time providing the concept of an "identity" property (empty) - which represents a default/ empty value

  def compactFold[T : Monoid](ls: List[T]): T =
    ls.foldLeft(Monoid.empty[T])(_ |+| _) // we provide a default/ empty starting value and then call on the "combine" method from Semigroup

  println(compactFold("hello" :: " again!" :: Nil))

  // combine a map of Map[String, Int] by defining an implicit Monoid[Map[String, Int]]
  val players =
    Map("Luis Diaz" -> 23, "Diogo Jota" -> 24) ::
    Map("Curtis Jones" -> 22) ::
    Map("Dominic Szoboslai" -> 23, "Darwin Nunez" -> 22) :: Nil

  val result = compactFold(players)
  println(result)

  case class ShoppingCart(items: List[String], total: Double)

  // define ShoppingCartMonoid
  implicit object ShoppingCartMonoid extends Monoid[ShoppingCart] {
    override def empty: ShoppingCart = ShoppingCart(Nil, 0.0)

    override def combine(x: ShoppingCart, y: ShoppingCart): ShoppingCart =
      ShoppingCart(x.items ++ y.items, x.total + y.total)
  }

  // alternative solution
  val shoppingCartMonoid = Monoid.instance[ShoppingCart](
    ShoppingCart(Nil, 0.0),
    (sa, sb) => ShoppingCart(sa.items ++ sb.items, sa.total + sb.total)
  )

  val carts = ShoppingCart(List("Nike air force", "t-shirt"), 2) :: ShoppingCart(List("Duffle bag", "Hoka running shoes"), 2) :: Nil
  println(compactFold(carts))
}

/**
 * The defining property of a monoid is that it includes an identity element e for the binary operation.
 * This element acts as a neutral element, meaning that combining it with any other element "a" using the binary operation
 * should yield "a" itself.
 */
