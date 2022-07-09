package comp3350.acmis.business;

import java.util.ArrayList;

import comp3350.acmis.application.Main;
import comp3350.acmis.application.Services;
import comp3350.acmis.objects.Booking;
import comp3350.acmis.objects.Flight;
import comp3350.acmis.objects.Location;
import comp3350.acmis.objects.Route;
import comp3350.acmis.objects.User;
import comp3350.acmis.persistence.DataAccess;
import comp3350.acmis.persistence.DataAccessStub;

public class BookingManager {

    private DataAccess data;

    // Constructor
    public BookingManager() {
        data = Services.getDataAccess(Main.dbName);
    }

    public BookingManager(String name) {
        data = Services.getDataAccess(name);
    }

    // Return List of Routes
    public String searchRoute(Location srcCity, Location destCity, ArrayList<Route> returnRoutes){

        returnRoutes.clear();
        ArrayList<Route> validRoutes = new ArrayList<>();

        //find route using an algo
        ArrayList<Flight> allDBFlights = new ArrayList<>();
        data.getAllFlights(allDBFlights);
        ArrayList<Location> allDBLocations = new ArrayList<>();
        data.getLocations(allDBLocations);

        Route validFLights = new Route();

        // This is meant for ITERATION 1 ONLY. For Further Iterations a general case solution will be applied to route searching problems. For now 1 stopOver routes are being processed.
        ArrayList <Flight> tempSrc = new ArrayList<>();
        ArrayList <Flight> tempDest = new ArrayList<>();
        ArrayList <Flight> stopOver = new ArrayList<>();

        // Check for StopOvers and Direct Routes. But First Populate both lists.
        for (int i = 0; i < allDBFlights.size(); i++) {
            // Fill up both lists. At the moment tempSrc has all Flights that begin at srcCity and tempDest has all flights that end at destCity
            if(allDBFlights.get(i).getDestination().getCity().equals(destCity.getCity()))   {tempDest.add(allDBFlights.get(i));}
            if(allDBFlights.get(i).getSource().getCity().equals(srcCity.getCity()))   {tempSrc.add(allDBFlights.get(i));}
        }

        // Check for StopOvers.
        for (int i = 0; i < tempSrc.size(); i++) {
            for (int j = 0; j < tempDest.size(); j++) {

                // Get Flights with Stop Overs.
                if(tempSrc.get(i).getDestination().getCity().equals(tempDest.get(j).getSource().getCity())) {
                    stopOver.add(tempSrc.get(i));
                    stopOver.add(tempDest.get(j));

//                    validRoutes.add(new Route(stopOver));
                    stopOver.clear();
                }
            }
        }

        // Check For Direct Routes.
        for (int i = 0; i < allDBFlights.size(); i++) {

            if(allDBFlights.get(i).getSource().getCity().equals(srcCity.getCity()) &&
               allDBFlights.get(i).getDestination().getCity().equals(destCity.getCity())) {
                    validRoutes.add(new Route(allDBFlights.get(i)));
            }
        }

        returnRoutes.addAll(validRoutes);
        if(validRoutes.size()>0){
            return null;
        }
        else{
            return "no_flights_found";
        }
    }       // validRoutes List should have stopOver FLights in the beginning and Direct Flights towards the end.




    //creating booking
    public String createBooking(String username, Route route) {
        User bookerObject = data.getUserObject(username);
        ArrayList<Booking> userBookings = new ArrayList<>();
        Booking newBooking;

        if (bookerObject != null && route != null) {
            data.getUserBookings(bookerObject,userBookings);
            for (int i = 0; i < userBookings.size(); i++) {
                if (route.getRoute().get(0).getFlightID() == userBookings.get(i).getRoute().getRoute().get(0).getFlightID()) {
                    return "You have already booked this flight for your account";
                }
            }

            newBooking = new Booking(bookerObject, route);
            //adding the booking to the master booking.
            data.addBooking(newBooking);
        }
        else {
            return "no object found";
        }
        return null;
    }



    public String cancelBooking(int bookingId) {
        Booking result = data.removeBooking(bookingId);
        if(result==null){
            return "Booking not found";
        }
        else{
            return null;
        }
    }

    public void editBooking(int bookingId){
        //look for the booking in the master database.
    }


}
