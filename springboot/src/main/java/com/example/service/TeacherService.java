package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.entity.Account;
import com.example.entity.TeacherInfo;
import com.example.exception.CustomException;
import com.example.mapper.TeacherInfoMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    @Resource
    private TeacherInfoMapper teacherInfoMapper;

    public void insert(TeacherInfo teacherInfo) {
        TeacherInfo dbTeacher = teacherInfoMapper.selectByUsername(teacherInfo.getUsername());
        if(ObjectUtil.isNotEmpty(dbTeacher)){
            throw new CustomException("用户名已存在");
        }
        if(ObjectUtil.isNull(teacherInfo.getPassword())){
            teacherInfo.setPassword("123456");
        }
        if(ObjectUtil.isNull(teacherInfo.getSex())){
            teacherInfo.setSex("男");
        }
        if(ObjectUtil.isNull(teacherInfo.getTitle())){
            teacherInfo.setTitle("教师");
        }
        teacherInfo.setRole("TEACHER");
        teacherInfoMapper.insert(teacherInfo);
    }

    public void updateById(TeacherInfo teacherInfo) {
        teacherInfoMapper.updateById(teacherInfo);
    }

    public void deleteById(Integer id) {
        teacherInfoMapper.deleteById(id);
    }

    public List<TeacherInfo> selectAll(TeacherInfo teacherInfo) {
        return teacherInfoMapper.selectAll(teacherInfo);
    }

    public PageInfo<TeacherInfo> selectPage(Integer pageNo, Integer pageSize,TeacherInfo teacherInfo) {
        PageHelper.startPage(pageNo, pageSize);
        List<TeacherInfo> list = teacherInfoMapper.selectAll(teacherInfo);
        return PageInfo.of(list);
    }

    public TeacherInfo login(Account account) {
        TeacherInfo dbTeacherInfo = teacherInfoMapper.selectByUsername(account.getUsername());
        if(ObjectUtil.isNull(dbTeacherInfo)){
            throw new CustomException("用户名不存在");
        }
        if(!account.getPassword().equals(dbTeacherInfo.getPassword())){
            throw new CustomException("账号或密码错误");
        }
        return dbTeacherInfo;
    }



    public void updatePassword(Account account) {
        TeacherInfo dbTeacherInfo = teacherInfoMapper.selectByUsername(account.getUsername());
        if(ObjectUtil.isNull(dbTeacherInfo)){
            throw new CustomException("用户名不存在");
        }
        if(!account.getPassword().equals(dbTeacherInfo.getPassword())){
            throw new CustomException("原密码错误");
        }
        dbTeacherInfo.setPassword(account.getPassword());
        teacherInfoMapper.updateById(dbTeacherInfo);
    }
}
