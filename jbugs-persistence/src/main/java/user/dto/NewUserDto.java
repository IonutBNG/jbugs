package user.dto;

import user.validation.Email;
import user.validation.Name;
import user.validation.MobileNumber;
import user.validation.Password;
import utils.BaseDto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Bungardean Tudor-Ionut
 * @since 19.1.2
 */
public class NewUserDto implements BaseDto {

    @Name
    private String firstName;

    @Name
    private String lastName;

    @MobileNumber
    private String mobileNumber;

    @Email
    private String email;

    @Password
    private String password;

    //TODO add RoleDTo
    //private RoleDto[] roles;

    public NewUserDto(String firstName, String lastName, String mobileNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.email = email;
    }

    public NewUserDto() {
    }

    public NewUserDto(String firstName, String lastName, String mobileNumber, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.password = password;
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

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
