package user.dto;

import user.validation.Email;
import user.validation.MobileNumber;
import user.validation.Name;
import user.validation.Username;

public class ViewUserDto {

    @Name
    private String firstName;

    @Name
    private String lastName;

    @Email
    private String email;

    @MobileNumber
    private String mobileNumber;

    @Username
    private String username;


    private int counter;

    public ViewUserDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

}
