package com.wyw.eshop.inventory.thread;

import com.wyw.eshop.inventory.request.Request;
import com.wyw.eshop.inventory.request.RequestQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RequestProcessThreadPool {

    private static int POOL_SIZE = 10;

    private ExecutorService threadPool = Executors.newFixedThreadPool(POOL_SIZE);

    public RequestProcessThreadPool(){
        RequestQueue requestQueue = RequestQueue.getInstance();
        for(int i=0;i<POOL_SIZE;i++){
            ArrayBlockingQueue<Request> queue =  new ArrayBlockingQueue<>(100);
            requestQueue.addQueue(queue);
            threadPool.submit(new RequestProcessThread(queue));
        }
    }

    private static class Singleton{
        private static RequestProcessThreadPool instance;
        static {
            instance = new RequestProcessThreadPool();
        }
        public static RequestProcessThreadPool getInstance(){
            return instance;
        }
    }

    public static RequestProcessThreadPool getInstance(){
        return Singleton.getInstance();
    }

    public static void init(){
        getInstance();
    }
}
