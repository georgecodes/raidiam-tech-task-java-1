package com.raidiam.techtask.accountapi.accountapi;

import com.raidiam.techtask.accountapi.model.Customer;
import com.raidiam.techtask.accountapi.services.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AccountApiTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CustomerService customerService;

    @Test
    void getAccounts() throws Exception {

        Customer customer = customerService.customerByName("Tom");

        mvc.perform(MockMvcRequestBuilders.get(String.format("/api/v1/accounts/%s", customer.getId()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId").value(customer.getId().toString()))
                .andExpect(jsonPath("$.data.length()").value(2))
                .andExpect(jsonPath("$.data.[0].accountId").value("4329842438"))
                .andExpect(jsonPath("$.data.[0].balance").value(3000))
                .andExpect(jsonPath("$.data.[1].accountId").value("94946133"))
                .andExpect(jsonPath("$.data.[1].balance").value(300));

    }

    @Test
    void getAccountsFailsIfCustomerUnknown() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get(String.format("/api/v1/accounts/%s", UUID.randomUUID()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

    @Test
    void getAccountTransactions() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/api/v1/accounts/4329842438/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.accountNumber").value("4329842438"))
                .andExpect(jsonPath("$.transactions.length()").value(5))
                .andExpect(jsonPath("$.transactions.[0].reference").value("Salary"))
                .andExpect(jsonPath("$.transactions.[0].amount").value(1300))
                .andExpect(jsonPath("$.transactions.[1].reference").value("Mortgage"))
                .andExpect(jsonPath("$.transactions.[1].amount").value(-800))
                .andExpect(jsonPath("$.transactions.[2].reference").value("Salary"))
                .andExpect(jsonPath("$.transactions.[2].amount").value(1300))
                .andExpect(jsonPath("$.transactions.[3].reference").value("cash"))
                .andExpect(jsonPath("$.transactions.[3].amount").value(-100))
                .andExpect(jsonPath("$.transactions.[4].reference").value("Salary"))
                .andExpect(jsonPath("$.transactions.[4].amount").value(1300));

    }

    @Test
    void getAccountTransactionsFailsIfNotFound() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/api/v1/accounts/nonexistemt/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }


}
