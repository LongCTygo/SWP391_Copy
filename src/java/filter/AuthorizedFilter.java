package filter;

import entity.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import static utility.WebRedirectionUtil.dispatch;

@WebFilter(urlPatterns = "/user/*", filterName = "AuthorizedFilter",
description = "Allow only authorized users to access pages with the above pattern.")
public class AuthorizedFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null){
//            response.sendError(403,"You cannot access this page unless you are logged in.");
            response.setStatus(403);
            request.setAttribute("message", "You cannot access this page unless you are logged in.");
            dispatch(request,response,"/view/error.jsp");
        } else {
            filterChain.doFilter(servletRequest,servletResponse);
        }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
