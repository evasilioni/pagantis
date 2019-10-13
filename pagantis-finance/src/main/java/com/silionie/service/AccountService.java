package com.silionie.service;


import com.silionie.dao.AccountRepository;
import com.silionie.dao.TransferRepository;
import com.silionie.dao.TransferTypeRepository;
import com.silionie.enums.TransferMessageCode;
import com.silionie.enums.TransferTypeEnum;
import com.silionie.exceptions.TransferException;
import com.silionie.model.Account;
import com.silionie.model.Transfer;
import com.silionie.model.TransferType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private TransferTypeRepository transferTypeRepository;

    public List<Transfer> findTransfers(Long accountId){
        Optional<Account> account = accountRepository.findAll().stream()
                .filter(a -> a.getId() == accountId).findFirst();

        if(account.isPresent()){
            return transferRepository.findTransfersByAccount(account.get().getId());
        }

        return new ArrayList<>();
    }


    public Transfer send(Long accountId, Transfer transfer){
        if(transfer.getAmount().compareTo(new BigDecimal(1000)) > 0 ){
            throw new TransferException(TransferMessageCode.TRANSFER_LIMIT.toString(), null);
        }

        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        if(optionalAccount.isPresent()){

            BigDecimal balance = optionalAccount.get().getBalance();

            if(transfer.getTransferType().getName().equals(TransferTypeEnum.INTER.name())){
                BigDecimal amountCommissioned = transfer.getAmount().add(new BigDecimal(5));
                if(balance.compareTo(amountCommissioned) >= 0) {
                    Transfer toPersist = save(optionalAccount.get(), transfer);
                    if(toPersist != null){
                        optionalAccount.get().setBalance(balance.subtract(amountCommissioned));
                        accountRepository.save(optionalAccount.get());
                        return toPersist;
                    }else {
                        throw new TransferException(TransferMessageCode.NOT_COMPLETED.toString(), null);
                    }

                } else {
                    throw new TransferException(TransferMessageCode.ERROR_ON_BALANCE.toString(), null);
                }
            } else if(transfer.getTransferType().getName().equals(TransferTypeEnum.INTRA.name())) {
                if(balance.compareTo(transfer.getAmount()) >= 0) {
                    Transfer toPersist = save(optionalAccount.get(), transfer);
                    if(toPersist != null){
                        optionalAccount.get().setBalance(balance.subtract(transfer.getAmount()));
                        accountRepository.save(optionalAccount.get());
                        return toPersist;
                    }else {
                        throw new TransferException(TransferMessageCode.NOT_COMPLETED.toString(), null);
                    }

                } else {
                    throw new TransferException(TransferMessageCode.ERROR_ON_BALANCE.toString(), null);
                }
            } else {
                throw new TransferException("Transfer type " + transfer.getTransferType().getName() + " is not accepted", null);
            }

        }  else {
            throw new TransferException(TransferMessageCode.ERROR_ON_ACCOUNT.toString(), null);
        }
    }


    private Transfer save(Account account, Transfer transfer){

        TransferType transferType = transferTypeRepository.findTransferTypeByName(transfer.getTransferType().getName());
        try{
            Transfer toBePersisted = new Transfer();
            toBePersisted.setAccount(account);
            toBePersisted.setSourceAccountNumber(transfer.getSourceAccountNumber());
            toBePersisted.setTargetAccountNumber(transfer.getTargetAccountNumber());
            toBePersisted.setTransferType(transferType);
            toBePersisted.setTransferDate(new Date());
            toBePersisted.setAmount(transfer.getAmount());
            return transferRepository.save(toBePersisted);
        }catch (Exception ex){
            LOGGER.error(ex.getMessage());
        }

        return null;
    }

}
