package comp3350.acmis.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import comp3350.acmis.application.Main;
import comp3350.acmis.objects.Flight;
import comp3350.acmis.objects.Location;
import comp3350.acmis.objects.User;
import comp3350.acmis.objects.UserFlight;

public class DataAccessStub {
    private String dbName;
    private String dbType =  "stub";

    private ArrayList<User> allUsers;
    private ArrayList<Flight> allFlights;
    private ArrayList<Location> allLocations;//tp store city,country,airport and other rdata later
    private ArrayList<UserFlight> allUserFlights;

    public DataAccessStub(String dbName)
    {
        this.dbName = dbName;
    }

    public DataAccessStub() {
        this(Main.dbName);
    }

    public void open(String dbName){
        User user;
        Flight flight;

        allUsers = new ArrayList<User>();
        user = new User("Johnny","victor", User.Gender.MALE,"johnnyv","jOhnNNyVi12","johnny.victor@gmail.com","2045558999");
        allUsers.add(user);
        user = new User("Julie","smith", User.Gender.FEMALE,"jsmith","j&smith$","jmith@gmail.com","2048889999");
        allUsers.add(user);

        allLocations = new ArrayList<Location>();
        Location winnipeg = new Location("Winnipeg","Canada","YWG");
        allLocations.add(winnipeg);
        Location newYork = new Location("NewYork","USA","JFK");
        allLocations.add(newYork);
        Location toronto = new Location("Toronto","Canada","YYZ");
        allLocations.add(toronto);
        Location vancouver = new Location("Vancouver","Canada","YVR");
        allLocations.add(vancouver);

        allFlights =  new ArrayList<Flight>();
        flight = new Flight(winnipeg.getCity(),newYork.getCity(),2064.97,941);
        allFlights.add(flight);
        flight = new Flight(toronto.getCity(),vancouver.getCity(),3361,846);
        allFlights.add(flight);

        System.out.println("Opened " +dbType +" database " +dbName);
    }

    public void close(){
        System.out.println("Closed " +dbType +" database " +dbName);
    }

    public String getAllUsers(List<User> resultList){
        resultList.addAll(allUsers);
        return null;
    }

    public String insertUser(User newUser){
        allUsers.add(newUser);
        return null;
    }

    public String getAllFlights(List<Flight> resultList){
        resultList.addAll(allFlights);
        return null;
    }

    public String insertFlight(Flight newFlight){
        allFlights.add(newFlight);
        return null;
    }

    public String insertLocation(Location newLocation){
        allLocations.add(newLocation);
        return  null;
    }
    //returns the locations
    public String getLocations(List<Location> resultList){
        resultList.addAll(allLocations);
        return null;
    }

    //creates a relationship between user and flight and hence the flight is booked
    public String bookFlight(String username,int flightNumber ){
        User userObject = getUserObject(username);
        Flight flightObject = getFlightObject(flightNumber);
        if(userObject != null && flightNumber > 0) {
            UserFlight userFlight = new UserFlight(userObject,flightObject);
            allUserFlights.add(userFlight);
        }
        else{
            System.out.println("no object found");
        }
        return  null;
    }

    //to get object of User from username
    public User getUserObject(String username){
        User result = null;
        for(int i=0; i<allUsers.size(); i++){
            if(Objects.equals(allUsers.get(i).getUsername(), username)){
                result = allUsers.get(i);
            }
        }
        return  result;
    }

    //to get object of Flight from flightNumber
    public Flight getFlightObject(int flightNumber){
        Flight result = null;
        for(int i=0; i<allFlights.size(); i++){
            if(allFlights.get(i).getFlightSerial() == flightNumber){
                result = allFlights.get(i);
            }
        }
        return  result;
    }



    //puts all the flights booked by user into the list in argument and returns null
    public String getUserFlightList(String username, List<Flight> bookedFlights){
        for(int i = 0; i<allUserFlights.size(); i++){
            if(username.equals(allUserFlights.get(i).getUser().getUsername())){
                bookedFlights.add(allUserFlights.get(i).getFlight());
            }
        }
        return null;
    }








}
