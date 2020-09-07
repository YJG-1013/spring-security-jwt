package com.alimather.myjwt.demo.dao;

import com.alimather.myjwt.demo.entity.DemoEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: yjg
 * @date: 2020-9-7 19:24:46
 * @description:
 */
@Component
public interface DemoMapper {

    List<DemoEntity> getUser();

    void register(@Param("username") String username, @Param("password") String password);
}
