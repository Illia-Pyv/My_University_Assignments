/**
 * User represents the super class of the classes
 * "Customer","PostOfficeEmployee" and "CallcenterEmployee".
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
abstract class User {
    private String password;
    private String firstName;
    private String lastName;

    /**
     * This method sets the First name.
     * 
     * @param firstName received name form the user input which will be set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * This method sets the Last name.
     * 
     * @param lastName received name form the user input which will be set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * This method sets the user password.
     * 
     * @param password received form the user input which will be set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * This method gets the First name.
     * 
     * @return Returns the first name as a String
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * This method gets the Last name.
     * 
     * @return Returns the last name as a String
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * This method gets the password name.
     * 
     * @return Returns the password as a String
     */
    public String getPassword() {
        return this.password;
    }

    public String getUserName() {
        return "";
    }

    public String listPrice() {
        return "";
    }

    public String getMail() {
        return "";
    }

    public String resetPin() {
        return "";
    }
    
    public String sendMail(String postalServices, String userName) {
        return "OK";
    }
}
