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

/*
* Mybatis的CRUD - 测试类
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
        String resource = "mybatis_conf/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test //测试添加方法
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
            employeeDao.addEmp(new Employee(null,"EMT!!!","EMT@emt.com",1));
        } finally {
            //4. 关闭连接
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    @Test //测试删除方法
    public void testDelete(){
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
            int i = employeeDao.deleteEmpById(2);
            System.out.println("影响行数：" + i);
        } finally {
            //4. 关闭连接
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    @Test //测试修改方法
    public void testUpdate(){
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
            boolean b = employeeDao.updateEmp(new Employee(1, "EMT!", "emt@qq.com", null));
            if (b){
                System.out.println("修改成功");
            }else{
                System.out.println("修改失败");
            }
        } finally {
            //4. 关闭连接
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
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

}
