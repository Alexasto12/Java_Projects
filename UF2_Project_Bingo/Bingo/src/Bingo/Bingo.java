/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Bingo;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author alejandro-cabrera
 */
public class Bingo {

    public static Random R = new Random();
    public static Scanner input = new Scanner(System.in);

    /**
     * Permet subrayar un text en groc.
     */
    public static final String YELLOW_UNDERLINED = "\033[4;33m"; // YELLOW
    /**
     * Permet reiniciar el color dels caràcters.
     */
    public static final String RESET = "\u001B[0m";

    /**
     * Permet pintar els caràcters de color negre.
     */
    public static final String BLACK = "\u001B[30m";

    /**
     * Permet pintar els caràcters de color vermell.
     */
    public static final String RED = "\u001B[31m";

    /**
     * Permet pintar els caràcters de color verd.
     */
    public static final String GREEN = "\u001B[32m";

    /**
     * Permet pintar els caràcters de color groc.
     */
    public static final String YELLOW = "\u001B[33m";

    /**
     * Permet pintar els caràcters de color blau.
     */
    public static final String BLUE = "\u001B[34m";

    /**
     * Permet pintar els caràcters de color lila.
     */
    public static final String PURPLE = "\u001B[35m";

    /**
     * Permet pintar els caràcters de color cian.
     */
    public static final String CYAN = "\u001B[36m";

    /**
     * Permet pintar els caràcters de color blanc.
     */
    public static final String WHITE = "\u001B[37m";

    /**
     * Ordenar el codigo para que todo tenga mas sentido, tanto para la
     * ejecucion como lectura.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
// Variable declarada i initzalitzada fora per poder controlar el bucle del joc.        
        boolean Reset = true;

// Comença el joc.
        do {

// Declarem algunes variables i introduïm al joc amb el títol.        
            int MultipleTres;
            int Contador = 0;
            boolean Començar;
            boolean Resum;
            boolean Linia = false;
            boolean Bingo = false;
            boolean Seguir = false;
            String Missatge = "\n\nAquests son el cartrons generats, vols començar?";

// Donem benvinguda i pregunta a l'usuari el rang de números, 75 o 90. Així podem adaptar l'array.
            System.out.println(""
                    + "██████╗░██╗███╗░░██╗░██████╗░░█████╗░██╗\n"
                    + "██╔══██╗██║████╗░██║██╔════╝░██╔══██╗██║\n"
                    + "██████╦╝██║██╔██╗██║██║░░██╗░██║░░██║██║\n"
                    + "██╔══██╗██║██║╚████║██║░░╚██╗██║░░██║╚═╝\n"
                    + "██████╦╝██║██║░╚███║╚██████╔╝╚█████╔╝██╗\n"
                    + "╚═════╝░╚═╝╚═╝░░╚══╝░╚═════╝░░╚════╝░╚═╝\n");
            int max = NumeroMaxim();
            int Array_ordenada[] = new int[max];
            int ValidadorArray[] = new int[max];
            int RandomsBombo[] = new int[max];
            int NumerosCoincidents[] = new int[max];
            OmplirArrayValidacio(ValidadorArray);

// Preguntem a l'usuari el número de cartons i inicialitzem el joc.       
            MultipleTres = MultipleTres();
            int cartó[][] = new int[MultipleTres][9];
            int CartroCopia[][] = new int[MultipleTres][9];
            Arrayorden(Array_ordenada);
            OmplirCartro(cartó, CartroCopia, ValidadorArray, max);
            MostrarCartro(cartó);
            OmplirArrayValidacio(ValidadorArray);
            OmplirBombo(RandomsBombo, ValidadorArray, max);

// Una vegada mostrat el cartró, pregunta si volem començar a jugar, cosa que provocarà entrar dintre del bucle que es repetirà fins que sigui Bingo o el jugador no vulgui continuar.          
            Començar = Continuar(Missatge);
            Missatge = "\n\nVols un altre número? " + GREEN + "S" + RESET + "/" + CYAN + "N" + RESET;
            if (Començar) {
                do {
                    System.out.println("");
                    System.out.println("\n\nEl número del bombo és: " + YELLOW + RandomsBombo[Contador] + RESET);
                    ValidarCartro(NumerosCoincidents, cartó, RandomsBombo[Contador]);
                    MostrarCartro(cartó);
                    if (Linia == false) {
                        Linia = Linia(cartó);
                    }
                    Bingo = ComprovarBingo(cartó, Bingo);
                    if (!Bingo) {
                        Seguir = Continuar(Missatge);
                    }
                    Contador++;
                } while (Seguir && !Bingo && Contador < max);

// Una vegada ha sortit Bingo o el jugador no vol continuar preguntem per veure un resum de la partida, si és que si es mostra.            
                Missatge = "\n\nVols veure el resum de la partida? " + GREEN + "S" + RESET + "/" + CYAN + "N" + RESET;
                Resum = Continuar(Missatge);
                if (Resum) {
                    MostrarCartroBombo(CartroCopia, RandomsBombo, Contador);
                }
            }
            Missatge = "\n\n Vols tornar a jugar? S/N";

// Després del resum preguntem a l'usuari si vol jugar un altre, en cas afirmatiu el bucle tornarà a començar el programa. En cas negatiu acabarà.
            Reset = Continuar(Missatge);
        } while (Reset);

    }

    /**
     * Omple una array de -1.
     *
     * @param ValidadorArray
     */
    public static void OmplirArrayValidacio(int ValidadorArray[]) {
        for (int i = 0; i < ValidadorArray.length; i++) {
            ValidadorArray[i] = -1;
        }
    }

