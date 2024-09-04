package com.example.controller;

import com.example.common.Result;
import com.example.entity.TeacherInfo;
import com.example.service.TeacherService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Resource
    private TeacherService teacherService;

    @PostMapping("/add")
    public Result add(@RequestBody TeacherInfo teacherInfo) {
        teacherService.insert(teacherInfo);
        return Result.success();
    }

    @PutMapping("/update")
    public Result updateById(@RequestBody TeacherInfo teacherInfo) {
        teacherService.updateById(teacherInfo);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        teacherService.deleteById(id);
        return Result.success();
    }


    @GetMapping("/selectAll")
    public Result selectAll(TeacherInfo teacherInfo) {
        List<TeacherInfo> list = teacherService.selectAll(teacherInfo);
        return Result.success(list);
    }

    @GetMapping("/selectPage")
    public Result selectPage(TeacherInfo teacherInfo,@RequestParam(defaultValue = "1") Integer pageNo,@RequestParam(defaultValue = "5") Integer pageSize) {
        PageInfo<TeacherInfo> list = teacherService.selectPage(pageNo,pageSize,teacherInfo);
        return Result.success(list);
    }
}
