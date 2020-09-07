package com.alimather.myjwt.demo.service;

import com.alimather.myjwt.demo.entity.DemoEntity;

import java.util.List;

/**
 * @author: yjg
 * @date: 2020-9-7 19:24:46
 * @description:
 */
public interface DemoService {

    List<DemoEntity> getUser();

    void register(String username, String password);
}
