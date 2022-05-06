package com.example.springboot.service.impl;

import com.example.springboot.entity.Menu;
import com.example.springboot.repository.MenuRepository;
import com.example.springboot.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuRepository menuRepository;

    public Menu findById(int id) {
        return menuRepository.findById(id).get();
    }
    public List<Menu> findAll() {
        return menuRepository.findAll();
    }

    public Menu save(Menu menu) {
        return menuRepository.save(menu);
    }

    public Menu edit(Menu menu) {
        return menuRepository.save(menu);
    }

    public boolean deleteById(int userId) {
        boolean result = true;
        try
        {
            menuRepository.deleteById(userId);
        }
        catch(Exception ex)
        {
            result = false;
        }
        return result;
    }
    public boolean deleteBatchById(List<Integer> ids) {
        boolean result = true;
        try
        {
            menuRepository.deleteAllByIdInBatch(ids);
        }
        catch(Exception ex)
        {
            result = false;
        }
        return result;
    }
}
