import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class AgencyServiceImpl implements AgencyService {


    public AgencyServiceImpl() {
    }

    @Override
    public Agency[] getAgencies(String site_id, String payment_method_id, String near_to, String limit,
                                          String offset/*, String criterio_orde*/) {

        String url = "https://api.mercadolibre.com/sites/" + site_id + "/payment_methods/" + payment_method_id + "/agencies?near_to=" + near_to;

        if(limit != null){
            url = url + "&limit=" + limit;
        }
        if(offset != null){
            url = url + "&offset=" + offset;
        }

        try {
            String data = readUrl(url);

            JsonObject jsonObject = new JsonParser().parse(data).getAsJsonObject();
            Agency[] agencies = new Gson().fromJson(jsonObject.get("results").getAsJsonArray(), Agency[].class);

            for (Agency agency : agencies){
                System.out.println("\n" + agency.getDescripcion());
            }

            return agencies;
        } catch (IOException e) {
            System.out.println("Ocurrio un error al traer las agencias.");
            e.printStackTrace();
            return null;
        }

    /*    if (criterio_orde.equals("address_line")){
            Agency.criterio = Agency.Criterio.ADDRESS_LINE;
        }
        else if (criterio_orde.equals("agency_code")){
            Agency.criterio = Agency.Criterio.AGENCY_CODE;
        }
        else if (criterio_orde.equals("distance")){
            Agency.criterio = Agency.Criterio.DISTANCE;
        }


        agencies = Operador.criterioAscendente(agencies);

*/

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
    }
}
