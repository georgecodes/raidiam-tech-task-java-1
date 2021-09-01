package com.raidiam.techtask.accountapi.controllers;

import com.raidiam.techtask.accountapi.model.Customer;
import com.raidiam.techtask.accountapi.model.http.AccountListResponse;
import com.raidiam.techtask.accountapi.services.AccountService;
import com.raidiam.techtask.accountapi.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

@Controller
public class AccountApiController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AccountService accountService;

    @RequestMapping(method = RequestMethod.GET, path = "/api/v1/accounts/{customerId}")
    public ResponseEntity<AccountListResponse> customerAccounts(@PathVariable("customerId") UUID customerId) {
        Customer customer = customerService.customerById(customerId);
        return ResponseEntity.ok().body(accountService.accounts(customer));
    }

}
