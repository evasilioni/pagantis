package com.silionie.service;


import com.silionie.dao.AccountRepository;
import com.silionie.dao.CustomerRepository;
import com.silionie.model.Account;
import com.silionie.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    public List<Customer> findCustomers(Long bankId){
        List<Customer> customers = customerRepository.findAll();

        return customers.stream()
                .filter(c -> c.getBankCustomers().stream().anyMatch(b -> b.getBank().getId() == bankId))
                .collect(Collectors.toList());
    }

    public List<Account> findCustomerAccounts(Long bankId, Long customerId){
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().filter(a -> a.getBankCustomer().getBank().getId() == bankId &&
                a.getBankCustomer().getCustomer().getId() == customerId)
                .collect(Collectors.toList());
    }
}
