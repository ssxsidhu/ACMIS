package comp3350.acmis.persistence;

import java.util.ArrayList;

import comp3350.acmis.objects.Booking;
import comp3350.acmis.objects.Flight;
import comp3350.acmis.objects.Location;
import comp3350.acmis.objects.User;

public interface DataAccess
{
	void open(String string);

	void close();


	String getAllFlights(ArrayList<Flight> resultList);


	String getLocations(ArrayList<Location> resultList);

	String addBooking(Booking newBooking);

	User getUserObject(String username);

	Flight getFlightObject(int flightNumber);

	Booking getBooking(int bookingId);

	String getUserBookings(User user, ArrayList<Booking> userBookings);

	Booking removeBooking(int bookingId);
}
