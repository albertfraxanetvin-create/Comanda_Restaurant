package Restaurant;

import java.util.InputMismatchException;
import java.util.Scanner;

public class restaurant {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        boolean continuar = true;
        while (continuar) {
            System.out.println("--------------------------------------\n===== GESTIÓ COMANDES RESTAURANT =====\n--------------------------------------");
            System.out.println("1. Crear nova comanda");
            System.out.println("2. Actualitzar comanda anterior");
            System.out.println("3. Visualitzar últim tiquet");
            System.out.println("4. Sortir");
            int seleccio = llegirInt("> Tria una opció:");
            switch (seleccio) {
                case 1:
                    novaComanda();
                    break;
                case 2:
                    actualitzarComanda();
                    break;
                case 3:
                    visualitzarTiquet();
                    break;
                case 4:
                    continuar = false;
                    System.out.println("Sortint del menú de comandes");
                    break;
                default:
                    System.out.println("Opció no vàlida.");
                    break;
            }
        }
    }
    public static int llegirInt (String missatge) {
        while (true) {
            System.out.println(missatge);
            try {
                int valor = scanner.nextInt();
                scanner.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("ERROR: Introdueix un número vàlid.");
                scanner.nextLine();
            }
        }
    }
    public static void novaComanda() {
        Boolean opcio = true;
        while (opcio) {
            System.out.println("--------------------------------------\n============ NOVA COMANDA ============\n--------------------------------------");
            System.out.print("Introdueix el nom del client: ");
            String client = scanner.nextLine();
            System.out.print("Introdueix el producte: ");
            String producte = scanner.nextLine();
            System.out.print("Preu unitari (€): ");
            double preu = llegirInt("Selecciona un número: "); // Crear nou try and catch!
            System.out.print("Quantitat: ");
            int quantitat = llegirInt("Selecciona un número: ");
            System.out.print("Vols afegir un altre producte? (s/n): ");
            String siNO = scanner.nextLine();
            switch (opcio) {
                case s:
                    
                    break;
                case n:
            
                default:
                    break;
            }
            String siNo = scanner.nextLine();
            
            System.out.println("\n--- Dades introduïdes ---");
            System.out.println("Client: " + client);
            System.out.println("Producte: " + producte);
            System.out.println("Preu unitari: " + preu);
            System.out.println("Quantitat: " + quantitat);
            System.out.println("Vol afegir més productes: " + siNo);
        }
        System.out.println("S’està generant el tiquet…");
    }
    public static void actualitzarComanda() {

    }
    public static void visualitzarTiquet() {

    }
}
