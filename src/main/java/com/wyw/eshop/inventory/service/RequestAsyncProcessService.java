package com.wyw.eshop.inventory.service;

import com.wyw.eshop.inventory.request.Request;

public interface RequestAsyncProcessService {
    void process(Request request);
}
