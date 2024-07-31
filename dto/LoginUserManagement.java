package jp.co.amiactive.dto;

import javax.xml.crypto.Data;

public class LoginUserManagement {
    
    private String userId;
    
    private String password;
    
    private int highestAuthority;
    
    private int errorCode;

    private String employeeCd;
    
    private Data insertDate;
    
    private Data updateDate;
    
    private int deleteFlg;
    
    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
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

    public int getHighestAuthority() {
        return highestAuthority;
    }

    public void setHighestAuthority(int highestAuthority) {
        this.highestAuthority = highestAuthority;
    }

    public String getEmployeeCd() {
        return employeeCd;
    }

    public void setEmproyeeCd(String employeeCd) {
        this.employeeCd = employeeCd;
    }

    public Data getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Data insertDate) {
        this.insertDate = insertDate;
    }

    public Data getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Data updateDate) {
        this.updateDate = updateDate;
    }

    public int getDeleteFlg() {
        return deleteFlg;
    }

    public void setDeleteFlg(int deleteFlg) {
        this.deleteFlg = deleteFlg;
    }

    @Override
    public String toString() {
        return "KKS05SC004_F_001Model{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", highestAuthority='" + highestAuthority + '\'' +
                ", employeeCd='" + employeeCd + '\'' +
                ", errorCode=" + errorCode +
                '}';
    }

}
