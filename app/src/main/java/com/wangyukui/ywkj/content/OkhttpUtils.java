package com.wangyukui.ywkj.content;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/11/24.
 */

public class OkhttpUtils {
    private static OkhttpUtils mInstance;
    private OkHttpClient okHttpClient;
   public synchronized static OkhttpUtils getInstance(){
       if (mInstance==null){
           mInstance=new OkhttpUtils();
       }
       return mInstance;
   }

    public OkhttpUtils(){
        okHttpClient=new OkHttpClient();
        okHttpClient.setConnectTimeout(10, TimeUnit.SECONDS);
        okHttpClient.setReadTimeout(30,TimeUnit.SECONDS);
        okHttpClient.setWriteTimeout(30,TimeUnit.SECONDS);

        okHttpClient.setCookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ORIGINAL_SERVER));
    }

    public void sendPostHttp(String url, String apiToken,FormEncodingBuilder builder, Callback callBack){
        Request request=new Request.Builder()
                .url(url)
                .addHeader("Accept","application/json")
                .addHeader("Authorization","Bearer "+apiToken)
                .post(builder.build())
                .build();
        okHttpClient.newCall(request).enqueue(callBack);
    }
}
