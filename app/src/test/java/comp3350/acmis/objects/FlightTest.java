/*
This class is used to test the functionality of the flight class.

 */
package comp3350.acmis.objects;

import static org.junit.Assert.*;

import androidx.annotation.DisplayContext;
import androidx.appcompat.app.ActionBar;

import org.junit.Test;

public class FlightTest {

    @Test
    //this method is used to test if every flight has a unique id. The id is sequential
    public void testgetFlightSeq() {
        assertEquals(0,Flight.getFlightSequence());
        Location location1 = new Location("Toronto", "Canada", "YYZ");
        Location location2 = new Location("Vancouver", "Canada", "YVR");
        Flight testY = new Flight(location1,location2,"2022-06-14","10:30","2022-06-15","4:30");
        assertEquals(1,Flight.getFlightSequence());
        Flight testZ = new Flight(location2,location1,"2022-11-14","07:30","2022-11-15","10:30");
        assertEquals(2,Flight.getFlightSequence());

    }

    @Test
    //Test the getSource method return the correct source city
    public void testgetSource() {
        Location location1 = new Location("Toronto", "Canada", "YYZ");
        Location location2 = new Location("Vancouver", "Canada", "YVR");
        Flight test1 = new Flight(location1,location2,"2022-06-14","10:30","2022-06-15","4:30");

        assertEquals("Toronto", test1.getSource().getCity());
    }

    @Test
    //Test the getDestination method returns the correct destination city
    public void testgetDestination() {
        Location location1 = new Location("Toronto", "Canada", "YYZ");
        Location location2 = new Location("Vancouver", "Canada", "YVR");
        Flight test1 = new Flight(location1,location2,"2022-06-14","10:30","2022-06-15","4:30");

        assertEquals("Vancouver", test1.getDestination().getCity());
    }

    //getting departure time
    @Test
    public void testgetDepartureTime() {
        Location location1 = new Location("Toronto", "Canada", "YYZ");
        Location location2 = new Location("Vancouver", "Canada", "YVR");
        Flight test1 = new Flight(location1,location2,"2022-06-14","10:30","2022-06-15","4:30");

        assertEquals("10:30", test1.getDepartureTime());
    }

    //testing the get arrival time
    @Test
    public void getArrivalTime() {
        Location location1 = new Location("Toronto", "Canada", "YYZ");
        Location location2 = new Location("Vancouver", "Canada", "YVR");
        Flight test1 = new Flight(location1,location2,"2022-06-14","10:30","2022-06-15","4:30");

        assertEquals("4:30", test1.getArrivalTime());
    }

    //testing the get arrival date that used the date formatter.
    @Test
    public void testgetArrivalDate() {
        Location location1 = new Location("Toronto", "Canada", "YYZ");
        Location location2 = new Location("Vancouver", "Canada", "YVR");
        Flight test1 = new Flight(location1,location2,"2022-06-14","10:30","2022-06-15","4:30");

        assertEquals("June 15, 2022", test1.getArrivalDate());

    }

    //testing the get departure date that uses the date formatter
    @Test
    public void testgetDepartureDate() {
        Location location1 = new Location("Toronto", "Canada", "YYZ");
        Location location2 = new Location("Vancouver", "Canada", "YVR");
        Flight test1 = new Flight(location1,location2,"2022-06-14","10:30","2022-06-15","4:30");

        assertEquals("June 14, 2022", test1.getDepartureDate());
    }

    //testing the get departure date without any formatting
    @Test
    public void testgetRawDepartureDate() {
        Location location1 = new Location("Toronto", "Canada", "YYZ");
        Location location2 = new Location("Vancouver", "Canada", "YVR");
        Flight test1 = new Flight(location1,location2,"2022-06-14","10:30","2022-06-15","4:30");

        assertEquals("2022-06-14", test1.getRawDepartureDate());
    }

    //testing the constructor parameters
    @Test
    public void testErrors(){

        //testing for null source location
        try{
            Location location1 = new Location("Toronto", "Canada", "YYZ");
            Location location2 = new Location("Vancouver", "Canada", "YVR");
            Flight testY = new Flight(null,location2,"2022-06-14","10:30","2022-06-15","4:30");
            fail("Expected a null pointer exception");
        }catch(NullPointerException unused){}

        //testing for null destination location
        try{
            Location location1 = new Location("Toronto", "Canada", "YYZ");
            Location location2 = new Location("Vancouver", "Canada", "YVR");
            Flight testY = new Flight(location1,null,"2022-06-14","10:30","2022-06-15","4:30");
            fail("Expected a null pointer exception");
        }catch(NullPointerException unused){}

        //testing for null departure date
        try{
            Location location1 = new Location("Toronto", "Canada", "YYZ");
            Location location2 = new Location("Vancouver", "Canada", "YVR");
            Flight testY = new Flight(location1,location2,null,"10:30","2022-06-15","4:30");
            fail("Expected a null pointer exception");
        }catch(NullPointerException unused){}

        //testing for null departure time
        try{
            Location location1 = new Location("Toronto", "Canada", "YYZ");
            Location location2 = new Location("Vancouver", "Canada", "YVR");
            Flight testY = new Flight(location1,location2,"2022-06-14",null,"2022-06-15","4:30");
            fail("Expected a null pointer exception");
        }catch(NullPointerException unused){}

        //testing for null arrival date
        try{
            Location location1 = new Location("Toronto", "Canada", "YYZ");
            Location location2 = new Location("Vancouver", "Canada", "YVR");
            Flight testY = new Flight(location1,location2,"2022-06-14","10:30",null,"4:30");
            fail("Expected a null pointer exception");
        }catch(NullPointerException unused){}

        //testing for null arrival time
        try{
            Location location1 = new Location("Toronto", "Canada", "YYZ");
            Location location2 = new Location("Vancouver", "Canada", "YVR");
            Flight testY = new Flight(location1,location2,"2022-06-14","10:30","2022-06-15",null);
            fail("Expected a null pointer exception");
        }catch(NullPointerException unused){}


    }

}