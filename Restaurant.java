package RestaurantActivitat;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Restaurant {

    static Scanner scanner = new Scanner(System.in);
    static final double IVA_PERCENT = 10.0;

    static String clientUltimaComanda;
    static String tiquetUltimaComanda;
    static double totalUltimaComanda;

    public static void main(String[] args) {
        boolean continuar = true;
        while (continuar) {
            mostrarMenuPrincipal();
            int opcio = llegirInt("> Tria una opció:");
            switch (opcio) {
                case 1 -> novaComanda();
                case 2 -> actualitzarComanda();
                case 3 -> visualitzarTiquet();
                case 4 -> {
                    continuar = false;
                    acomiadarUsuari();
                }
                default -> System.out.println("Opció no vàlida.");
            }
        }
    }

    static void mostrarMenuPrincipal() {
        System.out.println("--------------------------------------");
        System.out.println("===== GESTIÓ COMANDES RESTAURANT =====");
        System.out.println("--------------------------------------");
        System.out.println("1. Crear nova comanda");
        System.out.println("2. Actualitzar comanda anterior");
        System.out.println("3. Visualitzar últim tiquet");
        System.out.println("4. Sortir");
    }

    static void acomiadarUsuari() {
        System.out.println("--------------------------------------");
        System.out.println("========== FINS LA PROPERA! ==========");
        System.out.println("--------------------------------------");
    }

    static String llegirString(String missatge) {
        while (true) {
            System.out.println(missatge);
            String valor = scanner.nextLine();
            if (!valor.isBlank()) return valor;
            System.out.println("ERROR: El text no pot estar buit.");
        }
    }

    static int llegirInt(String missatge) {
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

    static double llegirDouble(String missatge) {
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

    static void novaComanda() {
        clientUltimaComanda = llegirString("Introdueix el nom del client:");
        totalUltimaComanda = 0;
        tiquetUltimaComanda = "";
        gestionarProductes();
        imprimirTiquet();
        System.out.println("Comanda enregistrada correctament.\n");
    }

    static void actualitzarComanda() {
        if (clientUltimaComanda == null) {
            System.out.println("No hi ha cap comanda enregistrada.\n");
            return;
        }
        gestionarProductes();
        System.out.println("S’està actualitzant la comanda…\n");
        imprimirTiquet();
        System.out.println("Comanda actualitzada correctament.\n");
    }

    static void gestionarProductes() {
        boolean afegirMes = true;
        while (afegirMes) {
            String producte = llegirString("Nom del producte:");
            double preu = llegirDouble("Preu unitari (€):");
            int quantitat = llegirInt("Quantitat:");
            afegirProducte(producte, preu, quantitat);
            afegirMes = demanarContinuar();
        }
    }

    static void afegirProducte(String producte, double preu, int quantitat) {
        double subtotal = preu * quantitat;
        totalUltimaComanda += subtotal;
        tiquetUltimaComanda += String.format(
                "%-15s %-10d %-12.2f %-10.2f%n",
                producte, quantitat, preu, subtotal
        );
    }

    static boolean demanarContinuar() {
        while (true) {
            System.out.print("Vols afegir un altre producte? (s/n): ");
            String resposta = scanner.nextLine().toLowerCase();
            if (resposta.equals("s")) return true;
            if (resposta.equals("n")) return false;
            System.out.println("ERROR: Introdueix 's' o 'n'.");
        }
    }

    static void visualitzarTiquet() {
        if (clientUltimaComanda == null) {
            System.out.println("No hi ha cap comanda registrada.\n");
            return;
        }
        imprimirTiquet();
    }

    static void imprimirTiquet() {
        double iva = totalUltimaComanda * IVA_PERCENT / 100.0;
        double totalPagar = totalUltimaComanda + iva;

        System.out.println("--------------------------------------------------");
        System.out.println("===================== TIQUET =====================");
        System.out.println("--------------------------------------------------");
        System.out.println("Client: " + clientUltimaComanda + "\n");
        System.out.printf("%-15s %-10s %-12s %-10s%n",
                "Producte", "Quantitat", "Preu unit.", "Subtotal");
        System.out.println("--------------------------------------------------");
        System.out.print(tiquetUltimaComanda);
        System.out.println("--------------------------------------------------");
        System.out.printf("Total sense IVA:%33.2f €%n", totalUltimaComanda);
        System.out.printf("IVA (%.0f%%):%37.2f €%n", IVA_PERCENT, iva);
        System.out.printf("TOTAL A PAGAR:%34.2f €%n", totalPagar);
        System.out.println("==================================================\n");
    }
}
