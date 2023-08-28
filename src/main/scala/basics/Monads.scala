package basics

import cats.Monad
import cats.implicits._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.Try

object Monads extends App {
  trait AbstractApplicative[F[_]] {
    def pure[A](value: A): F[A]
  }

  // usage
  val anOption: Option[Int] = 1.pure[Option]
  val aFuture: Future[Int]  = 14.pure[Future]
  val aTry: Try[String]     = "open".pure[Try]

  trait AbstractMonad[F[_]] extends AbstractApplicative[F] {
    def flatMap[A, B](fa: F[A])(f: A => F[B]): F[B] // flatMap is actually extended from FlatMap

    // we get "map" fn for free as "Applicative" extends "Apply" which in return extends "Functor" which contains the "map" fn
    def map[A, B](fa: F[A])(f: A => B): F[B] = flatMap(fa)(a => pure(f(a)))
  }

  // problem Monads try to solve
  def getPairsList(nums: List[Int], chars: List[Char]) = nums.flatMap(n => chars.map(c => (n, c)))
  def getPairsOption(num: Option[Int], char: Option[Char]) = num.flatMap(n => char.map(c => (n, c)))
  def getPairsFuture(num: Future[Int], char: Future[Char]) = num.flatMap(n => char.map(c => (n, c)))

  // generalized
  def getPairs[M[_], A, B](ma: M[A], mb: M[B])(implicit monad: Monad[M]): M[(A, B)] =
    monad.flatMap(ma)(a => monad.map(mb)(b => (a, b)))

  def getPairsAlt[M[_] : Monad, A, B](ma: M[A], mb: M[B]): M[(A, B)] =
    for { a <- ma; b <- mb } yield (a, b)

  println(getPairs[List, Int, String](List(1, 2, 3, 4), List("a", "b", "c", "d")))
  println(getPairsAlt(List(1, 2, 3, 4), List("a", "b", "c", "d")))

}

/**
 * A Monad can be described as a Higher-Kinded type that provides two methods:
 *   a) pure: wraps a normal value into a monadic value (lift)
 *   b) flatMap: transforms monadic values in sequence
 *
 * At their core, monads are about wrapping values in a container that carries additional information or context.
 * This container allows you to chain operations together, preserving context and managing the flow of data.
 *
 * Monads also offer a way to handle errors gracefully and deal with situations where computations might not produce a valid result.
 *
 * In essence, monads provide a design pattern for creating consistent, maintainable, and structured code when working
 * with sequences of operations that involve context, transformation, and error handling.
 */
