package secondkill.web.protocol;

/**
 * 响应状态码，定义自己的状态码可以继承该类
 * @author sunxin08
 * @date 2017/5/20
 */
public abstract class ResponseCode {

    public static final int WEB_STATUS_OK = 200; // 成功
    public static final int WEB_STATUS_PARTLY_OK = 300; // 部分成功
    public static final int WEB_STATUS_FAILED = 400; // 请求失败
    public static final int WEB_PERMISSION_DENIED = 403;
    public static final int WEB_STATUS_SYSTEM_ERROR = 500; // 系统错误
    public static final int WEB_STATUS_PARAM_ERROR = 600; // 参数错误
    public static final int WEB_STATUS_AUTH_ERROR = 700; // 权限错误
    public static final int WEB_STATUS_EXCEED_ERROR = 800;

}
