package com.raidiam.techtask.accountapi.model.http;

import com.raidiam.techtask.accountapi.model.Account;
import com.raidiam.techtask.accountapi.model.Transaction;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class AccountListResponse {

    private UUID customerId;
    private List<AccountListItem> data;

    private BigDecimal balanceFrom(Account a) {
        BigDecimal balance = new BigDecimal("0000.00");
        for(Transaction t: a.getTransactions()) {
            balance = balance.add(t.getAmount());
        }
        return balance;
    }

    @Data
    @Builder
    public static class AccountListItem {
        private String accountId;
        private BigDecimal balance;
    }
}
