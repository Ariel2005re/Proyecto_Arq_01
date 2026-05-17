public class LogicaBinaria {

    public static String DecimalAC2(int numero, int bits){
        // Formula para contar los bits que deben estar quitando el signo

        int minimo = -(1<<(bits-1));
        int maximo = (1<<(bits-1))-1;
        //para detectar el overflow
        if (numero<minimo || numero>maximo){
            return "Error de Overflow";
        }//Positivo
        if (numero>=0){
            String binario =Integer.toBinaryString(numero); //trasforma en binario
            while(binario.length()<bits){ //completa los 0
                binario ="0" + binario;
            }
            return binario;
        }
        //negativo
        else {
            int valorAbsoluto =Math.abs(numero);
            String binario = Integer.toBinaryString(valorAbsoluto);
            while(binario.length() < bits){
                binario = "0" + binario;
            }
            System.out.println("Binario: " + binario);
            //Empezamos el C1
            String c1="";
            for (int i=0; i<binario.length();i++ ){
                if (binario.charAt(i)== '0'){ //recorre para encontrar los caracteres
                    c1+="1";
                }else {
                    c1+="0";
                }
            }
            System.out.println("Complemento 1:" + c1);
            //C2
            String c2="";
            int carry=1; //acarreo
            for(int i=bits -1;i>=0;i--){
                int bit =c1.charAt(i)-'0';
                int suma=bit+carry;
                c2=(suma % 2)+c2;
                carry=suma/2;
            }
            System.out.println("Complemento 2:" +c2 );
            return c2;
        }


    }
    //Fase B
    public static int c2aDecimal(String c2){
        if(c2.charAt(0)=='0'){
            int decimal =Integer.parseInt(c2,2);
            return decimal;
        }
        else{
            System.out.println("C2 original: " +c2);
            String menosUno=restarUno(c2);
            System.out.println("Restamos 1: "+menosUno);
            //Invertirmos los bits
            String original="";
            for(int i=0;i<menosUno.length();i++){
                if(menosUno.charAt(i)=='0'){
                    original+="1";
                }else{
                    original+="0";
                }
            }
            System.out.println("Invertir bits: " +original );

            int decimal=Integer.parseInt(original,2);
            decimal=-decimal;
            System.out.println("Resultado decimal es: " +decimal);
            return decimal;
        }

    }
    private static String restarUno(String binario){
        char [] bits =binario.toCharArray();
        //recorremos desde la derecha
        for(int i=bits.length-1;i>=0;i++){
            if(bits[i]=='1'){
                bits[i]='0';
                break;
            }
            else{
                bits[i]='1';
            }
        }
        return new String(bits);
    }
    //Fase 4
    public static String SumaBinario(String a,String b,int bits){
        String resultado="";
        int carry=0;
        //Rellenamos para evitar errores con 0 a la izquierda
        while(a.length()<bits){
            a="0" +a;
        }
        while(b.length()<bits){
            b="0" +b;
        }
        for (int i=bits-1;i>=0;i--){
            int bitA=a.charAt(i)-'0';
            int bitB=b.charAt(i)-'0';
            int suma=bitA+bitB+carry;
            resultado=(suma%2)+resultado;
            carry= suma/2;

        }
        System.out.println("primer numero: " +a);
        System.out.println("Segundo numero:" +b);
        System.out.println("Resultado:" +resultado);

        return resultado;
    }
    public static String restaBinario(int a, int b, int bits){

        String binA = DecimalAC2(a, bits);

        String binB = DecimalAC2(b, bits);

        String resultado = SumaBinario(binA, binB, bits);

        return resultado;
    }
}