package amap.com.amapandgoogle;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;

/**
 * Description:
 * Author: 黄培忠
 * CreateDate: 19/1/29 16:51
 * UpdateUser:
 * UpdateDate: 19/1/29 16:51
 * UpdateRemark:
 */
public final class SaveCacheUtils {

    // 获取缓存信息
    public static WeatherBean getWeatherBean(Context context) {
        String str = ACacheUtil.get(context).getAsString("weather");
        if (!TextUtils.isEmpty(str)) {
            WeatherBean object = new Gson().fromJson(str, WeatherBean.class);
            return object;
        }
        return null;
    }

    //缓存天气信息
    public static void cacheWeatherBean(WeatherBean weatherBean) {
        String cacheData = null == weatherBean ? "" : new Gson().toJson(weatherBean);
        ACacheUtil.get(BaseApplication.getContext()).put("weather", cacheData);
    }
}
