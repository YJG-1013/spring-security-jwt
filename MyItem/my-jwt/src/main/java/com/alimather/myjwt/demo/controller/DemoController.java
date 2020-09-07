package com.alimather.myjwt.demo.controller;

import com.alimather.myjwt.common.Enums.ResultEnum;
import com.alimather.myjwt.common.VO.ResultVO;
import com.alimather.myjwt.demo.entity.DemoEntity;
import com.alimather.myjwt.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author: yjg
 * @date: 2020-9-7 19:24:46
 * @description: demo
 */
@RestController
@RequestMapping("/users")
public class DemoController {

    @Autowired
    private DemoService orderService;

    @GetMapping("/getUser")
    public List<DemoEntity> getUser(){
        List<DemoEntity> result = orderService.getUser();
        return result;
    }

    /**
     * 简单注册功能
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/register")
    public Map<String, Object> register(String username,String password){
        orderService.register(username,password);
        return ResultVO.result(ResultEnum.SUCCESS,true);
    }
}
