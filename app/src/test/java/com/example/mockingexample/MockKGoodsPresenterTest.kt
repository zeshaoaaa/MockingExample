package com.example.mockingexample

import com.example.mockingexample.goods.GoodsContract
import com.example.mockingexample.goods.GoodsPresenter
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

// 第 7 大节的示例代码
class MockKGoodsPresenterTest {

    private var presenter: GoodsPresenter? = null

    // @MockK(relaxed = true)
    @RelaxedMockK
    lateinit var view: GoodsContract.View

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        presenter = GoodsPresenter()
        presenter!!.attachView(view)
    }

    // 7.3 使用 MockK 测试 Presenter
    @Test
    fun testGetGoods3() {
        val goods = presenter!!.getGoods(1)
        assertEquals(goods.name, "纸巾")
    }

    // 7.6 验证多个方法被调用
    @Test
    fun testGetGoods6() {
        val goods = presenter!!.getGoods(1)
        verify {
            view.hideLoading()
            view.showLoading()
        }
        assertEquals(goods.name, "纸巾")
    }

    // 7.7 验证方法被调用的次数
    @Test
    fun testGetGoods7() {
        val goods = presenter!!.getGoods(1)
        // 验证调用了两次
        verify(exactly = 2) { view.showToast("请耐心等待") }

        // 验证调用了最少一次
        // verify(atLeast = 1) { view.showToast("请耐心等待") }

        // 验证最多调用了两次
        // verify(atMost = 1) { view.showToast("请耐心等待") }

        assertEquals(goods.name, "纸巾")
    }


    // 7.8 验证 Mock 方法都被调用了
    @Test
    fun testGetGoods8() {
        val goods = presenter!!.getGoods(1)
        verifyAll {
            view.showToast("请耐心等待")
            view.showToast("请耐心等待")
            view.showLoading()
            view.hideLoading()
        }
        assertEquals(goods.name, "纸巾")
    }

    // 7.9 验证 Mock 方法的调用顺序
    @Test
    fun testGetGoods9() {
        val goods = presenter!!.getGoods(1)
        verifyOrder {
            view.showLoading()
            view.hideLoading()
        }
        assertEquals(goods.name, "纸巾")
    }

    // 7.10 验证全部的 Mock 方法都按特定顺序被调用了
    @Test
    fun testGetGoods10() {
        val goods = presenter!!.getGoods(1)
        verifySequence {
            view.showLoading()
            view.showToast("请耐心等待")
            view.showToast("请耐心等待")
            view.hideLoading()
        }
        assertEquals(goods.name, "纸巾")
    }

    // 7.11 确认所有 Mock 方法都进行了验证
    @Test
    fun testGetGoods11() {
        val goods = presenter!!.getGoods(1)
        verify {
            view.showLoading()
            view.showToast("请耐心等待")
            view.showToast("请耐心等待")
            view.hideLoading()
        }
        confirmVerified(view)
        assertEquals(goods.name, "纸巾")
    }

    // 7.12 验证 Mock 方法接收到的单个参数
    @Test
    fun testCaptureSlot() {
        val slot = slot<String>()
        every { view.showToast(capture(slot)) } returns Unit
        val goods = presenter!!.getGoods(1)
        assertEquals(slot.captured, "请耐心等待")
    }

    // 7.13 验证 Mock 方法每一次被调用接收到参数
    @Test
    fun testCaptureList() {
        val list = mutableListOf<String>()
        every { view.showToast(capture(list)) } returns Unit
        val goods1 = presenter!!.getGoods(1)
        assertEquals(list[0], "请耐心等待")
        assertEquals(list[1], "请耐心等待")
    }


}
