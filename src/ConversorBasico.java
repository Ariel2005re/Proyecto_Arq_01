import java.util.Scanner;

public class ConversorBasico {

    // =========================================
    // DECIMAL A OTRA BASE
    // =========================================
    public static String decimalAOtraBase(int numero, int base) {

        // Caso especial
        if (numero == 0) {
            return "0";
        }

        String caracteres = "0123456789ABCDEF";

        String resultado = "";

        // Método de divisiones sucesivas
        while (numero > 0) {

            int residuo = numero % base;

            resultado = caracteres.charAt(residuo) + resultado;

            numero = numero / base;
        }

        return resultado;
    }

    // =========================================
    // OTRA BASE A DECIMAL
    // =========================================
    public static int otraBaseADecimal(String numero, int base) {

        numero = numero.toUpperCase();

        String caracteres = "0123456789ABCDEF";

        int resultado = 0;

        // Expansión polinómica
        for (int i = 0; i < numero.length(); i++) {

            char digito = numero.charAt(i);

            int valor = caracteres.indexOf(digito);

            resultado = resultado * base + valor;
        }

        return resultado;
    }

    // =========================================
    // VALIDAR SI EL NÚMERO ES CORRECTO
    // SEGÚN LA BASE
    // =========================================
    public static boolean validarNumero(String numero, int base) {

        numero = numero.toUpperCase();

        String permitidos = "";

        switch (base) {

            case 2:
                permitidos = "01";
                break;

            case 8:
                permitidos = "01234567";
                break;

            case 16:
                permitidos = "0123456789ABCDEF";
                break;
        }

        for (int i = 0; i < numero.length(); i++) {

            char c = numero.charAt(i);

            if (permitidos.indexOf(c) == -1) {
                return false;
            }
        }

        return true;
    }

    // =========================================
    // MAIN TEMPORAL PARA PRUEBAS
    // =========================================
    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        int opcion;

        do {

            System.out.println("\n========== MENU ==========");
            System.out.println("1. Decimal a otra base");
            System.out.println("2. Otra base a decimal");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opcion: ");

            while (!entrada.hasNextInt()) {
                System.out.print("Error. Ingrese un numero valido: ");
                entrada.next();
            }

            opcion = entrada.nextInt();

            switch (opcion) {

                // =====================================
                // DECIMAL A OTRA BASE
                // =====================================
                case 1:

                    int decimal;
                    int baseDestino;

                    System.out.print("\nIngrese un numero decimal positivo: ");

                    while (!entrada.hasNextInt()) {
                        System.out.print("Error. Ingrese un numero entero: ");
                        entrada.next();
                    }

                    decimal = entrada.nextInt();

                    // Restricción
                    if (decimal < 0) {
                        System.out.println("Error: solo se permiten numeros positivos.");
                        break;
                    }

                    System.out.println("\nBases disponibles:");
                    System.out.println("2 -> Binario");
                    System.out.println("8 -> Octal");
                    System.out.println("16 -> Hexadecimal");

                    System.out.print("Ingrese la base destino: ");

                    while (!entrada.hasNextInt()) {
                        System.out.print("Error. Ingrese 2, 8 o 16: ");
                        entrada.next();
                    }

                    baseDestino = entrada.nextInt();

                    // Restricción
                    if (baseDestino != 2 &&
                        baseDestino != 8 &&
                        baseDestino != 16) {

                        System.out.println("Error: base no valida.");
                        break;
                    }

                    String convertido =
                            decimalAOtraBase(decimal, baseDestino);

                    System.out.println("\nResultado: " + convertido);

                    break;

                // =====================================
                // OTRA BASE A DECIMAL
                // =====================================
                case 2:

                    String numero;
                    int baseOrigen;

                    System.out.println("\nBases disponibles:");
                    System.out.println("2 -> Binario");
                    System.out.println("8 -> Octal");
                    System.out.println("16 -> Hexadecimal");

                    System.out.print("Ingrese la base del numero: ");

                    while (!entrada.hasNextInt()) {
                        System.out.print("Error. Ingrese 2, 8 o 16: ");
                        entrada.next();
                    }

                    baseOrigen = entrada.nextInt();

                    // Restricción
                    if (baseOrigen != 2 &&
                        baseOrigen != 8 &&
                        baseOrigen != 16) {

                        System.out.println("Error: base no valida.");
                        break;
                    }

                    System.out.print("Ingrese el numero: ");
                    numero = entrada.next();

                    // Validación del número
                    if (!validarNumero(numero, baseOrigen)) {

                        System.out.println(
                                "Error: el numero no pertenece a la base seleccionada.");

                        break;
                    }

                    int decimalResultado =
                            otraBaseADecimal(numero, baseOrigen);

                    System.out.println(
                            "\nResultado decimal: " + decimalResultado);

                    break;

                case 3:
                    System.out.println("\nSaliendo...");
                    break;

                default:
                    System.out.println("\nOpcion invalida.");
            }

        } while (opcion != 3);

        entrada.close();
    }
}