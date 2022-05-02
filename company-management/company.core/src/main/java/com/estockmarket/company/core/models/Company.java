package com.estockmarket.company.core.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//import org.hibernate.validator.constraints.URL;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "companies")
public class Company {
    @Id
    private String id;
    @NotNull(message = "Company name is required")
    @Size(min = 2, message = "company name must have atleast 2 characters")
    private String companyname;
    @NotNull(message = "Company code is required")
    @Indexed(unique=true)
    private String companycode;
    @NotNull(message = "Company CEO name is required")
    @Size(min = 2, message = "company ceo name must have atleast 2 characters")
    private String companyceoname;
    @NotNull(message = "Company turnover is required")
    @DecimalMin(value = "100000000", inclusive = false, message = "Company turnover must be above 1000000000")
    private BigDecimal companyturnover;
    @NotNull(message = "Company website is required")
    private String companywebsite;
    @NotEmpty(message = "Company stock exchange is required")
    private List<String> stockexchange;

    public Company(Company byCompanycode) {
    }
}
