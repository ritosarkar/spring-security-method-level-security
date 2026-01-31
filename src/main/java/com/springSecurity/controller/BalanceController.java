package com.springSecurity.controller;


import com.springSecurity.model.AccountTransactions;
import com.springSecurity.repository.AccountTransactionsRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class BalanceController {

    private AccountTransactionsRepository accountTransactionsRepository;
    @GetMapping("/myBalance")
    public List<AccountTransactions> getBalanceDetails(@RequestParam long id){
        return accountTransactionsRepository.findByCustomerIdOrderByTransactionDtDesc(id);

    }
}
