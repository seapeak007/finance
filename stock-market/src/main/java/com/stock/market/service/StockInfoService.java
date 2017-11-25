package com.stock.market.service;

import com.stock.market.utils.HttpClientUtil;

/**
 * Created by UI03 on 2017/11/24.
 */
public interface StockInfoService {
    void readStock(HttpClientUtil clientUtil, String flag) throws Exception;
}
