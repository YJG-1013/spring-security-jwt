package com.alimather.myjwt.common.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: yjg
 * @date: 2020-9-7 19:24:46
 * @description: 权限访问控制
 */
@Component("rbacauthorityservice")
public class RbacAuthorityService {
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object userInfo = authentication.getPrincipal();
        boolean hasPermission  = false;
        if (userInfo instanceof UserDetails) {
            String username = ((UserDetails) userInfo).getUsername();
            //获取资源
            Set<String> urls = new HashSet();
            // 这些 url 都是要登录后才能访问，且其他的 url 都不能访问！
            urls.add("/api/**");
            urls.add("/**");
            AntPathMatcher antPathMatcher = new AntPathMatcher();
            for (String url : urls) {
                if (antPathMatcher.match(url, request.getRequestURI())) {
                    hasPermission = true;
                    break;
                }
            }
            return hasPermission;
        } else {
            return false;
        }
    }
}
