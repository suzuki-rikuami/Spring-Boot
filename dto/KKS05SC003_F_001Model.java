package jp.co.amiactive.dto;

public class KKS05SC003_F_001Model {

    private String employeeCd;
    
    private String userId;
    
    private String password;
    
    private String confirmPassword;
    
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    private int errorCode;

    public String getEmployeeCd() {
        return employeeCd;
    }

    public void setEmployeeCd(String employeeCd) {
        this.employeeCd = employeeCd;
    }

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

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}