package pers.dreamer07.dao;

import pers.dreamer07.bean.Key;

import java.util.List;

/*
* 定义操作 Key 表的规范
* */
public interface KeyDao {

    //根据id查考 Key
    public Key getKeyById(Integer id);

    //简单实现：根据 id 查询 Key
    public Key getKeyByIdSimple(Integer id);

    //根据 锁id 查询所有可以开这把锁的 key 所构成的集合
    public List<Key> getKeyListByLockId(Integer lockId);

}
