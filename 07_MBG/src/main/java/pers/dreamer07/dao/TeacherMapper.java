package pers.dreamer07.dao;

import org.apache.ibatis.annotations.Param;
import pers.dreamer07.bean.Teacher;
import pers.dreamer07.bean.TeacherExample;

import java.util.List;

public interface TeacherMapper {

    //批量插入
    void insertBatch(@Param("teachers") List<Teacher> teachers);

    long countByExample(TeacherExample example);

    int deleteByExample(TeacherExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    List<Teacher> selectByExample(TeacherExample example);

    Teacher selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Teacher record, @Param("example") TeacherExample example);

    int updateByExample(@Param("record") Teacher record, @Param("example") TeacherExample example);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);
}