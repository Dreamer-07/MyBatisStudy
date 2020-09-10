package pers.dreamer07.dao;

import pers.dreamer07.bean.Employee;

//定义操作 Employee 表的规范
public interface EmployeeDao {

    //根据id返回Emp对象
    public Employee getEmpById(Integer id);

    //添加 Emp 对象,返回 int影响行数
    public int addEmp(Employee employee);

    //根据id删除 Emp 对象
    public int deleteEmpById(Integer id);

    //修改Emp对象对应的数据库数据，为修改成功返回false(影响行数为0)，修改成功返回true(影响函数大于0)
    public boolean updateEmp(Employee employee);
}
