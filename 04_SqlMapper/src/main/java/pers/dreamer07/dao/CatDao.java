package pers.dreamer07.dao;

import pers.dreamer07.bean.Cat;

//定义操作 Cat 表的规范
public interface CatDao {

    //根据id查询 Cat
    public Cat getCatById(Integer id);

}
