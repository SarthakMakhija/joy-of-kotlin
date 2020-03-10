package chapter3

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class FunctionCompositionTest {

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

    @Test
    fun `should compose functions f and g using curried composition`() {
        val f: (Int) -> Int = { it + 2 }
        val g: (Int) -> Int = { it * 2 }

        val acceptsAnotherFunction: ((Int) -> Int) -> (Int) -> Int = curriedCompose(f)
        val acceptsAnInt: (Int) -> Int = acceptsAnotherFunction(g)
        val output = acceptsAnInt(5)

        assertThat(output, `is`(12))
    }

    @Test
    fun `should polymorphically compose functions f and g using curried composition`() {
        val f: (String) -> Char = { it.first() }
        val g: (Int) -> String = { "Constant Function" }

        val fRoundG = PolymorphicCurriedCompose<Int, String, Char>().curriedCompose(f)(g)
        val output = fRoundG(5)

        assertThat(output, `is`('C'))
    }

    @Test
    fun `should apply same tax rate (using curry) to different prices`() {
        val taxRate90Percent = addTax(rate = 0.9)
        val price1 = 100.0
        val price2 = 200.0

        val taxRateApplied1 = taxRate90Percent(price1)
        val taxRateApplied2 = taxRate90Percent(price2)

        assertThat(taxRateApplied1, `is`(190.0))
        assertThat(taxRateApplied2, `is`(380.0))
    }

    @Test
    fun `should partially apply a curried function of two arguments to its first argument`() {
        val f: (Int) -> (String) -> String = { i: Int -> { s: String -> s + i.toString() } }
        val bArrowCFunction: (String) -> String = partialA(10, f)

        val output = bArrowCFunction("Demo")
        assertThat(output, `is`("Demo10"))
    }

    @Test
    fun `should partially apply a curried function of two arguments to its second argument`() {
        val f: (Int) -> (String) -> String = { i: Int -> { s: String -> s + i.toString() } }
        val aArrowCFunction: (Int) -> String = partialB("Demo", f)

        val output = aArrowCFunction(10)
        assertThat(output, `is`("Demo10"))
    }

    @Test
    fun `should return a curried function with a total of 4 parameters`() {
        val output = curried<String, String, String, String>()("A")("B")("C")("D")
        assertThat(output, `is`("A, B, C, D"))
    }
}