import com.google.gson.Gson;
import static spark.Spark.*;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.net.MalformedURLException;
        import java.net.URL;
        import java.net.URLConnection;
        import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        final AgencyService agencyService = new AgencyServiceImpl(); // final para decir que no lo voy a modificar mas

        get("/agencias/:site_id/:payment_method_id/:near_to", (request, response) -> {
            //...
            response.type("application/json");
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(
                    agencyService.getAgencies(request.params(":site_id"), request.params(":payment_method_id"),
                            request.params(":near_to"), request.queryParams("limit"), request.queryParams("offset"),
                            request.queryParams("criterio_orden")))));
        });



       /* Sites[] sites = null;

        try {
            String sitesData = readUrl("https://api.mercadolibre.com/sites");
            sites = new Gson().fromJson(sitesData, Sites[].class);

            for (Sites site : sites){
                System.out.println(site.getName());
            }

        } catch (IOException e) {
            System.out.println("Ocurrio un error al traer los sites.");
            e.printStackTrace();
        }

        boolean bandera = true;
        String id = "";

        while (bandera){

            System.out.println("\nSeleccione un sitio: ");

            Scanner scanner = new Scanner(System.in);
            String sitio = scanner.nextLine();

            for (Sites site : sites){
                if (site.getName().equals(sitio)){
                    bandera = false;
                    id = site.getId();
                }
            }
        }

        PaymentMethods[] payments = null;

        try {
            String paymentData = readUrl("https://api.mercadolibre.com/sites/" + id + "/payment_methods");
            payments = new Gson().fromJson(paymentData, PaymentMethods[].class);

            for (PaymentMethods payment : payments){
                System.out.println(payment.getName());
            }

        } catch (IOException e) {
            System.out.println("Ocurrio un error al traer los sites.");
            e.printStackTrace();
        }

        bandera = true;
        String metodoPago = "";

        while (bandera){

            System.out.println("\nSeleccione un metodo de pago: ");

            Scanner scanner = new Scanner(System.in);
            String pago = scanner.nextLine();

            for (PaymentMethods payment : payments){
                if (payment.getName().equals(pago)){
                    bandera = false;
                    metodoPago = payment.getId();
                }
            }
        }





        try {
            String data = readUrl("https://api.mercadolibre.com/sites/" + id + "/categories/" + metodoPago + "/agencies");
            Agency[] agencies = new Gson().fromJson(data, Agency[].class);
            for (Agency agency : agencies){
                System.out.println("\n" + agency.getDescripcion());
            }
        } catch (IOException e) {
            System.out.println("Ocurrio un error al traer las categorias.");
            e.printStackTrace();
        }
    }






    private static String readUrl(String urlString) throws IOException {

        BufferedReader reader = null;

        try {
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            int read = 0;
            StringBuffer buffer = new StringBuffer();
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1){
                buffer.append(chars, 0, read);
            }
            return buffer.toString();

        } finally {
            if (reader != null){
                reader.close();
            }
        }




        */








    }
}
