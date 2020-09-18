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
    /**
     *
     * 功能描述: 测试一级缓存
     *
     * @param: []
     * @return: void
     * @auther: EMTKnight
     * @date: 2020/9/18
     */
    public void testOneCache(){
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            TeacherDao teacherDao = sqlSession.getMapper(TeacherDao.class);
            Teacher teacher = teacherDao.getTeacherById(1);
            System.out.println(teacher);
            System.out.println("=============");
            Teacher teacher1 = teacherDao.getTeacherById(1);
            System.out.println(teacher1);
            System.out.println(teacher == teacher1); //true
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    @Test
    /**
     *
     * 功能描述:
     *      一级缓存失效的情况1 - 开启两个 SqlSession 会话
     *          不同的 SqlSession，使用不同的一级缓存
     *          同一个 SqlSession 才可以访问刚刚缓存的数据
     *
     * @param: []
     * @return: void
     * @auther: EMTKnight
     * @date: 2020/9/16
     */
    public void testOneCacheInvalid1(){
        SqlSession sqlSession1 = null;
        SqlSession sqlSession2 = null;
        try {
            //sqlSession1 会话
            sqlSession1 = sqlSessionFactory.openSession();
            TeacherDao teacherDao1 = sqlSession1.getMapper(TeacherDao.class);
            Teacher teacher1 = teacherDao1.getTeacherById(1);
            System.out.println(teacher1);

            System.out.println("=============");

            //sqlSession2 会话
            sqlSession2 = sqlSessionFactory.openSession();
            TeacherDao teacherDao2 = sqlSession2.getMapper(TeacherDao.class);
            Teacher teacher2 = teacherDao2.getTeacherById(1);

            System.out.println(teacher1 == teacher2); //false
        } finally {
            if (sqlSession1 != null) {
                sqlSession1.close();
            }
            if (sqlSession2 != null) {
                sqlSession2.close();
            }
        }
    }

    @Test
    /**
     *
     * 功能描述:
     *      一级缓存失效的情况2 - 在同一个 SqlSession 中 调用同一个方法 使用不同参数
     * @param:
     * @return:
     * @auther: EMTKnight
     * @date: 2020/9/16
     */
    public void testOneCacheInvalid2(){
        SqlSession sqlSession1 = null;
        try {
            //sqlSession1 会话
            sqlSession1 = sqlSessionFactory.openSession();
            TeacherDao teacherDao1 = sqlSession1.getMapper(TeacherDao.class);

            Teacher teacher1 = teacherDao1.getTeacherById(1);
            System.out.println(teacher1);
            System.out.println("=============");
            Teacher teacher2 = teacherDao1.getTeacherById(2);
            System.out.println(teacher2);

            System.out.println(teacher1 == teacher2); //false
        } finally {
            if (sqlSession1 != null) {
                sqlSession1.close();
            }
        }
    }


   /**
    *
    * 功能描述:
    *       一级缓存失效的情况3 - 同一个 SqlSession 执行任意增删改操作时，都会清空一级缓存
    *
    * @param:
    * @return:
    * @auther: EMTKnight
    * @date: 2020/9/16
    */
    @Test
    public void testOneCacheInvalid3(){
        SqlSession sqlSession1 = null;
        try {
            //sqlSession1 会话
            sqlSession1 = sqlSessionFactory.openSession();
            TeacherDao teacherDao1 = sqlSession1.getMapper(TeacherDao.class);

            //查询id为1的数据
            Teacher teacher1 = teacherDao1.getTeacherById(1);
            System.out.println(teacher1);

            System.out.println();

            System.out.println("修改id为2的用户数据");
            Teacher updateT = new Teacher(2,"mio","贝斯",null,null);
            teacherDao1.updateTeacher(updateT);

            System.out.println();

            //查询id为2的数据
            Teacher teacher2 = teacherDao1.getTeacherById(1);
            System.out.println(teacher2);

            System.out.println(teacher1 == teacher2); //false
        } finally {
            if (sqlSession1 != null) {
                sqlSession1.close();
            }
        }
    }

    /**
     *
     * 功能描述:
     *      一级缓存失效的情况4 - 调用 sqlSession1.clearCache() 手动清理缓存
     *
     * @param:
     * @return:
     * @auther: EMTKnight
     * @date: 2020/9/16
     */
    @Test
    public void testOneCacheInvalid4(){
        SqlSession sqlSession1 = null;
        try {
            //sqlSession1 会话
            sqlSession1 = sqlSessionFactory.openSession();
            TeacherDao teacherDao1 = sqlSession1.getMapper(TeacherDao.class);

            //查询id为1的数据
            Teacher teacher1 = teacherDao1.getTeacherById(1);
            System.out.println(teacher1);
            System.out.println("========手动清理缓存");
            sqlSession1.clearCache();
            //查询id为2的数据
            Teacher teacher2 = teacherDao1.getTeacherById(1);
            System.out.println(teacher2);

            System.out.println(teacher1 == teacher2); //false
        } finally {
            if (sqlSession1 != null) {
                sqlSession1.close();
            }
        }
    }


}
