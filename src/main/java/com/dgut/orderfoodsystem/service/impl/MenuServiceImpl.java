package com.dgut.orderfoodsystem.service.impl;

import com.dgut.orderfoodsystem.entity.Menu;
import com.dgut.orderfoodsystem.mapper.MenuMapper;
import com.dgut.orderfoodsystem.service.MenuService;
import com.dgut.orderfoodsystem.utils.ApiResponse;
import com.dgut.orderfoodsystem.utils.CommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Description TODO
 * @Author akun
 * @Data 2023-3-9
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Resource
    private MenuMapper menuMapper;
    @Override
    public ApiResponse addMenu(Menu menu) {
        ApiResponse<Menu> menuApiResponse = new ApiResponse<>();
        if(menu==null){
            menuApiResponse.setMessage("商品类别信息为空！");
            menuApiResponse.setCode(400);
            return menuApiResponse;
        }
        if(StringUtils.isBlank(menu.getMenuName())){
            menuApiResponse.setCode(400);
            menuApiResponse.setMessage("类别名称为空！");
            return menuApiResponse;
        }
        if(StringUtils.isBlank(menu.getDescription())){
            menuApiResponse.setMessage("类别描述为空");
            menuApiResponse.setCode(400);
            return menuApiResponse;
        }
        //构造列别数据
        menu.setId(CommonUtils.generateId());
        menu.setCreateTime(new Date());
        try {
            menuMapper.insertMenu(menu);
            menuApiResponse.setCode(200);
            menuApiResponse.setMessage("添加成功!");
            menuApiResponse.setData(menu);
            return menuApiResponse;
        }catch (Exception e){
            menuApiResponse.setCode(500);
            menuApiResponse.setMessage("写入数据库失败！");
            return menuApiResponse;
        }

    }

    @Override
    public ApiResponse deleteMenu(String id) {
        ApiResponse<Menu> menuApiResponse = new ApiResponse<>();
        if(StringUtils.isBlank(id)){
            menuApiResponse.setMessage("删除失败！");
            menuApiResponse.setCode(500);
            return menuApiResponse;
        }
        try {
            menuMapper.deleteMenu(id);
        }catch (Exception e){
            menuApiResponse.setMessage("删除失败！");
            menuApiResponse.setCode(500);
            return menuApiResponse;
        }
        menuApiResponse.setMessage("删除成功！");
        menuApiResponse.setCode(200);
        return menuApiResponse;
    }

    @Override
    public List getMenusByStoreId(String storeId) {

        List<Menu> menus = menuMapper.selectMenusByStoreId(storeId);
        return menus;
    }

    @Override
    public ApiResponse updateMenu(Menu menu) {
        ApiResponse<Menu> menuApiResponse = new ApiResponse<Menu>();
        try {
            menuMapper.updateMenu(menu);
        }catch (Exception e){
            menuApiResponse.setCode(500);
            menuApiResponse.setMessage("更新失败！");
            return menuApiResponse;
        }
        menuApiResponse.setMessage("更新成功");
        menuApiResponse.setCode(200);
        menuApiResponse.setData(menu);
        return menuApiResponse;
    }

    @Override
    public ApiResponse<Menu> getMenuById(String id) {
        ApiResponse<Menu> menuApiResponse = new ApiResponse<>();
        if(StringUtils.isBlank(id)){
            menuApiResponse.setCode(400);
            menuApiResponse.setMessage("菜单类id为空！");
            return menuApiResponse;
        }
        try {
            Menu menu = menuMapper.selectMenuById(id);
            if(menu==null){
                menuApiResponse.setMessage("没有该菜品类的信息!");
                menuApiResponse.setCode(400);
                return menuApiResponse;
            }
            menuApiResponse.setCode(200);
            menuApiResponse.setMessage("查询菜品类成功！");
            menuApiResponse.setData(menu);
            return menuApiResponse;
        }catch (Exception e){
            e.printStackTrace();
            menuApiResponse.setMessage("获取菜品类失败！");
            menuApiResponse.setCode(500);
            return menuApiResponse;
        }
    }
}
