package pers.dreamer07.bean;

/*
* 员工类
* */
//@Alias("Emp") //批量起别名
public class Employee {

    private Integer id;
    private String empName;
    private String email;
    private Integer gender; //1 代表男，0 代表女
    private Integer deptId; //测试 mapUnderscoreToCamelCase 自动映射属性

    public Employee() {
    }

    public Employee(Integer id, String empName, String email, Integer gender) {
        this.id = id;
        this.empName = empName;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", empName='" + empName + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", deptId=" + deptId +
                '}';
    }
}
