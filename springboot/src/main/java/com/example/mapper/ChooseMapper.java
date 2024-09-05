package com.example.mapper;

import com.example.entity.Choose;
import com.example.entity.Course;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 操作choose相关数据接口
 */
public interface ChooseMapper {

    /**
     * 新增
     */
    int insert(Choose choose);

    /**
     * 删除
     */
    @Delete("delete from choose where id = #{id}")
    int deleteById(Integer id);

    /**
     * 修改
     */
    int updateById(Choose choose);

    List<Choose> selectAll(Choose choose);

    @Select("select * from choose where course_id=#{courseId} and student_id=#{studentId} ")
    List<Choose> selectStudentAndCourse(@Param("courseId") Integer courseId, @Param("studentId") Integer studentId);


    @Select("select * from choose where  id = #{id}")
    Choose selectById(Integer id);
}
