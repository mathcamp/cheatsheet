# Cheatsheet

> Kotlin extensions on commonly used structures for Math Camp

## What

Kotlin has a great feature that allows for scoped extensions to already existing types. This includes primitives in the language like Functions and any optional.

Plus we have inline functions with reified generics on extension functions. There is ZERO runtime overhead because the lambdas are inlined!

The standard library was missing a few operations and structures that we found very useful:

### Either
kkkkk
`Either<T, U>` is a disjoint union over two types `T` and `U`. It includes a right-biased map. This is useful for propagating errors safely without exceptions:

```kotlin
fun attemptParseInt(str: String) -> Either<ParseError, Int>

attemptParseInt("5").map { it + 1 } // 6
attemptParseInt("oops").map { it + 1 } // ParseError as a value!
```

### Function Composition

```kotlin
// f1 o f2 is { x -> f2(f1(x)) }
fun addOne(x: Int): Int
fun addTwo(x: Int): Int

val addThree = addOne o addTwo
```

Unfortunately it looks like the latest version of Kotlin chokes on the type inference here. We used to use this operator often.

### Always

Always returns an infinite iterable that always returns the same value

```kotlin
// useful for zipping
listOf(1,2).zip(always(5)) // [(5, 1), (5, 2)]
```

See the code for more `Iterables` operators

### Get and cast from map

```kotlin
// useful with JSON
json.getAs<String, Object, String>("name")
```

### Monadic bind + pure on Options

Monadic optionals (like in Swift) are very useful. See the `bind` and `pure` operators in Options. `Options.kt` also has many more functions.

### MapBoth on pairs

Sometimes it's useful to apply a `T -> U` transformation to make a `(T, T)` a `(U, U)`

### Time

We needed to display our time information in a specific way for Roll. Ourlogic lives here.

### Tuples

Kotlin only provides `Pair` and `Triple`. We also have `Tuple4` and `Tuple5`.
