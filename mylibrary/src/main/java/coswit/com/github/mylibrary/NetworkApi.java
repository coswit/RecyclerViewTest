package coswit.com.github.mylibrary;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zhengj on 2016/11/30.
 */
public interface NetworkApi {
    @POST("open/open.do")
    Observable<Object> post(@Query("ACID") int acid, @Body RequestBody  entery);

    @POST("open/open.do")
    Observable<ResponseInfo<Object>> response(@Query("ACID") int acid, @Body RequestBody entery);
}
