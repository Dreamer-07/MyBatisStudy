import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import pers.dreamer07.bean.Key;
import pers.dreamer07.dao.KeyDao;

import java.io.IOException;
import java.io.InputStream;

/*
* MyBatis 联合查询
* */
public class KeyDaoTest {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void initSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test //使用级联属性封装联合查询后的结果
    public void test01(){
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            KeyDao keyDao = sqlSession.getMapper(KeyDao.class);
            Key key = keyDao.getKeyById(1);
            System.out.println(key); //Key{keyId=1, keyName='一号键', lock=Lock{lockId=1, lockName='一号锁'}}
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    @Test //分步查询
    public void test02(){
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            KeyDao keyDao = sqlSession.getMapper(KeyDao.class);
            Key key = keyDao.getKeyByIdSimple(1);
            System.out.println(key); //Key{keyId=1, keyName='一号键', lock=Lock{lockId=1, lockName='一号锁'}}
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    @Test // 延迟加载 + 按需加载
    public void test03() throws InterruptedException {
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            KeyDao keyDao = sqlSession.getMapper(KeyDao.class);
            Key key = keyDao.getKeyByIdSimple(1);
            System.out.println(key.getKeyName());
            Thread.sleep(3000);
            System.out.println(key.getKeyId());
            Thread.sleep(3000);
            System.out.println(key.getLock().getLockName());
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }



}
