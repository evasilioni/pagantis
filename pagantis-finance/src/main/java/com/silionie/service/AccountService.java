package com.silionie.service;


import com.silionie.dao.AccountRepository;
import com.silionie.dao.CustomerRepository;
import com.silionie.dao.TransferRepository;
import com.silionie.model.Account;
import com.silionie.model.Customer;
import com.silionie.model.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransferRepository transferRepository;

    public List<Transfer> findTransfers(Long accountId){
        Optional<Account> account = accountRepository.findAll().stream()
                .filter(a -> a.getId() == accountId).findFirst();

        if(account.isPresent()){
            return transferRepository.findTransfersByAccount(account.get().getId());
        }

        return new ArrayList<>();
    }

    public Transfer send(Long accountId, Transfer transfer){
        /*1. find the account and check if the transfered amount exist in source account
        * 2. if exists then check if the transaction is inter or intra apply the commissions make the transfer and decrease the amount
        * 3. if the source amount is lkower that the requested return false
        * */

        return null;
    }

}
