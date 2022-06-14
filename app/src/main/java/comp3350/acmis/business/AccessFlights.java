package comp3350.acmis.business;

import java.util.List;

import comp3350.acmis.application.Main;
import comp3350.acmis.application.Services;
import comp3350.acmis.objects.Flight;
import comp3350.acmis.objects.Location;
import comp3350.acmis.persistence.DataAccessStub;

public class AccessFlights {
    private DataAccessStub dataAccess;
    private List<Flight> flights;
    private Flight Flight;

    public AccessFlights(){
        dataAccess= Services.getDataAccess(Main.dbName);
    }

    public String getFlights(List<Flight> flights){
        flights.clear();
        return dataAccess.getAllFlights(flights);
    }


}
