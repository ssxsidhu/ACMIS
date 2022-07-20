//User class holds the information a person who created an account in the application
// Name, gender , password , email and phone number.

package comp3350.acmis.objects;

import java.util.Objects;

public class User {

    // INSTANCE VARIABLES
    private final int userID;
    private final String firstName;
    private final String lastName;
    private final Gender gender;
    private final String username;
    private final String password;
    private final String email;
    private final long phoneNumber;

    private static int userSequence = 0;

    // CONSTRUCTOR
    public User(String firstName, String lastName, Gender whichGender, String username, String password, String email, String phoneNumber) {
        this.firstName = errorCheck(firstName, "First name");
        this.lastName = errorCheck(lastName, "Last name");
        this.gender = Objects.requireNonNull(whichGender, "Gender cannot be null");
        this.username = errorCheck(username, "Username");
        this.password = Objects.requireNonNull(password, "password cannot be null");

        // CHECK FOR VALID CHARACTER @. EVERY EMAIL ADDRESS HAS @
        if (email.contains("@")) {
            this.email = Objects.requireNonNull(email, "email cannot be null");
        } else {
            throw new IllegalArgumentException("Email must contain the character: @ ");
        }

        // CHECK FOR VALID PHONE NUMBER. EVERY PHONE NUMBER AS EXACTLY 10 DIGITS
        if (phoneNumber.length() != 10) {
            throw new IllegalArgumentException("Phone number should contain 10 digits");
        } else {
            this.phoneNumber = Long.parseLong(Objects.requireNonNull(phoneNumber, "Phone number should not be null"));
        }

        this.userID = userSequence;
        userSequence++;
    }

    public User(int userID, String firstName, String lastName, Gender whichGender, String username, String password, String email, String phoneNumber) {
        this.firstName = errorCheck(firstName, "First name");
        this.lastName = errorCheck(lastName, "Last name");
        this.gender = Objects.requireNonNull(whichGender, "Gender cannot be null");
        this.username = errorCheck(username, "Username");

        this.password = Objects.requireNonNull(password, "password cannot be null");
        this.email = Objects.requireNonNull(email, "email cannot be null");

        if (phoneNumber.length() != 10) {
            throw new IllegalArgumentException("Phone number should contain 10 digits");
        } else {
            this.phoneNumber = Long.parseLong(Objects.requireNonNull(phoneNumber, "Phone number should not be null"));
        }

        this.userID = userID;
        userSequence++;
    }

    // GETTERS
    public int getUserID() {
        return userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public String getUsername() {
        return username;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return phoneNumber == user.phoneNumber && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && gender == user.gender && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, gender, username, password, email, phoneNumber);
    }

    // PRIVATE HELPER METHOD
    // Helps Check for valid Input In Constructor when creating a User
    private String errorCheck(String value, String message) {
        if (value.trim().equals("")) {
            throw new IllegalArgumentException(message + " can not be empty");
        }
        return Objects.requireNonNull(value, message + " cannot be null").trim();
    }

    // ENCAPSULATE GENDER IN ENUM CLASS
    public enum Gender {
        MALE,
        FEMALE,
        OTHER
    }
}
