package com.estockmarket.stock.api.repositories;

import com.estockmarket.company.core.models.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface StockRepository extends MongoRepository<Stock, Integer> {

    List<Stock> findAllByCompanycode(String companycode);

    @Query("{'$and' : [{'companycode' : {$regex : ?0, $options: '1'}}, {'enteredDate' : { $gte: ?1, $lte: ?2 }}]}")
    List<Stock> findByEnteredCompanyAndBetweenDates(String companyCode, String startDate, String endDate);
}
