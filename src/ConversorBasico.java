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
}