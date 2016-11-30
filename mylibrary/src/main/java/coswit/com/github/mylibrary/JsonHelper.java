package coswit.com.github.mylibrary;


import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.orhanobut.logger.Logger;

/**
 * Created by zhengj on 2016/11/29.
 */

public class JsonHelper {

    public static Gson mGson;
    public static JsonHelper mJsonHelper;
    private JsonHelper() {
        mGson = new Gson();
    }

    public static JsonHelper getInstance() {
        if (mJsonHelper == null) {
            synchronized (JsonHelper.class) {
                if (mJsonHelper == null) {
                    mJsonHelper = new JsonHelper();
                }
            }
        }
        return mJsonHelper;
    }

    public static <T>T json2Bean (String json,Class<T> clazz) {
        T bean =null;
        try {
            bean = JsonHelper.getInstance().mGson.fromJson(json, clazz);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            Logger.d("json解析错误");
        }
        return bean;
    }
}
