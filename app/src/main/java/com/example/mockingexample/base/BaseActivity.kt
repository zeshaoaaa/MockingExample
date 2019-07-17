package com.example.mockingexample.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mockingexample.base.BasePresenter
import com.example.mockingexample.base.BaseView

abstract class BaseActivity<V : BaseView, P : BasePresenter<V>> : AppCompatActivity() {

    protected lateinit var mPresenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = createPresenter()
        mPresenter.attachView(this as V)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    fun showLoading() {

    }

    fun hideLoading() {

    }

    protected abstract fun createPresenter(): P
}
