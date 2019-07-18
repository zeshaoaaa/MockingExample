package com.example.mockingexample.goods

import android.os.AsyncTask
import com.example.mockingexample.data.Goods
import kotlinx.coroutines.*

class GoodsPresenter : GoodsContract.Presenter() {

    private val model: GoodsModel = GoodsModel()

    override fun getGoods(goodsId: Int): Goods {
        showLoading()
        val result = model.getGoods(goodsId)
        showToast("请耐心等待")
        showToast("请耐心等待")
        hideLoading()
        return result
    }

    override fun requestGoods(goodsId: Int) {
        byCoroutine(goodsId)
        // RequestGoodsTask(model, goodsId, view).execute()
    }

    class RequestGoodsTask(
        private val model: GoodsModel,
        private val goodsId: Int,
        private val view: GoodsContract.View
    ) : AsyncTask<Void, Void, Goods>() {

        override fun onPreExecute() {
            super.onPreExecute()
            view.showLoading()
            view.showToast("请耐心等待")
            view.showToast("请耐心等待")
        }

        override fun doInBackground(vararg p0: Void?): Goods {
            Thread.sleep(2000)
            return model.getGoods(goodsId)
        }

        override fun onPostExecute(result: Goods?) {
            super.onPostExecute(result)
            view.onGoodsRequested(result!!)
            view.hideLoading()
        }

    }

    private fun byCoroutine(goodsId: Int) {
        showLoading()
        showToast("请耐心等待")
        showToast("请耐心等待")
        GlobalScope.launch(Dispatchers.IO) {
            delay(2000)
            val result = model.getGoods(goodsId)
            withContext(Dispatchers.Main) {
                view.onGoodsRequested(result)
                hideLoading()
            }
        }
    }

}
