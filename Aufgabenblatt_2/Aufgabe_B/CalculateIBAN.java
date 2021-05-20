import java.math.*;

/**
 * Offers several operations over an given IBAN
 * 
 * @author Daniel Weingand, Illia Pyvovar, Noël Ollick
 * @version 1.0
 */
public class CalculateIBAN {

    /** The country code for the IBAN */
    String countryCode;
    /** The bank code of the bank */
    String bankCode;
    /** The account number of the customer */
    String account;
    /** The formatted linked bank code an account number */
    String linkedBankCodeAndAccNum;

    /**
     * Declares the required attributes to the values needed to create the IBAN
     * 
     * @param countryCode the country code
     * @param bankCode    the bank code
     * @param account     number
     */
    CalculateIBAN(String countryCode, String bankCode, String account) {
        this.countryCode = countryCode;
        this.bankCode = bankCode;
        this.account = account;
    }

    /**
     * Calculates and formats the IBAN
     * 
     * @return returns the formatted IBAN
     */
    public String calculateIBAN() {
        linkBankCodeToAccountNumber();
        String checksumOfIBAN = calculateChecksum();
        String ibanWithoutSpaces = this.countryCode + checksumOfIBAN + linkedBankCodeAndAccNum;
        String formattedIBAN = ibanWithoutSpaces.substring(0, 4) + " " + ibanWithoutSpaces.substring(4, 8) + " "
                + ibanWithoutSpaces.substring(8, 12) + " " + ibanWithoutSpaces.substring(12, 16) + " "
                + ibanWithoutSpaces.substring(16, 20) + " " + ibanWithoutSpaces.substring(20, 22);

        return formattedIBAN;
    }

    /**
     * Links the bank code to the formatted account number
     */
    public void linkBankCodeToAccountNumber() {

        int zerosToAdd = 10 - account.length();
        for (int i = 0; i < zerosToAdd; i++) {
            account = "0" + account;
        }

        linkedBankCodeAndAccNum = bankCode + account;
    }

    /**
     * Encodes the country code to a number
     * 
     * @return the encoded country code
     */
    public BigInteger encodeCountryCode() {
        String twoZeros = "00";

        String result = "";
        for (int i = 0; i < 2; i++) {
            result += ((int) countryCode.charAt(i) - 64) + 9;
        }

        result += twoZeros;

        BigInteger encodedCountryCode = new BigInteger(result);
        return encodedCountryCode;
    }

    /**
     * Calculates the checksum for the IBAN
     * 
     * @return the checksum of the IBAN
     */
    public String calculateChecksum() {
        BigInteger minuend = new BigInteger("98");
        BigInteger denominator = new BigInteger("97");
        BigInteger ten = new BigInteger("10");
        String checksum = "";

        BigInteger linkedIBAN = new BigInteger(linkedBankCodeAndAccNum + encodeCountryCode());

        BigInteger moduloOfIBAN = linkedIBAN.mod(denominator);
        BigInteger subtractionResult = minuend.subtract(moduloOfIBAN);

        if (subtractionResult.compareTo(ten) == -1) {
            checksum = "0" + subtractionResult;
        } else {
            checksum = subtractionResult.toString();
        }

        return checksum;
    }

}