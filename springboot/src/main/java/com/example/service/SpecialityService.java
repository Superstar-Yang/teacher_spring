package com.example.service;

import cn.hutool.core.date.DateUtil;
import com.example.entity.Speciality;
import com.example.mapper.SpecialityMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 管理员业务处理
 **/
@Service
public class SpecialityService {

    @Resource
    private SpecialityMapper specialityMapper;

    /**
     * 新增
     */
    public void add(Speciality speciality) {
        specialityMapper.insert(speciality);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        specialityMapper.deleteById(id);
    }

    /**
     * 修改
     */
    public void updateById(Speciality speciality) {
        specialityMapper.updateById(speciality);
    }

    /**
     * 查询所有
     */
    public List<Speciality> selectAll(Speciality speciality) {
        return specialityMapper.selectAll(speciality);
    }

    /**
     * 分页查询
     */
    public PageInfo<Speciality> selectPage(Speciality speciality, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Speciality> list = specialityMapper.selectAll(speciality);
        return PageInfo.of(list);
    }

}
