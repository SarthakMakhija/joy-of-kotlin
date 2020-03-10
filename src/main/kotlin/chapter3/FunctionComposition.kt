package chapter3

fun compose(f: (Int) -> Int, g: (Int) -> Int): (Int) -> Int = { x -> f(g(x)) }

fun <T, R, U> polymorphicCompose(f: (R) -> U, g: (T) -> R): (T) -> U = { f(g(it)) }