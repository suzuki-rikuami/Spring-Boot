package jp.co.amiactive.dto;

public class KKS05SC004_F_001Model {
    
    private String userId;
    
    private String password;
    
    private int highestAuthority;
    
    private String employeeCd;
    
    private int errorCode;

    // セッターメソッド
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHighestAuthority(int highestAuthority) {
        this.highestAuthority = highestAuthority;
    }

    public void setEmployeeCd(String employeeCd) {
        this.employeeCd = employeeCd;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    // ゲッターメソッド（必要に応じて）
    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public int getHighestAuthority() {
        return highestAuthority;
    }

    public String getEmployeeCd() {
        return employeeCd;
    }

    public int getErrorCode() {
        return errorCode;
    }
}