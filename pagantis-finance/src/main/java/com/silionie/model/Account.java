package com.silionie.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ACCOUNT")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "account_number")
    private String accountNumber;

    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name = "bank_customer_id")
    private BankCustomer bankCustomer;

//    @OneToMany(mappedBy = "account")
//    private List<Transfer> transfers = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BankCustomer getBankCustomer() {
        return bankCustomer;
    }

    public void setBankCustomer(BankCustomer bankCustomer) {
        this.bankCustomer = bankCustomer;
    }

}
