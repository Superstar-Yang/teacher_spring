package com.example.mapper;

import com.example.entity.College;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 操作college相关数据接口
*/
public interface CollegeMapper {

    /**
      * 新增
    */
    int insert(College college);

    /**
      * 删除
    */
    @Delete("delete from college where id = #{id}")
    int deleteById(Integer id);

    /**
      * 修改
    */
    int updateById(College college);

    /**
      * 根据ID查询
    */
    @Select("select * from college where id = #{id}")
    College selectById(Integer id);

    /**
      * 查询所有
    */
    List<College> selectAll(College college);
}
