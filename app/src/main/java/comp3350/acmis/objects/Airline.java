package comp3350.acmis.objects;

/*
-This is one of the target groups that will be using the application
-This class stores all the information about a specific airline that has created
an account in the application.
-An instance of this object will have the following details.
 Name, # of flights , # of airplanes, crew members, budget bound

*/

public class Airline {

    private String AirlineName;
    // flights
    // airplanes
    private int Crew;
    private int budget;

    //constructor
    public Airline(String airlineName, int crew, int budget) {
        AirlineName = airlineName;
        Crew = crew;
        this.budget = budget;
    }

    //----------------------------------------------------------------------------
    //what are the possible tests to run in the airline class.
    //unit tests are divided into the structural and the functional tests

    // 1. Test if the airline instance is created with the name, crew and its budget
    // 2. Test if the airline is able to store a list of airplanes and a list of flights
    // 3. Test if the airline is able to add new flights into the dub database
    // 4. Test if the airline able to add a new airplane if there is enough budget to buy it
    // 5. Test if the airline has airplanes / crew to create a new flight from one place to another
    // 6. Test if the airline is able to remove flight from the dub database.
    // 7. Test if the airline is able to manage the flight details or make changes.
    // 8. Test for the changes in the airline information such as the name/ budget / crew



    //----------------------------------------------------------------------------
    //GETTERS AND SETTERS
    public String getAirlineName() {
        return AirlineName;
    }
    public void setAirlineName(String airlineName) {
        AirlineName = airlineName;
    }
    public int getCrew() {
        return Crew;
    }
    public void setCrew(int crew) {
        Crew = crew;
    }
    public int getbudget() {
        return budget;
    }
    public void setbudget(int budget) {
        this.budget = budget;
    }
}
