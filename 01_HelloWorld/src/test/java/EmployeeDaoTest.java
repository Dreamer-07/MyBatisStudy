import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import pers.dreamer07.bean.Employee;
import pers.dreamer07.dao.EmployeeDao;

import java.io.IOException;
import java.io.InputStream;

/*
* 测试
* */
public class EmployeeDaoTest {

    @Test
    public void testGetEmpById() throws IOException {
        /*
        1. 根据全局配置文件生成一个 SqlSessionFactory 类对象
            SqlSessionFactory - 负责创建 SqlSession 类对象的一个工厂
            SqlSession - sql会话，代表一次和数据库的连接
        * */
        String resource = "mybatis_conf/mybatis-config";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = null;
        try {
            /*
            * 2. 根据 SqlSessionFactory 创建 SqlSession sql会话对象
            *       - 相当于 getConnection
            * */
            sqlSession = sqlSessionFactory.openSession();

            //3. 使用 sqlSession 操作数据库，获取对应的 Dao 接口实现类
            EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);

            //4. 执行对应的方法
            System.out.println(employeeDao.getEmpById(1));
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }


    }

}
