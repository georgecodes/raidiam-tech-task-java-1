package com.raidiam.techtask.accountapi.util;

import com.raidiam.techtask.accountapi.model.Account;
import com.raidiam.techtask.accountapi.model.Customer;
import com.raidiam.techtask.accountapi.model.Transaction;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class AccountDataFactory {

    private static Set<Customer> customers;

    public static Set<Customer> buildPretendModel() {
        if(customers == null) {
           init();
        }
        return customers;
    }

    private static void init() {
        Customer tom = Customer.builder()
                .name("Tom")
                .id(UUID.randomUUID())
                .build();

        tom.setAccounts(List.of(
                Account.builder()
                        .accountNumber("4329842438")
                        .customer(tom)
                        .transactions(List.of(
                                transactionFor("Salary", "1300.00"),
                                transactionFor( "Mortgage", "-800.00"),
                                transactionFor( "Salary", "1300.00"),
                                transactionFor( "cash", "-100.00"),
                                transactionFor( "Salary", "1300.00")
                        ))
                        .build(),
                Account.builder()
                        .accountNumber("94946133")
                        .customer(tom)
                        .transactions(List.of(
                                transactionFor("Savings", "100.00"),
                                transactionFor("Savings", "100.00"),
                                transactionFor("Savings", "100.00")
                        ))
                        .build())
        );

        Customer dick = Customer.builder()
                .name("Dick")
                .id(UUID.randomUUID())
                .build();

        dick.setAccounts(List.of(
                Account.builder().build())
        );

        Customer harry = Customer.builder()
                .name("Harry")
                .id(UUID.randomUUID())
                .build();

        harry.setAccounts(List.of(
                Account.builder().build())
        );

        customers =  Set.of(tom,dick, harry);
    }

    private static Transaction transactionFor(String ref, String amount) {
        return Transaction.builder()
                .reference(ref)
                .dateTime(ZonedDateTime.now())
                .amount(new BigDecimal(amount))
                .build();
    }

}
