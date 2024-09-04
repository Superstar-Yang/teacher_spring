package com.example.controller;

import com.example.common.Result;
import com.example.entity.Speciality;
import com.example.service.SpecialityService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 管理员前端操作接口
 **/
@RestController
@RequestMapping("/speciality")
public class SpecialityController {

    @Resource
    private SpecialityService specialityService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Speciality speciality) {
        specialityService.add(speciality);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        specialityService.deleteById(id);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Speciality speciality) {
        specialityService.updateById(speciality);
        return Result.success();
    }


    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Speciality speciality) {
        List<Speciality> list = specialityService.selectAll(speciality);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Speciality speciality,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "5") Integer pageSize) {
        PageInfo<Speciality> page = specialityService.selectPage(speciality, pageNum, pageSize);
        return Result.success(page);
    }

}
