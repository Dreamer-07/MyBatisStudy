package pers.dreamer07.bean;

import java.sql.Date;

public class Teacher {
    private Integer id;
    private String name;
    private String course;
    private String address;
    private Date birth;

    public Teacher() {
    }

    public Teacher(Integer id, String name, String course, String address, Date birth) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.address = address;
        this.birth = birth;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", course='" + course + '\'' +
                ", address='" + address + '\'' +
                ", birth=" + birth +
                '}';
    }
}
