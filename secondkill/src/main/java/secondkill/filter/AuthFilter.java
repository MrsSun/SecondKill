package secondkill.filter;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import secondkill.UserUtils;
import secondkill.entity.User;
import secondkill.service.UserService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author sunxin08
 * @date 2017/6/8
 */
@Component
public class AuthFilter implements Filter {

    private static final Logger logger = Logger.getLogger(AuthFilter.class);

    private static Set<String> urlPrefix;

    private static final String LOGIN_PAGE = "/login";

    static {
        urlPrefix = new HashSet<String>();
        urlPrefix.add("/login");
        urlPrefix.add("/register");
        urlPrefix.add("/static/");
        urlPrefix = Collections.unmodifiableSet(urlPrefix);
    }

    @Autowired
    private UserService userService;

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        logger.info("authFilter doFilter: " + request.getRequestURI());

        // 先进行登陆判断，防止通过url多次登陆
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(UserUtils.USER_AUTH_KEY);
        if (Objects.isNull(user)) {
            logger.info("user is null");
        } else {
            logger.info("user: " + user);
        }
        if (Objects.nonNull(user) && validate(user)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        // url路径验证
        if (urlPass(request.getRequestURI())) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        if (Objects.isNull(userService)) {
            logger.error("userService wired error: userService is null");
            return;
        }
        // 身份验证
        if (userService.validate(user)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        } else {
            redirect(response);
        }
    }

    /**
     * url路径验证
     * 特殊前缀直接放行
     * @param path
     * @return
     */
    private boolean urlPass(String path) {
        if (Objects.isNull(path)) {
            return false;
        }
        for (String prefix : urlPrefix) {
            if (path.startsWith(prefix)) {
                return true;
            }
        }
        return false;
    }

    private void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect(LOGIN_PAGE);
    }

    private boolean validate(User user) {
        return false;
    }

    public void destroy() {

    }
}
