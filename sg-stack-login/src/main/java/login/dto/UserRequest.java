package login.dto;

public class UserRequest {
    private String name;
    private String password;
    private boolean userType;

    public UserRequest(String name, String password, boolean userType) {
        this.name = name;
        this.password = password;
        this.userType = userType;
    }

    public UserRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isUserType() {
        return userType;
    }

    public void setUserType(boolean userType) {
        this.userType = userType;
    }
}
