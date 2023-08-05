package com.zheye.assignment.config;

import com.zheye.assignment.constant.BusinessException;
import com.zheye.assignment.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static com.zheye.assignment.constant.ResultCode.INVALID_TOKEN;

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("Authorization");
        if (StringUtils.isEmpty(token)) {
            throw new BusinessException(INVALID_TOKEN);
        }
        return JwtUtil.verifyToken(token);
    }
}
