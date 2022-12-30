import java.util.Scanner;

public class Fahrkartenautomat {

	public static void main(String[] args) {
		Scanner tastatur = new Scanner(System.in);
		fahrkartenautomat(tastatur);
		
		while(true)
		{
		System.out.println("Weitere Tickets buchen? (j/n)");
		
		String weiter = tastatur.next();
		
		System.out.println(weiter);
		if (weiter.equals("j"))
			fahrkartenautomat(tastatur);
	
		else 
			break;
		}
		tastatur.close();
	}

	public static double fahrkartenbestellungErfassen(Scanner tastatur) {
		
		int anzahlTickets;
		double ticketPreis = 0.0;
		

		// Aufgabe 4.7 - Menu zur Auswahl von Fahrkartentypen um wiederholte Eingabe erweitert

		double preisAllerTickets = 0.0;
		boolean auswahlBeenden = false;

		while (true) {

			int auswahlTickets = 0;
			boolean korrekteEingabe = false;

			/*System.out.println("Wählen Sie ihre Wunschfahrkarte für Berlin AB aus:");
			System.out.println("Einzelfahrschein Regeltarif AB [2,90 EUR] (1)");
			System.out.println("Tageskarte Regeltarif AB [8,60 EUR] (2)");
			System.out.println("Kleingruppen-Tageskarte Regeltarif AB [23,50 EUR] (3)");
			System.out.println("Bezahlen (9)\n");*/
			
		

			String [] bezeichnung = new String[] 
					{ 
							"EinzelFahrschein Berlin AB",
							"EinzelFahrschein Berlin BC",
							"Einzelfahrschein Berlin ABC",
							"KurzStrecke",
							"Tageskarte Berlin AB",
							"Tageskarte Berlin BC",
							"Tageskarte Berlin ABC",
							"Kleingruppen-Tageskarte Berlin AB",
							"Kleingruppen-Tageskarte Berlin BC",
							"Kleingruppen-Tageskarte Berlin ABC"};
			double [] preise = new double []
					{
							2.90,
							3.3,
							3.6,
							1.9,
							8.6,
							9.0,
							9.6,
							23.50,
							24.30,
							24.90
					};
			
			StringBuilder messagebuilder = new StringBuilder();
			
			for (int i = 0; i < bezeichnung.length; i++) {
	            messagebuilder.append(bezeichnung[i] + ": " + preise[i] + "\n");
	        }
	        String message = messagebuilder.toString();
	        System.out.print(message);

			
			while (korrekteEingabe == false) {
				System.out.print("Ihre Wahl: ");
				auswahlTickets = tastatur.nextInt();
				if (auswahlTickets >= 1 && auswahlTickets <= 3) {
					korrekteEingabe = true;
				} 
				else if (auswahlTickets == 9) {
					korrekteEingabe = true;
					auswahlBeenden = true;
				} 
				else {
					System.out.println(" >>falsche Eingabe<< ");
				}
			}

			if (auswahlBeenden) {
				break;
			}

			if (auswahlTickets == 1) {
				ticketPreis = preise[0];
			} 
			else if (auswahlTickets == 2) {
				ticketPreis = preise[4];
			} 
			else if (auswahlTickets == 3) {
				ticketPreis = preise[7];
			}

			korrekteEingabe = false;
			anzahlTickets = 0;

			while (korrekteEingabe == false) {
				System.out.print("Anzahl der Tickets: ");
				anzahlTickets = tastatur.nextInt();

				if (anzahlTickets >= 1 && anzahlTickets <= 10) { // Nur Eingaben von 1 bis 10 sind erlaubt
					korrekteEingabe = true;
				} else {
					System.out.println(" >> Wählen Sie bitte eine Anzahl von 1 bis 10 Tickets aus.\n");
				}

			}

			preisAllerTickets = preisAllerTickets + ticketPreis * anzahlTickets;

			System.out.format("%nZwischensumme: %4.2f € %n%n", preisAllerTickets);

		}

		return preisAllerTickets;
	}

	
	
	

	public static double fahrkartenBezahlen(double zuZahlen, Scanner tastatur) {
		// Geldeinwurf
		// -----------
		
		double eingezahlterGesamtbetrag = 0.0;
		while (eingezahlterGesamtbetrag < zuZahlen) {
			System.out.format("Noch zu zahlen: %4.2f €%n", (zuZahlen - eingezahlterGesamtbetrag));
			System.out.print("Eingabe (mind. 5Ct, höchstens 2 Euro): ");
			double eingeworfeneMünze = tastatur.nextDouble();
			eingezahlterGesamtbetrag += eingeworfeneMünze;
			/*
			 * if (eingeworfeneMünze > 2.0)
			 * System.out.print("Der Automat nimmt nur Münzen an!! ");
			 */
		}

		return eingezahlterGesamtbetrag;
	}

	public static void fahrkartenAusgeben() {
		// Fahrscheinausgabe
		// -----------------
		System.out.println("\nFahrschein wird ausgegeben");
		for (int i = 0; i < 8; i++) {
			System.out.print("=");
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("\n\n");
	}

	public static void rueckgeldAusgaben(double eingezahlterGesamtbetrag, double zuZahlenderBetrag) {
		// Rückgeldberechnung und -Ausgabe
		// -------------------------------
		double rückgabebetrag = eingezahlterGesamtbetrag - zuZahlenderBetrag;
		if (rückgabebetrag > 0.0) {
			System.out.format("Der Rückgabebetrag in Höhe von %4.2f € %n", rückgabebetrag);
			System.out.println("wird in folgenden Münzen ausgezahlt:");

			while (rückgabebetrag >= 2.0) // 2 EURO-Münzen
			{
				System.out.println("2 EURO");
				rückgabebetrag -= 2.0;
			}
			while (rückgabebetrag >= 1.0) // 1 EURO-Münzen
			{
				System.out.println("1 EURO");
				rückgabebetrag -= 1.0;
			}
			while (rückgabebetrag >= 0.5) // 50 CENT-Münzen
			{
				System.out.println("50 CENT");
				rückgabebetrag -= 0.5;
			}
			while (rückgabebetrag >= 0.2) // 20 CENT-Münzen
			{
				System.out.println("20 CENT");
				rückgabebetrag -= 0.2;
			}
			while (rückgabebetrag >= 0.1) // 10 CENT-Münzen
			{
				System.out.println("10 CENT");
				rückgabebetrag -= 0.1;
			}
			while (rückgabebetrag >= 0.05)// 5 CENT-Münzen
			{
				System.out.println("5 CENT");
				rückgabebetrag -= 0.05;
			}
		}
	}

	public static void fahrkartenautomat(Scanner tastatur) 
	{
		

		double zuZahlenderBetrag;
		double eingezahlterGesamtbetrag;

		double anzahlTickets;

		zuZahlenderBetrag = fahrkartenbestellungErfassen(tastatur);

		eingezahlterGesamtbetrag = fahrkartenBezahlen(zuZahlenderBetrag, tastatur);

		fahrkartenAusgeben();
		rueckgeldAusgaben(eingezahlterGesamtbetrag, zuZahlenderBetrag);

		System.out.println("\nVergessen Sie nicht, den Fahrschein\n" + "vor Fahrtantritt entwerten zu lassen!\n"
				+ "Wir wünschen Ihnen eine gute Fahrt.");
		
	}
}