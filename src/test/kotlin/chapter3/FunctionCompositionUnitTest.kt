package chapter3

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class FunctionCompositionUnitTest {

    @Test
    fun `should compose functions f and g`() {
        val f: (Int) -> Int = { it + 2 }
        val g: (Int) -> Int = { it * 2 }

        val fRoundG = compose(f, g)
        val output = fRoundG(5)

        assertThat(output, `is`(12))
    }

    @Test
    fun `should polymorphically compose functions f and g`() {
        val f: (String) -> Char = { it.first() }
        val g: (Int) -> String = { "Constant Function" }

        val fRoundG = polymorphicCompose(f, g)
        val output = fRoundG(5)

        assertThat(output, `is`('C'))
    }

    @Test
    fun `should add 2 arguments by returning a function which works on second argument`() {
        val output = add(2)(3)
        assertThat(output, `is`(5))
    }

    @Test
    fun `should add 2 arguments by returning a function which works on second argument using a function type signature`() {
        val output = addAsFunction(2)(3)
        assertThat(output, `is`(5))
    }

    @Test
    fun `should compose functions f and g using a function type signature`() {
        val f: (Int) -> Int = { it + 2 }
        val g: (Int) -> Int = { it * 2 }

        val fRoundG = composeAsFunction(f, g)
        val output = fRoundG(5)

        assertThat(output, `is`(12))
    }
}