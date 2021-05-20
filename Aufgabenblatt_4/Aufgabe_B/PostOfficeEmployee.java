
/**
 * This class represents the post office employee who is working at the post
 * office.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class PostOfficeEmployee extends User {

    private int personalNumber;
    private int counterBrief = 0;
    private int counterEinwurfSchreiben = 0;
    private int counterEinschreiben = 0;
    private int counterPaketS = 0;
    private int counterPaketM = 0;
    private int counterPaketL = 0;
    private int priceBrief = 0;
    private int priceEinwurfSchreiben = 0;
    private int priceEinschreiben = 0;
    private int pricePaketS = 0;
    private int pricePaketM = 0;
    private int pricePaketL = 0;
    
    private String[] mail;

    public PostOfficeEmployee(String firstName, String lastName, int personalNumber, String password) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setPassword(password);
        this.setPersonalNumber(personalNumber);
    }

    public boolean sendMail(String postalService, String receiver, String sender) {
        int currentIndex = mail.length;
        mail[currentIndex] = postalService + ";" + receiver + ";" + sender;
        return true;
    }

    public String getMail(String receiver) {
        if (mail.length > 0) {
            return "OK";
        }
        return "Error, there are no mails";
    }

    public boolean listMail(String userName) {
        return true;
    }

    public String listPrice(String userName) {
        PostalServices services = new PostalServices();
        String resultPrice = "";
        String[] prices = services.getPriceList();
        String[] line;
        String[] strPriceList;


        for (String str : prices) {
            strPriceList = str.split(";");
            if (strPriceList[0].equals("Brief")) {
                priceBrief = Integer.parseInt(strPriceList[1]);
            } else if (strPriceList[0].equals("EinwurfSchreiben")) {
                priceEinwurfSchreiben = Integer.parseInt(strPriceList[1]);
            } else if (strPriceList[0].equals("Einschreiben")) {
                priceEinschreiben = Integer.parseInt(strPriceList[1]);
            } else if (strPriceList[0].equals("PaketS")) {
                pricePaketS = Integer.parseInt(strPriceList[1]);
            } else if (strPriceList[0].equals("PaketL")) {
                pricePaketL = Integer.parseInt(strPriceList[1]);
            } else if (strPriceList[0].equals("PaketM")) {
                pricePaketM = Integer.parseInt(strPriceList[1]);
            }
        }

        for (String s : mail) {
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
        int counterSum = counterBrief + counterEinwurfSchreiben + counterEinschreiben + counterPaketS + counterPaketM
                + counterPaketL;
        double price = counterBrief * priceBrief;
        price += counterEinwurfSchreiben * priceEinwurfSchreiben;
        price += counterEinschreiben * priceEinschreiben;
        price += counterPaketS * pricePaketS;
        price += counterPaketL * pricePaketL;
        price += counterPaketM * pricePaketM;

        resultPrice += ";" + counterSum;
        resultPrice += ";" + price;
        return resultPrice;
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
