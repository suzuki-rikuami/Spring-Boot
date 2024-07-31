package jp.co.amiactive.dto;

public class T_PERSONNEL_INFO {
    
    private String employeeCd;
    
    private String employees_first_name;
    
    public String getEmployeeCd() {
        return employeeCd;
    }

    public void setEmployeeCd(String employeeCd) {
        this.employeeCd = employeeCd;
    }

    public String getEmployees_first_name() {
        return employees_first_name;
    }

    public void setEmployees_first_name(String employees_first_name) {
        this.employees_first_name = employees_first_name;
    }

    public String getEmployees_last_name() {
        return employees_last_name;
    }

    public void setEmployees_last_name(String employees_last_name) {
        this.employees_last_name = employees_last_name;
    }

    private String employees_last_name;

}
