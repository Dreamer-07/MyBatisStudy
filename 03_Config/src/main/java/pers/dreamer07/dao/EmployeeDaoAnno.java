package pers.dreamer07.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import pers.dreamer07.bean.Employee;

/* 使用注解配置 SQL 映射 */
public interface EmployeeDaoAnno {

    //根据id返回Emp对象
    @Select("SELECT * FROM employee WHERE id = #{id}")
    public Employee getEmpById(Integer id);

    //添加 Emp 对象,返回 int影响行数
    @Insert("INSERT INTO employee(empname,email,gender) VALUES(#{empName},#{email},#{gender})")
    public int addEmp(Employee employee);

    //根据id删除 Emp 对象
    @Delete("DELETE FROM employee WHERE id = #{id}")
    public int deleteEmpById(Integer id);

    //修改Emp对象对应的数据库数据，为修改成功返回false(影响行数为0)，修改成功返回true(影响函数大于0)
    @Update("UPDATE employee SET empname = #{empName},email = #{email},gender = #{gender} WHERE id = #{id}")
    public boolean updateEmp(Employee employee);

}
