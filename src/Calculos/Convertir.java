package Calculos;

import Modelos.ConversionExchange;
import Modelos.ResultadoConversion;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;

public class Convertir {

    public static ResultadoConversion ConversorMonedas(String convertirDe, String convertirA, double Monto) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        String ApiKey ="71204a03af4e63cd9d432ff4";
        DecimalFormat convertirMonto = new DecimalFormat("#####.####");
        // URL PARA LA CONSULTA
        String urlConsulta = "https://v6.exchangerate-api.com/v6/" + ApiKey +"/pair/" + convertirDe
                + "/" + convertirA + "/" + convertirMonto.format(Monto);

        try {
            //CONSULTA
            HttpClient cliente = HttpClient.newHttpClient();
            HttpRequest solicitud = HttpRequest.newBuilder()
                    .uri(URI.create(urlConsulta))
                    .build();
            HttpResponse<String> respuesta = cliente
                    .send(solicitud, HttpResponse.BodyHandlers.ofString());

            //LECTURA DE LA RESPUESTA DE LA API
            String respuestaJson = respuesta.body();
            //System.out.println(respuestaJson);


            ConversionExchange miConversion = gson.fromJson(respuestaJson, ConversionExchange.class);
            //System.out.println(miConversion);


            ResultadoConversion resultado = new ResultadoConversion(miConversion);
            resultado.setConversion_origin(Double.parseDouble(convertirMonto.format(Monto)));
            resultado.setConversion_result(Double.parseDouble(convertirMonto.format(resultado.getConversion_result())));

            // GUARDA LA CONVERSIÓN EN EL HISTORIAL DE CONVERSIONES


            System.out.println("\n*****************************************************************************\n");
            System.out.println(resultado);
            System.out.println("\n*****************************************************************************\n\n");

            return resultado;

        } catch (Exception e){
            System.out.println(" -------- ERROR - AL REALIZAR LA CONSULTA ----------");
            System.out.println(e.getMessage());
            ResultadoConversion resultado = new ResultadoConversion("ERROR",0.0,0.0,0.0,convertirDe,convertirA);
            return resultado;
        }


    }

}
