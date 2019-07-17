package com.example.mockingexample

import com.example.mockingexample.goods.GoodsContract
import com.example.mockingexample.goods.GoodsPresenter
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class CoroutineGoodsPresenterTest {

    private val mainThreadSurrogate = newSingleThreadContext("UI Thread")
    private var presenter: GoodsPresenter? = null

    @MockK
    lateinit var view: GoodsContract.View

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        presenter = GoodsPresenter()
        presenter!!.attachView(view)
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun testBlockingTask() {
        presenter!!.requestGoods(1)
        verify(timeout = 2000) { view.hideLoading() }
    }

}