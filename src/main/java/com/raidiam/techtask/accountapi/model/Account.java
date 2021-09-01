package com.raidiam.techtask.accountapi.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@EqualsAndHashCode(exclude = {"transactions"})
public class Account {

    private UUID id;
    private Customer customer;
    private String accountNumber;
    private List<Transaction> transactions;

    public static class AccountBuilder {

        public AccountBuilder transactions(List<Transaction> transactions) {
            this.transactions = transactions;
            transactions.stream().forEach(t -> t.setAccount(this.build()));
            return this;
        }

    }


}
