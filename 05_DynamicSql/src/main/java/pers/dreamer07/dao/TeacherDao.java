package pers.dreamer07.dao;

import org.apache.ibatis.annotations.Param;
import pers.dreamer07.bean.Teacher;

import java.util.List;

/*
* 定义操作 Teacher 表规范
* */
public interface TeacherDao {

    //根据 id 查询对应的数据封装后的 Teacher 对象
    public Teacher getTeacherById(Integer id);

    //根据现有条件
    public List<Teacher> getTeacherListByCondition(Teacher teacher);

    //根据 id 集合，查询所有对应的教师信息
    public List<Teacher> getTeacherListByIdList(@Param("idList") List<Integer> idList);

    //根据现有条件，选择其中一个条件查找对应的数据
    public List<Teacher> getTeacherListByConditionChoose(Teacher teacher);

    //动态更新
    public int updateTeacher(Teacher teacher);
}
