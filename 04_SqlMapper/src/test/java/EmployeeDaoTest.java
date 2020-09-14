import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import pers.dreamer07.bean.Employee;
import pers.dreamer07.dao.EmployeeDao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/*
* Mybatis 操作 Employee 数据表 - 测试类
* */
public class EmployeeDaoTest {

    private SqlSessionFactory sqlSessionFactory = null;

    @Before //作为Junit单元测试先行方法执行
    public void initSqlSessionFactory() throws IOException {
        /*
        1. 根据全局配置文件生成一个 SqlSessionFactory 类对象
            SqlSessionFactory - 负责创建 SqlSession 类对象的一个工厂
            SqlSession - sql会话，代表一次和数据库的连接
        * */
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void testSelect(){
        SqlSession sqlSession = null;
        try {
        /*
        1. 获取 sqlSession 会话对象
            - 传入一个布尔值，代表是否开始自动提交事务，默认为false，需要手动提交
         */
            sqlSession = sqlSessionFactory.openSession(true);
            //2. 获取对应的 Dao 实现类
            EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
            //3. 执行方法
            Employee employee = employeeDao.getEmpById(1);
            System.out.println(employee);
        } finally {
            //4. 关闭连接
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    @Test
    public void testSelect2(){
        SqlSession sqlSession = null;
        try {
        /*
        1. 获取 sqlSession 会话对象
            - 传入一个布尔值，代表是否开始自动提交事务，默认为false，需要手动提交
         */
            sqlSession = sqlSessionFactory.openSession(true);
            //2. 获取对应的 Dao 实现类
            EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
            //3. 执行方法
            Employee employee = employeeDao.getEmpByIdAndEmpName(1,"emt");
            System.out.println(employee);
        } finally {
            //4. 关闭连接
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    @Test //测试返回集为 List / Map 类型的
    public void testSelectColl(){
        SqlSession sqlSession = null;
        try {
        /*
        1. 获取 sqlSession 会话对象
            - 传入一个布尔值，代表是否开始自动提交事务，默认为false，需要手动提交
         */
            sqlSession = sqlSessionFactory.openSession(true);
            //2. 获取对应的 Dao 实现类
            EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
            //3. 执行方法

            //3.1 查询由一条 / 多条 记录封装的 List
//            List<Employee> emps = employeeDao.getEmps();
//            for (Employee emp : emps) {
//                System.out.println(emp);
//            }

            //3.2 查询由一条记录封装的 Map
//            Map<String, Object> empByIdReturnMap = employeeDao.getEmpByIdReturnMap(1);
//            System.out.println(empByIdReturnMap); //{gender=1, emp_name=emt, id=1, dept_id=20, email=emt@qq.com}

            //3.3 查询多条记录封装的 Map
            Map<Integer, Employee> allEmpReturnMap = employeeDao.getAllEmpReturnMap();
            allEmpReturnMap.keySet().stream().forEach(System.out::println);
            allEmpReturnMap.values().stream().forEach(System.out::println);
        } finally {
            //4. 关闭连接
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    @Test
    public void testInsert(){
        SqlSession sqlSession = null;
        try {
        /*
        1. 获取 sqlSession 会话对象
            - 传入一个布尔值，代表是否开始自动提交事务，默认为false，需要手动提交
         */
            sqlSession = sqlSessionFactory.openSession(true);
            //2. 获取对应的 Dao 实现类
            EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
            //3. 执行方法
            Employee employee1 = new Employee(null, "EMT!!", "emt@qq.com", 1);
            int i  = employeeDao.addEmp(employee1);
            System.out.println(employee1.getId()); //获取插入数据后自增id列的值
        } finally {
            //4. 关闭连接
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    @Test
    public void testInsert2(){
        SqlSession sqlSession = null;
        try {
        /*
        1. 获取 sqlSession 会话对象
            - 传入一个布尔值，代表是否开始自动提交事务，默认为false，需要手动提交
         */
            sqlSession = sqlSessionFactory.openSession(true);
            //2. 获取对应的 Dao 实现类
            EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
            //3. 执行方法
            Employee employee1 = new Employee(null, "EMT!!", "emt@qq.com", 1);
            int i  = employeeDao.addEmp2(employee1);
            System.out.println(employee1.getId()); //获取插入数据后自增id列的值
        } finally {
            //4. 关闭连接
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }



}
