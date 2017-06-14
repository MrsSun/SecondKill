package secondkill.web.protocol;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author chaoge
 * @date 2017/5/20
 */
public abstract class CommonWebRequest {

    protected HttpServletRequest httpServletRequest;

    protected Cookie cookie;

    protected HttpSession session;

    /**
     * 对参数做验证，成功返回 WEB_STATUS_OK，参数有问题返回 WEB_STATUS_PARAM_ERROR
     */
    protected int validate() {
        return ResponseCode.WEB_STATUS_OK;
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public Cookie getCookie() {
        return cookie;
    }

    public void setCookie(Cookie cookie) {
        this.cookie = cookie;
    }

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

}
