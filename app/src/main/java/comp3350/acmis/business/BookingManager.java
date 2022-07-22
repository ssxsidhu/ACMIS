//This method is used to created a booking for a user.
//It uses the user's name and a route instance to create a booking
//the booking is then added to the database

package comp3350.acmis.business;

import java.util.ArrayList;
import java.util.Objects;

import comp3350.acmis.application.Main;
import comp3350.acmis.application.Services;
import comp3350.acmis.objects.Booking;
import comp3350.acmis.objects.Flight;
import comp3350.acmis.objects.Location;
import comp3350.acmis.objects.Route;
import comp3350.acmis.objects.User;
import comp3350.acmis.persistence.DataAccess;

public class BookingManager {

    private final DataAccess data;

    // Constructor
    public BookingManager() {
        data = Services.getDataAccess(Main.dbName);
    }

    public BookingManager(String name) {
        data = Services.getDataAccess(name);
    }

    // Return List of Routes
    // travelling from one point to another there can be a multiple ways.
    // all the different routes are stored in a list and the list is returned.
    public String searchRoute(Location srcCity, Location destCity, ArrayList<Route> returnRoutes){

        returnRoutes.clear();
        ArrayList<Route> validRoutes = new ArrayList<>();


        ArrayList<Flight> allDBFlights = new ArrayList<>();
        data.getAllFlights(allDBFlights);
        ArrayList<Location> allDBLocations = new ArrayList<>();
        data.getLocations(allDBLocations);

        Route validFlights = new Route();
        // Check For Direct Routes.
        for (int i = 0; i < allDBFlights.size(); i++) {

            if(allDBFlights.get(i).getSource().getCity().equals(srcCity.getCity()) &&
               allDBFlights.get(i).getDestination().getCity().equals(destCity.getCity())) {
                {
                    validRoutes.add(new Route(allDBFlights.get(i)));
                }

            }
        }
//        Location winnipeg = new Location("Winnipeg", ZoneId.of("America/Winnipeg"), "Canada","YWG");
//        Location toronto = new Location("Toronto", ZoneId.of("America/Toronto"), "Canada","YYZ");
//        Location vancouver = new Location("Vancouver", ZoneId.of("America/Vancouver"), "Canada","YVR");
//
//        Flight winToTor = new Flight(winnipeg,toronto,  ZonedDateTime.of(2022,6,13,5,30,0,0,winnipeg.getZoneName()), 150, 2.6, 750);
//        Flight torToVan = new Flight(toronto,vancouver, ZonedDateTime.of(2022,6,14,13,15,0,0,toronto.getZoneName()), 300, 5.0, 1200);
//
//        Route route = new Route();
//        route.addToRoute(winToTor);
//        route.addToRoute(torToVan);
//
//        validRoutes.add(route);

        returnRoutes.addAll(validRoutes);
        if(validRoutes.size()>0){
            return null;
        }
        else{
            return "no_flights_found";
        }
    }       // validRoutes List should have stopOver FLights in the beginning and Direct Flights towards the end.




    //creating booking
    public String createBooking(String username, Route departRoute,Route returnRoute, int numPassengers) {
        User bookerObject = data.getUserObject(Objects.requireNonNull(username));
        Booking newBooking;
        if (bookerObject != null && departRoute != null) {

            if(returnRoute == null)
                newBooking = new Booking(bookerObject, departRoute,numPassengers,true);
            else
                newBooking = new Booking(bookerObject,departRoute,returnRoute,numPassengers,true);
            //adding the booking to the master booking.
            data.addBooking(newBooking);
        }
        else {
            throw new NullPointerException("object not found");
        }
        return null;
    }

    public String cancelBooking(int bookingId){
        return data.cancelBooking(bookingId);
    }

    //for testing purposes
    public DataAccess getData() {
        return data;
    }

}
