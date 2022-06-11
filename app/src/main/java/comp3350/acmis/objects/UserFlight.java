package comp3350.acmis.objects;

public class UserFlight {
    private User user;
    private Flight flight;

    public UserFlight(User user, Flight flight) {
        this.user = user;
        this.flight = flight;
    }

    public User getUser() {
        return user;
    }

    public Flight getFlight() {
        return flight;
    }
}