    /**
     * Crea una array que va de l'1 fins al màxim de forma ordenada. (L'1 va en
     * la posició 0, el 2 en la 1, etc.)
     *
     * @param Array_ordenada
     */
    public static void Arrayorden(int Array_ordenada[]) {

        for (int i = 0; i < Array_ordenada.length; i++) {
            Array_ordenada[i] = i + 1;
        }
    }

    /**
     * Genera un número random de l'1 al màxim, una vegada que ha sortit
     * l'utilitzem com a posició en l'array del paràmetre. Si troba un -1 canvia
     * el seu valor a 0 i retorna el número, així la pròxima vegada que surti el
     * mateix número veurà que el valor ja és 0 i generarà un altre random.
     * Evitant així duplicats
     *
     * @param ValidadorArray El lenght no pot ser mes gran que el max
     * @param max Defineix el rang màxim de números
     * @return Un número random de l'1 al màxim que no hagi sortit.
     */
    public static int GenerarRandom(int[] ValidadorArray, int max) {
        int NumeroAleatori;
        boolean repetit = true;
        do {
            NumeroAleatori = R.nextInt(max) + 1;
            if (ValidadorArray[NumeroAleatori - 1] == -1) {
                repetit = false;
            }
            ValidadorArray[NumeroAleatori - 1] = 0;

        } while (repetit == true);

        return NumeroAleatori;

    }

    /**
     * Genera un número random del 0 al 9 que no es repeteix
     *
     * @return Un número random del 0 al 9 diferent cada vegada
     */
    public static int GenerarHuecosCarton() {
        int NumeroAleatori;
        int[] ValidacioInterna = new int[9];

// Omplo l'array de qualsevol valor que no sigui del 0 al 9, ja que sinó l'array estaria plena de 0 i el número random mai podria retornar un 0.
        for (int i = 0; i < ValidacioInterna.length; i++) {
            ValidacioInterna[i] = 20;
        }
        do {
            NumeroAleatori = R.nextInt(9);
            if (ValidacioInterna[NumeroAleatori] != NumeroAleatori) {
                return NumeroAleatori;
            }
        } while (ValidacioInterna[NumeroAleatori] == NumeroAleatori);

        return NumeroAleatori;
    }

    /**
     * Valida que la dada introduïda sigui de tipus integer i superior a 0, és
     * necessita tenir declarat el mètode Scanner com a Public static.
     *
     * @param número
     * @return número
     */
    public static int ValidarInt(int número) {
        do {
            if (input.hasNextInt()) {
                número = input.nextInt();
                if (número <= 0) {
                    System.out.println("\nAixo no és un número valid");
                    System.out.print("Introdueix un número valid: ");

                }

            } else {
                System.out.println("\nAixo no és un número valid");
                System.out.print("Introdueix un número valid: ");

            }
            input.nextLine();
        } while (número <= 0);
        return número;
    }

    /**
     * Crida a l'usuari perquè introdueixi el número de cartrons que voldrà,una
     * vegada donat un valor el multiplica per tres i el retorna.
     *
     * @return El multiple de 3
     */
    public static int MultipleTres() {
        int NumeroCartrons = 0;
        int MultipleTres;
        System.out.print("Introdueix el número de cartrons que vols jugar: ");
        NumeroCartrons = ValidarInt(NumeroCartrons);
        MultipleTres = 3 * NumeroCartrons;
        return MultipleTres;
    }

