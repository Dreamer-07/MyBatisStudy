package pers.dreamer07.dao;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import pers.dreamer07.bean.Employee;

import java.util.List;
import java.util.Map;

//定义操作 Employee 表的规范
public interface EmployeeDao {

    //根据id返回Emp对象
    public Employee getEmpById(Integer id);

    //添加 Emp 对象,返回 int影响行数
    public int addEmp(Employee employee);

    //添加 Emp 对象，返回 int 影响行数
    public int addEmp2(Employee employee);

    //根据id删除 Emp 对象
    public int deleteEmpById(Integer id);

    //修改Emp对象对应的数据库数据，为修改成功返回false(影响行数为0)，修改成功返回true(影响函数大于0)
    public boolean updateEmp(Employee employee);

    //根据id和empName查询对应的员工信息
    public Employee getEmpByIdAndEmpName(@Param("id") Integer id,@Param("empName") String empName);

    //查询所有信息封装为 List
    public List<Employee> getEmps();

    //查询一条记录封装的 Map
    public Map<String,Object> getEmpByIdReturnMap(Integer id);

    //查询多条记录封装的 Map
    @MapKey("id") //需要通过 @MapKey 注解指定查询结果的字段名作为 返回结果集Map的key值
    public Map<Integer,Employee> getAllEmpReturnMap();
}
