package com.silionie.service;


import com.silionie.dao.BankRepository;
import com.silionie.model.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {

    @Autowired
    private BankRepository bankRepository;

    public List<Bank> findBanks(){
        return bankRepository.findAll();
    }
}
