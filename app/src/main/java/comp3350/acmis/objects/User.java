package comp3350.acmis.objects;



import java.util.ArrayList;
import java.util.Objects;

public class User {

    // Instance Variables
    private final String firstName;
    private final String lastName;
    private final Gender gender;
    private final String username;
    private String password;
    private String email;
    private long phoneNumber;

    //need this one for BOOKINGs
    private ArrayList<Booking> bookings;

    //constructor
    public User(String firstName, String lastName, Gender whichGender, String username, String password, String email, String phoneNumber) {

        this.firstName = Objects.requireNonNull(firstName, "First name cannot be null");
        this.lastName = Objects.requireNonNull(lastName, "Last name cannot be null");
        this.gender = Objects.requireNonNull(whichGender, "Gender cannot be null");
        this.username = Objects.requireNonNull(username, "username cannot be null");
        this.password = Objects.requireNonNull(password, "password cannot be null");
        this.email = Objects.requireNonNull(email, "email cannot be null");
        if(phoneNumber.length() < 10){
            throw new IllegalArgumentException("Number should contain 10 digits");
        }
        else{
            this.phoneNumber = Long.parseLong(Objects.requireNonNull(phoneNumber,"Phone number should not be null"));
        }
        bookings = new ArrayList<>();
    }

    // SETTERS
    public void addBooking(Booking newBook){
        bookings.add(newBook);
    }
    public void removeBooking(int bookingID){
        Booking removal = bookings.get(bookingID);
        if(removal!= null){
            bookings.remove(removal);
        }
    }

    // GETTERS

    public String getFirstName() {return firstName;}
    public String getUsername() {return username;}

    public String getMyBookings(ArrayList<Booking> myBookings){
        myBookings.addAll(bookings);
        return null;
    }

    public enum Gender{
        MALE,
        FEMALE
    }

}
