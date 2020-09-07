package com.alimather.myjwt.common.security;

import com.alibaba.fastjson.JSON;
import com.alimather.myjwt.common.Enums.ResultEnum;
import com.alimather.myjwt.common.VO.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: yjg
 * @date: 2020-9-7 19:24:46
 * @description: 用户未登录时返回给前端的数据
 */
@Slf4j
@Component
public class AjaxAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        log.info("-------->USER_NEED_AUTHORITIES<--------");
        httpServletResponse.getWriter().write(JSON.toJSONString(ResultVO.result(ResultEnum.USER_NEED_AUTHORITIES,false)));
    }
}
