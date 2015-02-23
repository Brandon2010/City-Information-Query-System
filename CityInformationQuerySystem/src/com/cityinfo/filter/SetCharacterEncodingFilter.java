package com.cityinfo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Example filter that sets the character encoding to be used in parsing the
 * incoming request
 */
public class SetCharacterEncodingFilter implements Filter {

    /**
     * Take this filter out of service.
     */
    public void destroy() {
    }
    /**
     * Select and set (if specified) the character encoding to be used to
     * interpret request parameters for this request.
     */
    public void doFilter(ServletRequest request, ServletResponse response,
    FilterChain chain)throws IOException, ServletException {
    	    	
        request.setCharacterEncoding("utf-8");

        // 传递控制到下一个过滤器
        chain.doFilter(request, response);
        System.out.println("Filtered");
    }

    public void init(FilterConfig filterConfig) throws ServletException {
    }
}
