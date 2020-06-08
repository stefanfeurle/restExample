package tsf.reservation.database.models;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String creationDate;
    private String creationTime;
    private String token = null;
    private boolean isLogin = false;

    public User(String firstName, String lastName, String email, String password, String phoneNumber, String creationDate, String creationTime, String token, boolean isLogin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.creationDate = creationDate;
        this.creationTime = creationTime;
        this.token = token;
        this.isLogin = isLogin;
    }

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getToken() {
        return token;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }
}
