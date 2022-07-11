//This class is yours as in interface to connect the database and the presentation
//helps to get all the locations from the database.

package comp3350.acmis.business;

import java.util.ArrayList;
import java.util.List;

import comp3350.acmis.application.Main;
import comp3350.acmis.application.Services;
import comp3350.acmis.objects.Location;
import comp3350.acmis.persistence.DataAccess;
import comp3350.acmis.persistence.DataAccessStub;

public class AccessLocations {
    private DataAccess dataAccess;
    private List<Location> locations;
    private Location location;

    public AccessLocations() {
        dataAccess = Services.getDataAccess(Main.dbName);
    }

    public String getLocations(ArrayList<Location> locations) {
        locations.clear();
        return dataAccess.getLocations(locations);
    }
}
