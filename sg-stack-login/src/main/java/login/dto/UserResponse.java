package login.dto;

public class UserResponse {

    private String userId;
    private String name;
    private boolean userType;
    private String email;
    private String mobileNo;

    public UserResponse(String userId, String name, boolean userType, String email, String mobileNo) {
        this.userId = userId;
        this.name = name;
        this.userType = userType;
        this.email = email;
        this.mobileNo = mobileNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UserResponse() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isUserType() {
        return userType;
    }

    public void setUserType(boolean userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
}
