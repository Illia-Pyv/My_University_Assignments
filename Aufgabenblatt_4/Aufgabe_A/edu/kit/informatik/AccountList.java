package edu.kit.informatik;

/**
 * This class represents a List of all Accounts in a bank.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class AccountList {

    private ListCell head;

    /**
     * This is the constructor of the class.
     * 
     * It initializes the List head element.
     */
    public AccountList() {
        this.head = null;
    }

    /**
     * This method adds an account to the end of the list.
     * 
     * @param account The account which is to be added
     */
    public void add(Account account) {
        ListCell cell = this.head;
        if (cell == null) {
            this.head = new ListCell(account, this.head);
        } else {
            while (cell.next != null) {
                cell = cell.next;
            }
            cell.next = new ListCell(account, null);
        }
    }

    /**
     * This method adds an account in between other accounts at the position index.
     * 
     * If the index is invalid the account is added at the end of the list.
     * 
     * @param account The account which is to be added
     * @param index   The index which describes the position at which the account
     *                should be added
     */
    public void add(Account account, int index) {
        ListCell cell = this.head;
        int indexNumber = 0;
        if (cell == null) {
            add(account);
            return;
        }

        if (indexNumber == index) {
            this.head = new ListCell(account, this.head);
            return;
        }

        indexNumber++;
        while (indexNumber < index) {
            if (cell.next != null) {
                indexNumber++;
                cell = cell.next;
            } else {
                add(account);
                return;
            }
        }
        cell.next = new ListCell(account, cell.next);
    }

    /**
     * This method removes an account from the list.
     * 
     * @param index The account number of the account which should be removed.
     * @return Returns true if the account was removed successfully and false if
     *         there was no account to be removed or if the account number was
     *         invalid
     */
    public boolean remove(int index) {
        boolean removed = false;
        ListCell cell = this.head;
        if (cell != null && cell.content.getAccountNumber() == index) {
            cell = cell.next;
            this.head = cell;
            return true;
        }

        if (cell == null) {
            removed = false;
            return removed;
        }

        while (cell.next != null) {
            if (cell.next.content.getAccountNumber() == index) {
                cell.next = cell.next.next;
                removed = true;
                return removed;
            } else {
                cell = cell.next;
            }
        }
        return removed;
    }

    /**
     * This method gets the first account in the list.
     * 
     * @return Returns the account which is positioned as the first one in the list
     */
    public Account getFirst() {
        if (this.head == null) {
            return null;
        }
        return this.head.content;
    }

    /**
     * This method gets the last account in the list.
     * 
     * @return Returns the account which is positioned at the very end of the list
     */
    public Account getLast() {
        ListCell cell = this.head;
        if (cell == null) {
            return null;
        }
        while (cell.next != null) {
            cell = cell.next;
        }
        return cell.content;
    }

    /**
     * This method gets the account with the received account number.
     * 
     * @param index The account number of an account we are looking for
     * @return Retruns the account of the received account number
     */
    public Account get(int index) {
        ListCell cell = this.head;
        if (cell != null && cell.content.getAccountNumber() == index) {
            return cell.content;
        } else if (cell != null && cell.content.getAccountNumber() != index) {
            while (cell.next != null && cell.next.content.getAccountNumber() != index) {
                cell = cell.next;
            }
            if (cell.next == null) {
                return null;
            }
        } else {
            return null;
        }
        return cell.next.content;
    }

    /**
     * This method tells if the list contains the searched account.
     * 
     * @param account The received account which we search for in the list
     * @return Returns true if the account is in the list and false if the account
     *         is not in the list
     */
    public boolean contains(Account account) {
        for (ListCell cell = this.head; cell != null; cell = cell.next) {
            if (cell.content == account) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method determines the size of the account list.
     * 
     * @return Returns the amount of accounts in this list
     */
    public int size() {
        int numOfAccounts = 0;
        ListCell cell = this.head;
        if (this.head == null) {
            return numOfAccounts;
        } else {
            numOfAccounts++;
            while (cell.next != null) {
                numOfAccounts++;
                cell = cell.next;
            }
        }
        return numOfAccounts;
    }

    /**
     * This class represents a cell in the list "AccountList".
     * 
     * It stores the values for the content of a cell as well as the reference to
     * the next cell.
     */
    private class ListCell {
        public Account content;
        public ListCell next;

        ListCell(Account argAccount, ListCell argNext) {
            this.content = argAccount;
            this.next = argNext;
        }
    }
}
