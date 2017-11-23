package com.stock.market.repository.entity;

import com.stock.market.repository.primary.StockMarketPrimary;
import lombok.*;

import javax.persistence.*;

/**
 * author 7
 * date 2017-11-23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString(callSuper = true)
@Entity
@IdClass(StockMarketPrimary.class)
@Table(name="stock_market")
public class StockMarketEntity
{
    //hq 行情数据  时间 ，开盘，收盘，涨幅额 ，涨跌幅，最低，最高，成交手，成交金额，换手率
    //时间 yyyy-mm-dd
    @Id
    @Column(name = "trade_date")
    private String tradeDate;
    @Id
    @Column(name = "stock_code")
    private String stockCode;
    @Id
    @Column(name = "stock_market")
    private String stockMarket;
    //开盘价 xx.xx
    @Column(name = "open")
    private String open;
    //昨日收盘价 xx.xx
    @Column(name = "close")
    private String close;
    //涨跌额 xx.xx
    @Column(name = "increase_money")
    private String increaseMoney;
    //涨跌幅 xx.xx%
    @Column(name = "increase_ratio")
    private String increaseRatio;
    //最低 xx.xx
    @Column(name = "low")
    private String low;
    //最高 xx.xx
    @Column(name = "high")
    private String high;
    //成交手 xx.xx
    @Column(name = "trading_hand")
    private String tradingHand;
    //成交额
    @Column(name = "trading_volume")
    private String tradingVolume;
    //换手率 xx.xx%
    @Column(name = "change_ratio")
    private String changeRatio;

}