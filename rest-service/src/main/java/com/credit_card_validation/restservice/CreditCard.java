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

public class CreditCard {
    
    static enum CreditCardType {Amex, Discover, MasterCard, Visa};  
        
    private CreditCardAmex ccAmex = null;
    
    private CreditCardDiscover ccDiscover = null;    
    
    private CreditCardMasterCard ccMasterCard = null;   
    
    private CreditCardVisa ccVisa = null;      
    
    private int numberLength = 0;
    
    private String ccNumber = null;
    
    private CreditCardType ccType = null;
    
    private boolean isLuhnValid = false;
    
    private boolean isValidCreditCard = false;
    
    private String constructorError = null;




    public String getConstructorError() {
        return constructorError;
    }


    

    private boolean isValidLengthRange(String argCcNumber){
        
        if ( (argCcNumber.length() < 13) || (argCcNumber.length() == 14) || (argCcNumber.length() > 16) ) 

            return false;
        else

            return true;

    }
    
    
    private boolean isOnlyDigits(String argCcNumber){
    

        for (int i = 0; i < argCcNumber.length(); i++) {

            if (!(argCcNumber.charAt(i) >= '0' && argCcNumber.charAt(i) <= '9')) 
                
                return false;

        }
         return true;

    }
    
    
    
    private boolean filterCcNumber(String argCcNumber){
        
        if (!isValidLengthRange(argCcNumber) ){
            
            constructorError = "Constructor error - The length of the passed credit card number is out of range!";
            return false;
        }
        else if (!isOnlyDigits(argCcNumber)){
            
            constructorError = "Constructor error - The passed credit card number contains a non-numeric character!";
            return false;
        }
        else 
            return true;
            
            
    }
    
    
    public CreditCard(String argCcNumber) {
        
        if (filterCcNumber(argCcNumber)){ // filter first - ensure that the passed credit card number has a length that is in range and that all characters are numeric only
        
            ccNumber = argCcNumber;

            numberLength = ccNumber.length();

            ccAmex = new CreditCardAmex(argCcNumber, numberLength);

            ccDiscover = new CreditCardDiscover(argCcNumber, numberLength);

            ccMasterCard = new CreditCardMasterCard(argCcNumber, numberLength);

            ccVisa = new CreditCardVisa(argCcNumber, numberLength);
            
            isValidCC(argCcNumber);
        }

    }

    public CreditCard() {
    }

    
    
    public boolean isIsValidCreditCard() {
        return isValidCreditCard;
    }



    public boolean isIsLuhnValid() {
        return isLuhnValid;
    }


    
   public boolean isLuhnValidated(String argCcNumber){
       
        numberLength = argCcNumber.length();
        
        boolean isSkipped = false;
        
        int sum = 0;

        for (int x = numberLength - 1; x >= 0; x--) // parse each digit of the original String representing the credit card number from right to left
        {
            int digit = argCcNumber.charAt(x) - '0'; // d represents the original digit, at each iteration, that is being parsed from right to left

            if (isSkipped == true)  // as the iteration progresses, parsing the digits from right to left, skip a digit; skipping is toggled by the isSkipped = !isSkipped flag
                digit = digit * 2;          // double the non-skipped digit

            // if the doubling results in a number that is greater than 9 such as 10, break the 10 into two separate numbers 1 and 0 and add those up, and add the result to the sum total 

            sum += digit / 10;  // eg. 18     (18/10) = 1                      (if the number is less than 10 such as 3, it becomes 0)
            sum += digit % 10;  // modulus    (18%10) = 18-10=8     1+8=9      (if the number is smaller than 10 such as 3, then it just remains 3)

            isSkipped = !isSkipped; // skipping digits when parsing from right to left is achieved by toggling this flag at every iteration
        }
        
        isLuhnValid = (sum % 10 == 0);
        
            
    return isLuhnValid;

   
   }

    
    
