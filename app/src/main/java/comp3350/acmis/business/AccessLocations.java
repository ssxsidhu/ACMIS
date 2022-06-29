package comp3350.acmis.business;

import java.util.ArrayList;
import java.util.List;

import comp3350.acmis.application.Main;
import comp3350.acmis.application.Services;
import comp3350.acmis.objects.Location;
import comp3350.acmis.persistence.DataAccessStub;

public class AccessLocations {

    // INSTANCE VARIABLES
    private DataAccessStub dataAccess;
    private List<Location> locations;
    private Location location;

    // CONSTRUCTOR
    public AccessLocations() {
        dataAccess=Services.getDataAccess(Main.dbName);
    }

    // GETTER
    public String getLocations (ArrayList<Location> locations) {
        locations.clear();
        return dataAccess.getLocations(locations);
    }
}
