package com.stock.market.restful;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * Created by UI03 on 2017/11/23.
 */
@RestController
@Slf4j
@RequestMapping("/stock")
public class StockMarketController {
    private final static ObjectMapper mapper = new ObjectMapper();
    private final RestTemplate restTemplate;
    public StockMarketController(RestTemplate restTemplate){
        this.restTemplate =restTemplate ;
    }

    @GetMapping("/market")
    public void getStockInfos(){
        String loginUrl="https://xueqiu.com//snowman/login?username="+"ysygy23@sina.com"+"&password="+"qjmcomeon1990" ;
        this.restTemplate.postForObject(loginUrl,String.class ,String.class) ;
        String url = "http://xueqiu.com/stock/quote_order.json?order=asc&exchange=CN&stockType=sha&column=symbol&orderBy=amount" ;
        url = url +"&page=1&size=100" ;
        String resp = this.restTemplate.getForObject(url,String.class) ;
        try {
            JsonNode json = this.mapper.readTree(resp);
            log.info("json:"+json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
