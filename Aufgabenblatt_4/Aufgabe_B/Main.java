import edu.kit.informatik.Terminal;

public class Main {
    private static final int ARG0 = 0;
    private static final int ARG1 = 1;
    private static final int ARG2 = 2;
    private static final int ARG3 = 3;
    private static final int ARG4 = 4;

    private static final String QUIT = "quit";
    private static final String ADD_CUSTOMER = "add-customer";
    private static final String ADD_MAILMAN = "add-mailman";
    private static final String ADD_AGENT = "add-agent";
    private static final String AUTHENTICATE = "authenticate";
    private static final String SENDMAIL = "send-mail";
    private static final String GETMAIL = "get-mail";
    private static final String LISTMAIL = "list-mail";
    private static final String LISTPRICE = "list-price";
    private static final String RESETPIN = "reset-pin";
    private static final String LOGOUT = "logout";
    private static final String SPACE = " ";
    private static final String SEMICOLON = ";";
    private static final String SUCCESS = "OK";

    public static final String ERROR = "Error, ";

    static boolean exitNow = false;
    static String befehl = "";
    static String command = "";

    static private String firstName = "";
    static private String lastName = "";
    static private String userName = "";
    static private String password = "";
    static private String idCardNumber = "";
    static private String postDienstLeistungen = "";
    static private int personalNumber = 0;
    
    public static void main(String[] args) {
        PostOffice post = new PostOffice();
        while (!exitNow) {
            String[] befehlArgs = {};
            String[] parameterArgs = {};
            try {
                command = Terminal.readLine();
                befehlArgs = command.split(SPACE);
                befehl = befehlArgs[ARG0];
                if (befehlArgs.length > 1) {
                    parameterArgs = befehlArgs[ARG1].split(SEMICOLON);
                }
                if (befehl.equals(QUIT)) {
                    if (parameterArgs.length > 0) {
                        throw new Exception(
                                ERROR + "incorrect input format, this command does not accept any parameters.");
                    }
                    break;
                } else if (befehl.equals(ADD_CUSTOMER)) {
                    firstName = parameterArgs[ARG0];
                    lastName = parameterArgs[ARG1];
                    userName = parameterArgs[ARG2];
                    password = parameterArgs[ARG3];
                    idCardNumber = parameterArgs[ARG4];
                    if (post.addCustomer(firstName, lastName, userName, password, idCardNumber)) {
                        Terminal.printLine(SUCCESS);
                    }
                } else if (befehl.equals(ADD_MAILMAN)) {
                    firstName = parameterArgs[ARG0];
                    lastName = parameterArgs[ARG1];
                    personalNumber = Integer.parseInt(parameterArgs[ARG2]);
                    password = parameterArgs[ARG3];
                    if (post.addMailman(firstName, lastName, personalNumber, password)) {
                        Terminal.printLine(SUCCESS);
                    }
                } else if (befehl.equals(ADD_AGENT)) {
                    firstName = parameterArgs[ARG0];
                    lastName = parameterArgs[ARG1];
                    personalNumber = Integer.parseInt(parameterArgs[ARG2]);
                    password = parameterArgs[ARG3];
                    if (post.addAgent(firstName, lastName, personalNumber, password)) {
                        Terminal.printLine(SUCCESS);
                    }
                } else if (befehl.equals(AUTHENTICATE)) {
                    userName = parameterArgs[ARG0];
                    password = parameterArgs[ARG1];
                    if (post.authenticate(userName, password)) {
                        Terminal.printLine(SUCCESS);
                    }
                } else if (befehl.equals(SENDMAIL)) {
                    postDienstLeistungen = parameterArgs[ARG0];
                    userName = parameterArgs[ARG1];
                    if (post.sendMail(postDienstLeistungen, userName)) {
                        Terminal.printLine(SUCCESS);
                    }
                } else if (befehl.equals(GETMAIL)) {
                    // Call PostOffice
                    post.getMail();
                } else if (befehl.equals(LISTMAIL)) {
                    // Call PostOffice
                } else if (befehl.equals(LISTPRICE)) {
                    String priceList = post.listPrice();
                    Terminal.printLine(priceList);
                } else if (befehl.equals(RESETPIN)) { // Call PostOffice
                    
                } else if (befehl.equals(LOGOUT)) {
                    if (post.logout()) { 
                        Terminal.printLine(SUCCESS);
                    } else {
                        throw new Exception(ERROR + "no user logged in");
                    }
                } else {
                    throw new Exception(ERROR + "incorrect input format, unknown command");
                }
            } catch (Exception ex) {
                Terminal.printLine(ex.toString());
            }
        }
    }
}
