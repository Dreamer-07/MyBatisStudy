import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import pers.dreamer07.bean.Cat;
import pers.dreamer07.dao.CatDao;

import java.io.IOException;
import java.io.InputStream;

/*
* Mybatis 自定义封装规则 - 测试类
* */
public class CatDaoTest {
    //创建一个会话工厂
    private SqlSessionFactory sqlSessionFactory = null;

    @Before //该方法会在所有 @Test 前执行
    public void initSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void testGetCatById(){
        SqlSession sqlSession = null; //获取一个 Sql 会话对象
        try {
            sqlSession = sqlSessionFactory.openSession();
            CatDao catDao = sqlSession.getMapper(CatDao.class);
            Cat cat = catDao.getCatById(1);
            System.out.println(cat); // Cat{id=1, name='阿梓喵', age=15, gender=1}
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

}
