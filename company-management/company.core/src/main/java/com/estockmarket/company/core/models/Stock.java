package com.estockmarket.company.core.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "stocks")
public class Stock {
    @Id
    private String id;
    @NotNull(message = "price cannot be null")
    @Size(min = 0, message = "price should be > 0")
    private Integer price;
    @NotNull(message = "price cannot be null")
    private String companycode;
    private LocalDate enteredDate;
    private LocalTime enteredTime;
}