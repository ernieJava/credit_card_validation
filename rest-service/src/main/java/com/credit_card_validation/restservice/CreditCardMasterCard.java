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
public class CreditCardMasterCard extends CreditCard{
    
    public final static String BEGINS_WITH_1 = "51";    // MasterCard constant
    public final static String BEGINS_WITH_2 = "52";    // MasterCard constant
    public final static String BEGINS_WITH_3 = "53";    // MasterCard constant
    public final static String BEGINS_WITH_4 = "54";    // MasterCard constant
    
    public final static int NUMBER_LENGTH = 16;        // MasterCard constant
    
    private boolean isBeginsWithValidated = false;
    
    private boolean isNumberLengthValidated = false;

    
    public CreditCardMasterCard(String argCcNumber, int argNumberLength) {
        
        this.setCcNumber(argCcNumber);
        this.setNumberLength(argNumberLength);
    }    
    
    
    
    // Return true if MASTERCARD passes the 'begins with' & 'number length' validations.
    
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
        
        String first2Digits = this.getCcNumber().substring(0,2);
     
        
        if (first2Digits.contentEquals(BEGINS_WITH_1) || first2Digits.contentEquals(BEGINS_WITH_2) || first2Digits.contentEquals(BEGINS_WITH_3) || first2Digits.contentEquals(BEGINS_WITH_4)){
            
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
