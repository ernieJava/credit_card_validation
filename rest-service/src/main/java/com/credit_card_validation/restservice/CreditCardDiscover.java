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
public class CreditCardDiscover extends CreditCard{
    
    public final static String BEGINS_WITH = "6011";    // Discover constant
    
    public final static int NUMBER_LENGTH = 16;        // Discover constant
    
    private boolean isBeginsWithValidated = false;
    
    private boolean isNumberLengthValidated = false;
    
 

    public CreditCardDiscover(String argCcNumber, int argNumberLength) {
        
        this.setCcNumber(argCcNumber);
        this.setNumberLength(argNumberLength);
    }    
    
    
    
    // Return true if DISCOVER passes the 'begins with' & 'number length' validations.
    
    public boolean isBeginsWithValidated() {
        return isBeginsWithValidated;
    }



    public void setBeginsWithValidated(boolean isBeginsWithValidated) {
        this.isBeginsWithValidated = isBeginsWithValidated;
    }



    public boolean isNumberLengthValidated() {
        return isNumberLengthValidated;
    }



    public void setNumberLengthValidated(boolean isNumberLengthValidated) {
        this.isNumberLengthValidated = isNumberLengthValidated;
    }



    public boolean isCandidate (){
        
        String first4Digits = this.getCcNumber().substring(0,4);
        

        if (first4Digits.contentEquals(BEGINS_WITH)){
            
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
