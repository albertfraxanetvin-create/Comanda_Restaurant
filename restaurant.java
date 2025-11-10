package Restaurant;

import java.util.InputMismatchException;
import java.util.Scanner;

public class restaurant {
    static Scanner scanner = new Scanner(System.in);

    // Variables globals per guardar l'última comanda
    static String clientUltimaComanda;
    static String tiquetUltimaComanda; 
    static double totalUltimaComanda;
    // Variables per calcular l'import de la comanda
    static double ivaPercent = 10;
    static double iva = totalUltimaComanda * ivaPercent / 100;
    static double totalPagar = totalUltimaComanda + iva;

    public static void main(String[] args) {
        boolean continuar = true;
        while (continuar) {
            System.out.println("--------------------------------------");
            System.out.println("===== GESTIÓ COMANDES RESTAURANT =====");
            System.out.println("--------------------------------------");
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
                    System.out.println("--------------------------------------");
                    System.out.println("========== FINS LA PROPERA! ==========");
                    System.out.println("--------------------------------------");
                    break;
                default:
                    System.out.println("Opció no vàlida.");
                    break;
            }
        }
    }

    public static String llegirString(String missatge) {
    while (true) {
        System.out.println(missatge);
        String valor = scanner.nextLine().trim();
        if (!valor.isEmpty()) {
            return valor;
        } else {
            System.out.println("ERROR: El text no pot estar buit.");
        }
      }
    }

    public static int llegirInt(String missatge) {
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

    public static double llegirDouble(String missatge) {
        while (true) {
            System.out.println(missatge);
            try {
                double valor = scanner.nextDouble();
                scanner.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("ERROR: Introdueix un número vàlid.");
                scanner.nextLine();
            }
        }
    }

    public static void novaComanda() {

        System.out.print("Introdueix el nom del client: ");
        clientUltimaComanda = scanner.nextLine();
        totalUltimaComanda = 0;
        tiquetUltimaComanda = "";
        boolean afegirMes = true;
        while (afegirMes) {
            System.out.print("Introdueix el producte: ");
            String producte = llegirString("Nom del producte: ");
            double preu = llegirDouble("Preu unitari (€): ");
            int quantitat = llegirInt("Quantitat: ");

            double subtotal = preu * quantitat;
            totalUltimaComanda += subtotal;
            tiquetUltimaComanda += String.format("%-15s %-10d %-12.2f %-10.2f%n", producte, quantitat, preu, subtotal);
            System.out.print("Vols afegir un altre producte? (s/n): ");
            String siNO = scanner.nextLine();
            if (!siNO.equalsIgnoreCase("s")) {
                afegirMes = false;
            }
        }
        System.out.println("S’està generant el tiquet…\n");
        
        System.out.println("--------------------------------------------------");
        System.out.println("===================== TIQUET =====================");
        System.out.println("--------------------------------------------------");
        System.out.println("Client: " + clientUltimaComanda + "\n");
        System.out.printf("%-15s %-10s %-12s %-10s%n", "Producte", "Quantitat", "Preu unit.", "Subtotal");
        System.out.println("--------------------------------------------------");
        System.out.print(tiquetUltimaComanda);
        System.out.println("--------------------------------------------------");
        System.out.printf("Total sense IVA:%33.2f €%n", totalUltimaComanda);
        System.out.printf("IVA (10%%):%37.2f €%n", iva);
        System.out.printf("TOTAL A PAGAR:%34.2f €%n", totalPagar);
        System.out.println("==================================================\n");

        System.out.println("Comanda enregistrada correctament.\n");
    }

    public static void actualitzarComanda() {
        
        boolean afegirMes = true;
        while (afegirMes) {
            System.out.print("Introdueix un nou producte: ");
            String producte = llegirString("Nom del producte: ");
            double preu = llegirDouble("Preu unitari (€): ");
            int quantitat = llegirInt("Quantitat: ");

            double subtotal = preu * quantitat;
            totalUltimaComanda += subtotal;
            tiquetUltimaComanda += String.format("%-15s %-10d %-12.2f %-10.2f%n", producte, quantitat, preu, subtotal);
            System.out.print("Vols afegir un altre producte? (s/n): ");
            String siNO = scanner.nextLine();
            if (!siNO.equalsIgnoreCase("s")) {
                afegirMes = false;
            }
        }
        System.out.println("S’està actualitzant la comanda…\n");
        
        System.out.println("--------------------------------------------------");
        System.out.println("=============== TIQUET ACTUALITZAT ===============");
        System.out.println("--------------------------------------------------");
        System.out.println("Client: " + clientUltimaComanda + "\n");
        System.out.printf("%-15s %-10s %-12s %-10s%n", "Producte", "Quantitat", "Preu unit.", "Subtotal");
        System.out.println("--------------------------------------------------");
        System.out.print(tiquetUltimaComanda);
        System.out.println("--------------------------------------------------");
        System.out.printf("Total sense IVA:%33.2f €%n", totalUltimaComanda);
        System.out.printf("IVA (10%%):%37.2f €%n", iva);
        System.out.printf("TOTAL A PAGAR:%34.2f €%n", totalPagar);
        System.out.println("==================================================\n");

        System.out.println("Comanda actualitzada correctament.\n");
        
    }

    public static void visualitzarTiquet() {
        if (clientUltimaComanda == null) {
            System.out.println("No hi ha cap comanda registrada.");
            return;
        }

        System.out.println("--------------------------------------------------");
        System.out.println("===================== TIQUET =====================");
        System.out.println("--------------------------------------------------");
        System.out.println("Client: " + clientUltimaComanda + "\n");
        System.out.printf("%-15s %-10s %-12s %-10s%n", "Producte", "Quantitat", "Preu unit.", "Subtotal");
        System.out.println("--------------------------------------------------");
        System.out.print(tiquetUltimaComanda);
        System.out.println("--------------------------------------------------");
        System.out.printf("Total sense IVA:%33.2f €%n", totalUltimaComanda);
        System.out.printf("IVA (10%%):%37.2f €%n", iva);
        System.out.printf("TOTAL A PAGAR:%34.2f €%n", totalPagar);
        System.out.println("==================================================\n");
    }
}
