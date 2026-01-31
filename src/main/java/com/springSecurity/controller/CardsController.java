package com.springSecurity.controller;


import com.springSecurity.model.Cards;
import com.springSecurity.repository.CardsRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CardsController {

    private final CardsRepository cardsRepository;
    @GetMapping("/myCards")
    public List<Cards> getCardsDetails(@RequestParam long id){
        return cardsRepository.findByCustomerId(id);
    }
}
