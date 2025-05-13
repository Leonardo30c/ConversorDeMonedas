import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Properties;
import java.util.Scanner;

public class ConversorMonedas {
    private static final String CONFIG_FILE = "/config.properties";
    private static String API_KEY;
    private static final DecimalFormat df = new DecimalFormat("#,##0.00");

    static {
        try (InputStream input = ConversorMonedas.class.getResourceAsStream(CONFIG_FILE)) {
            Properties props = new Properties();
            props.load(input);
            API_KEY = props.getProperty("api.key");
        } catch (IOException e) {
            System.err.println("Error al cargar configuración: " + e.getMessage());
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n***************************************");
        System.out.println("***   CONVERSOR DE MONEDAS - LEONARDO  ***");
        System.out.println("***************************************\n");

        while (true) {
            mostrarMenu();
            System.out.print("\nSeleccione una opción (1-7): ");
            String opcion = scanner.nextLine();

            if (opcion.equals("7")) {
                System.out.println("\n¡Gracias por usar el Conversor de Monedas!");
                System.out.println("¡Continúa tu aprendizaje con Alura Latam!");
                break;
            }

            try {
                realizarConversion(opcion, scanner);
            } catch (Exception e) {
                System.out.println("\n⚠️ Error: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n1. Dólares (USD) → Pesos Mexicanos (MXN)");
        System.out.println("2. Pesos Mexicanos (MXN) → Dólares (USD)");
        System.out.println("3. Dólares (USD) → Euros (EUR)");
        System.out.println("4. Euros (EUR) → Dólares (USD)");
        System.out.println("5. Dólares (USD) → Reales Brasileños (BRL)");
        System.out.println("6. Reales Brasileños (BRL) → Dólares (USD)");
        System.out.println("7. Salir");
    }

    private static void realizarConversion(String opcion, Scanner scanner) throws IOException {
        String[][] conversiones = {
                {"USD", "MXN", "Dólares", "Pesos Mexicanos"},
                {"MXN", "USD", "Pesos Mexicanos", "Dólares"},
                {"USD", "EUR", "Dólares", "Euros"},
                {"EUR", "USD", "Euros", "Dólares"},
                {"USD", "BRL", "Dólares", "Reales Brasileños"},
                {"BRL", "USD", "Reales Brasileños", "Dólares"}
        };

        int index = Integer.parseInt(opcion) - 1;
        String[] conversion = conversiones[index];

        System.out.printf("\nIngrese cantidad en %s: ", conversion[2]);
        double cantidad = Double.parseDouble(scanner.nextLine());

        double tasa = obtenerTasaConversion(conversion[0], conversion[1]);
        double resultado = cantidad * tasa;

        System.out.printf("\n🔹 %s %s = %s %s\n",
                df.format(cantidad), conversion[0],
                df.format(resultado), conversion[1]);
    }

    private static double obtenerTasaConversion(String de, String a) throws IOException {
        String urlStr = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/" + de + "/" + a;
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        try (InputStreamReader reader = new InputStreamReader(conn.getInputStream())) {
            JsonObject response = JsonParser.parseReader(reader).getAsJsonObject();

            if (!response.get("result").getAsString().equals("success")) {
                throw new IOException("API Error: " + response.get("error-type").getAsString());
            }

            return response.get("conversion_rate").getAsDouble();
        }
    }
}