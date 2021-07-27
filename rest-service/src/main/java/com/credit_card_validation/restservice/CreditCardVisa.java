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
public class CreditCardVisa extends CreditCard{
    
    public final static String BEGINS_WITH = "4";    // Visa constant
    
    public final static int NUMBER_LENGTH_1 = 13;     // Visa constant
    public final static int NUMBER_LENGTH_2 = 16;     // Visa constant   
    
    private boolean isBeginsWithValidated = false;
    
    private boolean isNumberLengthValidated = false;

    
    public CreditCardVisa(String argCcNumber, int argNumberLength) {
        
        this.setCcNumber(argCcNumber);
        this.setNumberLength(argNumberLength);
    }        
    
    
    // Return true if VISA passes the 'begins with' & 'number length' validations.
    
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
        
        String firstDigit = this.getCcNumber().substring(0,1); 
    
        
        if (firstDigit.contentEquals(BEGINS_WITH)){
            
            setBeginsWithValidated(true);
            
            if ((super.getNumberLength() == NUMBER_LENGTH_1) || ( super.getNumberLength() == NUMBER_LENGTH_2) )  {
            
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
