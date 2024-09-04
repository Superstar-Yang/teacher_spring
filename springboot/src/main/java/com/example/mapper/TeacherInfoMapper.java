package com.example.mapper;

import com.example.entity.TeacherInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author lonelyEagle
 * @description 针对表【teacher_info(教师信息)】的数据库操作Mapper
 * @createDate 2024-07-30 13:54:43
 * @Entity com.example.entity.TeacherInfo
 */
@Mapper
public interface TeacherInfoMapper {

    void insert(TeacherInfo teacherInfo);

    void updateById(TeacherInfo teacherInfo);

    void deleteById(Integer id);

    List<TeacherInfo> selectAll(TeacherInfo teacherInfo);

    @Select("select * from teacher_info where username = #{username}")
    TeacherInfo selectByUsername(String username);

}




