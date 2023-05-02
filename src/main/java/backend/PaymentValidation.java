package backend;

enum ERROR_CODE{TOO_SHORT, INVALID_NUMBERS, INVALID_DATE, VALID};
public class PaymentValidation {

    public static ERROR_CODE verifyCardNumber(String Number){
        return ERROR_CODE.VALID;
    }

    public static ERROR_CODE verifyCardDate(String Date){
        return ERROR_CODE.VALID;
    }

    public static ERROR_CODE verifyCardCVV(String CVV){
        return ERROR_CODE.VALID;
    }
}
