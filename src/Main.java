import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcionPrincipal = 0;

        do {
            System.out.println("\n=============================================");
            System.out.println("   PROYECTO PARCIAL 1: CONVERSOR NUMÉRICO    ");
            System.out.println("=============================================");
            System.out.println("1. Conversión de Base Decimal a Otras Bases");
            System.out.println("2. Conversión de Otras Bases a Decimal");
            System.out.println("3. Aritmética Binaria y Complemento a Dos");
            System.out.println("4. Fase 4: Avanzado (Operaciones Binarias Ca2)");
            System.out.println("5. Salir");
            System.out.println("=============================================");

            opcionPrincipal = leerEnteroSeguro("Seleccione una opción: ");

            switch (opcionPrincipal) {
                case 1:
                    menuFase1();
                    break;
                case 2:
                    menuFase2();
                    break;
                case 3:
                    menuFase3();
                    break;
                case 4:
                    menuFase4();
                    break;
                case 5:
                    System.out.println("\n¡Saliendo del programa! Gracias por usar el conversor.");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcionPrincipal != 5);
    }

    /**
     * FASE 1: DECIMAL A OTRAS BASES
     */
    private static void menuFase1() {
        System.out.println("\n--- DE DECIMAL A OTRAS BASES ---");
        int decimal = leerEnteroSeguro("Ingrese un número entero decimal positivo: ");

        if (decimal < 0) {
            System.out.println("Error: El número debe ser positivo.");
            return;
        }

        System.out.println("1. Convertir a Binario (Base 2)");
        System.out.println("2. Convertir a Octal (Base 8)");
        System.out.println("3. Convertir a Hexadecimal (Base 16)");
        System.out.println("4. Mostrar todas las conversiones");
        int subOpcion = leerEnteroSeguro("Seleccione base destino: ");

        switch (subOpcion) {
            case 1:
                System.out.println("Binario: " + ConversorBasico.decimalAOtraBase(decimal, 2));
                break;
            case 2:
                System.out.println("Octal: " + ConversorBasico.decimalAOtraBase(decimal, 8));
                break;
            case 3:
                System.out.println("Hexadecimal: " + ConversorBasico.decimalAOtraBase(decimal, 16));
                break;
            case 4:
                System.out.println("Binario: " + ConversorBasico.decimalAOtraBase(decimal, 2));
                System.out.println("Octal: " + ConversorBasico.decimalAOtraBase(decimal, 8));
                System.out.println("Hexadecimal: " + ConversorBasico.decimalAOtraBase(decimal, 16));
                break;
            default:
                System.out.println("Opción inválida.");
        }
    }

    /**
     * FASE 2: OTRAS BASES A DECIMAL
     */
    private static void menuFase2() {
        System.out.println("\n--- DE OTRAS BASES A DECIMAL ---");
        System.out.println("1. De Binario a Decimal");
        System.out.println("2. De Octal a Decimal");
        System.out.println("3. De Hexadecimal a Decimal");
        int subOpcion = leerEnteroSeguro("Seleccione la base de origen: ");

        int baseOrigen = 0;
        switch (subOpcion) {
            case 1: baseOrigen = 2; break;
            case 2: baseOrigen = 8; break;
            case 3: baseOrigen = 16; break;
            default:
                System.out.println("Opción inválida.");
                return;
        }

        System.out.print("Ingrese el número a convertir: ");
        String numeroEnBase = scanner.nextLine().trim();

        if (!ConversorBasico.validarNumero(numeroEnBase, baseOrigen)) {
            System.out.println("Error: El número ingresado no pertenece a la base seleccionada.");
            return;
        }

        int resultadoDecimal = ConversorBasico.otraBaseADecimal(numeroEnBase, baseOrigen);
        System.out.println("Equivalente Decimal: " + resultadoDecimal);
    }

    /**
     * FASE 3: ARITMÉTICA BINARIA Y COMPLEMENTO A DOS
     */
    private static void menuFase3() {
        System.out.println("\n--- ARITMÉTICA BINARIA Y COMPLEMENTO A DOS ---");
        int decimal = leerEnteroSeguro("Introduce un número decimal entero (positivo o negativo): ");
        int bits = leerEnteroSeguro("Introduce el número de bits (N) para la representación: ");

        if (bits <= 0) {
            System.out.println("Error: El número de bits debe ser mayor a 0.");
            return;
        }

        System.out.println("\n--- [Paso A - Representación] ---");
        // Invocación al método de lógica binaria
        String resultadoCa2 = LogicaBinaria.DecimalAC2(decimal, bits);

        if (resultadoCa2.equals("Error de Overflow")) {
            System.out.println("Error: " + resultadoCa2 + ". El número no cabe en " + bits + " bits.");
            return;
        }

        System.out.println("\n--- [Paso B - Verificación / Proceso Inverso] ---");
        System.out.println("Tomando el Ca2 generado para revertirlo...");
        // Invocación al método de verificación real c2aDecimal
        int verificado = LogicaBinaria.c2aDecimal(resultadoCa2);
        System.out.println("-> Confirmación final: El número decimal recuperado es: " + verificado);
    }

    /**
     * FASE 4: AVANZADO (SUMA Y RESTA BINARIA)
     */
    private static void menuFase4() {
        System.out.println("\n--- FASE 4: OPERACIONES EN BINARIO (Ca2) ---");
        System.out.println("1. Sumar dos cadenas binarias directamente");
        System.out.println("2. Restar dos números enteros mediante Ca2");
        int subOpcion = leerEnteroSeguro("Seleccione la operación: ");

        int bits = leerEnteroSeguro("Ingrese la cantidad de bits para la operación: ");
        if (bits <= 0) {
            System.out.println("Error: Bits inválidos.");
            return;
        }

        switch (subOpcion) {
            case 1:
                System.out.print("Ingrese la primera cadena binaria: ");
                String bin1 = scanner.nextLine().trim();
                System.out.print("Ingrese la segunda cadena binaria: ");
                String bin2 = scanner.nextLine().trim();

                System.out.println("\nProcesando Suma Binaria...");
                LogicaBinaria.SumaBinario(bin1, bin2, bits);
                break;

            case 2:
                int numA = leerEnteroSeguro("Ingrese el primer entero (Minuendo): ");
                int numB = leerEnteroSeguro("Ingrese el segundo entero (Sustraendo): ");

                System.out.println("\nProcesando Resta Binaria (A + B cambiado de signo)...");
                // Como la resta requiere sumar el negativo de b, le pasamos numA y -numB
                LogicaBinaria.restaBinario(numA, -numB, bits);
                break;

            default:
                System.out.println("Opción inválida.");
        }
    }

    /**
     * MÉTODO AUXILIAR: Captura segura de datos enteros
     */
    private static int leerEnteroSeguro(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("¡Error! Debe ingresar un número entero válido.");
            }
        }
    }
}