package junte.com.recycleviewtest;

import android.app.Application;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.store.PersistentCookieStore;
import com.lzy.okgo.model.HttpHeaders;

import java.util.logging.Level;

import okhttp3.Headers;

/**
 * @author: wujun
 * @Filename:
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2017/4/1 11:07
 */
public class MyApplication extends Application {
   private final  static  String  TAG="";
    public static MyApplication mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext=this;
      initOkGo();

    }

    private void initOkGo() {
        HttpHeaders hearders=new HttpHeaders();
        OkGo.init(this);

        OkGo.getInstance().debug(TAG, Level.INFO,true)
                .setConnectTimeout(OkGo.DEFAULT_MILLISECONDS)
                .setReadTimeOut(OkGo.DEFAULT_MILLISECONDS)
                .setWriteTimeOut(OkGo.DEFAULT_MILLISECONDS)
                .setCacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)
                .setRetryCount(3)
                .setCookieStore(new PersistentCookieStore())
                .setCertificates()
                .addCommonHeaders(hearders);


    }

    public static MyApplication getmContext() {
        return mContext;
    }
}
