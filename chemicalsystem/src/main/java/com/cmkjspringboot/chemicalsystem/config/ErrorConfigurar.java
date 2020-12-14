package com.cmkjspringboot.chemicalsystem.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

// 404重定向页面
@Configuration
public class ErrorConfigurar implements ErrorPageRegistrar {

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage[] errorPages = new ErrorPage[2];
        errorPages[0] = new ErrorPage(HttpStatus.NOT_FOUND, "/index.html"); //404报错捕获
        errorPages[1] = new ErrorPage(HttpStatus.METHOD_NOT_ALLOWED, "/index.html"); //405报错捕获

//        registry.addError(errorPages);
        registry.addErrorPages(errorPages);
    }
}
