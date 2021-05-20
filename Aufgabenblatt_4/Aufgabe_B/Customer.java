
/**
 * This class represents the customer at the PostOffice.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class Customer extends User {

    private String userName;
    private String idCardNumber;
    private String[] mail = new String[5];
    private int mailIndex = 0;
    private int counterBrief = 0;
    private int counterEinwurfSchreiben = 0;
    private int counterEinschreiben = 0;
    private int counterPaketS = 0;
    private int counterPaketM = 0;
    private int counterPaketL = 0;
    private double priceBrief = 0.0;
    private double priceEinwurfSchreiben = 0.00;
    private double priceEinschreiben = 0.00;
    private double pricePaketS = 0.00;
    private double pricePaketM = 0.00;
    private double pricePaketL = 0.00;

    public Customer(String firstName, String lastName, String userName, String password, String idCardNumber) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setPassword(password);
        this.setUserName(userName);
        this.setIDNumber(idCardNumber);
    }
    
    public String sendMail(String postalService, String receiver) {
        mail[mailIndex++] = postalService + ";" + receiver;
        return "OK";
    }
    
    public String getMail() {
        if ( mail.length > 0 ) {
            return "OK";
        }
        return "Error, there are no mails";
    }
    
    public String[] listMail() {
        return mail;
    }
    
    public String listPrice() {
        PostalServices services = new PostalServices();
        String resultPrice = "";
        String[] prices = services.getPriceList();
        String[] line;
        String[] strPriceList;
        
        
        for (String str: prices) {
            strPriceList = str.split(";");
            if (strPriceList[0].equals("Brief")) {
                priceBrief = Double.parseDouble(strPriceList[1]);
            } else if (strPriceList[0].equals("EinwurfSchreiben")) {
                priceEinwurfSchreiben = Double.parseDouble(strPriceList[1]);
            } else if (strPriceList[0].equals("Einschreiben")) {
                priceEinschreiben = Double.parseDouble(strPriceList[1]);
            } else if (strPriceList[0].equals("PaketS")) {
                pricePaketS = Double.parseDouble(strPriceList[1]);
            } else if (strPriceList[0].equals("PaketL")) {
                pricePaketL = Double.parseDouble(strPriceList[1]);
            } else if (strPriceList[0].equals("PaketM")) {
                pricePaketM = Double.parseDouble(strPriceList[1]);
            }
        }
        
        for (String s: mail) {
            if (s != null) {
                line = s.split(";");
                if (line[0].equals("Brief")) {
                    counterBrief++;
                } else if (line[0].equals("EinwurfScreiben")) {
                    counterEinwurfSchreiben++;
                } else if (line[0].equals("Einschreiben")) {
                    counterEinschreiben++;
                } else if (line[0].equals("PaketS")) {
                    counterPaketS++;
                } else if (line[0].equals("PaketM")) {
                    counterPaketM++;
                } else if (line[0].equals("PaketL")) {
                    counterPaketL++;
                }
            }
        }
        
        if (counterBrief > 0) {
            resultPrice = "Brief";
        } 
        if (counterEinwurfSchreiben > 0) {
            resultPrice += "," + "EinwurfScreiben";
        }
        if (counterEinschreiben > 0) {
            resultPrice += "," + "Eischreiben";
        }
        if (counterPaketS > 0) {
            resultPrice += "," + "PaketS";
        }
        if (counterPaketM > 0) {
            resultPrice += "," + "PaketM";
        }
        if (counterPaketL > 0) {
            resultPrice += "," + "PaketL";
        }
        int counterSum = counterBrief;
        counterSum += counterEinwurfSchreiben;
        counterSum += counterEinschreiben;
        counterSum += counterPaketS;
        counterSum += counterPaketM;
        counterSum += counterPaketL;
        double price = counterBrief * priceBrief;
        price += counterEinwurfSchreiben * priceEinwurfSchreiben;
        price += counterEinschreiben * priceEinschreiben;
        price += counterPaketS * pricePaketS;
        price += counterPaketL * pricePaketL;
        price += counterPaketM * pricePaketM;
             
        resultPrice += ";" + counterSum;
        resultPrice += ";" + String.format("%.2f", price);
        return resultPrice;
    }
    
    /**
     * This method sets the User name which is not their real name.
     * 
     * @param userName received from the user input which will be set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * This method sets the ID Card Number.
     * 
     * @param idNumber received from the user input and which will be set
     */
    public void setIDNumber(String idNumber) {
        this.idCardNumber = idNumber;
    }

    /**
     * This method gets the User name.
     * 
     * @return Returns the User name as a String
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * This method gets the ID Card Number.
     * 
     * @return Returns the ID Card Number as a String
     */
    public String getIDNumer() {
        return this.idCardNumber;
    }
}
