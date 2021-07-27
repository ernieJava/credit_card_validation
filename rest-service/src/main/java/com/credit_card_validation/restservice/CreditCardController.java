package com.credit_card_validation.restservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ernesto Sanchez 
 * 
 * version 1.0 
 * 26-07-21
 * 
 * 
 */
@RestController
public class CreditCardController {


    @GetMapping("/validate-credit-card-number")
    public CreditCard validate_credit_card_number(@RequestParam(value="ccNumber") String argCcNumber){

        return new CreditCard(argCcNumber);
    

    }    
    
}
