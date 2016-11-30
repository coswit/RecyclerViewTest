package coswit.com.github.mylibrary;

import android.content.Context;
import android.text.TextUtils;

import rx.Subscriber;

/**
 * Created by zhengj on 2016/11/30.
 */
public class ApiSubscriber<T> extends Subscriber<T>{

    @Override
    public void onCompleted() {
        if(isShowWaitDialog){
            dismissDialog();
        }
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(T t) {

    }


    private Context mCtx;
    private WaitingDialog waitingDialog;  //加载dialog
    private boolean isShowWaitDialog;

    public void setShowWaitDialog(boolean showWaitDialog) {
        isShowWaitDialog = showWaitDialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(isShowWaitDialog){
            showWaitDialog();
        }
    }

    public void setmCtx(Context mCtx) {
        this.mCtx = mCtx;
    }

    /**
     * 服务器返回的错误
     * @param ex
     */
    protected  void onResultError(ApiException ex){
        switch (ex.getCode()){  //服务器返回code默认处理
            case 10021:
                ToastUtil.showToast(mCtx, "10021错误");
                break;
            case 10431:
                ToastUtil.showToast(mCtx, "2错误");
                break;
            default:
                String msg = ex.getMessage();
                if(TextUtils.isEmpty(msg)){
                    ToastUtil.showToast(mCtx, "");
                }else {
                    ToastUtil.showToast(mCtx, msg);
                }
        }

    }

    private void dismissDialog(){
        if(waitingDialog!=null) {
            if(waitingDialog.isShowing()) {
                waitingDialog.dismiss();
            }
        }
    }

    private void showWaitDialog(){
        if (waitingDialog == null) {
            waitingDialog = new WaitingDialog(mCtx);
            waitingDialog.setDialogWindowStyle();
            waitingDialog.setCanceledOnTouchOutside(false);
        }
        waitingDialog.show();
    }
}
