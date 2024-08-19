package com.coding404.myweb.interceptor;

import com.coding404.myweb.command.UserVO;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserAuthHandler implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        UserVO vo = (UserVO) session.getAttribute("userVO"); // 로그인 성공 시 세션 값

        if (vo == null) { // 로그인 XXX
            response.sendRedirect("/user/login");
            return false;
        } else { // 로그인 OOO
            return true;
        }
    }
}
