import java.util.Scanner;

/**
 * the main method for the interaction with the user
 * 
 * @author Daniel Weingand, Illia Pyvovar, Noël Ollick
 */
public class Main_IBAN {

    /**
     * returns the formatted IBAN
     * 
     * @param args (arguments used to enter the IBAN (country code, bank code,
     *             account number))
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\nGeben Sie die Länderkennung an:");
        String land = scanner.nextLine();
        System.out.println("\nGeben Sie nun die Bankleitzahl an:");
        String banknummer = scanner.nextLine();
        System.out.println("\nUnd zuletzt ihre Kontonummer:");
        String kontonummer = scanner.nextLine();

        CalculateIBAN iban = new CalculateIBAN(land, banknummer, kontonummer);

        System.out.println("\nIhre IBAN lautet:");
        System.out.println(iban.calculateIBAN() + "\n");

        scanner.close();
    }

}