package com.example.mockingexample;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CoroutineGoodsPresenterTest.class, MockitoCalculatorTest.class,
        MockKCalculatorTest.class, MockitoGoodsPresenterTest.class,
        MockKGoodsPresenterTest.class
})
public class ExampleTestSuite {
}
