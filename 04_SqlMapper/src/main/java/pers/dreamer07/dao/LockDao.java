package pers.dreamer07.dao;

import pers.dreamer07.bean.Lock;

public interface LockDao {

    //根据 LockId 返回所有可以开这把锁的 Key 所构成的集合
    public Lock getLockById(int Id);

    //简单实现 - 根据 id 查询 Lock
    public Lock getLockByIdSimple(int id);

    //collection 标签完成分步查询
    public Lock getLockByIdSimple2(int id);
}
