package com.silionie.dao;

import com.silionie.model.BankCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
@Transactional
public interface BankCustomerRepository extends JpaRepository<BankCustomer, Long> {

}
