package com.example.mockingexample.goods


import android.widget.Button
import com.example.mockingexample.base.BasePresenter
import com.example.mockingexample.base.BaseView
import com.example.mockingexample.goods.Goods

interface GoodsContract {

    interface Model {

        val goodsList: List<Goods>

        fun getGoods(goodsId: Int): Goods

    }

    interface View : BaseView {

        fun onGoodsRequested(result: Goods)

    }

    abstract class Presenter : BasePresenter<View>() {

        abstract fun getGoods(goodsId: Int): Goods

        abstract fun requestGoods(goodsId: Int)

    }


}
