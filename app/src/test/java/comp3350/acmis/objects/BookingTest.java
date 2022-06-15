package comp3350.acmis.objects;

import static org.junit.Assert.*;

import org.junit.Test;

public class BookingTest {


    //normal test to get the user who booked
    @Test
    public void TestgetBooker() {
        Location location1 = new Location("Toronto", "Canada", "YYZ");
        Location location2 = new Location("Vancouver", "Canada", "YVR");
        Flight test1Flight = new Flight(location1,location2,"2022-06-14","10:30","2022-06-15","4:30");
        User user = new User("Julie","smith", User.Gender.FEMALE,"jsmith","j&smith$","jmith@gmail.com","2048889999");
        Route testRoute = new Route(test1Flight);
        Booking firstBooking = new Booking(user,testRoute);

        assertEquals(user,firstBooking.getBooker());

    }

    //normal test to get the route booked
    @Test
    public void getRoute() {
        Location location1 = new Location("Toronto", "Canada", "YYZ");
        Location location2 = new Location("Vancouver", "Canada", "YVR");
        Flight test1Flight = new Flight(location1,location2,"2022-06-14","10:30","2022-06-15","4:30");
        User user = new User("Julie","smith", User.Gender.FEMALE,"jsmith","j&smith$","jmith@gmail.com","2048889999");
        Route testRoute = new Route(test1Flight);
        Booking firstBooking = new Booking(user,testRoute);

        assertEquals(testRoute,firstBooking.getRoute());
    }

    //checking for the errors when creating a booking
    @Test
    public void testErrors(){
        Location location1 = new Location("Toronto", "Canada", "YYZ");
        Location location2 = new Location("Vancouver", "Canada", "YVR");
        Flight test1Flight = new Flight(location1,location2,"2022-06-14","10:30","2022-06-15","4:30");
        User user = new User("Julie","smith", User.Gender.FEMALE,"jsmith","j&smith$","jmith@gmail.com","2048889999");
        Route testRoute = new Route(test1Flight);

        //when a null is passed instead of a user instance
        try{
            Booking firstBooking = new Booking(null,testRoute);
            fail("Expected a null pointer exception");
        }catch(NullPointerException unused){}
        //when null is passed instead of a route instance
        try{
            Booking firstBooking = new Booking(user,null);
            fail("Expected a null pointer exception");
        }catch(NullPointerException unused){}

    }
}