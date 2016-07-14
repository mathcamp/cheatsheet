package com.mathcamp.cheatsheet

/**
 * Created by Chris on 9/16/15.
 */

// see https://discuss.kotlinlang.org/t/disjoint-unions-in-kotlin/413
sealed class Either<out T, out U>() {
  class Left<T>(val obj: T): Either<T, Nothing>() {
    override fun equals(other: Any?) = other is Left<*> && obj == other.obj
    override fun hashCode() = obj?.hashCode() ?: 0
    override fun toString() = "Left(" + obj.toString() + ")"
  }
  class Right<U>(val obj: U): Either<Nothing, U>() {
    override fun equals(other: Any?) = other is Right<*> && obj == other.obj
    override fun hashCode() = obj?.hashCode() ?: 0
    override fun toString() = "Right(" + obj.toString() + ")"
  }

  // right-biased
  fun <R> map(f: (U) -> R): Either<T, R> =
    when (this) {
      is Either.Left -> Either.Left(this.obj)
      is Either.Right -> Either.Right(f(this.obj))
    }
}
