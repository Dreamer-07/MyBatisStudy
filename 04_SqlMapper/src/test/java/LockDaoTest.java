import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import pers.dreamer07.bean.Lock;
import pers.dreamer07.dao.LockDao;

import java.io.IOException;
import java.io.InputStream;

public class LockDaoTest {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void initSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void test01(){
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            LockDao lockDao = sqlSession.getMapper(LockDao.class);

            Lock lock = lockDao.getLockById(1);
            System.out.println(lock);
            System.out.println();
            lock.getKeyList().stream().forEach(System.out::println);
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }
    @Test // 分步查询 + 按需加载
    public void test04(){
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            LockDao lockDao = sqlSession.getMapper(LockDao.class);
            Lock lock = lockDao.getLockByIdSimple2(1);
            System.out.println(lock.getLockName());
            Thread.sleep(3000);
            lock.getKeyList().stream().forEach(System.out::println);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }
}
