package com.example.controller;

import com.example.common.Result;
import com.example.entity.Choose;
import com.example.entity.Course;
import com.example.service.ChooseService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 管理员前端操作接口
 **/
@RestController
@RequestMapping("/choose")
public class ChooseController {

    @Resource
    private ChooseService chooseService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Course course) {
        chooseService.add(course);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        chooseService.deleteById(id);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Choose choose) {
        chooseService.updateById(choose);
        return Result.success();
    }


    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Choose choose) {
        List<Choose> list = chooseService.selectAll(choose);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Choose choose,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "5") Integer pageSize) {
        PageInfo<Choose> page = chooseService.selectPage(choose, pageNum, pageSize);
        return Result.success(page);
    }

}
