package com.example.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Result;
import com.example.springboot.entity.Menu;
import com.example.springboot.service.IMenuService;
import com.example.springboot.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private IMenuService imenuService;
    @Resource
    private MenuService menuService;

//    @GetMapping
//    public Result findAll(@RequestParam(defaultValue = "") String name) {
//        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
//        queryWrapper.like("name", name);
//        List<Menu> list = imenuService.list(queryWrapper);
//        List<Menu> parentNode = list.stream().filter(menu -> menu.getPid() == null).collect(Collectors.toList());
//        for (Menu menu: parentNode) {
//            menu.setChildren(list.stream().filter(m -> menu.getId().equals(m.getPid())).collect(Collectors.toList()));
//        }
//        //List<Menu> all = menuService.findAll();
//        return Result.success(parentNode);
//    }
    @GetMapping
    public Result findAll(@RequestParam(defaultValue = "") String name) {
        List<Menu> list = menuService.findAll();
        List<Menu> parentNode = list.stream().filter(menu -> menu.getPid() == null).collect(Collectors.toList());
        for (Menu menu: parentNode) {
            menu.setChildren(list.stream().filter(m -> menu.getId().equals(m.getPid())).collect(Collectors.toList()));
        }
        return Result.success(parentNode);
    }
    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id) {
        return Result.success(menuService.findById(id));
    }

    @PostMapping
    public Result add(@RequestBody Menu menu) {
        boolean result = true;
        try
        {
            menuService.save(menu);
        }
        catch(Exception ex)
        {
            result = false;
        }
        return Result.success(result);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        menuService.deleteById(id);
        return Result.success(true);
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        menuService.deleteBatchById(ids);
        return Result.success(true);
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam(defaultValue = "") String name) {
        Page<Menu> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        if (!"".equals(name)) {
            queryWrapper.like("name", name);
        }
//        queryWrapper.orderByDesc("id");
        return Result.success(imenuService.page(page, queryWrapper));
    }
}
