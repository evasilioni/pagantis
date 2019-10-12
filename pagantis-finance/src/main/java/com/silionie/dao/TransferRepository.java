package com.silionie.dao;

import com.silionie.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {

    @Query("select t from Transfer t where t.accountId = :accountId")
    List<Transfer> findTransfersByAccount(@Param("accountId") Long accountId);
}
