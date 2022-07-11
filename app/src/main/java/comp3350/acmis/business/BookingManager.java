//

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

    private DataAccess data;

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
    public String searchRoute(Location srcCity, Location destCity, ArrayList<Route> returnRoutes, int numPassengers){

        returnRoutes.clear();
        ArrayList<Route> validRoutes = new ArrayList<>();


        ArrayList<Flight> allDBFlights = new ArrayList<>();
        data.getAllFlights(allDBFlights);
        ArrayList<Location> allDBLocations = new ArrayList<>();
        data.getLocations(allDBLocations);

        Route validFlights = new Route();

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
                if(allDBFlights.get(i).enoughSeats(numPassengers)){
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
    public String createBooking(String username, Route route, int numPassengers) {
        User bookerObject = data.getUserObject(Objects.requireNonNull(username));
        ArrayList<Booking> userBookings = new ArrayList<>();
        Booking newBooking;

        //get the user's all the bookings
        //check if the flight is already booked
        //add a booking only if the flight is not booked already
        if (bookerObject != null && route != null) {
            data.getUserBookings(bookerObject,userBookings);
            for (int i = 0; i < userBookings.size(); i++) {
                if (route.getRoute().get(0).getFlightId() == userBookings.get(i).getRoute().getRoute().get(0).getFlightId()) {
                    return "You have already booked this flight for your account";
                }
            }
            newBooking = new Booking(bookerObject, route,numPassengers);
            //adding the booking to the master booking.
            data.addBooking(newBooking);
        }
        else {
            throw new NullPointerException("object not found");
        }
        return null;
    }




}
