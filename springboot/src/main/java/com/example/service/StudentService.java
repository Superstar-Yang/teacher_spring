package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.entity.Account;
import com.example.entity.Student;
import com.example.exception.CustomException;
import com.example.mapper.StudentMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Resource
    private StudentMapper studentMapper;
    public void insert(Student student) {
        Student dbStudent  = studentMapper.selectByUsername(student.getUsername());
        if(ObjectUtil.isNotNull(dbStudent)){
            throw new CustomException("用户名已存在");
        }
        if(ObjectUtil.isEmpty(student.getPassword())){
            student.setPassword("123456");
        }
        if(ObjectUtil.isEmpty(student.getName())){
            student.setName(student.getUsername());
        }
        student.setRole("STUDENT");
        student.setScore(0);
        studentMapper.insert(student);
    }

    public void updateById(Student student) {
        studentMapper.updateById(student);
    }

    public void deleteById(Integer id) {
        studentMapper.deleteById(id);
    }

    public List<Student> selectAll(Student student) {
        return studentMapper.selectAll(student);
    }

    public PageInfo<Student> selectPage(Student student,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Student> list = studentMapper.selectAll(student);
        return PageInfo.of(list);
    }

    public Student login(Account account) {
        Student dbStudent = studentMapper.selectByUsername(account.getUsername());
        if(ObjectUtil.isNull(dbStudent)){
            throw new CustomException("账号不存在");
        }
        if(!account.getPassword().equals(dbStudent.getPassword())){
            throw new CustomException("账号或密码错误");
        }
        return dbStudent;
    }

    public void updatePassword(Account account) {
        Student dbStudent = studentMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbStudent)) {
            throw new CustomException("用户不存在");
        }
        if (!account.getPassword().equals(dbStudent.getPassword())) {
            throw new CustomException("原密码错误");
        }
        dbStudent.setPassword(account.getNewPassword());
        studentMapper.updateById(dbStudent);
    }

    public void register(Account account) {
        Student student = new Student();
        student.setUsername(account.getUsername());
        student.setPassword(account.getPassword());
        insert(student);
    }

    public Student selectById(Integer id) {
        return studentMapper.selectById(id);
    }
}
