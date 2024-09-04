package com.example.mapper;

import com.example.entity.Speciality;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

/**
 * 操作speciality相关数据接口
 */
public interface SpecialityMapper {

    /**
     * 新增
     */
    int insert(Speciality speciality);

    /**
     * 删除
     */
    @Delete("delete from speciality where id = #{id}")
    int deleteById(Integer id);

    /**
     * 修改
     */
    int updateById(Speciality speciality);

    List<Speciality> selectAll(Speciality speciality);
}
