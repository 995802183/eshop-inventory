package com.wyw.eshop.inventory.thread;

import com.wyw.eshop.inventory.request.Request;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;

public class RequestProcessThread implements Callable<Boolean> {
    private ArrayBlockingQueue<Request> queue;

    public RequestProcessThread(ArrayBlockingQueue<Request> queue) {
        this.queue = queue;
    }

    @Override
    public Boolean call() throws Exception {
        Request request = queue.take();
        request.process();
        return true;
    }
}
