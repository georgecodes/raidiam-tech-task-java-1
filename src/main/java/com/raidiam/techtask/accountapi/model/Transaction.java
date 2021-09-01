package com.raidiam.techtask.accountapi.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Builder
public class Transaction {

    private UUID id;
    private Account account;
    private ZonedDateTime dateTime;
    private BigDecimal amount;
    private String reference;

}
