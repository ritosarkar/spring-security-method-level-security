package com.springSecurity.controller;


import com.springSecurity.model.Loans;
import com.springSecurity.repository.LoanRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class LoansController {

    private final LoanRepository loanRepository;

    @GetMapping("/myLoans")
    @PostAuthorize("hasRole('USER')")
    public List<Loans> getLoansDetails(@RequestParam long id){
        return loanRepository.findByCustomerIdOrderByStartDtDesc(id);
    }
}
