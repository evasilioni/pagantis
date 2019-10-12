package com.silionie.dao;

import com.silionie.model.Bank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface BankRepository extends JpaRepository<Bank, Long> {

    List<Bank> findAll();
}
