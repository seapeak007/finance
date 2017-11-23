package com.stock.market.repository.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name = "stock_info")
public class StockInfoEntity
{
    @Id
    @Column(name = "stock_code")
    private String stockCode;
    @Column(name="stock_market")
    private String stockMarket;
    @Column(name = "stock_name")
    private String stockName;
}