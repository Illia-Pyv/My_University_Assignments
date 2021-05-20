package edu.kit.informatik;

/**
 * This class is used to test all methods in the classes Account and Bank.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public final class Test {

    /** Utility classes have a private constructor */
    private Test() {
    }

    /**
     * This class is used to test instantiate a bank and an account.
     * 
     * @param args optional arguments entered by the user
     */
    public static void main(String[] args) {
        Bank volksbank = new Bank(1);

        Account myAccount = new Account(volksbank.getBankCode(), volksbank.createAccount());
        myAccount.deposit(20);
    }
}