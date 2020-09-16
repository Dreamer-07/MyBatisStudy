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
import java.util.Arrays;
import java.util.List;

public class TeacherDaoTest {

    //创建一个会话工厂
    private SqlSessionFactory sqlSessionFactory = null;

    @Before //该方法会在所有 @Test 前执行
    public void initSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }


    @Test
    public void testGetTeacherById(){
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            TeacherDao teacherDao = sqlSession.getMapper(TeacherDao.class);
            Teacher teacher = teacherDao.getTeacherById(1);
            System.out.println(teacher);
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    @Test
    public void testGetTeacherListByCondition(){
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            TeacherDao teacherDao = sqlSession.getMapper(TeacherDao.class);
            Teacher teacher = new Teacher(
                    1,
                    "%e%", null,
                    null, null
            );
            List<Teacher> teacherList =
                    teacherDao.getTeacherListByCondition(teacher);

            teacherList.stream().forEach(System.out::println);
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    @Test
    public void testGetTeacherListByIdList(){
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            TeacherDao teacherDao = sqlSession.getMapper(TeacherDao.class);
            List<Teacher> teacherList = teacherDao.getTeacherListByIdList(Arrays.asList(1, 2, 3, 4, 5));
            teacherList.stream().forEach(System.out::println);
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    @Test
    public void testGetTeacherListByConditionChoose(){
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            TeacherDao teacherDao = sqlSession.getMapper(TeacherDao.class);
            Teacher teacher = new Teacher(
                    null,
                    "emt",null,
                    null,null
            );
            List<Teacher> teacherList = teacherDao.getTeacherListByConditionChoose(teacher);
            teacherList.stream().forEach(System.out::println);
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    @Test
    public void testUpdateTeacher(){
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession(true);
            TeacherDao teacherDao = sqlSession.getMapper(TeacherDao.class);
            Teacher teacher = new Teacher(
                    1,
                    "emt","ohhhhh",
                    null,null
            );
            int i = teacherDao.updateTeacher(teacher);
            System.out.println(i);
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }
}
