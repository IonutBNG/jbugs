package user.dto;

import user.validation.Email;
import user.validation.Name;
import user.validation.MobileNumber;
import utils.BaseDto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Bungardean Tudor-Ionut
 * @since 19.1.2
 */
public class NewUserDto implements BaseDto {

    @Name
    @NotNull
    @Size(min = 1, max = 35)
    private String firstName;

    @Name
    @NotNull
    @Size(min = 1, max = 35)
    private String lastName;

    @MobileNumber
    @NotNull
    private String mobileNumber;

    @Email
    @NotNull
    @Size(max = 100)
    private String email;

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
}
