package coswit.com.github.recyclerviewtest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import coswit.com.github.mylibrary.JsonHelper;
import coswit.com.github.mylibrary.OkhttpHelper;
import coswit.com.github.recyclerviewtest.bean.InfoNewsBean;
import coswit.com.github.recyclerviewtest.network.ApiConstants;
import coswit.com.github.recyclerviewtest.network.ISevice;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zhengj on 2016/11/25.
 */
public class RecylerViewAct extends AppCompatActivity {


    @BindView(R.id.recylerview)
    RecyclerView recylerview;
    @BindView(R.id.getDatas)
    Button getDatas;
    @BindView(R.id.rxjava)
    Button rxjava;
    @BindView(R.id.iv_load)
    Button loadimg;
    @BindView(R.id.iv_recyclerview)
    ImageView ivRecyclerview;
    @BindView(R.id.tv_img_title)
    TextView mImgTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recylerview);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.getDatas, R.id.rxjava,R.id.iv_load})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.getDatas:
                getRetrofit();
                break;
            case R.id.rxjava:
                try {
                    doRxjava();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.iv_load:
                loadImage();
                break;
        }
    }

    private void loadImage() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder().client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(ApiConstants.BASE_URL)
                .build();

        ISevice iSevice = retrofit.create(ISevice.class);



        iSevice.getISInfo(0,10).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody >() {


                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            Logger.d(responseBody.string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

    }


    private void getRetrofit() {
        String baseUrl = "http://c.m.163.com/nc/article/headline/T1348647909107/";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        IInfos iInfos = retrofit.create(IInfos.class);

        Call<ResponseBody> infos = iInfos.getInfos("0-20");
        infos.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

//


    }



    public interface IInfos {
        @GET("{size}.html")
        Call<ResponseBody> getInfos(@Path("size") String infonews);
    }

    private void doRxjava() throws IOException {
        final String url = "http://c.m.163.com/nc/article/headline/T1348647909107/0-20.html";


        Observable.just(ivRecyclerview).subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .map(new Func1<ImageView, String>() {
            @Override
            public String call(ImageView imageView) {
                return getImgUrl(url);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Glide.with(getBaseContext()).load(s).into(ivRecyclerview);
            }
        });

    }

    private String getImgUrl(String url) {
        String result = null;
        try {
            Logger.d(url);
            OkhttpHelper.setConnectTime(2l, TimeUnit.SECONDS);
            String synRequest = OkhttpHelper.getSynRequest(url).body().string();
            Logger.d(synRequest);
            InfoNewsBean bean = JsonHelper.json2Bean(synRequest, InfoNewsBean.class);
            result = bean.getT1348647909107().get(0).getAds().get(0).getImgsrc();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
