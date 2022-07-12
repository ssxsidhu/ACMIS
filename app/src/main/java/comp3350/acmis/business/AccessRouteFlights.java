package comp3350.acmis.business;



import org.threeten.bp.Duration;
import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.Objects;

import comp3350.acmis.objects.Flight;
import comp3350.acmis.objects.Location;
import comp3350.acmis.objects.Route;

public class AccessRouteFlights  {
    private ArrayList<Flight> currRouteFlights;
    private Flight currConnectFlight;

    public AccessRouteFlights(Route route) {
        currRouteFlights = Objects.requireNonNull(route).getRoute();
        currConnectFlight = currRouteFlights.get(0);
    }

    public long calculateLayover(Flight flightArrival, Flight flightDeparture) {
        if (flightArrival != null && flightDeparture != null) {
            return Duration.between(flightArrival.getArrivalDateTime(), flightDeparture.getDepartureDateTime()).toMillis();
        }else{
            throw new NullPointerException();
        }
    }

    public String toStringDuration(long durationInMillis) {
        if(durationInMillis > 0){
            Duration duration = Duration.ofMillis(durationInMillis);
            long hours = duration.toHours();
            System.out.println(hours+"gasgahqjt");
            long minutes = duration.minusHours(hours).toMinutes();
            return hours + "h " + minutes + "m";
        }else{
            throw new IllegalArgumentException("duration cannot be negative");
        }

    }

    public void setConnectFlightPos(int pos) {
        currConnectFlight = currRouteFlights.get(pos);
    }

    public int getRouteTotalCost() {
        int cost = 0;
        for (int i = 0; i < currRouteFlights.size(); i++)
            cost += currRouteFlights.get(i).getCost();

        return cost;
    }

    public String getRouteTotalDuration() {
        long totalFlying = currRouteFlights.get(0).getDuration().toMillis();
        long totalLayover = 0;
        if (currRouteFlights.size() > 1) {
            for (int i = 1; i < currRouteFlights.size(); i++) {
                totalLayover += calculateLayover(currRouteFlights.get(i - 1), currRouteFlights.get(i));
                totalFlying += currRouteFlights.get(i).getDuration().toMillis();
            }
        }
        return toStringDuration(totalFlying + totalLayover);
    }

    public String getCurrLayoverTime() {
        int currentFlightPos = currRouteFlights.indexOf(currConnectFlight);
        long layover = 0;
        if (currRouteFlights.size()>currentFlightPos+1) {
            layover = calculateLayover(currConnectFlight, currRouteFlights.get(currentFlightPos + 1));
        }
        return toStringDuration(layover);
    }

    public Location getConnectSource() {
        return currConnectFlight.getSource();
    }
    public Location getConnectDestination() {
        return currConnectFlight.getDestination();
    }
    public int getConnectSeats() {
        return currConnectFlight.getSeats();
    }
    public int getNumStops(){
        return currRouteFlights.size()-1;
    }

    public String getConnectDuration() {
        return toStringDuration(currConnectFlight.getDuration().toMillis());
    }
    public String getConnectArrivalTime() {
        return currConnectFlight.getArrivalDateTime().toLocalTime().toString();
    }

    public String getConnectDepartureTime() {
        return currConnectFlight.getDepartureDateTime().toLocalTime().toString();
    }

    public LocalDate getConnectDepartureDate(){
         return currConnectFlight.getDepartureDateTime().toLocalDate();
    }
}
