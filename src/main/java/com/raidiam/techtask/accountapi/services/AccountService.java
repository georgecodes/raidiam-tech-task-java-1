package com.raidiam.techtask.accountapi.services;

import com.raidiam.techtask.accountapi.model.http.AccountListResponse;
import com.raidiam.techtask.accountapi.model.Customer;
import com.raidiam.techtask.accountapi.model.http.TransactionListResponse;

public interface AccountService {

    AccountListResponse accounts(Customer customer);
    TransactionListResponse transactions(String accountNumber);

}
