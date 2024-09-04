package com.example.controller;

import com.example.common.Result;
import com.example.entity.Account;
import com.example.service.AdminService;
import com.example.service.StudentService;
import com.example.service.TeacherService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


@RestController
public class WebController {

    @Resource
    private AdminService adminService;
    @Resource
    private TeacherService teacherService;
    @Resource
    private StudentService studentService;

    /**
     * 默认请求接口
     */
    @GetMapping("/")
    public Result hello() {
        return Result.success();
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public Result login(@RequestBody Account account) {
        Account ac = null;
        if ("ADMIN".equals(account.getRole())) {
            return Result.success(adminService.login(account));
        }
        if ("STUDENT".equals(account.getRole())) {
            return Result.success(studentService.login(account));
        }
        if ("TEACHER".equals(account.getRole())) {
            return Result.success(teacherService.login(account));
        }
        return Result.success(ac);
    }

    /**
     * 注册
     */
    @PostMapping("/register")
    public Result register(Account account) {
        studentService.register(account);
        return Result.success();
    }

    /**
     * 修改密码
     */
    @PutMapping("/updatePassword")
    public Result updatePassword(@RequestBody Account account) {
        if ("ADMIN".equals(account.getRole())) {
            adminService.updatePassword(account);
        }
        if ("STUDENT".equals(account.getRole())) {
            studentService.updatePassword(account);
        }
        if ("TEACHER".equals(account.getRole())) {
            teacherService.updatePassword(account);
        }
        return Result.success();
    }

}
