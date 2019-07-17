package com.example.mockingexample

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

// 7.2 小节示例代码
class MockKCalculatorTest {

    @MockK
    lateinit var calculator: Calculator

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun testAddAndSubtract() {
        every { calculator.add(1, 1) } returns 2
        every { calculator.subtract(2, 1) } returns 1
        assertEquals(calculator.add(1, 1), 2)
        assertEquals(calculator.subtract(2, 1), 1)
    }

    class Calculator {
        fun add(value1: Int, value2: Int): Int {
            // 未实现
            return 0
        }

        fun subtract(value1: Int, value2: Int): Int {
            return 0
        }
    }

}
