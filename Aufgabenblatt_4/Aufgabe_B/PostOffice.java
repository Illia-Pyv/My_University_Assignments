
public class PostOffice {
    private User loggedUser;
    PostAccountList accountList = new PostAccountList();

    public PostOffice() {
        loggedUser = null;
    }

    public boolean addCustomer(String firstName, String lastName, String userName, String password,
            String idCardNumber) {
        User customer = new Customer(firstName, lastName, userName, password, idCardNumber);
        accountList.add(customer);
        return true;
    }

    public boolean addMailman(String firstName, String lastName, int personalNumber, String password) {
        User mailMan = new PostOfficeEmployee(firstName, lastName, personalNumber, password);
        accountList.add(mailMan);
        return true;
    }

    public boolean addAgent(String firstName, String lastName, int personalNumber, String password) {
        User agent = new PostOfficeEmployee(firstName, lastName, personalNumber, password);
        accountList.add(agent);
        return true;
    }

    public boolean authenticate(String userName, String password) {
        User user = accountList.findUser(userName, password);
        if (user == null) {
            //return "Error, user not found";
            return false;
        }
        loggedUser = user;
        return true;
    }

    public boolean logout() {
        loggedUser = null;
        return true;
    }

    public String listPrice() {
        String retValue = "";
        if (loggedUser != null) {
            retValue = loggedUser.listPrice();
        }
        return retValue;
    }

    public String listPrice(String userName) {
        User user = accountList.findUser(userName);
        String price = "";
        if (user != null) {
            price = user.listPrice();
        }
        return price;
    }

    public String getMail() {
        return (loggedUser.getMail());
    }

    public String resetPin(String userName, String password) {
        User user = accountList.findUser(userName);
        if (user != null) {
            user.setPassword(password);
            return "OK";
        }
        return "Error, user does not exist";
    }
    
    public boolean sendMail(String postalServices, String userName) {
        loggedUser.sendMail(postalServices, userName);
        return true;
    }
}
