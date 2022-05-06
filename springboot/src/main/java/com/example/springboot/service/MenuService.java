package com.example.springboot.service;

import com.example.springboot.entity.Menu;

import java.util.List;

public interface MenuService {
    public Menu findById(int id);
    public List<Menu> findAll();
    public Menu save(Menu menu);
    public Menu edit(Menu menu);
    public boolean deleteById(int id);
    public boolean deleteBatchById(List<Integer> ids);
}
