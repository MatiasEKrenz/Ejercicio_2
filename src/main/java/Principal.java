import com.google.gson.Gson;
import java.io.IOException;
import static spark.Spark.*;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Principal {

    public static void main(String[] args) {

        Logger logger = Logger.getLogger("LogAPI");
        FileHandler fh;

        final AgencyService agencyService = new AgencyServiceImpl(); // final para decir que no lo voy a modificar mas

        try {

            fh = new FileHandler("./LogAPI.log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }

        get("/agencias/:site_id/:payment_method_id/:near_to", (request, response) -> {

            response.type("application/json");
            try{
                //logger.info(agencyService.getURL());
                logger.info(request.url() + "/" + request.queryString());

                return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(
                        agencyService.getAgencies(request.params(":site_id"), request.params(":payment_method_id"),
                                request.params(":near_to"), request.queryParams("limit"), request.queryParams("offset"),
                                request.queryParams("criterio_orden")))));
            } catch (CustomException e){
                return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
            }
        });



    }
}
