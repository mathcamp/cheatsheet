package com.mathcamp.cheatsheet

/**
 * Created by bkase on 3/30/15.
 */

inline fun <T, R> T?.bind(f: (T) -> R?): R? = this?.let(f)

inline fun <reified T> T.pure(): T? = this

inline fun <reified T, reified R> T.safeCast(): R? {
  if (this is R) {
    return this
  } else {
    return null
  }
}

fun String.emptyToOption(): String? {
  return if (this != "") this else null
}
