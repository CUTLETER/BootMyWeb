package com.coding404.myweb.config;

import com.coding404.myweb.interceptor.MenuHandler;
import com.coding404.myweb.interceptor.UserAuthHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // 설정 파일
public class WebConfig implements WebMvcConfigurer {
    // 인터셉터 빈 등록
    @Bean
    public UserAuthHandler userAuthHandler() {
        return new UserAuthHandler();
    }

    //메뉴 처리 인터셉터 빈 등록
    @Bean
    public MenuHandler menuHandler() {
        return new MenuHandler();
    }

    //인터셉터 등록
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userAuthHandler())
                .addPathPatterns("/product/*") // 상품 관련 모든 내역에 추가시킴
                .addPathPatterns("/topic/*")
                .addPathPatterns("/notice/*")
                .addPathPatterns("/"); // 메인 페이지 추가

        registry.addInterceptor(menuHandler())
                .addPathPatterns("/product/*")
                .addPathPatterns("/topic/*")
                .addPathPatterns("/notice/*");
    }
}
