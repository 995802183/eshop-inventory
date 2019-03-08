package com.wyw.eshop.inventory.service.impl;

import com.wyw.eshop.inventory.request.ProductInventoryCacheRefreshRequest;
import com.wyw.eshop.inventory.request.ProductInventoryDBUpdateRequest;
import com.wyw.eshop.inventory.request.Request;
import com.wyw.eshop.inventory.request.RequestQueue;
import com.wyw.eshop.inventory.service.RequestAsyncProcessService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;

@Service
public class RequestAsyncProcessServiceImpl implements RequestAsyncProcessService {

    @Override
    public void process(Request request){
        try {
            RequestQueue requestQueue = RequestQueue.getInstance();
            Map<Integer, Boolean> flagMap = requestQueue.getFlagMap();
            //duplicate remove
            if(request instanceof ProductInventoryDBUpdateRequest){
                flagMap.put(request.getProductId(),true);
            }else if(request instanceof ProductInventoryCacheRefreshRequest){
                Boolean flag = flagMap.get(request.getProductId());
                if(flag == null){
                    flagMap.put(request.getProductId(),false);
                }

                if(flag != null && flag){
                    flagMap.put(request.getProductId(),false);
                }

                if(flag != null && !flag){
                    return ;
                }
            }

            ArrayBlockingQueue<Request> queue = getRoutingQueue(request.getProductId());
            queue.put(request);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public ArrayBlockingQueue<Request> getRoutingQueue(Integer productId){
        RequestQueue requestQueue = RequestQueue.getInstance();
        String key = String.valueOf(productId);
        int h;
        int hash = (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
        int index = (requestQueue.queueSize()-1) & hash;
        ArrayBlockingQueue<Request> queue = requestQueue.getQueue(index);
        return queue;
    }
}
