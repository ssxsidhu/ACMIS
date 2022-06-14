package comp3350.acmis.business;

import java.util.List;

import comp3350.acmis.application.Main;
import comp3350.acmis.application.Services;
import comp3350.acmis.objects.Flight;
import comp3350.acmis.objects.UserFlight;
import comp3350.acmis.persistence.DataAccessStub;

public class AccessUserFlights {
    private DataAccessStub dataAccess;
    private List<UserFlight> userFlights;
    private UserFlight userFlight;
    private String username;

    public AccessUserFlights(String user){
        username=user;
        dataAccess= Services.getDataAccess(Main.dbName);
    }

    public String getUserFlights(List<Flight> flights){
        flights.clear();
        return dataAccess.getUserFlightList(username,flights);
    }

}
