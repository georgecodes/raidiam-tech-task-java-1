package com.raidiam.techtask.accountapi.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@EqualsAndHashCode(exclude = "accounts")
public class Customer {

    private UUID id;
    private String name;
    private List<Account> accounts;

}
