package com.credit_card_validation.restservice;

/**
 *
 * @author Ernesto Sanchez 
 * 
 * version 1.0 
 * 26-07-21
 * 
 * 
 */
public class CreditCardAmex extends CreditCard{
    
    public final static String BEGINS_WITH_1 = "34";    // AMEX constant
    public final static String BEGINS_WITH_2 = "37";    // AMEX constant
    
    public final static int NUMBER_LENGTH = 15;        // AMEX constant
    
    private boolean isBeginsWithValidated = false;
    
    private boolean isNumberLengthValidated = false;

    
    public CreditCardAmex(String argCcNumber, int argNumberLength) {
        
        this.setCcNumber(argCcNumber);
        this.setNumberLength(argNumberLength);
    }

    
    // Return true if AMEX passes the 'begins with' & 'number length' validations.
    
    
    public boolean isNumberLengthValidated() {
        return isNumberLengthValidated;
    }


    public void setNumberLengthValidated(boolean isNumberLengthValidated) {
        this.isNumberLengthValidated = isNumberLengthValidated;
    }


    public boolean isBeginsWithValidated() {
        return isBeginsWithValidated;
    }


    public void setBeginsWithValidated(boolean isBeginsWithValidated) {
        this.isBeginsWithValidated = isBeginsWithValidated;
    }



    public boolean isCandidate (){
        
        String first2Digits = this.getCcNumber().substring(0,2);


        if (first2Digits.contentEquals(BEGINS_WITH_1) || first2Digits.contentEquals(BEGINS_WITH_2)){
            
            setBeginsWithValidated(true);
            
            if (super.getNumberLength() == NUMBER_LENGTH){
            
                setNumberLengthValidated(true);

                return true;
            
            }
            else
                return false;
        }
        else 
            return false;
        
    
        
    }
    
}



