package com.alimather.myjwt.common.holder;

import com.alimather.myjwt.demo.entity.SysUser;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/**
 * description:
 *
 * @author: YJG
 * @time: 2020/9/4 11:29
 */
@Slf4j
public class RequestHolder {
    private static final ThreadLocal<SysUser> userHolder = new ThreadLocal<SysUser>();

    private static final ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<HttpServletRequest>();

    public static void add(SysUser sysUser) {
        userHolder.set(sysUser);
    }

    public static void add(HttpServletRequest request) {
        requestHolder.set(request);
    }

    public static SysUser getCurrentUser() {
        SysUser sysUser = userHolder.get();
        /*if (sysUser == null) {
            log.error("=====获取用户信息为空,尝试通过Attribute中的登录信息获取SysUser！==");
            HttpServletRequest httpServletRequest = requestHolder.get();
            UserInfo userInfo = (UserInfo) httpServletRequest.getAttribute("userInfo");
            if (userInfo == null) {
                throw new RuntimeException("=================无法获取用户信息！================");
            }
            UserApi userService = SpringContextHolder.getBean(UserApi.class);
            sysUser = userService.queryUserByUsername(userInfo.getUsername());
            if (sysUser == null) {
                throw new RuntimeException("=================无法获取用户信息！================");
            }
            add(sysUser);
        }
        log.info("============ 当前登录用户：----->{}", sysUser.getUsername());*/
        return sysUser;
    }

    public static HttpServletRequest getCurrentRequest() {
        return requestHolder.get();
    }

    public static void remove() {
        userHolder.remove();
        requestHolder.remove();
    }
}
