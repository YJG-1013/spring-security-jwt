package com.alimather.myjwt.demo.dao;

import com.alimather.myjwt.demo.entity.SelfUserDetails;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @author: yjg
 * @date: 2020-9-7 19:24:46
 * @description: 用户dao层
 */

@Component
public interface UserMapper {

    //通过username查询用户
    SelfUserDetails getUser(@Param("username") String username);

}
