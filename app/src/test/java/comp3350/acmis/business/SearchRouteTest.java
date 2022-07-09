package comp3350.acmis.business;

import static org.junit.Assert.*;

//import androidx.room.jarjarred.org.antlr.v4.misc.Graph;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import comp3350.acmis.application.Main;
import comp3350.acmis.application.Services;
import comp3350.acmis.objects.Flight;
import comp3350.acmis.objects.Location;
import comp3350.acmis.persistence.DataAccess;
import comp3350.acmis.persistence.DataAccessStub;

public class SearchRouteTest {

//    private Graph<Location> graph;
    private DataAccess data;
    private SearchRoute s;
    private ArrayList <Flight> f;

    @Before
    private void setUp()   {
//        this.graph = new Graph<>();
        data = Services.getDataAccess(Main.dbName);
        s = new SearchRoute();
        f = new ArrayList<>();
    }

    @After
    private void tearDown()   {
//        graph = null;
        data = null;
        s = null;

    }

    @Test
    public void testAddedges(){
        setUp();

        data.getAllFlights(f);

        s.addAllEdges(f);






    }


}