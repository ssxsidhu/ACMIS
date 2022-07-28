package comp3350.acmis.business;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

import java.util.ArrayList;

import comp3350.acmis.application.Services;
import comp3350.acmis.objects.Flight;
import comp3350.acmis.objects.Location;
import comp3350.acmis.objects.Route;
import comp3350.acmis.persistence.DataAccess;
import comp3350.acmis.persistence.DataAccessStub;

public class RouteManagerTest {

    ArrayList<Route> testRoutes;
    ArrayList<Location> locs;

    RouteManager test;


    @Before
    public void setup() {


        locs = new ArrayList<>();
        testRoutes = new ArrayList<>();

        DataAccess testAccess = Services.createDataAccess(new DataAccessStub());
        Services.dataAccessOpen();
        test = new RouteManager();
        testAccess.getLocations(locs);

    }

    @After
    public void tear() {

    }

    @Test
    public void testSearchRoute() {

        testRoutes.clear();

        System.out.println(locs.get(0)==null);
        test.searchRoute(locs.get(0),locs.get(1),testRoutes, ZonedDateTime.now());
        Assert.assertEquals(testRoutes.size(),0);

        test.searchRoute(locs.get(0),locs.get(1),testRoutes, ZonedDateTime.of(2022, 8, 1, 16, 30, 0, 0, locs.get(0).getZoneName()));
        Assert.assertTrue(testRoutes.size()>0);
    }

}
