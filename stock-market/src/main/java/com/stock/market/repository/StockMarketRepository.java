package com.stock.market.repository;

import com.stock.market.repository.entity.StockMarketEntity;
import com.stock.market.repository.primary.StockMarketPrimary;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by UI03 on 2017/11/23.
 */
public interface StockMarketRepository extends CrudRepository<StockMarketEntity,StockMarketPrimary> {

}
