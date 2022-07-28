//This class is used as an interface to connect the database and the presentation
//helps to get all the locations from the database.

package comp3350.acmis.business;

import java.util.ArrayList;

import comp3350.acmis.application.Services;
import comp3350.acmis.objects.Location;
import comp3350.acmis.persistence.DataAccess;

public class AccessLocations {
    private final DataAccess dataAccess;

    public AccessLocations() {
        dataAccess = Services.getDataAccess();
    }

    public String getLocations(ArrayList<Location> locations) {
        if (locations != null) {
            locations.clear();
        } else {
            throw new NullPointerException();
        }

        return dataAccess.getLocations(locations);
    }
}
