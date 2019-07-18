package com.example.mockingexample.goods;

import com.example.mockingexample.data.Goods;

import java.util.ArrayList;
import java.util.List;

public class GoodsModel implements GoodsContract.Model {
    @Override
    public Goods getGoods(int goodsId) {
        Goods goods = new Goods();
        if (goodsId == 1) {
            goods.name = "纸巾";
        } else if (goodsId == 2) {
            goods.name = "口香糖";
        }
        return goods;
    }

    @Override
    public List<Goods> getGoodsList() {
        List<Goods> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Goods("纸巾", 10));
        }
        return list;
    }

}
