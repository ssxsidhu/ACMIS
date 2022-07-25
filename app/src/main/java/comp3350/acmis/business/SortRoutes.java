package comp3350.acmis.business;

import java.util.ArrayList;

import comp3350.acmis.objects.Route;

public class SortRoutes {

    public SortRoutes() {

    }

    public String lowestPrice(ArrayList<Route> routeList) {

        ArrayList<Route> duplicate = new ArrayList<>();
        duplicate.addAll(routeList);

        ArrayList<Route> newList = new ArrayList<>();
        Route curr;

        while (duplicate.size()!=1) {

            curr = duplicate.get(0);                                                        //Compare First Element of Every Iteration And Re Assign Accordingly.

            for (int i=0;i<duplicate.size();i++) {
            AccessRouteFlights compareThis = new AccessRouteFlights(duplicate.get(i));
            AccessRouteFlights currAccess = new AccessRouteFlights(curr);

            if(compareThis.getRouteTotalCost()<currAccess.getRouteTotalCost()) {
                    curr = duplicate.get(i);
                }
            }
            newList.add(curr);
        }

        routeList = newList;
        return null;
    }

    public String leastStops(ArrayList<Route> routeList) {

        ArrayList<Route> duplicate = new ArrayList<>();
        duplicate.addAll(routeList);

        ArrayList<Route> newList = new ArrayList<>();
        Route curr;

        while (duplicate.size()!=1) {

            curr = duplicate.get(0);                                                        //Compare First Element of Every Iteration And Re Assign Accordingly.

            for (int i=0;i<duplicate.size();i++) {
                AccessRouteFlights compareThis = new AccessRouteFlights(duplicate.get(i));
                AccessRouteFlights currAccess = new AccessRouteFlights(curr);

                if(compareThis.getNumStops()<currAccess.getNumStops()) {
                    curr = duplicate.get(i);
                }
            }
            newList.add(curr);
        }

        routeList = newList;
        return null;


    }

    public String earliestArrival(ArrayList<Route> routeList) {


        ArrayList<Route> duplicate = new ArrayList<>();
        duplicate.addAll(routeList);

        ArrayList<Route> newList = new ArrayList<>();
        Route curr;

        while (duplicate.size()!=1) {

            curr = duplicate.get(0);                                                        //Compare First Element of Every Iteration And Re Assign Accordingly.

            for (int i=0;i<duplicate.size();i++) {
                AccessRouteFlights compareThis = new AccessRouteFlights(duplicate.get(i));
                AccessRouteFlights currAccess = new AccessRouteFlights(curr);

                if(compareThis.getConnectArrivalZdt().isBefore(currAccess.getConnectArrivalZdt())) {
                    curr = duplicate.get(i);
                }
            }
            newList.add(curr);
        }
        routeList = newList;
        return null;
    }

    public String earliestDepart(ArrayList<Route> routeList) {


        ArrayList<Route> duplicate = new ArrayList<>();
        duplicate.addAll(routeList);

        ArrayList<Route> newList = new ArrayList<>();
        Route curr;

        while (duplicate.size()!=1) {

            curr = duplicate.get(0);                                                        //Compare First Element of Every Iteration And Re Assign Accordingly.

            for (int i=0;i<duplicate.size();i++) {
                AccessRouteFlights compareThis = new AccessRouteFlights(duplicate.get(i));
                AccessRouteFlights currAccess = new AccessRouteFlights(curr);

                if(compareThis.getConnectDepartureZdt().isBefore(currAccess.getConnectDepartureZdt())) {
                    curr = duplicate.get(i);
                }
            }
            newList.add(curr);
        }
        routeList = newList;
        return null;
    }
}
