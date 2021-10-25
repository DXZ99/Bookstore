package edu.neu.filter;

import edu.neu.utils.JDBCUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author Zhao Dongxiao
 * @create 2020-12-29 22:36
 */
public class TransactionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            chain.doFilter(request, response);
            JDBCUtils.commitAndClose();
        } catch (Exception e) {
            JDBCUtils.rollbackAndClose();
            e.printStackTrace();
            throw new RuntimeException(e); //把异常抛给Tomcat统一展示错误页面
        }
    }

    @Override
    public void destroy() {

    }
}