    private void initialise(String argCcNumber){
    
        ccNumber = argCcNumber;
        numberLength = (byte)ccNumber.length();
        
        if (ccAmex == null) ccAmex = new CreditCardAmex(argCcNumber, numberLength);
        if (ccDiscover == null) ccDiscover = new CreditCardDiscover(argCcNumber, numberLength);
        if (ccMasterCard == null) ccMasterCard = new CreditCardMasterCard(argCcNumber, numberLength);
        if (ccVisa == null) ccVisa = new CreditCardVisa(argCcNumber, numberLength);        

    }
    
    
    public boolean isValidCC(String argCcNumber){
        
        initialise(argCcNumber);
        
        
        if ( this.ccMasterCard.isCandidate() && isLuhnValidated(argCcNumber) ){
            
            this.setCcType(CreditCardType.MasterCard);
            
            isValidCreditCard = true;
    
            return true;
        
        }    
        
        else if ( this.ccVisa.isCandidate() && isLuhnValidated(argCcNumber) ){
            
            this.setCcType(CreditCardType.Visa);
            
            isValidCreditCard = true;
    
            return true;
        
        }
        
        else if ( this.ccAmex.isCandidate() && isLuhnValidated(argCcNumber) ){
            
            this.setCcType(CreditCardType.Amex);
            
            isValidCreditCard = true;
    
            return true;
        
        }
        
        else if ( this.ccDiscover.isCandidate() && isLuhnValidated(argCcNumber) ){
            
            this.setCcType(CreditCardType.Discover);
            
            isValidCreditCard = true;
    
            return true;
        
        }
        
        else
            
            return false;
            
        
    }


    
 //   public boolean isValidCC(){
    
 //       return isValidCC(this.ccNumber);
    
 //   }

    public int getNumberLength() {
        return numberLength;
    }

    public void setNumberLength(int numberLength) {
        this.numberLength = numberLength;
    }

    public String getCcNumber() {
        return ccNumber;
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public CreditCardType getCcType() {
        return ccType;
    }

    private void setCcType(CreditCardType ccType) {
        this.ccType = ccType;
    }

    
    
    
    /*
    
     static public void main (String[] args)
    {
       
        String ccNum1 = "4408041234567893"; // valid Visa
        String ccNum2 = "4111111111111111"; // valid Visa
        String ccNum3 = "4111111111111";    // not valid
        String ccNum4 = "4012888888881881"; // valid Visa
        String ccNum5 = "378282246310005";  // valid Amex
        String ccNum6 = "6011111111111117"; // valid Discover        
        String ccNum7 = "5105105105105100"; // valid Mastercard
        String ccNum8 = "5105105105105106"; // not valid
        String ccNum9 = "9111111111111111"; // not valid
        String ccNum10 = "51051T5105105100"; // Constructor error - The passed credit card number contains a non-numeric character!
        String ccNum11 = "51051851051051002872"; // Constructor error - The length of the passed credit card number is out of range!
        
        
        // @@@@@@@ CONSTRUCTOR ALONE CONTAINS ALL THE INFORMATION 
        
        CreditCard objCC = new CreditCard(ccNum1);
        
        if ((objCC.constructorError == null)){
            
            if (objCC.isIsValidCreditCard()) {

                System.out.println("SCENARIO 1 - THE CONSTRUCTOR ALONE DOES ALL THE WORK. JUST CHECK THE MEMBER VARIABLES' VALUES; The card number " + ccNum1 + " is valid and is of type - " + objCC.getCcType().name());

            }  
        }
            
        
         // @@@@@@@ BUT YOU CAN ALSO INVOKE THE PUBLIC METHOD .isValidCC() - USING DEFAULT CONSTRUCTOR
         
        objCC = new CreditCard();
        
        if ((objCC.constructorError == null)){

                boolean isValidCC = objCC.isValidCC(ccNum8);

                if (isValidCC)

                    System.out.println("SCENARIO 2 - This is a valid card number " + ccNum8 + " of type " + objCC.getCcType().name());

                else

                    System.out.println("SCENARIO 2 - This card number " + ccNum8 + " is not a valid card");

        }
        
        else
            System.out.println("SCENARIO 2 - " + objCC.getConstructorError());
        
        
        
        
         // @@@@@@@ BUT YOU CAN ALSO INVOKE THE PUBLIC METHOD .isValidCC() 
         
        objCC = new CreditCard();
        
        if ((objCC.constructorError == null)){

                boolean isValidCC = objCC.isValidCC(ccNum5);

                if (isValidCC)

                    System.out.println("SCENARIO 3 - This is a valid card number " + ccNum5 + " of type " + objCC.getCcType().name());

                else

                    System.out.println("SCENARIO 3 - This is not a valid card number " + ccNum5);

        }
        
        else
            System.out.println("SCENARIO 3 - " + objCC.getConstructorError());
                
        
        
         // @@@@@@@ INVOKE A CONSTRUCTOR ERROR BY PASSING A CREDIT CARD NUMBER THAT HAS AN OUT OF RANGE LENGTH OR THAT CONTAINS A CHARACTER IN THE STRING THAT IS NOT NUMERIC
         
        objCC = new CreditCard(ccNum10);
        
        if ((objCC.constructorError == null)){}
        
        else
            System.out.println("SCENARIO 4 - " + ccNum10 + " - " + objCC.getConstructorError()); // see the error message in the member variable constructorError
                        
     
    }      
    */
}