    /**
     * Omple l'array del cartró i la seva còpia de cinc números a l'atzar,
     * dintre del rang màxim que no es repeteixen, i col·loca 4 zeros per fila
     * en columnes a l'atzar que representen el símbol del bombo. Cada tres
     * files l'array de validació es reinicia perquè els números sortits tornin
     * a estar disponibles.
     *
     * @param cartó
     * @param CartroCopia
     * @param ValidadorArray
     * @param max
     */
    public static void OmplirCartro(int cartó[][], int[][] CartroCopia, int[] ValidadorArray, int max) {
        int Random;
        for (int i = 0; i < cartó.length; i++) {
            int Contador = 0;
            if (i % 3 == 0) {
                OmplirArrayValidacio(ValidadorArray);

            }
            for (int j = 0; j < cartó[0].length; j++) {

                cartó[i][j] = GenerarRandom(ValidadorArray, max);
                CartroCopia[i][j] = cartó[i][j];
            }
            do {
                Random = GenerarHuecosCarton();
                while (cartó[i][Random] == 0) {
                    Random = GenerarHuecosCarton();
                }
                cartó[i][Random] = 0;
                CartroCopia[i][Random] = 0;
                Contador++;
            } while (Contador < 4);

        }

    }

    /**
     * Mostra l'array del cartró. Cada tres files fa un salt i dona format a la
     * array per donar la sensació de que és un altre cartró. A més, cada vegada
     * que troba un 0 o un -1 imprimeix un char definit dintre del metode en
     * comptes del valor original.
     *
     * @param cartó
     */
    public static void MostrarCartro(int cartó[][]) {
        int numcartro = 0;
        char Bombo = '•';
        char Tachada = '█';
        for (int i = 0; i < cartó.length; i++) {
            System.out.println("");
            if (i % 3 == 0) {
                numcartro++;
                System.out.println("‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾");
                if (numcartro <= 9) {

                    System.out.println(GREEN + "----------------------" + RESET + "Cartró 0" + (numcartro) + GREEN + "-----------------------" + RESET);
                    System.out.println("______________________________________________________");
                } else {
                    System.out.println(GREEN + "----------------------" + RESET + "Cartró " + (numcartro) + GREEN + "-----------------------" + RESET);
                    System.out.println("______________________________________________________");
                }
            }
            for (int j = 0; j < cartó[0].length; j++) {
                switch (cartó[i][j]) {
                    case 0:
                        System.out.printf("||");
                        System.out.printf(" %2c ", Bombo);
                        System.out.printf("" + RESET);
                        break;
                    case -1:
                        System.out.printf("||");
                        System.out.printf("" + RED);
                        System.out.printf(" %2c ", Tachada);
                        System.out.printf("" + RESET);
                        break;
                    default:
                        System.out.print("" + RESET);
                        System.out.printf("||");
                        System.out.printf("" + YELLOW);
                        System.out.printf(" %2d ", cartó[i][j]);
                        System.out.printf("" + RESET);
                        break;
                }
            }
        }
        System.out.println("\n‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾");
    }

    /**
     * Omple l'array del bombo de números aleatoris a l'atzar, utilitzant el
     * mètode GenerarRandom
     *
     * @param RandomsBombo
     * @param ValidadorArray
     * @param max
     */
    public static void OmplirBombo(int[] RandomsBombo, int[] ValidadorArray, int max) {
        for (int i = 0; i < RandomsBombo.length; i++) {
            RandomsBombo[i] = GenerarRandom(ValidadorArray, max);
        }
    }

    /**
     * Compara l'array del cartró amb l'array de bombos prèviament omplerta.
     *
     * @param NumerosCoincidents
     * @param cartó
     * @param RandomsBombo
     */
    public static void ValidarCartro(int[] NumerosCoincidents, int[][] cartó, int RandomsBombo) {
        for (int i = 0; i < cartó.length; i++) {
            for (int j = 0; j < cartó[0].length; j++) {
                if (cartó[i][j] == RandomsBombo) {
                    NumerosCoincidents[RandomsBombo - 1] = RandomsBombo;
                    cartó[i][j] = -1;
                }
            }
        }

    }

