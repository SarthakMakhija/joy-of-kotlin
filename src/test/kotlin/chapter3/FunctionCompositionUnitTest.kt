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
}