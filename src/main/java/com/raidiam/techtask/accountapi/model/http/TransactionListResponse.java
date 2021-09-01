package com.raidiam.techtask.accountapi.model.http;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@Builder
public class TransactionListResponse {

    private String accountNumber;
    private List<TransactionListItem> transactions;

    @Data
    @Builder
    public static class TransactionListItem {

        private String reference;
        private ZonedDateTime date;
        private BigDecimal amount;

    }

}
