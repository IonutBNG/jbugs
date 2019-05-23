package user.entity;

import role.entity.RoleEntity;
import utils.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Bungardean Tudor-Ionut
 * @since 19.1.2
 */
@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = UserEntity.GET_USER_BY_USERNAME, query = "Select user from UserEntity user where user.username = :" + UserEntity.USERNAME),
        @NamedQuery(name = UserEntity.CHECK_IF_EMAIL_EXISTS, query = "SELECT COUNT(u) FROM UserEntity u WHERE u.email = :"+ UserEntity.EMAIL),
        @NamedQuery(name = UserEntity.CHECK_IF_USERNAME_EXISTS, query = "SELECT COUNT(u) FROM UserEntity u WHERE u.username = :"+ UserEntity.USERNAME),
        @NamedQuery(name = UserEntity.EDIT_USER, query = "UPDATE UserEntity u SET" +
                " u.firstName=:" + UserEntity.FIRST_NAME +
                ", u.lastName=:" + UserEntity.LAST_NAME +
                ", u.mobileNumber=:" + UserEntity.MOBLE_NUMBER +
                ", u.email=:" + UserEntity.EMAIL +
                ", u.counter=:" + UserEntity.COUNTER +
                ", u.password=:" + UserEntity.PASSWORD +
                " WHERE u.username=:" + UserEntity.USERNAME),
        @NamedQuery(name = UserEntity.GET_ALL_USERS, query = "SELECT users from UserEntity users"),
        //@NamedQuery(name = UserEntity.MATCH_LOGIN_CREDENTIALS, query = "SELECT count()")
})
public class UserEntity extends BaseEntity<Long> {

    //Query names
    public static final String CHECK_IF_EMAIL_EXISTS = "UserEntity.checkIfEmailExists";
    public static final String CHECK_IF_USERNAME_EXISTS = "UserEntity.chekcIfUsernameExists";
    public static final String GET_USER_BY_USERNAME = "UserEntity.getUserByUsername";
    public static final String GET_ALL_USERS = "UserEntity.getAllUsers";
    public static final String EDIT_USER = "UserEntity.editUser";
    //public static final String MATCH_LOGIN_CREDENTIALS = "UserEntity.matchLoginCredentials";

    //Parameter names
    public static final String USERNAME = "username";
    public static final String EMAIL = "email";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String COUNTER = "counter";
    public static final String MOBLE_NUMBER = "mobileNumber";
    public static final String PASSWORD = "password";



    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "mobile_number", nullable = false)
    private String mobileNumber;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "counter", nullable = false)
    private int counter;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name="user_id", referencedColumnName = "id",nullable = false),
            inverseJoinColumns = @JoinColumn(name="role_id",referencedColumnName = "id",nullable = false)
    )
    private List<RoleEntity> roleEntityList = new ArrayList<>();

    public UserEntity() {
    }

    public UserEntity(String firstName, String lastName, String mobileNumber, String email, String username, String password, int counter, List<RoleEntity> roleEntityList) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.username = username;
        this.password = password;
        this.counter = counter;
        this.roleEntityList = roleEntityList;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public List<RoleEntity> getRoleEntityList() {
        return roleEntityList;
    }

    public void setRoleEntityList(List<RoleEntity> roleEntityList) {
        this.roleEntityList = roleEntityList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return counter == that.counter &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(mobileNumber, that.mobileNumber) &&
                Objects.equals(email, that.email) &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(roleEntityList, that.roleEntityList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, mobileNumber, email, username, password, counter, roleEntityList);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", counter=" + counter +
                ", roleEntityList=" + roleEntityList +
                '}';
    }
}
