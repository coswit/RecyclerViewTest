package coswit.com.github.recyclerviewtest.network;


import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zhengj on 2016/11/30.
 */

public interface ISevice<T> {

    @GET("top250")
    Observable<ResponseBody> getISInfo(@Query("start") int start, @Query("count") int size);



}
