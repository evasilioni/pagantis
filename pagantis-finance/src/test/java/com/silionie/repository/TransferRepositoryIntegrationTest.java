package com.silionie.repository;

import com.silionie.dao.*;
import com.silionie.model.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TransferRepositoryIntegrationTest {

    private static Random random = new Random();
    private static Bank bank;
    private static Customer customer;
    private static BankCustomer bankCustomer;
    private static Account account;
    private static TransferType transferType;

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private TransferRepository transferRepository;
    @Autowired
    private BankRepository bankRepository;
    @Autowired
    private BankCustomerRepository bankCustomerRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransferTypeRepository transferTypeRepository;

    @BeforeClass
    public static void initData(){
        bank = initBank("BANK" + String.valueOf(random.nextInt(100)));
        customer = initCustomer(bank);
        bankCustomer = initBankCustomer(customer, bank);
        account = initAccount(bankCustomer);
    }


    @Test
    public void whenFindByAccount_thenReturnTransfers(){
        //
        bankRepository.save(bank);
        customerRepository.save(customer);
        bankCustomerRepository.save(bankCustomer);
        accountRepository.save(account);
        transferType = transferTypeRepository.findTransferTypeByName("INTER");

        //when
        List<Transfer> transfersByAccount = transferRepository.findTransfersByAccount(account.getId());

        //then
        assertThat(transfersByAccount.size()).isEqualTo(0);


        //when
        Transfer transfer = new Transfer();
        transfer.setAmount(new BigDecimal(450));
        transfer.setSourceAccountNumber(account.getAccountNumber());
        transfer.setTransferDate(new Date());
        transfer.setTargetAccountNumber("QAZ1234567890");
        transfer.setTransferType(transferType);
        transfer.setAccount(account);
        transferRepository.save(transfer);

        account.setBalance(account.getBalance().subtract(new BigDecimal(450).add(new BigDecimal(5))));
        accountRepository.save(account);

        //when
        transfersByAccount = transferRepository.findTransfersByAccount(account.getId());

        //then
        assertThat(transfersByAccount.size()).isEqualTo(1);
        assertThat(accountRepository.findById(account.getId()).get().getBalance()).isEqualTo(new BigDecimal(14545));
    }


    private static Bank initBank(String bankName) {
        Bank bank = new Bank();
        bank.setName(bankName);
        return bank;
    }

    private static Customer initCustomer(Bank bank) {
        Customer customer = new Customer();
        customer.setLastName("JACOBS");
        customer.setFirstName("MARC");
        customer.setSocialSecurityNumber("ZXC09867");
        customer.setPhoneNumber("1236547893");
        customer.setEmail("jacobs@gmail.com");
        return customer;
    }

    private static BankCustomer initBankCustomer(Customer customer, Bank bank){
        BankCustomer bankCustomer = new BankCustomer();
        bankCustomer.setCustomer(customer);
        bankCustomer.setBank(bank);
        return bankCustomer;
    }

    private static Account initAccount(BankCustomer bankCustomer){
        Account account = new Account();
        account.setBalance(new BigDecimal(15000));
        account.setAccountNumber("EDW145236987418");
        account.setBankCustomer(bankCustomer);
        return account;
    }
}
