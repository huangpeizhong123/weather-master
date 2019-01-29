package amap.com.amapandgoogle;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Description:
 * Author: 黄培忠
 * CreateDate: 19/1/28 20:56
 * UpdateUser:
 * UpdateDate: 19/1/28 20:56
 * UpdateRemark:
 */
public class NetworkUtil {

    /**
     * 判断网络连接有效
     *
     * @return 判断网络连接有效
     */
    public static boolean isNetworkAvailable(Context context) {
        if (context == null) {
            return false;
        }
        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] infos = manager.getAllNetworkInfo();
        for (NetworkInfo info : infos) {
            if (info.isConnected()) {
                return true;
            }
        }
        return false;
    }
}
