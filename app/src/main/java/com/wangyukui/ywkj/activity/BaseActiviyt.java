package com.wangyukui.ywkj.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/5.
 */

public abstract class BaseActiviyt extends AppCompatActivity {
    List<BaseActiviyt> mActivitys=new LinkedList<BaseActiviyt>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
     //   ButterKnife.bind(this);
        synchronized (mActivitys){
            mActivitys.add(this);
        }


        //初始化数据
        initData();
        //初始化数据
        initView();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        synchronized (mActivitys){
            mActivitys.remove(this);
          //  killAll();
        }
    }

    public void killAll(){
        //复制一份mActivitys的集合给copyActivity
     List<BaseActiviyt>  copyActivitys;
        synchronized(mActivitys){
            copyActivitys=new LinkedList<BaseActiviyt>(mActivitys);
        }

        for (BaseActiviyt activity : copyActivitys) {
            activity.finish();
        }

        //杀死当前进程
        android.os.Process.killProcess(android.os.Process.myPid());

    }


    /**
     * 获取布局文件的ID
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 初始化数据
     */
    public abstract void initData();

    /**
     * 初始化view
     */
    public abstract void initView();

}
