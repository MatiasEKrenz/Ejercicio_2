import java.util.Collection;

public interface AgencyService {

    public Agency[] getAgencies(String site_id, String payment_method_id, String near_to, String limit,
                                          String offset, String criterio_orden) throws CustomException;
}
