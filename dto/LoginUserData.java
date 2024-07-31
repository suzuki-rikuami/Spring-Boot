package jp.co.amiactive.dto;

public class LoginUserData {
    
    private String userId;
    
    private String password;
    
    private int highestAuthority;
    
    private String employeeCd;
    
    private String employeesFirstName;
    
    private String employeesLastName;
    
    public String getEmployeesFirstName() {
        return employeesFirstName;
    }

    public void setEmployeesFirstName(String employeesFirstName) {
        this.employeesFirstName = employeesFirstName;
    }

    public String getEmployeesLastName() {
        return employeesLastName;
    }

    public void setEmployeesLastName(String employeesLastName) {
        this.employeesLastName = employeesLastName;
    }

    public LoginUserData() {}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getHighestAuthority() {
        return highestAuthority;
    }

    public void setHighestAuthority(int highestAuthority) {
        this.highestAuthority = highestAuthority;
    }

    public String getEmployeeCd() {
        return employeeCd;
    }

    public void setEmployeeCd(String employeeCd) {
        this.employeeCd = employeeCd;
    }

}
