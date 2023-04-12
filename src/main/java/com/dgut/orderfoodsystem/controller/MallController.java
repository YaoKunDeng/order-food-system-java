package com.dgut.orderfoodsystem.controller;

import com.dgut.orderfoodsystem.entity.Dishes;
import com.dgut.orderfoodsystem.entity.Menu;
import com.dgut.orderfoodsystem.entity.Store;
import com.dgut.orderfoodsystem.service.DishesService;
import com.dgut.orderfoodsystem.service.MenuService;
import com.dgut.orderfoodsystem.service.StoreService;
import com.dgut.orderfoodsystem.utils.ApiResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @Description TODO
 * @Author akun
 * @Data 2023-3-9
 */
@RestController
@RequestMapping("/mall")
public class MallController {

    @Value("${application.path.upload}")
    private String uploadPath;
    @Resource
    private MenuService menuService;

    @Resource
    private DishesService dishesService;

    @Resource
    private StoreService storeService;

    @RequestMapping(path = "/add/menu",method = RequestMethod.POST)
    public ApiResponse addMenu(Menu menu){
        ApiResponse response = menuService.addMenu(menu);
        Menu data = (Menu) response.getData();
        ApiResponse storeById = storeService.getStoreById(data.getStoreId());
        if(storeById.getCode()!=200){
            response.setData(400);
            response.setMessage("添加失败！");
            return response;
        }
        Store data1 = (Store) storeById.getData();
        Map<String,Object> map = new HashMap<>();
        map.put("id",data.getId());
        map.put("storeId",data.getStoreId());
        map.put("storeName",data1.getStoreName());
        map.put("menuName",data.getMenuName());
        map.put("description",data.getDescription());
        map.put("createTime",data.getCreateTime());
        response.setData(map);
        response.setMessage("添加成功！");
        response.setCode(200);
        return response;
    }

    @RequestMapping(path = "/delete/menu", method = RequestMethod.GET)
    public ApiResponse delMenu(String id){
        ApiResponse response = menuService.deleteMenu(id);
        return response;
    }

    @RequestMapping(path = "/update/menu", method = RequestMethod.POST)
    public ApiResponse updateMenu(Menu menu){
        ApiResponse response = menuService.updateMenu(menu);
        return response;
    }

    @RequestMapping(path = "/get/menus", method = RequestMethod.GET)
    public ApiResponse getMenuByStoreId(String storeId){
        ApiResponse<List> response = new ApiResponse<>();

        List<Map> resultList = new ArrayList<>();
        List<Menu> menus = menuService.getMenusByStoreId(storeId);
        if(menus.size()==0){
            response.setCode(400);
            response.setMessage("该店铺商品列表为空！");
            return response;
        }
        ApiResponse storeById = storeService.getStoreById(menus.get(0).getStoreId());
        if(storeById.getCode()!=200){
            storeById.setMessage("获取商品分类信息失败！");
            return storeById;
        }
        Store store = (Store) storeById.getData();

        for (Menu menu : menus) {
            Map<String, Object> map = new HashMap<>();
            map.put("id",menu.getId());
            map.put("storeId",menu.getStoreId());
            map.put("storeName",store.getStoreName());
            map.put("menuName",menu.getMenuName());
            map.put("description",menu.getDescription());
            map.put("createTime",menu.getCreateTime());
            resultList.add(map);

        }
        response.setCode(200);
        response.setMessage("获取商品列表信息成功！");
        response.setData(resultList);
        return response;
    }

    @RequestMapping(path = "/upload",method = RequestMethod.POST)
    public ApiResponse uploadImage(MultipartFile file){
        System.out.println(file);
        //MultipartFile是SpringMVC提供简化上传操作的工具类
        ApiResponse<String> mapApiResponse = new ApiResponse<>();
        if(file==null){
            mapApiResponse.setCode(400);
            mapApiResponse.setMessage("您还没有选择图片");
            return mapApiResponse;
        }
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        if(StringUtils.isBlank(suffix)||(!suffix.equals(".jpg")&&!suffix.equals(".png"))){
            mapApiResponse.setMessage("只接受.jpg和.png两种格式的图片！");
            mapApiResponse.setCode(500);
            return mapApiResponse;
        }
        //生成随机文件名
        fileName = UUID.randomUUID().toString().replaceAll("-","")+suffix;
        //确定文件存储路径
        String filePath = uploadPath + "\\" + fileName;
        File dest = new File(filePath);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            mapApiResponse.setMessage("上传文件失败");
            mapApiResponse.setCode(500);
            return mapApiResponse;
        }
        mapApiResponse.setCode(200);
        mapApiResponse.setMessage("文件上传成功!");
        mapApiResponse.setData(filePath);
        return mapApiResponse;
    }

    @RequestMapping(path = "/get/id/name",method = RequestMethod.GET)
    public ApiResponse getMenusIdAndName(String storeId){
        ApiResponse<List> listApiResponse = new ApiResponse<>();
        if(StringUtils.isBlank(storeId)){
            listApiResponse.setMessage("获取类别信息失败！");
            listApiResponse.setCode(500);
            return listApiResponse;
        }
        List<Map> resultList = new ArrayList<>();
        List<Menu> menus = menuService.getMenusByStoreId(storeId);
        for(Menu menu:menus){
            Map<String, String> map = new HashMap<>();
            map.put("value",menu.getId());
            map.put("label",menu.getMenuName());
            resultList.add(map);
        }
        listApiResponse.setCode(200);
        listApiResponse.setMessage("获取类别信息成功");
        listApiResponse.setData(resultList);
        return listApiResponse;
    }

    @RequestMapping(path = "/get/dishes",method = RequestMethod.GET)
    public ApiResponse getDishByStoreId(String storeId){
        ApiResponse response = dishesService.getDishesByStoreId(storeId);
        System.out.println(response);
        ArrayList<Map> resultList = new ArrayList<>();
        if(response.getCode()==201){
            response.setCode(500);
            response.setMessage("该店铺暂时没有商品！");
            return response;
        }
        if(response.getCode()!=200){
            response.setCode(500);
            response.setMessage("获取商品列表失败！");
            return response;
        }
        List<Dishes> dishes = (List<Dishes>) response.getData();

        for (Dishes dish : dishes) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id",dish.getId());
            map.put("storeId",dish.getStoreId());
            //menuid转为name
            //还没写根据id获取menuName
            ApiResponse<Menu> menuById = menuService.getMenuById(dish.getMenuId());
            System.out.println(menuById);
            if(menuById.getCode()!=200){
                continue;
            }
            Menu menu = menuById.getData();
            map.put("menuId",dish.getMenuId());
            map.put("menuName",menu.getMenuName());
            map.put("dishName",dish.getDishName());
            map.put("description",dish.getDescription());
            map.put("image",dish.getImage());
            map.put("oldPrice",dish.getOldPrice());
            map.put("newPrice",dish.getNewPrice());
            resultList.add(map);
        }
        response.setCode(200);
        response.setMessage("获取商品列表成功！");
        response.setData(resultList);
        return response;

    }

    @RequestMapping(path = "/add/dish", method = RequestMethod.POST)
    public ApiResponse addDish(Dishes dishes){
        ApiResponse response = dishesService.addDish(dishes);
        return response;
    }

    @RequestMapping(path = "update/dish", method = RequestMethod.POST)
    public ApiResponse updateDish(Dishes dishes){
        ApiResponse response = dishesService.updateDish(dishes);
        return response;
    }

    @RequestMapping(path = "del/dish", method = RequestMethod.GET)
    public ApiResponse delDish(String id){
        ApiResponse response = dishesService.delDish(id);
        return response;
    }

}
