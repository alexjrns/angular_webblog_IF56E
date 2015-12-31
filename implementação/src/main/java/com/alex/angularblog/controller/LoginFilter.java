package com.alex.angularblog.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "LoginFilter",
        urlPatterns = {"/*"},
        initParams = {
            @WebInitParam(name = "angularControllerURI", value = "/controller/LoginController.js"),
            @WebInitParam(name = "angularAnimateURI", value = "/view/resources/scripts/*"),
            @WebInitParam(name = "angularURI", value = "/view/resources/scripts/angular.min.js")
        }
)
public class LoginFilter implements Filter {
    private String ANGULARCONTROLLER_URI;
    private String ANGULARANIMATE_URI;
    private String ANGULAR_URI;
    
    public LoginFilter() {
        super();
    }    
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession sessao = ((HttpServletRequest) request).getSession();
        HttpServletRequest req = (HttpServletRequest) request;        
        
        if(sessao.getAttribute("usuarioLogado") == null
                && !ANGULARCONTROLLER_URI.equals(req.getRequestURI())
                && !ANGULARANIMATE_URI.equals(req.getRequestURI())
                && !ANGULAR_URI.equals(req.getRequestURI())
                && !"/AngularBlog/".equals(req.getRequestURI())
                && !(req.getRequestURI().contains("scripts/angular.min.js"))
                && !(req.getRequestURI().contains("controller/LoginController.js"))
                ){

            String contextPath = req.getContextPath();
            Logger.getLogger(LoginFilter.class.getName()).log(Level.WARNING, ("Um usuário não logado está tentando acessar -> " + req.getRequestURI()));
            //((HttpServletResponse)response).sendRedirect(contextPath + "/view/index.html");
            req.getRequestDispatcher("/view/index.html").forward(request, response);
            return;

        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {        
    }

    @Override
    public void init(FilterConfig filterConfig) {
        ANGULARCONTROLLER_URI = filterConfig.getInitParameter("angularControllerURI");
        ANGULARANIMATE_URI = filterConfig.getInitParameter("angularAnimateURI");
        ANGULAR_URI = filterConfig.getInitParameter("angularURI");
    }
}
