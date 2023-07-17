package ca.oh.pcis.mocker.restful.filter;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.support.WebApplicationContextUtils;

import ca.oh.pcis.mocker.restful.Consts;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
@Order(1)
public class MockerFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        String path = httpServletRequest.getRequestURI();
        String method = httpServletRequest.getMethod().toLowerCase();        
        
        boolean validResource = isValidResource(path, method);
        
        if( validResource ) {        
           chain.doFilter(request, response);
        }
        else {
           System.out.println(String.format("received unexpected resource '%s' with method '%s'", path, method));  
           sendResourceNotFound(response);
        }
    }
    
    private boolean isValidResource(String path, String method) {
        boolean validResource = false;
        String definedMethod = Consts.pathMap.get(path);
        if ( method.equalsIgnoreCase(definedMethod)) {
            validResource = true;
        }
        return validResource;
    }
    
    private void sendResourceNotFound(ServletResponse response) throws IOException {
        HttpServletResponse servletResponse = (HttpServletResponse) response;  
        servletResponse.setStatus( 404 );
        servletResponse.setContentType("application/json");
        servletResponse.setHeader("Error", "Client Error");
        String payload = "{\"error\": \"requested resource not found\"}";
        
        servletResponse.getWriter().write(payload);
        servletResponse.flushBuffer();
    }

}
