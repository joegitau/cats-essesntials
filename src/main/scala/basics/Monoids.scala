package basics

object Monoids extends App {
  // Monoids extend Semigroup - and as such are used to combine values of the same type while at
  // the same time providing the concept of an "identity" property (empty) - which represents a default/ empty value


}

/**
 * The defining property of a monoid is that it includes an identity element e for the binary operation.
 * This element acts as a neutral element, meaning that combining it with any other element a using the binary operation
 * should yield a itself. Mathematically, for any element a in the set: a ∗ e = e ∗ a = a.
 */
