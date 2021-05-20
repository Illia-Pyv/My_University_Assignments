
/**
 * This class represents the call center employee who works at the post office.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class CallcenterEmployee extends User {

    private int personalNumber;

    public CallcenterEmployee(String firstName, String lastName, int personalNumber, String password) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setPassword(password);
        this.setPersonalNumber(personalNumber);
    }

    public String listPrice(String userName) {
        String price = "";
        return price;
    }

    /**
     * This method sets the Personal Number.
     * 
     * @param personalNumber received from the user input which will be set
     */
    public void setPersonalNumber(int personalNumber) {
        this.personalNumber = personalNumber;
    }

    /**
     * This method gets the Personal Number.
     * 
     * @return Returns the Personal Number as an Integer
     */
    public int getPersonalNumber() {
        return personalNumber;
    }
}
