package com.merchant.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class AgeRequest {
    @NotNull(message = "The first age is required.")
    private BigDecimal agePersonFirst;
    @NotNull(message = "The first dead is required.")
    private BigDecimal deadPersonFirst;
    @NotNull(message = "The second age is required.")
    private BigDecimal agePersonSecond;
    @NotNull(message = "The second dead is required.")
    private BigDecimal deadPersonSecond;
}
