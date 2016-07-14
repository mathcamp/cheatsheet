package com.mathcamp.cheatsheet

/**
 * Created by bkase on 5/2/15.
 */

fun <T> Iterable<T>.distinctFirst(): List<T> {
  val s = hashSetOf<T>()

  return this.filter {
    if (s.contains(it)) {
      false
    } else {
      s.add(it)
      true
    }
  }
}

inline fun <T1, T2, R> Iterable<Pair<T1, T2>>.mapPair(transform: (T1, T2) -> R): List<R> {
    return map{ p -> transform(p.first, p.second) }
}

fun <T, R> Iterable<Iterable<T>>.deepFoldLeft(z: R, combine: (R, T) -> R): Iterable<R> {
  return this.firstOrNull().bind{ xs -> // xs :: rest
    val rest = this.drop(1)
    xs.map{ x -> combine(z, x) }.flatMap{ newZ ->
      rest.deepFoldLeft(newZ, combine)
    }
  } ?: listOf(z)
}

fun <T> Iterable<Iterable<T>>.permutations(): Iterable<Iterable<T>> {
  return this.deepFoldLeft(emptyList()) { build, elem -> build + elem }
}

fun <T> always(t: T): Iterable<T> {
  return object: Iterable<T> {
    override fun iterator(): Iterator<T> {
      return object: Iterator<T> {
        override fun next(): T { return t }
        override fun hasNext(): Boolean { return true }
      }
    }
  }
}

