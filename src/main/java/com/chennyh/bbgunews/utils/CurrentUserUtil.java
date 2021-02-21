package com.chennyh.bbgunews.utils;

import com.chennyh.bbgunews.dto.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author Chennyh
 * @date 2021/2/21 15:55
 * @description 获取当前的用户名
 */
@Component
public class CurrentUserUtil {

    public String getCurrentOpenId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() != null) {
            UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
            return principal.getUsername();
        }
        return null;
    }

}
