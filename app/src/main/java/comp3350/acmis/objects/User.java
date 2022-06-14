package comp3350.acmis.objects;

import java.util.ArrayList;
import java.util.Objects;

public class User {

    private String firstName;
    private String lastName;
    private Gender gender;
    private String username;
    private String password;
    private String email;
    private long phoneNumber;

    //need this one for BOOKINGs
    private ArrayList<Booking> bookings;

    //constructor
    //I have made all the variables require non-null values
    // TODO: I have to ask prof If I can do that and test it using a different method as shown in testClass

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

    public void addBooking(Booking newBook){
        bookings.add(newBook);
    }

    public void removeBooking(int bookingID){
        Booking removal = bookings.get(bookingID);
        if(removal!= null){
            bookings.remove(removal);
        }
    }




    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public enum Gender{
        MALE,
        FEMALE
    }
}
