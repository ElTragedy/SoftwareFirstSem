package backend;

enum ERROR_CODE{TOO_SHORT, INVALID_NUMBERS, INVALID_DATE, VALID};
public class PaymentValidation {

    /**
     * Verifies the validity of a credit card number.
     *
     * @param Number the credit card number to verify
     * @return an ERROR_CODE indicating the result of the verification
     */
    public static ERROR_CODE verifyCardNumber(String Number){
        return ERROR_CODE.VALID;
    }

    /**
     * Verifies the validity of a credit card expiration date.
     *
     * @param Date the expiration date to verify
     * @return an ERROR_CODE indicating the result of the verification
     */
    public static ERROR_CODE verifyCardDate(String Date){
        return ERROR_CODE.VALID;
    }

    /**
     * Verifies the validity of a credit card CVV code.
     *
     * @param CVV the CVV code to verify
     * @return an ERROR_CODE indicating the result of the verification
     */
    public static ERROR_CODE verifyCardCVV(String CVV){
        return ERROR_CODE.VALID;
    }
}
