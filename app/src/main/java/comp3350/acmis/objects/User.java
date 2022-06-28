package comp3350.acmis.objects;

import java.util.ArrayList;
import java.util.Objects;

public class User {

    // Instance Variables
    private final String firstName;
    private final String lastName;
    private final Gender gender;
    private final String username;
    private int userID;
    private String password;
    private String email;
    private long phoneNumber;

    //need this one for BOOKINGS
    private ArrayList<Booking> bookings;

    //constructor
    public User(String firstName, String lastName, Gender whichGender, String username, String password, String email, String phoneNumber) {
        this.firstName = errorCheck(firstName, "First name");
        this.lastName = errorCheck(lastName, "Last name");
        this.gender = Objects.requireNonNull(whichGender, "Gender cannot be null");
        this.username = errorCheck(username, "Username");

        this.password = Objects.requireNonNull(password, "password cannot be null");
        this.email = Objects.requireNonNull(email, "email cannot be null");

        if(phoneNumber.length() != 10){
            throw new IllegalArgumentException("Phone number should contain 10 digits");
        }

        else{
            this.phoneNumber = Long.parseLong(Objects.requireNonNull(phoneNumber,"Phone number should not be null"));
        }
        bookings = new ArrayList<Booking>();
    }

    // GETTERS
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public Gender getGender() {return gender;}
    public String getUsername() {return username;}

    //Error checking in constructor
    private String errorCheck(String value, String message) {
        if (value.trim().equals("")){
            throw new IllegalArgumentException(message + " can not be empty");
        }
        return Objects.requireNonNull(value, message + " cannot be null").trim();
    }

    public enum Gender {
        MALE,
        FEMALE,
        OTHER
    }

}
