package comp3350.acmis.persistence;


import org.junit.Test;
import java.util.ArrayList;
import comp3350.acmis.application.Main;
import comp3350.acmis.objects.Location;

public class DataAccessStubTest {

    @Test
    public void checkGetLocations() {
        DataAccessStub dataAccessStub  =  new DataAccessStub(Main.dbName);
        dataAccessStub.open(Main.dbName);
        ArrayList<Location> locationArrayList = new ArrayList<Location>();
        dataAccessStub.getLocations(locationArrayList);
//        System.out.println(locationArrayList.get(0).getCity());

    }
}