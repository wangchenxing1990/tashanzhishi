package com.wangyukui.ywkj.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XRefreshViewFooter;
import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.wangyukui.R;
import com.wangyukui.ywkj.activity.JobInfoActivity;
import com.wangyukui.ywkj.adapter.MyReceiveResumeAdapter;
import com.wangyukui.ywkj.bean.CompanyJobBean;
import com.wangyukui.ywkj.bean.FindWorkDataBean;
import com.wangyukui.ywkj.content.ContentUrl;
import com.wangyukui.ywkj.content.OkhttpUtils;
import com.wangyukui.ywkj.view.RecycleViewDivider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import rx.android.plugins.RxAndroidSchedulersHook;

/**
 * Created by Administrator on 2017/8/26.
 */

public class FindingWorkFragment extends Fragment {
    FrameLayout frameLayout;
    String api_token;
    View loadingView;
    View successView;
    View empty;

    public FindingWorkFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        SharedPreferences shares = getActivity().getSharedPreferences("Activity", Context.MODE_PRIVATE);
        api_token = shares.getString("api_token", "");
        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("data",Context.MODE_PRIVATE);
        idid=sharedPreferences.getString("com_id","");
        frameLayout=new FrameLayout(getActivity());
        loadingView=inflater.inflate(R.layout.loading_view,null);
        successView=createSuccessView();
        empty=inflater.inflate(R.layout.empty_view_icon,null);
        frameLayout.addView(loadingView);

        recyclerViewReceiveResumeAdapter = new MyReceiveResumeAdapter(allJobList, getActivity());
        recycler_view_test_rv.setAdapter(recyclerViewReceiveResumeAdapter);

        xrefreshview.setPullLoadEnable(true);
        xrefreshview.setAutoLoadMore(false);
        xrefreshview.setPinnedTime(1000);
        xrefreshview.setMoveForHorizontal(true);

        recyclerViewReceiveResumeAdapter.setCustomLoadMoreView(new XRefreshViewFooter(getActivity()));

