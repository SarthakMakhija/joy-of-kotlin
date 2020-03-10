package chapter3

fun compose(f: (Int) -> Int, g: (Int) -> Int): (Int) -> Int = { x -> f(g(x)) }

fun <T, R, U> polymorphicCompose(f: (R) -> U, g: (T) -> R): (T) -> U = { f(g(it)) }

fun add(addend: Int): (Int) -> Int = { addend + it }

val addAsFunction: (Int) -> (Int) -> Int = { addend -> { augend -> augend + addend } }

val composeAsFunction: ((Int) -> Int, (Int) -> Int) -> (Int) -> Int = { f, g -> { f(g(it)) } }