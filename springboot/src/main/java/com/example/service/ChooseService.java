package com.example.service;

import cn.hutool.core.collection.CollectionUtil;
import com.example.entity.Choose;
import com.example.entity.Course;
import com.example.entity.Student;
import com.example.exception.CustomException;
import com.example.mapper.ChooseMapper;
import com.example.mapper.CourseMapper;
import com.example.mapper.StudentMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 选课业务处理
 **/
@Service
public class ChooseService {

    @Resource
    private ChooseMapper chooseMapper;
    @Resource
    private CourseMapper courseMapper;
    @Resource
    private StudentMapper studentMapper;

    /**
     * 新增
     */
    public void add(Course course) {
        if (course.getNum().equals(course.getAlreadyNum())) {
            throw new CustomException("该门课程满员了，请选择其他课程");
        }
        List<Choose> list = chooseMapper.selectStudentAndCourse(course.getId(), course.getStudentId());
        if (CollectionUtil.isNotEmpty(list)) {
            throw new CustomException("你已经选过这门课程了，给其他同学留个机会！");
        }
        Choose choose = new Choose();
        choose.setCourseId(course.getId());
        choose.setStudentId(course.getStudentId());
        choose.setTeacherId(course.getTeacherId());
        choose.setName(course.getName());
        chooseMapper.insert(choose);
        course.setAlreadyNum(course.getAlreadyNum() + 1);
        courseMapper.updateById(course);
        Student student = studentMapper.selectById(choose.getStudentId());
        student.setScore(student.getScore() + course.getScore());
        studentMapper.updateById(student);

    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        Choose choose = chooseMapper.selectById(id);
        chooseMapper.deleteById(id);
        Course course = courseMapper.selectById(choose.getCourseId());
        course.setAlreadyNum(course.getAlreadyNum() - 1);
        courseMapper.updateById(course);
        Student student = studentMapper.selectById(choose.getStudentId());
        if (student.getScore() != 0) {
            student.setScore(student.getScore() - course.getScore());
        } else {
            student.setScore(0);
        }
        studentMapper.updateById(student);


    }

    /**
     * 修改
     */
    public void updateById(Choose choose) {
        chooseMapper.updateById(choose);
    }

    /**
     * 查询所有
     */
    public List<Choose> selectAll(Choose choose) {
        return chooseMapper.selectAll(choose);
    }

    /**
     * 分页查询
     */
    public PageInfo<Choose> selectPage(Choose choose, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Choose> list = chooseMapper.selectAll(choose);
        return PageInfo.of(list);
    }

}
