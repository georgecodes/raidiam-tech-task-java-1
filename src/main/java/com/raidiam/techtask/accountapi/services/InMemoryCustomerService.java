package com.raidiam.techtask.accountapi.services;

import com.raidiam.techtask.accountapi.util.AccountDataFactory;
import com.raidiam.techtask.accountapi.model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class InMemoryCustomerService implements CustomerService {

    private Map<String, Customer> customersByName = new HashMap<>();
    private Map<UUID, Customer> customersById = new HashMap<>();


    public InMemoryCustomerService() {
        AccountDataFactory.buildPretendModel().stream()
                .forEach(c -> {
                    customersById.put(c.getId(), c);
                    customersByName.put(c.getName(), c);
                });

    }

    @Override
    public Customer customerById(UUID id) {
        return Optional.ofNullable(customersById.get(id)).orElseThrow(this::notFound);
    }

    @Override
    public Customer customerByName(String name) {
        return Optional.ofNullable(customersByName.get(name)).orElseThrow(this::notFound);
    }

    private ResponseStatusException notFound() {
        return new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

}
