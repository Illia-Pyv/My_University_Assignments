package edu.kit.informatik;

/**
 * The following class represents a bank.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class Bank {

    private final int bankCode;
    private int accountNumber;
    private AccountList accountList = new AccountList();

    /**
     * This constructor is used to initialize the bank code of an object of this
     * class.
     * 
     * @param bankCode the bank code of the bank
     */
    public Bank(int bankCode) {
        this.bankCode = bankCode;
        this.accountNumber = 0;
    }

    /**
     * This method is used to get the bank code.
     * 
     * There is no method to change the bank code, since the bank code of the bank
     * should not be changed after an object of this class was initialized
     * 
     * @return the bank code of the bank
     */
    public int getBankCode() {
        return bankCode;
    }

    /**
     * This method is used to create a new bank account.
     * 
     * @return the account number of the new account
     */
    public int createAccount() {
        if (accountList.get(accountNumber) == null && accountNumber == 0) {
            Account newAccount = new Account(bankCode, accountNumber);
            accountList.add(newAccount);
        } else if (accountList.get(accountNumber) == null) {
            accountNumber++;
            Account newAccount = new Account(bankCode, accountNumber);
            accountList.add(newAccount);
        } else {
            accountNumber++;
            Account newAccount = new Account(bankCode, accountNumber);
            accountList.add(newAccount);
        }
        return accountNumber;
    }

    /**
     * This method is used to create a new bank account and add it in between other
     * accounts at position index.
     * 
     * @param index Position at which the account should be created
     * @return the account number of the new account
     */
    public int createAccountBetween(int index) {
        if (accountList.get(accountNumber) == null) {
            Account newAccount = new Account(bankCode, accountNumber);
            accountList.add(newAccount);
        } else {
            accountNumber++;
            Account newAccount = new Account(bankCode, accountNumber);
            accountList.add(newAccount, index);
        }
        return accountNumber;
    }

    /**
     * This method is used to remove an account.
     * 
     * @param accountNumber the account number of the account, which should be
     *                      removed
     * @return true, if the removal was successful and false, if not
     */
    public boolean removeAccount(int accountNumber) {
        return accountList.remove(accountNumber);
    }

    /**
     * Checks, whether the bank contains an account with a specific account number.
     * 
     * @param accountNumber the account number of the account
     * @return true, if the bank contains an account with the account number, false
     *         if not
     */
    public boolean containsAccount(int accountNumber) {
        return accountList.contains(accountList.get(accountNumber));
    }

    /**
     * This method is used to transfer money between to accounts in the same bank.
     * 
     * @param fromAccountNumber account number of the sender
     * @param toAccountNumber   account number of the recipient
     * @param amount            the amount of money to transfer
     * @return true, if transfer was successful, false if it was not
     */
    public boolean internalBankTransfer(int fromAccountNumber, int toAccountNumber, int amount) {
        Account sender = getAccount(fromAccountNumber);
        Account recipient = getAccount(toAccountNumber);
        boolean transferSuccessful = false;
        if (sender.getBankCode() == recipient.getBankCode()) {
            transferSuccessful = sender.transfer(recipient, amount);
        }
        return transferSuccessful;
    }
    
    /**
     * The following method is used to get the amount of bank accounts of a bank.
     * 
     * @return the amount of accounts
     */
    public int size() {
        return accountList.size();
    }

    /**
     * This method will get the same number as the method "size()".
     * 
     * It was designed to return the length of an Array, but as I had to replace the
     * arrays for a list "AccountList" it does practically the same as "size()".
     * 
     * @return the length of the accounts list
     */
    public int length() {
        return accountList.size();
    }

    /**
     * This method returns the wanted account from the account list.
     * 
     * @param accountNumber The account number of the account you are looking for
     * @return Returns the account you are looking for
     */
    public Account getAccount(int accountNumber) {
        return accountList.get(accountNumber);
    }

}