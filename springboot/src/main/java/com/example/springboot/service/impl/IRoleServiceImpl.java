package com.example.springboot.service.impl;

import com.example.springboot.entity.Role;
import com.example.springboot.entity.RoleMenu;
import com.example.springboot.mapper.RoleMapper;
import com.example.springboot.mapper.RoleMenuMapper;
import com.example.springboot.service.IRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import javax.annotation.Resource;
import java.util.List;

@Service
public class IRoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Transactional
    @Override
    public void setRoleMenu(Integer roleId, List<Integer> menuIds) {
//        QueryWrapper<RoleMenu> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("role_id", roleId);
//        roleMenuMapper.delete(queryWrapper);

        roleMenuMapper.deleteByRoleId(roleId);

        for (Integer menuId : menuIds) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menuId);
            roleMenuMapper.insert(roleMenu);
        }
    }

    @Override
    public List<Integer> getRoleMenu(Integer roleId) {
        return roleMenuMapper.selectByRoleId(roleId);
    }
}
