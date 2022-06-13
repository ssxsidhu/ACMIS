package comp3350.acmis.business;

import java.util.ArrayList;
import java.util.Date;

import comp3350.acmis.application.Main;
import comp3350.acmis.application.Services;
import comp3350.acmis.objects.Booking;
import comp3350.acmis.objects.Flight;
import comp3350.acmis.objects.User;
import comp3350.acmis.persistence.DataAccessStub;

public class BookingManager {

    private DataAccessStub data;

    public BookingManager() {
        data = Services.getDataAccess(Main.dbName);
    }

    public ArrayList<Flight> searchRoute(String srcCity, String destCity){
        //find route using an algo


        //has to return an arraylist unless the route dsnt exist
        return null;
    }



    //creating booking
    public void createBooking(User booker, ArrayList<Flight> route){
        Booking newBooking = new Booking(booker,route);

        //adding to the users all the booking.
        booker.addBooking(newBooking);

        //adding the booking to the master booking.
        //data.addBooking(newBooking);

    }

    public void cancelBooking(int bookingId){
        Booking temp = data.getBooking(bookingId);
        User canceller = temp.getBooker();
        canceller.removeBooking(bookingId);
    }

}
