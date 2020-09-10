package pers.dreamer07.dao;

import pers.dreamer07.bean.Employee;

//定义操作 Employee 表的规范
public interface EmployeeDao {

    //根据id返回Emp对象
    public Employee getEmpById(Integer id);

}
