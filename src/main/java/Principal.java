import com.google.gson.Gson;
import static spark.Spark.*;

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

    }
}
