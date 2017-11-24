package com.stock.market.restful;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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

    private Gson gson = new Gson();
    @GetMapping("/infos")
    public void getStockInfos(){
        HttpClientUtil httpClientUtil = SnowBallLoginUtil.sbLogin() ;
        String url = "http://xueqiu.com/stock/quote_order.json?order=asc&exchange=CN&stockType=sza&column=symbol&orderBy=amount";
        try {
            readStock(httpClientUtil,url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private  void readStock( HttpClientUtil clientUtil, String url ) throws Exception
    {
        int page = 1;
        int pageSize = 90;

        while ( true )
        {
            String tmpUrl = url + "&page=" + ( page++ ) + "&size=" + pageSize;

            String result = clientUtil.get( tmpUrl );

            JsonObject jsonObject =  gson.fromJson(result,JsonObject.class);

            JsonArray dataArray = jsonObject.getAsJsonArray("data") ;

            if ( dataArray.size() == 0 )
            {
                break;
            }

            for ( int i = 0; i < dataArray.size(); i++ )
            {
                JsonArray stockDataArray = dataArray.get(i).getAsJsonArray() ;

                String stockInfo = stockDataArray.get(0).getAsString();
                log.info(stockInfo);

//                recordStockInfo( stockInfo.substring( 2 ), stockInfo.substring( 0, 2 ) );
            }
        }

    }
}
