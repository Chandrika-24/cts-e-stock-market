package com.estockmarket.stock.api.repositories;

import com.estockmarket.company.core.models.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StockRepository extends MongoRepository<Stock, Integer> {

    List<Stock> findAllByCompanycode(String companycode);
}
