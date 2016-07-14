package com.mathcamp.cheatsheet

/**
 * Created by bkase on 7/14/15.
 */

inline fun <T, U> Pair<T,T>.mapBoth(transform: (T) -> U): Pair<U, U> {
  return transform(this.first) to transform(this.second)
}