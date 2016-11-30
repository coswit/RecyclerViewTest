package coswit.com.github.mylibrary;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by zhengj on 2016/11/30.
 */
public class ToastUtil {
   public static void showToast(Context mCtx, String s) {
       Toast.makeText(mCtx,s,Toast.LENGTH_LONG).show();
    }
}
