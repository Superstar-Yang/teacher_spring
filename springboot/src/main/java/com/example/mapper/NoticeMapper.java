package com.example.mapper;

import com.example.entity.Notice;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

/**
 * 操作notice相关数据接口
 */
public interface NoticeMapper {

    /**
     * 新增
     */
    int insert(Notice notice);

    /**
     * 删除
     */
    @Delete("delete from notice where id = #{id}")
    int deleteById(Integer id);

    /**
     * 修改
     */
    int updateById(Notice notice);

    List<Notice> selectAll(Notice notice);
}
