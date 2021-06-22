import java.util.Scanner;


public class Ritterspiel {
	public static int spieleranzahl;
	public static char[][] spielfeld;
	public static int hoehe;
	public static int breite;

	public static void main(String[] args) {

		ablauf_manager();

	}

	private static void ablauf_manager() {
		spieler_abfrage();
		spielfeld_abfrage();
		spiel_starten(hoehe, breite);

	}

	private static void spielfeld_abfrage() {
		Scanner input = new Scanner(System.in);
		try {
			System.out.print("Breite der Tafel: ");
			breite = input.nextInt();
			System.out.print("Höhe der Tafel: ");
			hoehe = input.nextInt();

			spielfeld = spielfeld_aktualisieren(hoehe, breite);
			spielfeld_ausgeben(spielfeld);

		} catch (Exception e) {
			System.err.println("FEHLER BEI DER EINGABE, bitte versuchen es sie erneut.");
			spielfeld_abfrage();
		}

	}

	private static void spieler_abfrage() {
		Scanner input = new Scanner(System.in);
		System.out.println("Anzahl der menschlichen Spieler (1 oder 2):  ");
		int eingabe = 0;
		try {
			eingabe = input.nextInt();
			spieleranzahl = eingabe <= 2 ? eingabe : 0;
		} catch (Exception e) {
			System.err.println("FEHLER BEI DER EINGABE, bitte 1 oder 2 Spieler waehlen.");
			spieler_abfrage();
		}
		if (spieleranzahl == 0) {
			System.err.println("FEHLER BEI DER EINGABE, bitte 1 oder 2 Spieler waehlen.");
			spieler_abfrage();
		}
	}

	private static void spiel_starten(int hoehe, int breite) {
		char[][] spielfeld;
		boolean win = false;
		int h = hoehe;
		int b = breite;
		int groesse;
		char hor_ver;
		String[] spieler = {"2","Ambrosius IV"};
		int count = 1;
		Scanner input = new Scanner(System.in);
		
		// AUFGABE 4 
		if(spieleranzahl==1) {
			System.out.println("Du spielst gegen Ambrosius IV! ");
			do {
				if (h == 1 && b == 1) {
					if(count==1)
						System.out.println("Ambrosius IV hat gewonnen!!!");
					else
						System.out.println("Spieler1 hat gewonnen!!!");
					win = true;
					break;
				} else {

					if(count==1) {
						count--;
						System.out.println(
								"Spieler 1: Wollen sie horizontal oder vertikal abbrechen (Eingabe H oder V)?");
						hor_ver = input.next().charAt(0);
						
						if (hor_ver != 'V' && hor_ver != 'H') {
							System.err.println("FEHLER BEI DER EINGABE");
							spiel_starten(hoehe, breite);
			
						} else {
							System.out.println("Wie hoch ist das abgebrochene Stück?");
							groesse = input.nextInt();
			
							if (hor_ver == 'H') {
								if (groesse > 0 && groesse < h + 1) {
									h = h - groesse;
									spielfeld = spielfeld_aktualisieren(h, b);
									spielfeld_ausgeben(spielfeld);
								} else {
									System.err.println("FEHLER BEI DER EINGABE");
								}
							}
			
							if (hor_ver == 'V') {
								if (groesse > 0 && groesse < b + 1) {
									b = b - groesse;
									spielfeld = spielfeld_aktualisieren(h, b);
									spielfeld_ausgeben(spielfeld);
								} else {
									System.err.println("FEHLER BEI DER EINGABE");
								}
							}
						
						
					}
					}
					else {
						count++;
						if(h==1 || b==1) {
							h = b==1 ? 1 : h;
							b = h==1 ? 1 : b;
						}
						else if((h+b)%2!=0) { //bei nicht quadratischer Feldgroesse, Feld quadratisch machen.
							if(b%2!=0) {
								b = b-1;
								System.out.println(
										"Ambrosius IV brach ein Stueck Vertikal ab.");
							}
							else {
								h = h-1;
								System.out.println(
										"Ambrosius IV brach ein Stueck Horizontal ab.");
							}
						

						}
						else {
							h = b<h ? h-1 : h; 	// deckt Paarungen wie A(4,6) oder 
							b = h<b ? b-1 : b;	//
							
							h = b==h ? h-1 : h;
							
						}
						spielfeld = spielfeld_aktualisieren(h, b);
						spielfeld_ausgeben(spielfeld);
						}
				}
			}while (win == false);
			
		// VERSUCH AUFGABE 4 ENDE	
			
		} // 2-spieler Modus
		else {
				do {
					if (h == 1 && b == 1) {
						System.out.println("Spieler " + spieler[0] + " hat gewonnen!!!");
						win = true;
						break;
					} else {
						spieler_wechseln(spieler);
					}
		
					System.out.println(
							"Spieler " + spieler[0] + ": Wollen sie horizontal oder vertikal abbrechen (Eingabe H oder V)?");
					hor_ver = input.next().charAt(0);
		
					if (hor_ver != 'V' && hor_ver != 'H') {
						System.err.println("FEHLER BEI DER EINGABE");
						spiel_starten(hoehe, breite);
		
					} else {
						System.out.println("Wie hoch ist das abgebrochene Stück?");
						groesse = input.nextInt();
		
						if (hor_ver == 'H') {
							if (groesse > 0 && groesse < h + 1) {
								h = h - groesse;
								spielfeld = spielfeld_aktualisieren(h, b);
								spielfeld_ausgeben(spielfeld);
							} else {
								System.err.println("FEHLER BEI DER EINGABE");
							}
						}
		
						if (hor_ver == 'V') {
							if (groesse > 0 && groesse < b + 1) {
								b = b - groesse;
								spielfeld = spielfeld_aktualisieren(h, b);
								spielfeld_ausgeben(spielfeld);
							} else {
								System.err.println("FEHLER BEI DER EINGABE");
							}
						}
		
					}
		
				} while (win == false);
			}
		

	}

	public static void spieler_wechseln(String[] spieler) {
		if (spieler[0] == "1") spieler[0] = "2";
		 else 
			spieler[0] = "1";


	}



	public static void spielfeld_ausgeben(char[][] feld) {
		for (int i = 0; i < feld.length; i++) {
			for (int m = 0; m < feld[i].length; m++) {
				System.out.print(feld[i][m]);
			}
			System.out.println();

		}
	}

	public static char[][] spielfeld_aktualisieren(int h, int b) {
		char[][] feld = new char[h][b];
		for (int i = 0; i < h; i++) {
			for (int m = 0; m < b; m++) {
				feld[i][m] = 'O';
			}
		}
		
		feld[0][0] = '*';
		return feld; }

}
