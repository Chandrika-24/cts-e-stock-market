package com.estockmarket.company.query.api.repositories;

import com.estockmarket.company.core.models.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CompanyRepository extends MongoRepository<Company, String> {
    @Query("{'$or' : [{'companycode': {$regex : ?0, $options: '1'}}, {'companyname': {$regex : ?0, $options: '1'}}]}")
    List<Company> findByFilterRegex(String filter);
}
