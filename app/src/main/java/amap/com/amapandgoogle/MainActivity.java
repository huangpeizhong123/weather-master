package amap.com.amapandgoogle;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.maps.AMap;
import com.amap.api.maps.TextureMapView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnCameraMoveListener {
    private LinearLayout mContainerLayout;
    private LayoutParams mParams;
    private TextureMapView mAMapView;
    private MapView mGoogleMapView;
    private GoogleMap googlemap;
    private AMapLocationClient mLocationClient;
    private Timer timer;
    private TimerTask timerTask;
    private ProgressDialog progressDialog;
    private float zoom = 5;//设置要看的具体位置
    private List<WeatherBean.AreaMetadataBean> listWeather = new ArrayList<>();

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContainerLayout = (LinearLayout) findViewById(R.id.map_container);
        ImageView imageView = (ImageView) findViewById(R.id.iv_refresh);

        //数据加载框
        progressDialog = new ProgressDialog(MainActivity.this, ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(true);
        progressDialog.setMessage("正在刷新...");
        progressDialog.show();

        timer = new Timer();

        //刷新按钮点击事件
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                timerTask.cancel();
                initData();
            }
        });

        //如果有缓存先展示缓存的，增强体验效果
        if (SaveCacheUtils.getWeatherBean(MainActivity.this) != null && NetworkUtil.isNetworkAvailable(this)) {
            initViewData();
            listWeather = Objects.requireNonNull(SaveCacheUtils.getWeatherBean(MainActivity.this)).getArea_metadata();
        }

        //定时器轮询2分钟刷新下接口数据
        initData();

    }

    /**
     * 初始化地图
     */
    private void initViewData() {
        mAMapView = new TextureMapView(this);
        mParams = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);

        //显示地图页面
        if (!checkGooglePlayServices()) {
            return;
        }
        zoom = mAMapView.getMap().getCameraPosition().zoom;
        mGoogleMapView = new com.google.android.gms.maps.MapView(this, new GoogleMapOptions()
                .camera(new com.google.android.gms.maps.model
                        .CameraPosition(new com.google.android.gms.maps.model.LatLng(1.375, 103.839), zoom, 0, 0)));
        mGoogleMapView.onCreate(null);
        mGoogleMapView.onResume();
        mContainerLayout.addView(mGoogleMapView, mParams);
        mGoogleMapView.getMapAsync(this);
        handler.sendEmptyMessage(0);
        AlphaAnimation anAppear = new AlphaAnimation(0, 1);
        AlphaAnimation anDisappear = new AlphaAnimation(1, 0);
        anAppear.setDuration(5000);
        anDisappear.setDuration(5000);

        //注册广播，监听应用必须谷歌服务安装情况
        IntentFilter mIntentFilter = new IntentFilter(
                ConnectivityManager.CONNECTIVITY_ACTION);
        mIntentFilter.addAction(Intent.ACTION_PACKAGE_ADDED);
        mIntentFilter.addAction(Intent.ACTION_PACKAGE_REPLACED);
        mIntentFilter.addDataScheme("package");
        registerReceiver(mInstallReceiver, mIntentFilter);
    }

    //网络请求
    private void initData() {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                //轮询请求，2分钟
                requestWeatherData();
            }
        };
        timer.schedule(timerTask, 0, 2 * 60 * 1000);
    }

    //接口数据
    private void requestWeatherData() {
        OkHttpUtils.get().url(ApiConstant.WEATHER_APT).build().execute(new StringCallback() {
            @TargetApi(Build.VERSION_CODES.KITKAT)
            @Override
            public void onError(Call call, Exception e, int id) {
                //请求失败的情况
                progressDialog.dismiss();
                if (SaveCacheUtils.getWeatherBean(MainActivity.this) != null) {
                    listWeather = Objects.requireNonNull(SaveCacheUtils.getWeatherBean(MainActivity.this)).getArea_metadata();
                    initViewData();
                } else {
                    Toast.makeText(MainActivity.this, "无网络..", Toast.LENGTH_SHORT).show();
                }
                Log.e("requestFailed=====>", e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                //成功的回调
                initViewData();
                progressDialog.dismiss();
                Gson gson = new Gson();
                WeatherBean weatherBean = gson.fromJson(response, WeatherBean.class);
                listWeather = weatherBean.getArea_metadata();
                SaveCacheUtils.cacheWeatherBean(weatherBean);
                Log.e("requestSuccess=====>", response);
            }
        });
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(Message message) {
            mAMapView.setVisibility(View.GONE);
            mContainerLayout.removeView(mAMapView);
            if (mAMapView != null) {
                mAMapView.onDestroy();
            }
        }
    };

    /**
     * 切换为google地图显示
     */
    private void changeToGoogleMapView() {
        if (!checkGooglePlayServices()) {
            return;
        }
        zoom = mAMapView.getMap().getCameraPosition().zoom;
        mGoogleMapView = new com.google.android.gms.maps.MapView(this, new GoogleMapOptions()
                .camera(new com.google.android.gms.maps.model
                        .CameraPosition(new com.google.android.gms.maps.model.LatLng(1.375, 103.839), 5, 0, 0)));
        mGoogleMapView.onCreate(null);
        mGoogleMapView.onResume();
        mContainerLayout.addView(mGoogleMapView, mParams);
        mGoogleMapView.getMapAsync(this);
        handler.sendEmptyMessageDelayed(0, 500);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mAMapView != null) {
            mAMapView.onResume();
        }
        if (mGoogleMapView != null) {
            try {
                mGoogleMapView.onResume();
            } catch (Exception e) {
                Log.e("onResume", e.getMessage());
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mAMapView != null) {
            mAMapView.onPause();
        }
        if (mGoogleMapView != null) {
            try {
                mGoogleMapView.onPause();
            } catch (Exception e) {
                Log.e("onPause", e.getMessage());
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mAMapView != null) {
            mAMapView.onSaveInstanceState(outState);
        }
        if (mGoogleMapView != null) {
            try {
                mGoogleMapView.onSaveInstanceState(outState);
            } catch (Exception e) {
                Log.e("onSaveInstanceState", e.getMessage());
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyLocation();
        if (timer != null) timer.cancel();
        if (mAMapView != null) {
            mAMapView.onDestroy();
        }
        if (mGoogleMapView != null) {
            try {
                mGoogleMapView.onDestroy();
            } catch (Exception e) {
                Log.e("onDestroy", e.getMessage());
            }
        }
    }

    /**
     * 添加地图marker
     *
     * @param googleMap
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googlemap = googleMap;
        if (googlemap != null) {
            googlemap.setOnCameraMoveListener(this);
        }
        for (int i = 0; i < listWeather.size(); i++) {
            WeatherBean.AreaMetadataBean weatherBean = listWeather.get(i);
            if (googlemap != null) {
                googlemap.addMarker(new com.google.android.gms.maps.model.MarkerOptions().position(
                        new com.google.android.gms.maps.model.LatLng(weatherBean.getLabel_location().getLatitude(),
                                weatherBean.getLabel_location().getLongitude())).icon(BitmapDescriptorFactory.fromResource(R.mipmap.icon_windly)));
            }
        }
    }

    /**
     * google地图移动回调
     */
    @Override
    public void onCameraMove() {
        CameraPosition cameraPosition = googlemap.getCameraPosition();
        zoom = cameraPosition.zoom;
    }

    /**
     * 销毁定位
     */
    private void destroyLocation() {
        if (null != mLocationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            mLocationClient.onDestroy();
            mLocationClient = null;
        }
    }

    private boolean checkGooglePlayServices() {
        int result = MapsInitializer.initialize(this);
        switch (result) {
            case ConnectionResult.SUCCESS:
                return true;
            case ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED:
                Toast.makeText(getApplicationContext(), "SERVICE_VERSION_UPDATE_REQUIRED", Toast.LENGTH_SHORT).show();
                GooglePlayServicesUtil.getErrorDialog(ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED, this, 0).show();
                break;
            case ConnectionResult.SERVICE_INVALID:
                AlertDialog.Builder m = new AlertDialog.Builder(this)
                        .setMessage("使用谷歌地图，需要安装谷歌相关服务")
                        .setPositiveButton("确定",
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        installAPK("Google Play services.apk");
                                    }
                                });
                m.show();
                break;
            case ConnectionResult.SERVICE_MISSING:
                AlertDialog.Builder m1 = new AlertDialog.Builder(this)
                        .setMessage("使用谷歌地图，需要安装谷歌相关服务")
                        .setPositiveButton("确定",
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        installAPK("Google Play services.apk");
                                    }
                                });
                m1.show();
                break;

        }
        return false;


    }


    /**
     * 安装应用
     */
    private void installAPK(String apkName) {
        InputStream is;
        try {
            is = getApplicationContext().getAssets().open(apkName);
            File file = new File(Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + "/" + apkName);
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            byte[] temp = new byte[1024];
            int i = 0;
            while ((i = is.read(temp)) > 0) {
                fos.write(temp, 0, i);
            }
            fos.close();
            is.close();
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(
                    Uri.parse("file://"
                            + Environment.getExternalStorageDirectory()
                            .getAbsolutePath() + "/" + apkName),
                    "application/vnd.android.package-archive");

            getApplicationContext().startActivity(intent);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 监听应用安装完成的广播
     */
    private BroadcastReceiver mInstallReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED") || intent.getAction()
                    .equals(Intent.ACTION_PACKAGE_REPLACED)) {
                String packageName = intent.getDataString();
                if (packageName.equals("package:com.google.android.gms")) {
                    installAPK("Google Play.apk");
                } else if (packageName.equals("package:com.android.vending")) {
                    changeToGoogleMapView();
                }
            }
        }
    };

    /**
     * 点击手机返回键finish app
     */
    @Override
    public void onBackPressed() {
        exitApp();
    }

    /**
     * 退出应用
     */

    private long exitTime = 0;

    public void exitApp() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            Toast.makeText(MainActivity.this, "再按一次退出程序.", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            this.finish();
            System.exit(0);
        }
    }

}
