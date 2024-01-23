package ca.oh.pcis.mocker.restful.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import ca.oh.pcis.mocker.restful.controller.RequestBodyInterceptor;

//@Configuration
//public class MSConfig extends DelegatingWebMvcConfiguration {
//
//    @Autowired
//    RequestMappingHandlerAdapter adapter;
//    
//    public RequestBodyAdvice myRequestBodyAdvice(){
//        return new RequestBodyInterceptor();
//    }
//
//
//
//}