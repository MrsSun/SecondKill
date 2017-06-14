package secondkill.web.protocol;

/**
 * @author chaoge
 * @date 2017/5/20
 */
public class CommonWebResponse<T> {

    protected T data;

    protected int status;

    protected String errorDetails;

    public CommonWebResponse() {
        status = ResponseCode.WEB_STATUS_FAILED;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(String errorDetails) {
        this.errorDetails = errorDetails;
    }
}
