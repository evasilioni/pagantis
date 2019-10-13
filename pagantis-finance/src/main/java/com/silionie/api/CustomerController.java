package com.silionie.api;

import com.silionie.model.Account;
import com.silionie.model.Bank;
import com.silionie.model.Customer;
import com.silionie.service.BankService;
import com.silionie.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(value = "/bank/{bankId}")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(
            value = "/customers",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public @ResponseBody
    ResponseEntity<List<Customer>> getCustomers(@PathVariable Long bankId) {
        List<Customer> customers = customerService.findCustomers(bankId);
        return ResponseEntity.ok(customers);
    }

    @RequestMapping(
            value = "/customers/{customerId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public @ResponseBody
    ResponseEntity<List<Account>> getAccounts(@PathVariable Long bankId,
                                                      @PathVariable Long customerId) {
        List<Account> accounts = customerService.findCustomerAccounts(bankId, customerId);
        return ResponseEntity.ok(accounts);
    }
}
