package coswit.com.github.mylibrary;

/**
 * Created by zhengj on 2016/11/30.
 */
public class ApiException extends Exception{
    int code;
    public ApiException(int code,String s) {
        super(s);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
