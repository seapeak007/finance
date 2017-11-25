package com.stock.market.restful;

import com.google.gson.JsonObject;
import com.stock.market.service.StockInfoService;
import com.stock.market.utils.HttpClientUtil;
import com.stock.market.utils.SnowBallLoginUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by UI03 on 2017/11/23.
 */
@RestController
@Slf4j
@RequestMapping("/stock")
public class StockInfoController {

    private SnowBallLoginUtil snowBallLoginUtil ;
    private StockInfoService stockInfoService ;

    public StockInfoController(SnowBallLoginUtil snowBallLoginUtil,StockInfoService stockInfoService){
        this.snowBallLoginUtil = snowBallLoginUtil;
        this.stockInfoService = stockInfoService ;
    }

    @GetMapping("/infos")
    public String getStockInfos(){

        JsonObject  jsonObject = new JsonObject() ;
        HttpClientUtil httpClientUtil = snowBallLoginUtil.sbLogin() ;
        try {
            stockInfoService.readStock(httpClientUtil,"SH");
            stockInfoService.readStock(httpClientUtil,"SZ");
            jsonObject.addProperty("flag","success") ;
            log.info("getStockInfos success");
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.addProperty("flag","fail") ;
            jsonObject.addProperty("error",e.getMessage());
            log.error("save stockinfos error:"+e);
        }
        return jsonObject.toString() ;
    }

}
