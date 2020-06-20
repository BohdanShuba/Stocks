package ua.shuba.stocks.service;

import java.util.concurrent.CountDownLatch;

public interface Task extends Runnable{
    void setStockCode(String code);

    void setLatch(CountDownLatch countDownLatch);

}
