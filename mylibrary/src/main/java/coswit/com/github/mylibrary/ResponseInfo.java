package coswit.com.github.mylibrary;

/**
 * Created by zhengj on 2016/11/30.
 */
public class ResponseInfo<T> {
    private int code;
    private String message;
    private T data;
    private String responsestamp;
    public String getResponsestamp() {
        return responsestamp;
    }
    public void setResponsestamp(String responsestamp) {
        this.responsestamp = responsestamp;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
}
