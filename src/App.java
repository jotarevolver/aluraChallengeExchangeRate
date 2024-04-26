import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class App {

    static class ExchangeRates {
        JsonObject conversion_rates; // Objeto JSON que contiene los tipos de cambio
    }

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        String url = "https://v6.exchangerate-api.com/v6/afec98820a669bc383497541/latest/USD";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .build();
        HttpResponse<String> response = client
            .send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();
        Gson gson = new Gson();
        ExchangeRates exchangeRates = gson.fromJson(json, ExchangeRates.class);

        System.out.println("Bienvenido al conversor de monedas\n");

        System.out.println("""
        Elija la opcion que desea realizar:
        -------------------------PESOS ARGENTINOS-------------------------
        1. Convertir dolares a pesos argentinos
        2. Convertir pesos argentinos a dolares
        -------------------------BOLIVIANOS-------------------------------
        3. Convertir dolares a bolivianos
        4. Convertir bolivianos a dolares
        -----------------------------REALES-------------------------------
        5. Convertir dolares a reales
        6. Convertir reales a dolares
        -------------------------PESOS CHILENOS---------------------------
        7. Convertir dolares a pesos chilenos
        8 Convertir pesos chilenos a dolares
        -------------------------PESOS COLOMBIANOS------------------------
        9. Convertir dolares a pesos colombianos
        10. Convertir pesos colombianos a dolares
        ------------------------------------------------------------------
        """);
        var opcion = input.nextInt();
        
        switch (opcion) {
            case 1:
                System.out.println("Cantidad de dolares a convertir:");
                double dolaresUsuario = input.nextDouble();
                input.nextLine();
                double pesos = exchangeRates.conversion_rates.get("ARS").getAsDouble();
                double resultadoArs = dolaresUsuario * pesos;
                System.out.println(String.format("La cantidad de %.2f dolares, en pesos es: %.2f", dolaresUsuario, resultadoArs));
                break;
            case 2:
                System.out.println("Cantidad de pesos argentinos a convertir:");
                double pesosUsuario = input.nextDouble();
                input.nextLine();
                double dolares = exchangeRates.conversion_rates.get("ARS").getAsDouble();
                double resultadoUsd = pesosUsuario / dolares;
                System.out.println(String.format("La cantidad de pesos argentinos de %.2f, en dolares es: %.2f", pesosUsuario, resultadoUsd));
                break;
            case 3:
                System.out.println("Cantidad de dolares a convertir:");
                double dolaresToBob = input.nextDouble();
                input.nextLine();
                double pesosBob = exchangeRates.conversion_rates.get("BOB").getAsDouble();
                double resultadoBob = dolaresToBob * pesosBob;
                System.out.println(String.format("La cantidad de %.2f dolares, en bolivianos es: %.2f", dolaresToBob, resultadoBob));
                break;
            case 4:
                System.out.println("Cantidad de bolivianos a convertir:");
                double pesosUsr = input.nextDouble();
                input.nextLine();
                double dolarestoBob = exchangeRates.conversion_rates.get("BOB").getAsDouble();
                double resultadoUsdfromBob = pesosUsr / dolarestoBob;
                System.out.println(String.format("La cantidad de bolivianos de %.2f, en dolares es: %.2f", pesosUsr, resultadoUsdfromBob));
                break;
            case 5:
                System.out.println("Cantidad de dolares a convertir:");
                double dolaresToBrl = input.nextDouble();
                input.nextLine();
                double realesBrl = exchangeRates.conversion_rates.get("BRL").getAsDouble();
                double resultadoBrl = dolaresToBrl * realesBrl;
                System.out.println(String.format("La cantidad de %.2f dolares, en reales es: %.2f", dolaresToBrl, resultadoBrl));
                break;
            case 6:
                System.out.println("Cantidad de reales a convertir:");
                double reales = input.nextDouble();
                input.nextLine();
                double dolaresToReales = exchangeRates.conversion_rates.get("BRL").getAsDouble();
                double resultadoUsdfromReales = reales / dolaresToReales;
                System.out.println(String.format("La cantidad de pesos reales de %.2f, en dolares es: %.2f", reales, resultadoUsdfromReales));
                break;
            case 7:
                System.out.println("Cantidad de dolares a convertir:");
                double dolaresToClp = input.nextDouble();
                input.nextLine();
                double pesosClp = exchangeRates.conversion_rates.get("CLP").getAsDouble();
                double resultadoClp = dolaresToClp * pesosClp;
                System.out.println(String.format("La cantidad de %.2f dolares, en pesos chilenos es: %.2f", dolaresToClp, resultadoClp));
                break;
            case 8:
                System.out.println("Cantidad de pesos chilenos a convertir:");
                double pesosChilenos = input.nextDouble();
                input.nextLine();
                double dolarestoClp = exchangeRates.conversion_rates.get("CLP").getAsDouble();
                double resultadoUsdfromClp = pesosChilenos / dolarestoClp;
                System.out.println(String.format("La cantidad de pesos chilenos de %.2f, en dolares es: %.2f", pesosChilenos, resultadoUsdfromClp));
                break;
            case 9:
                System.out.println("Cantidad de dolares a convertir:");
                double dolaresToCop = input.nextDouble();
                input.nextLine();
                double pesosCop = exchangeRates.conversion_rates.get("COP").getAsDouble();
                double resultadoCop = dolaresToCop * pesosCop;
                System.out.println(String.format("La cantidad de %.2f dolares, en pesos colombianos es: %.2f", dolaresToCop, resultadoCop));
                break;
            case 10:
                System.out.println("Cantidad de pesos colombianos a convertir:");
                double pesosColombianos = input.nextDouble();
                input.nextLine();
                double dolarestoCop = exchangeRates.conversion_rates.get("COP").getAsDouble();
                double resultadoUsdfromCop = pesosColombianos / dolarestoCop;
                System.out.println(String.format("La cantidad de pesos colombianos de %.2f, en dolares es: %.2f", pesosColombianos, resultadoUsdfromCop));
                break;
            default:
                System.out.println("Opcion incorrecta, vuelva a intentar");
                System.out.println("Cerrando sesion...");
                break;
        }
        input.close();
    }
}