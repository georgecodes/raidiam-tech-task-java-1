package com.raidiam.techtask.accountapi.services;

import com.raidiam.techtask.accountapi.model.Customer;

import java.util.UUID;

public interface CustomerService {

    Customer customerById(UUID id);
    Customer customerByName(String name);

}
