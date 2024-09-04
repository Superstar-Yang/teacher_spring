package com.example.service;

import cn.hutool.core.date.DateUtil;
import com.example.entity.College;
import com.example.mapper.CollegeMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 管理员业务处理
 **/
@Service
public class CollegeService {

    @Resource
    private CollegeMapper collegeMapper;

    /**
     * 新增
     */
    public void add(College college) {
        collegeMapper.insert(college);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        collegeMapper.deleteById(id);
    }

    /**
     * 修改
     */
    public void updateById(College college) {
        collegeMapper.updateById(college);
    }

    /**
     * 查询所有
     */
    public List<College> selectAll(College college) {
        return collegeMapper.selectAll(college);
    }

    /**
     * 分页查询
     */
    public PageInfo<College> selectPage(College college, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<College> list = collegeMapper.selectAll(college);
        return PageInfo.of(list);
    }

}