        recyclerViewReceiveResumeAdapter.setOnItemClickListener(new MyReceiveResumeAdapter.OnRecyclerViewOnItemClickListener() {
            @Override
            public void onItemClick(View v, FindWorkDataBean.DataBean data) {
                Intent intent = new Intent(getActivity(), JobInfoActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("id", data.getId());
                startActivity(intent);
            }
        });

        initData();
        return frameLayout;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 110:
                    Toast.makeText(getActivity(), "联网超时，请重试", Toast.LENGTH_SHORT).show();
                    break;
                case 120:
                    parsCompanyJob();//解析公司所有职位的数据
                    break;
                case 130:
                    parsRefreshCompanyJob();//解析下拉刷新获取的数据
                    break;
                case 300:
                    parsLoadNewData();//上垃加载更多的数据
                    break;
                case 150:
                    parsRefreshNewData();//解析下拉刷新的数据
                    break;
                case ONE_PAGE:
                    parsOnePageData();//解析当前数据是第一个页面的数据
                    break;
            }
        }
    };

    /**
     * 解析当前数据是第一个页面的数据
     */
    private void parsOnePageData() {
        if (code == 200) {
           Gson gson=new Gson();
            findWorkDataBean=gson.fromJson(str,FindWorkDataBean.class);

            if ("1".equals(findWorkDataBean.getCode())) {
                if (next_page_url==null||"".equals(next_page_url)){
                    xrefreshview.setLoadComplete(true);
                    xrefreshview.setPullLoadEnable(false);
                }
                xrefreshview.stopRefresh();
                recyclerViewReceiveResumeAdapter.setData(findWorkDataBean.getData(),true);
            } else if ("-1".equals(findWorkDataBean.getCode())) {

            }

            if (findWorkDataBean.getMsg()!=null&&!"".equals(findWorkDataBean.getMsg())){
                Toast.makeText(getActivity(),findWorkDataBean.getMsg(),Toast.LENGTH_SHORT).show();
            }

        } else {

        }
    }
    private List<FindWorkDataBean.DataBean> allJobList = new ArrayList<>();

    MyReceiveResumeAdapter recyclerViewReceiveResumeAdapter;
    /**
     * 解析数据公司所有职位
     */
    private FindWorkDataBean findWorkDataBean;
    private void parsCompanyJob() {
        if (code == 200) {
            Gson gson=new Gson();
            findWorkDataBean=gson.fromJson(str,FindWorkDataBean.class);
            if (findWorkDataBean.getCode().equals("1")) {
                frameLayout.removeView(loadingView);
                frameLayout.addView(successView);
                recyclerViewReceiveResumeAdapter.setData(findWorkDataBean.getData(),false);
                if (findWorkDataBean.getNext_page_url() == null || "".equals(findWorkDataBean.getNext_page_url())) {

//                     xrefreshview.setAutoLoadMore(true);
                     xrefreshview.setLoadComplete(true);
                    xrefreshview.setPullLoadEnable(false);
                    Log.i("a=========a", "111111"+next_page_url);
                }

            } else {
                Toast.makeText(getActivity(), findWorkDataBean.getMsg(), Toast.LENGTH_SHORT).show();
            }
            if (findWorkDataBean.getMsg()!=null&&!"".equals(findWorkDataBean.getMsg())){
                Toast.makeText(getActivity(),findWorkDataBean.getMsg(),Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getActivity(), "获取数据失败", Toast.LENGTH_SHORT).show();
        }
    }


    private void initData() {
        FormEncodingBuilder formEncoding = new FormEncodingBuilder();
        formEncoding.add("id", idid);
        OkhttpUtils.getInstance().sendPostHttp(ContentUrl.BASE_URL + ContentUrl.COMPANY_JOB_LIST, api_token, formEncoding, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                if (e.getMessage() != null) {
                    handler.sendEmptyMessage(110);
                }
            }

            @Override
            public void onResponse(Response response) throws IOException {
                str = response.body().string();
                code = response.code();
                Log.i("111111114", str);
                handler.sendEmptyMessage(120);
            }
        });
    }


    /**
     * 上垃加载更多的数据
     */
    private void parsLoadNewData() {
        if (code == 200) {
          Gson gson=new Gson();
            findWorkDataBean=gson.fromJson(str,FindWorkDataBean.class);
            if (findWorkDataBean.getCode().equals("1")) {

                recyclerViewReceiveResumeAdapter.setData(findWorkDataBean.getData(),false);
                xrefreshview.stopLoadMore();
            } else {
                Toast.makeText(getActivity(), findWorkDataBean.getMsg(), Toast.LENGTH_SHORT).show();
            }
        } else {

        }
    }
    /**
     * 解析下拉刷新获取的数据
     */
    private void parsRefreshCompanyJob() {
        FormEncodingBuilder formEncoding = new FormEncodingBuilder();
        OkhttpUtils.getInstance().sendPostHttp(prev_page_url, api_token, formEncoding, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                str = response.body().string();
                code = response.code();
                Log.i("112124124124", str);
                handler.sendEmptyMessage(150);
            }
        });

    }

    /**
     * 解析下拉刷新的数据n
     */
    private void parsRefreshNewData() {
        if (code == 200) {
           Gson gson=new Gson();
            findWorkDataBean=gson.fromJson(str,FindWorkDataBean.class);
            if ("1".equals(findWorkDataBean.getCode())) {

                recyclerViewReceiveResumeAdapter.setData(findWorkDataBean.getData(),true);
                xrefreshview.stopRefresh();
                xrefreshview.setLoadComplete(false);
            } else {

            }
        } else {

        }
    }

    private RecyclerView recycler_view_test_rv;
    private XRefreshView xrefreshview;
    private View createSuccessView() {
        View view=LayoutInflater.from(getActivity()).inflate(R.layout.fram_listview,null);
        recycler_view_test_rv = (RecyclerView) view.findViewById(R.id.recycler_view_test_rv);
        xrefreshview = (XRefreshView) view.findViewById(R.id.xrefreshview);
        recycler_view_test_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler_view_test_rv.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayoutManager.HORIZONTAL,1,0xffdfdfdf));
        xrefreshview.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh(boolean isPullDown) {
                super.onRefresh(isPullDown);
                refreshMoreData();
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                super.onLoadMore(isSilence);
                downLoadMoreData();
            }
        });
        return view;
    }

    /**
     * 下拉刷新更多数据
     */
    String prev_page_url;
    String next_page_url;
    String current_page;
    private void refreshMoreData() {
        if ("1".equals(findWorkDataBean.getCurrent_page())) {
            //refreshOnePageData();//刷新当前页面是第一个页面的数据
            xrefreshview.stopRefresh();
            xrefreshview.setLoadComplete(true);
        } else {
            if (findWorkDataBean.getPrev_page_url() == null||"".equals(findWorkDataBean.getPrev_page_url())) {
                xrefreshview.stopRefresh();
            } else {
                upLoadMore();//下拉加载更多
            }
        }
    }

    /**
     * 上垃加载更多数据
     */
    private String str;
    private int code;
    private void downLoadMoreData() {
        if (next_page_url == null||"".equals(next_page_url)) {
            xrefreshview.setLoadComplete(true);
            xrefreshview.stopRefresh();
        } else {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Activity", Context.MODE_PRIVATE);
            String api_token = sharedPreferences.getString("api_token", "");
            if (next_page_url == null) {
                xrefreshview.setLoadComplete(true);
            } else {
                OkHttpClient okHttpClient = new OkHttpClient();
                FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();
                Request request = new Request.Builder()
                        .url(next_page_url)
                        .addHeader(ContentUrl.ACCEPT, ContentUrl.APPJSON)
                        .addHeader(ContentUrl.ACCEPT, ContentUrl.BEAR + api_token)
                        .post(formEncodingBuilder.build())
                        .build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {

                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                        str = response.body().string();
                        code = response.code();
                        Log.i("::::::", str);
                        handler.sendEmptyMessage(300);
                    }
                });

            }
        }
    }

    /**
     * 刷新当前页面是第一个页面的数据
     */
    String idid;
    String uid;
    String company_name;
    String brands;
    String temptation;
    private static final int ONE_PAGE = 1;
    private void refreshOnePageData() {
        OkHttpClient okHttpClient = new OkHttpClient();
        FormEncodingBuilder formEncoding = new FormEncodingBuilder();
        formEncoding.add("page", "1");
        formEncoding.add("id", idid);
        Request request = new Request.Builder()
                .url(ContentUrl.BASE_URL + ContentUrl.COMPANY_JOB)
                .addHeader(ContentUrl.ACCEPT, ContentUrl.APPJSON)
                .addHeader(ContentUrl.AUTHORIZATION, ContentUrl.BEAR+api_token)
                .post(formEncoding.build())
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                str = response.body().string();
                code = response.code();
                Log.i("aassssss", str);
                handler.sendEmptyMessage(ONE_PAGE);
            }
        });

    }


    /**
     * 下拉加载更多
     */
    private void upLoadMore() {
        OkHttpClient okhttp = new OkHttpClient();
        FormEncodingBuilder form = new FormEncodingBuilder();

        Request request = new Request.Builder()
                .url(prev_page_url)
                .addHeader(ContentUrl.ACCEPT, ContentUrl.APPJSON)
                .addHeader(ContentUrl.AUTHORIZATION, ContentUrl.BEAR+api_token)
                .post(form.build())
                .build();
        okhttp.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                str = response.body().string();
                code = response.code();
                Log.i("ggggg", str);
                handler.sendEmptyMessage(130);
            }
        });
    }


}
