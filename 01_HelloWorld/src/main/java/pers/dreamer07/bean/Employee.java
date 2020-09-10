package pers.dreamer07.bean;

/*
* 员工类
* */
public class Employee {

    private Integer id;
    private String empName;
    private String eamil;
    private Integer gender; //1 代表男，0 代表女

    public Employee() {
    }

    public Employee(Integer id, String empName, String eamil, Integer gender) {
        this.id = id;
        this.empName = empName;
        this.eamil = eamil;
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEamil() {
        return eamil;
    }

    public void setEamil(String eamil) {
        this.eamil = eamil;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", empName='" + empName + '\'' +
                ", eamil='" + eamil + '\'' +
                ", gender=" + gender +
                '}';
    }
}
