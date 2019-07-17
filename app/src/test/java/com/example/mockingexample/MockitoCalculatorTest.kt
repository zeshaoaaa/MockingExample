package com.example.mockingexample

import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

// 6.2 小节示例代码
@RunWith(MockitoJUnitRunner::class)
class MockitoCalculatorTest {

    @Mock
    internal var calculator: Calculator? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testAddAndSubtract() {
        `when`(calculator!!.add(1, 1)).thenReturn(2)
        `when`(calculator!!.subtract(2, 1)).thenReturn(1)
        assertEquals(calculator!!.add(1, 1), 2)
        assertEquals(calculator!!.subtract(2, 1), 1)
    }

    open class Calculator {
        open fun add(value1: Int, value2: Int): Int {
            // 未实现
            return 0
        }

        open fun subtract(value1: Int, value2: Int): Int {
            return 0
        }
    }

}