    /**
     * Pregunta al usuari si o no.No (N) retornarà false. Qualsevol altre cosa retornarà true
     *
     * @param Missatge
     * @return
     */
    public static boolean Continuar(String Missatge) {
        boolean Seguir = false;
        System.out.println(Missatge);
        String Continuar = input.next();
        Continuar = Continuar.toUpperCase();
        char Continuar2 = Continuar.charAt(0);
        if (Continuar2 != 'N') {
            Seguir = true;
            return Seguir;
        }
        return Seguir;
    }

    /**
     *Aquest mètode comprova que tots els números d'una fila siguin -1 o 0, quan trobi 9 retornarà un true. Si no torba cap retornarà un false.
     * 
     * @param cartó
     * @return Linia
     */
    public static boolean Linia(int[][] cartó) {
        boolean Linia = false;
        int NumeroCartro = 0;
        for (int i = 0; i < cartó.length; i++) {
            int contador = 0;
            if (i % 3 == 0) {
                NumeroCartro++;
            }
            for (int j = 0; j < cartó[0].length; j++) {
                if (cartó[i][j] == 0 || cartó[i][j] == -1) {
                    contador++;
                }
            }
            if (contador == 9) {
                Linia = true;
                if (NumeroCartro < 10) {
                    System.out.println(YELLOW_UNDERLINED + "\nHas fet linia en el cartró: 0" + NumeroCartro + RESET);
                } else {
                    System.out.println(YELLOW_UNDERLINED + "\nHas fet linia en el cartró: " + NumeroCartro + RESET);
                }
                return Linia;
            }
        }
        return Linia;
    }

    /**
     * Demana un número que sigui 75 o 90. Si no és, t'ho torna a demanar.
     * 
     * @return un número, 75 o 90.
     */
    public static int NumeroMaxim() {
        int max = 0;
        do {
            System.out.print("\nEscull el rang màxim de números" + CYAN + "[75 o 90]: " + RESET);
            max = ValidarInt(max);           
            if (max != 90 && max != 75) {
                System.out.println("\nRECORDA");
            }
            
        } while (max != 90 && max != 75);

        return max;
    }

    /**
     *Recorreix l'array del cartó, i compta el número de -1 que hi ha. 
     * 
     * @param cartó
     * @param Bingo
     * @return Un boolean true o false
     */
    public static boolean ComprovarBingo(int[][] cartó, boolean Bingo) {
        int Trobats = 0;
        int Cartrons = 0;
        for (int i = 0; i < cartó.length; i++) {
            if (i % 3 == 0) {
                Trobats = 0;
                Cartrons++;
            }
            for (int j = 0; j < cartó[0].length; j++) {
                if (cartó[i][j] == -1) {
                    Trobats++;
                    if (Trobats == 15) {
                        Bingo = true;
                        System.out.println(" "
                                + "____    ___   _   _    ____    ___  \n"
                                + " | __ )  |_ _| | \\ | |  / ___|  / _ \\ \n"
                                + " |  _ \\   | |  |  \\| | | |  _  | | | |\n"
                                + " | |_) |  | |  | |\\  | | |_| | | |_| |\n"
                                + " |____/  |___| |_| \\_|  \\____|  \\___/ \n"
                                + "                                      " + "Al cartó 0" + Cartrons + "    \n");

                    }
                }

            }

        }

        return Bingo;
    }

    /**
     * Mostra el resum de la partida i marca els números que han coincidit després de comprarar la array amb la copia que conté els valors originals de l'array del cartró.
     * 
     * @param CopiaCarto
     * @param RandomsBombo
     * @param Contador
     */
    public static void MostrarCartroBombo(int[][] CopiaCarto, int RandomsBombo[], int Contador) {
        boolean Salido = false;
        System.out.println("\nEls números dels cartrons eren: ");
        MostrarCartro(CopiaCarto);
        System.out.println("");
        System.out.println(GREEN + "------------------------------------------------------" + RESET);
        System.out.println("______________________________________________________");
        System.out.println("\nI els números del Bombo que han sortit han estat els següents: ");
        for (int i = 0; i < Contador; i++) {
            if (i % 8 == 0) {
                System.out.println("");
            }
            for (int j = 0; j < CopiaCarto.length; j++) {
                for (int k = 0; k < CopiaCarto[0].length; k++) {
                    if (CopiaCarto[j][k] == RandomsBombo[i] && Salido != true) {
                        System.out.print("|" + GREEN);
                        System.out.printf(" %2d ", RandomsBombo[i]);
                        System.out.print(RESET + "|");
                        Salido = true;
                    }
                }
            }

            if (Salido != true) {
                System.out.printf("| %2d |", RandomsBombo[i]);
            }
            Salido = false;
        }
    }
}
