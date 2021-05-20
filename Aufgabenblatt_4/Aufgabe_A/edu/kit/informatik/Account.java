package edu.kit.informatik;

/**
 * The following class represents a bank account.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class Account implements Comparable<Account> {

    private int accountNumber;
    private int bankCode;
    private int balance = 0;

    /**
     * The following constructor initializes the account number and the bank code
     * 
     * @param bankCode      the bank code
     * @param accountNumber the account number of the customer
     */
    public Account(int bankCode, int accountNumber) {
        this.bankCode = bankCode;
        this.accountNumber = accountNumber;
    }

    /**
     * The getter-method for the attribute balance
     * 
     * There is no method called setBalance(), since the balance of an account
     * should only be changed by a withdrawal, a deposit or a transfer from/to
     * another account
     * 
     * @return the balance of the account
     */
    public int getBalance() {
        return balance;
    }

    /**
     * The getter-method for the attribute accountNumber
     *
     * There is no method called setAccountNumber() since the account number should
     * not be changed after an object of this class was created
     * 
     * @return the account number
     */
    public int getAccountNumber() {
        return accountNumber;
    }

    /**
     * This method is used to get the bank code of the account
     * 
     * There is no method to change the bank code, since the bank code should not be
     * changed after an object of this class was created
     * 
     * @return the bank code
     */
    public int getBankCode() {
        return bankCode;
    }

    /**
     * This method compares this account and the received account.
     * 
     * @param account receives an account, which this account should be compared to
     * @return Returns a "0" if this account number is the same as the received
     *         account number, returns a "-1" if this account number is smaller than
     *         the received account number, and it returns a "1" if this account
     *         number is bigger than the received account number
     */
    public int compareTo(Account account) {
        int areSame;
        if (getAccountNumber() == account.getAccountNumber()) {
            areSame = 0;
        } else if (getAccountNumber() < account.getAccountNumber()) {
            areSame = -1;
        } else {
            areSame = 1;
        }
        return areSame;
    }

    /**
     * The following method allows the withdrawal from an account, but only if the
     * amount is covered by the balance of the account.
     * 
     * @param amount the amount to withdraw
     * @return true, if the withdrawal was successful and false, if it was not
     */
    public boolean withdraw(int amount) {
        boolean withdrawalSuccessful = true;

        // Check, whether the parameter amount is really positive
        if (amount >= 0) {
            /*
             * If so, check, whether amount is covered by the balance of the account and
             * withdraw the money, if the amount is covered
             */
            if (balance - amount >= 0) {
                balance -= amount;
            } else {
                withdrawalSuccessful = false;
            }
        }

        return withdrawalSuccessful;
    }

    /**
     * This method is used to deposit money to the account.
     *
     * @param amount the amount of money to deposit
     */
    public void deposit(int amount) {
        // Check whether the parameter amount is really positive
        if (amount >= 0) {
            // If so, deposit the money
            balance += amount;
        }
    }

    /**
     * This method is used to transfer money from an account to another account.
     * 
     * @param account the account of the recipient
     * @param amount  the amount to transfer
     * @return true, if the transfer was successful; false, if it was not successful
     */
    public boolean transfer(Account account, int amount) {
        boolean transferSuccessful = true;
        Account recipient = account;

        if (withdraw(amount) == true) {
            recipient.deposit(amount);
        } else {
            transferSuccessful = false;
        }

        return transferSuccessful;
    }
}