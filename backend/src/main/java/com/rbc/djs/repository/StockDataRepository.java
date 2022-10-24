package com.rbc.djs.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.rbc.djs.model.Stock;

import reactor.core.publisher.Flux;

@Repository
public interface StockDataRepository extends ReactiveCrudRepository<Stock, String> {

    @Query("select * from stock s where s.stock = :stock")
    Flux<Stock> findByStock(String stock);

}