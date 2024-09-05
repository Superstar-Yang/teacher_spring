package com.example.mapper;

import com.example.entity.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper {

    void insert(Student student);

    void updateById(Student student);

    @Delete("delete from student where id=#{id}")
    void deleteById(Integer id);

    List<Student> selectAll(Student student);

    @Select("select * from student where username = #{username}")
    Student selectByUsername(String username);



    Student selectById(Integer id);
}
