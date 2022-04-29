package com.estockmarket.company.query.api.repositories;

import com.estockmarket.company.core.models.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompanyRepository extends MongoRepository<Company, String> {
}
