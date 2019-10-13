package com.silionie.dao;

import com.silionie.model.Transfer;
import com.silionie.model.TransferType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferTypeRepository extends JpaRepository<TransferType, Long> {

    TransferType findTransferTypeByName(String name);
}
