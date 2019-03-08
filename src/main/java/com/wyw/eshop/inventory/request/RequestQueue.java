package com.wyw.eshop.inventory.request;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class RequestQueue {


    private List<ArrayBlockingQueue<Request>> queues = new ArrayList<>();
    private Map<Integer, Boolean> flagMap = new ConcurrentHashMap<>();


    private static class Singleton{
        private static RequestQueue instance;
        static {
            instance = new RequestQueue();
        }
        public static RequestQueue getInstance(){
            return instance;
        }
    }

    public static RequestQueue getInstance(){
        return Singleton.getInstance();
    }

    public void addQueue(ArrayBlockingQueue<Request> queue){
        this.queues.add(queue);
    }

    public ArrayBlockingQueue<Request> getQueue(int index){
        return this.queues.get(index);
    }

    public int queueSize(){
        return this.queues.size();
    }

    public Map<Integer, Boolean> getFlagMap() {
        return flagMap;
    }
}
