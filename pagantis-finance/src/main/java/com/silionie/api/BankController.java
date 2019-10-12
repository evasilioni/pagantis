package com.silionie.api;

import com.silionie.model.Bank;
import com.silionie.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class BankController {

    @Autowired
    private BankService bankService;

    @RequestMapping(
            value = "/banks",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public @ResponseBody
    ResponseEntity<List<Bank>> getBanks() {
        List<Bank> banks = bankService.findBanks();
        return ResponseEntity.ok(banks);
    }
}
