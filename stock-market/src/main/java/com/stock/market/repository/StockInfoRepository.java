package com.stock.market.repository;

import com.stock.market.repository.entity.StockInfoEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by UI03 on 2017/11/23.
 */
public interface StockInfoRepository extends CrudRepository<StockInfoEntity,String> {

}
