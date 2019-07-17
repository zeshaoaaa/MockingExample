package com.example.mockingexample;

import com.example.mockingexample.goods.Goods;
import com.example.mockingexample.goods.GoodsContract;
import com.example.mockingexample.goods.GoodsPresenter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

// 5.2 小节代码
@RunWith(MockitoJUnitRunner.class)
public class MockitoGoodsPresenterTest {

    private GoodsPresenter presenter;

    @Mock
    GoodsContract.View view;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new GoodsPresenter();
        presenter.attachView(view);
    }

    @Test
    public void testGetGoods() {
        Goods goods = presenter.getGoods(1);
        assert goods.name.equals("纸巾");
    }

}