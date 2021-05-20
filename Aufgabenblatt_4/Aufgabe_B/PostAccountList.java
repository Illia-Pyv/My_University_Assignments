
/**
 * This class represents a list of all accounts in the post office.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class PostAccountList {

    private ListCell head;

    /**
     * This is the constructor of the class.
     * 
     * It initializes the List head element.
     */
    public PostAccountList() {
        this.head = null;
    }

    public User findUser(String userName, String password) {
        User user; // = null;
        ListCell cell = this.head;
        user = cell.content;
        
        while (cell != null) {
            //user = cell.content;
            if (userName.equals(user.getUserName()) && password.equals(user.getPassword())) {
                break;
            }
            cell = cell.next;
        }        

        return user;
    }

    public User findUser(String userName) {
        User user = null;
        ListCell cell = this.head;

        while (cell != null) {
            user = cell.content;
            if (userName.equals(user.getUserName())) {
                break;
            }
            cell = cell.next;
        }

        return user;
    }

    /**
     * This method adds a post account to the end of the list.
     * 
     * @param account The account which is to be added
     */
    public void add(User account) {
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
     * This class represents a cell in the list "PostAccountList".
     * 
     * It stores the values for the content of a cell as well as the reference to
     * the next cell.
     */
    private class ListCell {
        public User content;
        public ListCell next;

        ListCell(User argAccount, ListCell argNext) {
            this.content = argAccount;
            this.next = argNext;
        }
    }
}
