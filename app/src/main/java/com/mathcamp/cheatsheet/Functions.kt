package com.mathcamp.cheatsheet

/**
 * Created by bkase on 5/12/15.
 */

// Usage: f1 o f2 = { x -> f2(f1(x)) }
fun <A, B, C> Function1<A, B>.o(g: (B) -> C): (A) -> C {
  return { g(this.invoke(it)) }
}
