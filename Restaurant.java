package RestaurantActivitat;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Restaurant {

    static Scanner scanner = new Scanner(System.in);
    static final double IvaPerCent = 10.0;

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
                    despedirUsuari();
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

    static void despedirUsuari() {
        System.out.println("--------------------------------------");
        System.out.println("========== FINS LA PROPERA! ==========");
        System.out.println("--------------------------------------");
    }

    static String llegirString(String missatge) {
        String valor;
        do {
            System.out.println(missatge);
            valor = scanner.nextLine();
            if (valor.isBlank()) System.out.println("ERROR: El text no pot estar buit.");
        } while (valor.isBlank());
        return valor;
    }

    static int llegirInt(String missatge) {
        int valor = 0;
        boolean valid = false;
        do {
            System.out.println(missatge);
            try {
                valor = scanner.nextInt();
                scanner.nextLine();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("ERROR: Introdueix un número vàlid.");
                scanner.nextLine();
            }
        } while (!valid);
        return valor;
    }

    static double llegirDouble(String missatge) {
        double valor = 0;
        boolean valid = false;
        do {
            System.out.println(missatge);
            try {
                valor = scanner.nextDouble();
                scanner.nextLine();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("ERROR: Introdueix un número vàlid.");
                scanner.nextLine();
            }
        } while (!valid);
        return valor;
    }

    static void novaComanda() {
        clientUltimaComanda = llegirString("Introdueix el nom del client:");
        totalUltimaComanda = 0;
        tiquetUltimaComanda = "";
        gestionarProductes();
        System.out.println("S’està generant el tiquet…");
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
        String resposta;
        do {
            System.out.print("Vols afegir un altre producte? (s/n): ");
            resposta = scanner.nextLine().toLowerCase();
            if (!resposta.equals("s") && !resposta.equals("n"))
                System.out.println("ERROR: Introdueix 's' o 'n'.");
        } while (!resposta.equals("s") && !resposta.equals("n"));
        return resposta.equals("s");
    }

    static void visualitzarTiquet() {
        if (clientUltimaComanda == null) {
            System.out.println("No hi ha cap comanda registrada.\n");
            return;
        }
        imprimirTiquet();
    }

    static void imprimirTiquet() {
        double iva = totalUltimaComanda * IvaPerCent / 100.0;
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
        System.out.printf("IVA (%.0f%%):%37.2f €%n", IvaPerCent, iva);
        System.out.printf("TOTAL A PAGAR:%34.2f €%n", totalPagar);
        System.out.println("==================================================\n");
    }
}
