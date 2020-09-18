import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import pers.dreamer07.bean.Teacher;
import pers.dreamer07.dao.TeacherDao;

import java.io.IOException;
import java.io.InputStream;

/**
 * 二级缓存的使用
 * @ClassName SecondCacheTest
 * @Author EMTKnight
 * @Date
 * @Version 1.0
 **/
public class SecondCacheTest {

    //创建一个会话工厂
    private SqlSessionFactory sqlSessionFactory = null;

    @Before //该方法会在所有 @Test 前执行
    public void initSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    /**
     *
     * 功能描述: 测试二级缓存
     *
     * @param: []
     * @return: void
     * @auther: EMTKnight
     * @date: 2020/9/18
     */
    public void testSecondCache(){
        // 开启两个 SqlSession 会话
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();

        //使用 sql1 进行数据库交互
        TeacherDao teacherDao1 = sqlSession1.getMapper(TeacherDao.class);
        Teacher teacher1 = teacherDao1.getTeacherById(1);
        System.out.println(teacher1);
        //关闭 sql1 会话
        sqlSession1.close();
        System.out.println("关闭 sql1 会话 ======");

        //使用 sql2 进行数据库交互
        TeacherDao teacherDao2 = sqlSession2.getMapper(TeacherDao.class);
        Teacher teacher2 = teacherDao2.getTeacherById(1);
        System.out.println(teacher2);

    }

}
