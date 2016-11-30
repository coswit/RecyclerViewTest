package coswit.com.github.mylibrary;


import android.service.carrier.CarrierMessagingService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zhengj on 2016/8/12.
 */
public class OkhttpHelper {

    private static OkhttpHelper mInstance;
    private static OkHttpClient mOkHttpClient;
    private static OkHttpClient.Builder mBuilder;

    private OkhttpHelper() {
//        mOkHttpClient = new OkHttpClient();
        mBuilder= new OkHttpClient.Builder();
        mOkHttpClient = mBuilder.build();
    }

    public static OkhttpHelper getInstance() {
        if (mInstance == null) {
            synchronized (OkhttpHelper.class) {
                if (mInstance == null) {
                    mInstance = new OkhttpHelper();
                }
            }
        }
        return mInstance;
    }

    /**
     * 响应时间设置
     * @param time
     * @param unit
     */
    public static void setConnectTime(Long time,TimeUnit unit) {
        getInstance().mBuilder.connectTimeout(time, unit);
    }

    private static Response _getSynRequest(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Call call = mOkHttpClient.newCall(request);
        Response response = call.execute();
        return response;
    }

    /**
     * 同步get请求
     * @param url
     * @return
     * @throws IOException
     */
    public static Response getSynRequest(String url) throws IOException {
        return getInstance()._getSynRequest(url);
    }

    private static Response _getSynRequest(String url, HashMap<String,Object> params) throws IOException {
        String requestUrl =createRequestUrl(url,params);
        Request request = new Request.Builder().url(requestUrl).build();
        Call call = mOkHttpClient.newCall(request);
        Response response = call.execute();
        return response;
    }

    public static Response getSynRequest(String url, HashMap<String,Object> params) throws IOException {
        return getInstance()._getSynRequest(url,params);
    }

    private static Response _postSynRequest(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Call call = mOkHttpClient.newCall(request);
        Response response = call.execute();
        return response;
    }

    /**
     * 同步post请求
     * @param url
     * @return
     * @throws IOException
     */
    public static Response postSynRequest(String url) throws IOException {
        return getInstance()._postSynRequest(url);
    }


    private void _getAsyn(String url, final CarrierMessagingService.ResultCallback callback)
    {
       Request request = new Request.Builder().url(url).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }

    private void deliveryResult(CarrierMessagingService.ResultCallback callback, Request request) {


    }


    public static String createRequestUrl(String url, HashMap<String,Object> params) {
        String requestUrl=null;
        if (url != null && params !=null) {
            Set<String> keySet = params.keySet();
            ArrayList<String> keys = new ArrayList<>(keySet);
            Collections.sort(keys);
            StringBuffer sb = new StringBuffer();
            for (String key : keys) {
                sb.append("&").append(key).append("=").append(params.get(key));
            }
            //删除开始的&
            sb.deleteCharAt(0);
            requestUrl = url + "?"+sb.toString();
        }
        return requestUrl;
    }

}
