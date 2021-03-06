package com.example.springboot.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.Menu;
import com.example.springboot.mapper.MenuMapper;
import com.example.springboot.service.IMenuService;
import com.example.springboot.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IMenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Resource
    private MenuService menuService;
    @Override
    public List<Menu> findMenus(String name) {
//        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
//        if (StrUtil.isNotBlank(name)) {
//            queryWrapper.like("name", name);
//        }
//        List<Menu> list = list(queryWrapper);
        List<Menu> list = menuService.findAll();
        List<Menu> parentNodes = list.stream().filter(menu -> menu.getPid() == null).collect(Collectors.toList());
        for (Menu parent : parentNodes) {
            parent.setChildren(list.stream().filter(m -> parent.getId().equals(m.getPid())).collect(Collectors.toList()));
        }
        return parentNodes;
    }
}
