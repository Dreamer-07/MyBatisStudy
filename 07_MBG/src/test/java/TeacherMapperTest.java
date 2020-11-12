import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import pers.dreamer07.bean.Teacher;
import pers.dreamer07.bean.TeacherExample;
import pers.dreamer07.dao.TeacherMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @program: MybatisCode
 * @description: 测试 TeacherMapper 接口
 * @author: EMTKnight
 * @create: 2020-11-12
 **/

public class TeacherMapperTest {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void initSqlSessionFactory(){
        InputStream inputStream = null;
        try {
            String resource = "mybatis-config.xml";
            inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 测试根据主键查询
     */
    @Test
    public void testSelectByPrimaryKey(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TeacherMapper teacherMapper = sqlSession.getMapper(TeacherMapper.class);
        Teacher teacher = teacherMapper.selectByPrimaryKey(1);
        System.out.println(teacher);
        sqlSession.close();
    }

    /**
     * 测试复杂查询
     */
    @Test
    public void testSelectByExample(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TeacherMapper teacherMapper = sqlSession.getMapper(TeacherMapper.class);
        //1.创建复杂查询对象
        TeacherExample teacherExample = new TeacherExample();
        //-. 设置按指定列名排序,可以在后面加上` desc`表示降序
        teacherExample.setOrderByClause("birth_date");
        //2.创建查询条件对象
        TeacherExample.Criteria criteria = teacherExample.createCriteria();
        /*-. 也可以设置多个 or 查询条件
        TeacherExample.Criteria criteria2 = teacherExample.createCriteria();
        teacherExample.or(criteria2)
        */
        //3.通过链式调用设置查询条件
        criteria.andClassNameIsNotNull().andTeacherNameIsNotNull();
        List<Teacher> teachers = teacherMapper.selectByExample(teacherExample);
        teachers.forEach(System.out::println);
        sqlSession.close();
    }

    @Test
    public void testInsertBatch(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true); //开启自动提交事务
        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
        List<Teacher> teachers = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            String teacherName = UUID.randomUUID().toString().substring(0, 5);
            String className = UUID.randomUUID().toString().substring(0, 5);
            teachers.add(new Teacher(teacherName,className));
        }
        mapper.insertBatch(teachers);
        sqlSession.close();
    }

    /**
     * 测试分页插件 - pageHelper
     */
    @Test
    public void testSelectPage(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
        //1. 开始分页,第一个参数为页号，从1开始，第二个参数为每页页数
        PageHelper.startPage(201,5);
        //2. 进行查询(仅生效一次)
        List<Teacher> teacherList = mapper.selectByExample(new TeacherExample());
        //3. 使用 pageInfo 封装查询的数据.第一个参数为数据集合，第二个参数为每页显示的页码数量
        PageInfo pageInfo = new PageInfo(teacherList,5);
        System.out.println("当前页码：" + pageInfo.getPageSize());
        System.out.println("总页码：" + pageInfo.getPages());
        System.out.println("当前数据集合：");
        pageInfo.getList().forEach(System.out::println);
        System.out.println("后一页：" + pageInfo.getNextPage());
        System.out.println("前一页：" + pageInfo.getPrePage());
        System.out.println("当前页需要显示的页码：" + Arrays.toString(pageInfo.getNavigatepageNums()));
        sqlSession.close();
    }


}
