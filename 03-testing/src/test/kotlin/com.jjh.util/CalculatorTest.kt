package com.midmarsh.util

import org.junit.jupiter.api.*

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.function.Executable
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class CalculatorTest {

    private val calculator = Calculator()

    @BeforeEach
    fun setUp() {
        println("BeforeEach")
    }

    @AfterEach
    fun tearDown() {
        println("AfterEach")
    }

    @Test
    @Order(1)
    fun testWhenAdding1and3ThenAnswerIs4() {
        assertEquals(4, calculator.add(1, 3))
    }

    @Test
    @Order(2)
    fun `Adding 1 and 0 should be equal to 0`() {
        assertEquals(1, calculator.add(1, 0))
    }

    @Test
    @Order(3)
    fun testDivide22by7By() {
        val myPi = calculator.div(22.0, 7.0)
        println(myPi)
        assertEquals(3.14, myPi, 0.01)
    }

    @Test
    @Order(5)
    fun `Dividing by zero should throw the DivideByZeroException`() {
        val exception = assertThrows(DivideByZeroException::class.java) {
            calculator.div(5.0, 0.0)
        }

        assertEquals(5.0, exception.numerator)
    }

    @Test
    @Order(4)
    fun `The result of adding two numbers should be equal to their total`() {
        assertAll(
                Executable { assertEquals(0, calculator.add(0, 0)) },
                Executable { assertEquals(1, calculator.add(0, 1)) },
                Executable { assertEquals(2, calculator.add(1, 1)) }
        )
    }

    @Test
    @Disabled("for demonstration purposes")
    fun skippedTest() {
        // not executed
    }

    @ParameterizedTest
    @MethodSource("valuesProvider")
    fun testWithValueSource(x: Int, y: Int, total: Int) {
        val calc = Calculator()
        val result = calc.add(x, y)
        assertEquals(total, result, "result $result should be $total")
    }

    companion object {
        @JvmStatic
        fun valuesProvider() = listOf(Arguments.of(1, 1, 2), Arguments.of(1, 2, 3))
    }

    private val testData = listOf(Triple(1, 1, 2), Triple(1, 2, 3))

    @TestFactory
    fun testWithTestFactoryData() = testData.map{ (x, y, result) ->
        DynamicTest.dynamicTest("when I add $x and $y then I get $result") {
            assertEquals(result, calculator.add(x, y))
        }
    }

}