package com.silionie.api;

import com.silionie.exceptions.TransferException;
import com.silionie.model.Transfer;
import com.silionie.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(
            value = "/account/{accountId}/transfers",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public @ResponseBody
    ResponseEntity<List<Transfer>> getTransfers(@PathVariable Long accountId) {
        List<Transfer> transfers = accountService.findTransfers(accountId);
        return ResponseEntity.ok(transfers);
    }

    @RequestMapping(
            value = "/account/{accountId}/transfers",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public @ResponseBody
    ResponseEntity<Transfer> sendTransfer(@PathVariable Long accountId, @RequestBody Transfer transfer) throws TransferException {
        Transfer transferPersisted = accountService.send(accountId, transfer);
        if(transferPersisted.getId() != 0){
            return ResponseEntity.accepted().body(transferPersisted);
        }

        return ResponseEntity.badRequest().build();
    }
}
