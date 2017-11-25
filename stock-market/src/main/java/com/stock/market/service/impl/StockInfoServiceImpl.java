package com.stock.market.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.stock.market.repository.StockInfoRepository;
import com.stock.market.repository.entity.StockInfoEntity;
import com.stock.market.service.StockInfoService;
import com.stock.market.utils.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by UI03 on 2017/11/24.
 */
@Service
@Slf4j
public class StockInfoServiceImpl implements StockInfoService {

    private StockInfoRepository stockInfoRepository ;
    public StockInfoServiceImpl(StockInfoRepository stockInfoRepository){
        this.stockInfoRepository =stockInfoRepository ;
    }

    @Value("${sb_stock_list_sh}")
    private String SHUrl;
    @Value("${sb_stock_list_sz}")
    private String SZUrl;
    private Gson gson = new Gson();

    @Override
    public void readStock(HttpClientUtil clientUtil, String flag) throws Exception {
        if("SH".equals(flag)){
            readSBStock(clientUtil,SHUrl) ;
        }else if("SZ".equals(flag)){
            readSBStock(clientUtil,SZUrl) ;
        }else{
            log.error("not support flag");
        }

    }

    private  void readSBStock( HttpClientUtil clientUtil, String url ) throws Exception {
        int page = 1;
        int pageSize = 90;

        List<StockInfoEntity> list = new ArrayList<StockInfoEntity>();
        while (true) {
            String tmpUrl = url + "&page=" + (page++) + "&size=" + pageSize;

            String result = clientUtil.get(tmpUrl);

            JsonObject jsonObject = gson.fromJson(result, JsonObject.class);

            JsonArray dataArray = jsonObject.getAsJsonArray("data");

            if (dataArray.size() == 0) {
                break;
            }

            for (int i = 0; i < dataArray.size(); i++) {
                JsonArray stockDataArray = dataArray.get(i).getAsJsonArray();

                String siStr = stockDataArray.get(0).getAsString();
                log.info(siStr);
                StockInfoEntity sie = new StockInfoEntity(siStr.substring(2),siStr.substring(0,2),"");
                list.add(sie) ;
            }
        }
        this.stockInfoRepository.save(list) ;

    }
}
