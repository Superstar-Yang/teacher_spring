package com.example.mapper;

import com.example.entity.Course;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 操作course相关数据接口
 */
public interface CourseMapper {

    /**
     * 新增
     */
    int insert(Course course);

    /**
     * 删除
     */
    @Delete("delete from course where id = #{id}")
    int deleteById(Integer id);

    /**
     * 修改
     */
    int updateById(Course course);

    List<Course> selectAll(Course course);

    @Select("select * from course where id=#{courseId}")
    Course selectById(Integer courseId);
}
