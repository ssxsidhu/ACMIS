package comp3350.acmis.business;


import org.threeten.bp.Duration;
import org.threeten.bp.ZonedDateTime;

import java.util.ArrayList;

import comp3350.acmis.objects.Flight;
import comp3350.acmis.objects.Location;
import comp3350.acmis.objects.Route;

public class UseRouteFlights {
    private final ArrayList<Flight> currRouteFlights;
    private Flight currConnectFlight;

    public UseRouteFlights(Route route) {
        currRouteFlights =  new ArrayList<>();
        route.getRoute(currRouteFlights);
        if(currRouteFlights.size()!=0)
            currConnectFlight = currRouteFlights.get(0);
    }

    public long calculateLayover(Flight flightArrival, Flight flightDeparture) {
        if (flightArrival != null && flightDeparture != null) {
            return Duration.between(flightArrival.getArrivalDateTime(), flightDeparture.getDepartureDateTime()).toMillis();
        } else {
            throw new NullPointerException();
        }
    }

    public String toStringDuration(long durationInMillis) {
        if (durationInMillis >= 0) {
            Duration duration = Duration.ofMillis(durationInMillis);
            long hours = duration.toHours();
            long minutes = duration.minusHours(hours).toMinutes();
            return hours + "h " + minutes + "m";
        } else {
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

    public String getCurrLayoverTime(int flightCurrIndex , int flightNextIndex) {
        long layover = 0;
        if(currRouteFlights.size() > flightNextIndex)
        layover = calculateLayover(currRouteFlights.get(flightCurrIndex), currRouteFlights.get(flightNextIndex));

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

    public int getNumStops() {
        return currRouteFlights.size() - 1;
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

    public ZonedDateTime getConnectDepartureZdt() {
        return currConnectFlight.getDepartureDateTime();
    }

    public ZonedDateTime getConnectArrivalZdt(){
        return currConnectFlight.getArrivalDateTime();
    }

    public String stopNames(){
        StringBuilder names = new StringBuilder();
        for (int i=1; i<currRouteFlights.size();i++){
            if(i!=1)
                names.append(", ");
            names.append(currRouteFlights.get(i).getSource().getAirport()).append(" (").append(toStringDuration(calculateLayover(currRouteFlights.get(i - 1), currRouteFlights.get(i)))).append(")");
        }
        return names.toString();
    }
}
