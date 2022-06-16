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

    // SETTERS
    public void addBooking(Booking newBook) {
        if (newBook == null) {
            throw new NullPointerException();
        }
        bookings.add(newBook);
    }

    public boolean removeBooking(int bookingID) {
        boolean found = false;
        Booking removal = null;

        if (bookingID < 0) {
            throw new NegativeArraySizeException();
        }

        //find booking with matching ID
        for (int i = 0; i < bookings.size() && !found; i++) {
            removal = bookings.get(i);

            if (removal != null && removal.getBookingId() == bookingID) {
                bookings.remove(removal);
                found = true;
            }
        }
        return found;
    }


    // GETTERS
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public Gender getGender() {return gender;}
    public String getUsername() {return username;}

    public String getMyBookings(ArrayList<Booking> myBookings) {
        if (myBookings == null) {
            throw new NullPointerException();
        }
        myBookings.addAll(bookings);
        return null;
    }

    //used for iteration 1 testing only, used to see if add and remove booking methods are working
    public ArrayList<Booking> getBookingsTemporaryTest() {
        return bookings;
    }

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
