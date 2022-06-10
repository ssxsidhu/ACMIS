package comp3350.acmis.objects;

import java.util.Objects;

public class User {
    private String firstName, lastName, gender, username, password, email;
    private long phoneNumber;

    //constructor
    //I have made all the variables require non-null values
    // TODO: I have to ask prof If I can do that and test it using a different method as shown in testClass

    public User(String firstName, String lastName, String gender, String username, String password, String email, String phoneNumber) {

        this.firstName = Objects.requireNonNull(firstName, "First name cannot be null");
        this.lastName = Objects.requireNonNull(lastName, "Last name cannot be null");
        this.gender = Objects.requireNonNull(gender, "Gender cannot be null");
        this.username = Objects.requireNonNull(username, "username cannot be null");
        this.password = Objects.requireNonNull(password, "password cannot be null");
        this.email = Objects.requireNonNull(email, "email cannot be null");
        if(phoneNumber.length() < 10){
            throw new IllegalArgumentException("Number should contain 10 digits");
        }
        else{
            this.phoneNumber = Long.parseLong(Objects.requireNonNull(phoneNumber,"Phone number should not be null"));
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
}
