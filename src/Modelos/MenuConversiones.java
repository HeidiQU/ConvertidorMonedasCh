package Modelos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import static Calculos.Convertir.ConversorMonedas;

public class MenuConversiones {

    public static void desplegarMenu() throws IOException {
        Scanner teclado= new Scanner(System.in);
        int seleccion = 0;
        double cantidad = 0.0;
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        //  ARRAY DONDE SE GUARDARÁ EL HISTORIAL DE CONVERSIONES
        List<ResultadoConversion> historial = new ArrayList<>();

        do{
            System.out.println("\n 1. Peso Mexicano (MXN) a Euro (EUR)");
            System.out.println(" 2. Peso Mexicano (MXN) a Dolar (USD)");
            System.out.println(" 3. Dolar (USD) a Euro (EUR)");
            System.out.println(" 4. Euro (EUR) a Dolar (USD)");
            System.out.println(" 5. Peso Mexicano (MXN) a Remminbi Chino (CNY)");
            System.out.println(" 6. Remminbi Chino (CNY) a Peso Mexicano (MXN)");
            System.out.println(" 7. Peso Mexicano (MXN) a Dong Vietnamita (VND)");
            System.out.println(" 8. Dong Vietnamita (VND) a Peso Mexicano (MXN)");
            System.out.println(" 0. SALIR");
            System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("\n SELECCIONA UNA OPCIÓN VÁLIDA: ");

            try{
                seleccion = teclado.nextInt();

                if(seleccion>8){
                    System.out.println("---------------- ERROR - OPCIÓN INEXISTENTE ----------------");
                    desplegarMenu();
                }
            }catch (Exception e){
                System.out.println("---------------- ERROR - SELECCIONA UNA OPCIÓN VÁLIDA ----------------");
                desplegarMenu();
            }

            try {
                if(seleccion!=0) {
                    System.out.println("INGRESA LA CANTIDAD A CONVERTIR: ");
                    cantidad = teclado.nextDouble();
                }
            }catch (InputMismatchException e){
                System.out.println("-----------  ERROR - FAVOR DE INGRESAR UN NÚMERO ------------");
                teclado.nextLine();
                //break;
            }

            switch (seleccion) {
                case 1:
                    ResultadoConversion miResultado = new ResultadoConversion(ConversorMonedas("MXN", "EUR", cantidad)) ;
                    historial.add(miResultado);
                    //System.out.println(miResultado);
                    break;
                case 2:
                    miResultado = new ResultadoConversion(ConversorMonedas("MXN", "USD", cantidad));
                    historial.add(miResultado);
                    break;
                case 3:
                    miResultado = new ResultadoConversion(ConversorMonedas("USD", "EUR", cantidad));
                    historial.add(miResultado);
                    break;
                case 4:
                    miResultado = new ResultadoConversion(ConversorMonedas("EUR","USD", cantidad)) ;
                    historial.add(miResultado);
                    break;
                case 5:
                    miResultado = new ResultadoConversion(ConversorMonedas("MXN", "CNY", cantidad));
                    historial.add(miResultado);
                    break;
                case 6:
                    miResultado = new ResultadoConversion(ConversorMonedas("CNY","MXN", cantidad));
                    historial.add(miResultado);
                    break;
                case 7:
                    miResultado = new ResultadoConversion(ConversorMonedas("MXN","VND", cantidad));
                    historial.add(miResultado);
                    break;
                case 8:
                    miResultado = new ResultadoConversion( ConversorMonedas("VND","MXN", cantidad));
                    historial.add(miResultado);
                    break;
            }
        }while (seleccion!=0);

        FileWriter archivoHistorial = new FileWriter("historial.json");
        archivoHistorial.write(gson.toJson(historial));
        archivoHistorial.close();
        System.out.println(historial);
    }


}
