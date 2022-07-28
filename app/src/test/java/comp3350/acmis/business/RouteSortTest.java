package comp3350.acmis.business;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

import java.util.ArrayList;
import java.util.Collections;

import comp3350.acmis.objects.Flight;
import comp3350.acmis.objects.Location;
import comp3350.acmis.objects.Route;

public class RouteSortTest {

    Location loc1, loc2, loc3, loc4, loc5;
    Flight f1, f2, f3, f4, f5;
    Route r1, r2, r3, r4, r5;
    ArrayList<Route> testRoutes;
    SortRoutes test;

    @Before
    public void setup() {

        test = new SortRoutes();
        testRoutes = new ArrayList<Route>();

        loc1 = new Location("City 1", ZoneId.of("America/Toronto"), "TEST COUNTRY", "AIRPORT 1");
        loc2 = new Location("City 2", ZoneId.of("America/Winnipeg"), "TEST COUNTRY", "AIRPORT 2");
        loc3 = new Location("City 3", ZoneId.of("America/Winnipeg"), "TEST COUNTRY", "AIRPORT 3");
        loc4 = new Location("City 4", ZoneId.of("America/Winnipeg"), "TEST COUNTRY", "AIRPORT 4");
        loc5 = new Location("City 5", ZoneId.of("America/Vancouver"), "TEST COUNTRY", "AIRPORT 5");

        f1 = new Flight(loc1, loc2, ZonedDateTime.of(2022, 8, 1, 7, 30, 0, 0, loc1.getZoneName()), 250, 2.5, 100);
        f2 = new Flight(loc2, loc3, ZonedDateTime.of(2022, 8, 1, 8, 30, 0, 0, loc1.getZoneName()), 250, 6.5, 200);
        f3 = new Flight(loc3, loc4, ZonedDateTime.of(2022, 8, 1, 9, 30, 0, 0, loc1.getZoneName()), 250, 3.5, 300);
        f4 = new Flight(loc4, loc5, ZonedDateTime.of(2022, 8, 1, 2, 30, 0, 0, loc1.getZoneName()), 250, 1.5, 400);
        f5 = new Flight(loc5, loc1, ZonedDateTime.of(2022, 8, 1, 3, 30, 0, 0, loc1.getZoneName()), 250, 4.5, 500);

        reset();
        resetList();
    }

    @Test
    public void testLowestPrice() {

        reset();
        resetList();
        test.lowestPrice(testRoutes);

        // R1 should be at first place since it is lowest price. R5 should be last because its max price
        Assert.assertEquals(testRoutes.get(0), r1);
        Assert.assertEquals(testRoutes.get(testRoutes.size() - 1), r5);

        reset();
        r1.addToRoute(f2);          // r1 no longer lowest. r2 is lowest now
        r4.addToRoute(f3);          // r5 no longer highest. r4 is highest now
        resetList();

        test.lowestPrice(testRoutes);
        // R2 should be at first place since it is lowest price. R4 should be last because its max price
        Assert.assertEquals(testRoutes.get(0), r2);
        Assert.assertEquals(testRoutes.get(testRoutes.size() - 1), r4);

    }

    @Test
    public void testNumStops() {        // Test for NULL CASE

        reset();

        // R4 should have 1 stop. R5 now should have 2 stops
        r4.addToRoute(f5);
        r5.addToRoute(f2);
        r5.addToRoute(f3);

        resetList();
        test.leastStops(testRoutes);

        // R4 MUST be on the second last index and R5 MUST be on the last index
        Assert.assertEquals(testRoutes.get(testRoutes.size() - 1), r5);
        Assert.assertEquals(testRoutes.get(testRoutes.size() - 2), r4);
    }

    @Test
    public void testEarliestDepart() {

        reset();
        resetList();


        // R4 Departs Earliest. and R3 departs lates.
        test.earliestDepart(testRoutes);
        Assert.assertEquals(testRoutes.get(0), r4);
        Assert.assertEquals(testRoutes.get(testRoutes.size() - 1), r3);

    }

    @Test
    public void testLeastDuration() {

        reset();
        resetList();

        test.lowestDuration(testRoutes);
        Assert.assertEquals(testRoutes.get(0), r4);
        Assert.assertEquals(testRoutes.get(testRoutes.size() - 1), r2);
    }

    private void reset() {

        r1 = new Route(f1);
        r2 = new Route(f2);
        r3 = new Route(f3);
        r4 = new Route(f4);
        r5 = new Route(f5);

    }

    private void resetList() {

        testRoutes = new ArrayList<>();

        testRoutes.add(r1);
        testRoutes.add(r2);
        testRoutes.add(r3);
        testRoutes.add(r4);
        testRoutes.add(r5);


        Collections.shuffle(testRoutes);
    }
}


