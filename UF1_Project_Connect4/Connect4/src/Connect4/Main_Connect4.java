/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Connect4;

import java.util.Scanner;

/**
 *
 * @author Alexasto
 */
public class Main_Connect4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
// Declarem variables
        int fila, columna, f, c, MarcadorP1 = 0, MarcadorP2 = 0;
        String Jugador1;
        String Jugador2;
        String Jugadorgeneral;
// Declarem i initliatizem la variable que controlarà el fi del programa
        char restart = ' ';
        char vacio = ' ';
// Impotem Scanner per poder demanar al usuari la mida del taulell
        Scanner Input = new Scanner(System.in);
        System.out.println("****Benvingut a 4 en ratlla!****\n"
                + "==============================");
        System.out.println("Introdueix numero de files del taulell: ");
        fila = Input.nextInt();
        System.out.println("Introdueix numero de columnes del taulell: ");
        columna = Input.nextInt();
// Demanem el nom als jugadors       
        System.out.println("Intordueix el nom del primer jugador");
        Jugador1 = Input.next();
        System.out.println("Intordueix el nom del segon jugador");
        Jugador2 = Input.next();
// Declarem l'array i la initcialitzem		          
        do {
            char[][] Taulell = new char[fila][columna];
            char[][] Taulell2 = new char[fila + 4][columna + 4];
// Initcilitzem l'array
            for (f = 0; f < Taulell.length; f++) {
                for (c = 0; c < Taulell[0].length; c++) {
                    Taulell[f][c] = vacio;
                }
            }
// Definim la casella delS jugadors i la codició de victoria
            char Fitxa1 = 'X';
            char Fitxa2 = 'Ø';
            char FitxaGeneral = ' ';
            FitxaGeneral = Fitxa1;
            Jugadorgeneral = Jugador1;
            boolean Victoria = false;
// Comencem la partida
            do {
                int Torn1;
                boolean FitxaOK = false;
                System.out.print("\n" + Jugadorgeneral + " amb la fitxa " + FitxaGeneral + ", escull columna del [1-" + columna + "]: ");
                Torn1 = Input.nextInt() - 1;
                while (Taulell[0][Torn1] != vacio) {
                    System.out.println("La columna esta plena, torna  a escollir una columna del [1-" + columna + "]:");
                    Torn1 = Input.nextInt() - 1;
                }
                while (Torn1 >= columna) {
                    System.out.println("El numero no corrrespon a cap columna, torna  a escollir una columna del [1-" + columna + "]: ");
                    Torn1 = Input.nextInt() - 1;
                }
                f = Taulell.length - 1;
                while (f >= 0 && FitxaOK == false) {
                    if (Taulell[f][Torn1] == vacio) {
                        Taulell[f][Torn1] = FitxaGeneral;
                        Taulell2[f][Torn1] = FitxaGeneral;
                        FitxaOK = true;
                    } else {
                        f--;
                    }
                }
// Imprimim el Taulell desprès de cada torn

                System.out.println("".repeat(columna / 2) + "4 en ratlla!\n"
                        + "-".repeat(columna * 2 + 3));

                for (f = 0; f < Taulell.length; f++) {
                    System.out.printf("%d»|", Taulell.length - f);
                    for (c = 0; c < Taulell.length; c++) {
                        System.out.printf("%s|", Taulell[f][c]);
                    }
                    System.out.println("");
                    System.out.println("-".repeat(Taulell[f].length * 2 + 3));
                }
                System.out.print("   ");
                for (c = 0; c < Taulell[0].length; c++) {
                    System.out.printf("%d|", c + 1);
                }
                System.out.println("\n");

// Verifiquem horitzonatal
                for (f = 0; f < Taulell2.length; f++) {
                    for (c = 0; c < Taulell2[0].length; c++) {
                        if (Taulell2[f][c] == FitxaGeneral && Taulell2[f][c + 1] == FitxaGeneral && Taulell2[f][c + 2] == FitxaGeneral && Taulell2[f][c + 3] == FitxaGeneral) {
                            Victoria = true;
                            System.out.println("\n Has guanyat " + Jugadorgeneral);
                            if (FitxaGeneral == Fitxa1) {
                                MarcadorP1++;
                            } else {
                                MarcadorP2++;
                            }
                        }
                    }
                }
// Verifiquem vertical
                for (f = 0; f < Taulell2.length - 3; f++) {
                    for (c = 0; c < Taulell2[0].length; c++) {
                        if (Taulell2[f + 3][c] == FitxaGeneral && Taulell2[f][c] == FitxaGeneral && Taulell2[f + 1][c] == FitxaGeneral && Taulell2[f + 2][c] == FitxaGeneral) {
                            Victoria = true;
                            System.out.println("Has guanyat " + Jugadorgeneral);
                            if (FitxaGeneral == Fitxa1) {
                                MarcadorP1++;
                            } else {
                                MarcadorP2++;
                            }
                        }
                    }
                }
// Verifiquem diagonal
                for (f = 0; f < Taulell2.length - 3; f++) {
                    for (c = 0; c < Taulell2[0].length - 3; c++) {
                        if (Taulell2[f + 3][c + 3] == FitxaGeneral && Taulell2[f][c] == FitxaGeneral && Taulell2[f + 1][c + 1] == FitxaGeneral && Taulell2[f + 2][c + 2] == FitxaGeneral) {
                            Victoria = true;
                            System.out.println("Has guanyat " + Jugadorgeneral);
                            if (FitxaGeneral == Fitxa1) {
                                MarcadorP1++;
                            } else {
                                MarcadorP2++;
                            }
                        }
                    }
                }
// Verifiquem empat               
                int contador = 0;
                for (c = 0; c < Taulell[0].length; c++) {
                    if (Taulell[0][c] != vacio) {
                        contador++;
                    }
                }
                if (contador == columna) {
                    Victoria = true;
                    System.out.println("\nEmpat tecnic! ");

                }
// Cambiem el torn
                if (FitxaGeneral == Fitxa1) {
                    FitxaGeneral = Fitxa2;
                    Jugadorgeneral = Jugador2;
                    FitxaOK = false;
                } else {
                    FitxaGeneral = Fitxa1;
                    Jugadorgeneral = Jugador1;
                }
            } while (Victoria == false);
// Imprimim el marcador
            System.out.println("Puntuació\n"
                    + " =========\n "
                    + Jugador1 + " --------- " + MarcadorP1 + "\n "
                    + Jugador2 + " --------- " + MarcadorP2);
// Preguntem si vol tornar a jugar
            System.out.println("Vols tornar a jugar S/N ? ");
            restart = Input.next().charAt(0);
// Convertim el resultat a majuscules            
            restart = Character.toUpperCase(restart);
        } while (restart != 'N');
    }
}
