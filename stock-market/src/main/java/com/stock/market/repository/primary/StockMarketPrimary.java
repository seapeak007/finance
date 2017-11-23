package com.stock.market.repository.primary;

import lombok.*;

import java.io.Serializable;

/**
 * Created by UI03 on 2017/11/23.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class StockMarketPrimary implements Serializable {

    private String tradeDate;
    private String stockCode;
    private String stockMarket;
}
