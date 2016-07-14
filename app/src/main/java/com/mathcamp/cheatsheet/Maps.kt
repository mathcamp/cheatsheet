package com.mathcamp.cheatsheet

/**
 * Created by bkase on 3/30/15.
 */

inline fun <K, V, reified R> Map<K, V>.getAs(k: K): R? {
  val v = this[k] ?: return null

  if (v is R) {
    return v
  } else {
    return null
  }
}
