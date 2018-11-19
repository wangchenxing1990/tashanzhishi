package com.wangyukui.ywkj.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
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
import com.wangyukui.wxapi.WXEntryActivity;
import com.wangyukui.ywkj.adapter.MyJobCollectionAdapter;
import com.wangyukui.ywkj.bean.CompanyJobBean;
import com.wangyukui.ywkj.bean.JobCollectionBean;
import com.wangyukui.ywkj.content.ContentUrl;
import com.wangyukui.ywkj.content.OkhttpUtils;
import com.wangyukui.ywkj.tools.UiUtils;
import com.wangyukui.ywkj.view.RecycleViewDivider;
import com.wangyukui.ywkj.view.SwipeItemLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/5.
 */

public class JobCollectionActivity extends BaseActiviyt implements View.OnClickListener {
    @Override
    public int getLayoutId() {
        return R.layout.activity_job_collection;
    }

    @Override
    public void initData() {
        gainDataFromService();
    }

    private FrameLayout fram_job_back;
    private FrameLayout frameLayout;
    private View loadingView;
    private View emptyView;
    private View successView;
    private View noCollectView;

    @Override
    public void initView() {
        fram_job_back = (FrameLayout) findViewById(R.id.fram_job_back);
        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        loadingView = createLoadingView();
        emptyView = createEmptyView();
        successView = createSucessView();
        noCollectView = createNoCollectionView();

        frameLayout.addView(loadingView);
        fram_job_back.setOnClickListener(this);

    }

    /**
     * 创建没有收藏简历的界面
     *
     * @return
     */
    private View createNoCollectionView() {
        View view = View.inflate(JobCollectionActivity.this, R.layout.empty_resume_view, null);
        TextView textview = (TextView) view.findViewById(R.id.textview);
        textview.setText("你还没有收藏职位");
        return view;
    }

    /**
     * 创建加载成功的界面
     *
     * @return
     */
    private XRefreshView xrefreshview;
    private RecyclerView recycler_view_test_rv;

    private View createSucessView() {

        View view = View.inflate(JobCollectionActivity.this, R.layout.fram_listview_job, null);
        recycler_view_test_rv = (RecyclerView) view.findViewById(R.id.recycler_view_test_rv);
        xrefreshview = (XRefreshView) view.findViewById(R.id.xrefreshview);
        recycler_view_test_rv.setBackgroundColor(0xffffffff);
        recycler_view_test_rv.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL, 1, 0xffdfdfdf));

        recycler_view_test_rv.setLayoutManager(new LinearLayoutManager(this));
        recycler_view_test_rv.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(this));
        xrefreshview.setPullLoadEnable(true);

        adapter = new MyJobCollectionAdapter(listCollection, JobCollectionActivity.this);
        recycler_view_test_rv.setAdapter(adapter);

        xrefreshview.setAutoLoadMore(false);
        xrefreshview.setPinnedTime(1000);
        xrefreshview.setMoveForHorizontal(true);

        adapter.setCustomLoadMoreView(new XRefreshViewFooter(this));

        xrefreshview.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh(boolean isPullDown) {//下拉刷新
                super.onRefresh(isPullDown);
                pullToRefreshData();//下拉刷新
            }

            @Override
            public void onLoadMore(boolean isSilence) {//上垃加载
                super.onLoadMore(isSilence);
                loadMoreData();//上垃加载更多
            }
        });
        return view;
    }

    /**
     * 创建网络出错的空界面
     *
     * @return
     */
    private TextView textview_click;

    private View createEmptyView() {
        View view = View.inflate(JobCollectionActivity.this, R.layout.empty_view, null);
        textview_click = (TextView) view.findViewById(R.id.textview_click);
        textview_click.setOnClickListener(this);
        return view;
    }

    /**
     * 创建正在加载的界面
     *
     * @return
     */
    private View createLoadingView() {
        View view = View.inflate(JobCollectionActivity.this, R.layout.loading_view, null);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fram_job_back:
                finish();
                break;
            case R.id.textview_click:
                frameLayout.removeView(emptyView);
                frameLayout.addView(loadingView);
                gainDataFromService();//从服务器获取数据
                break;
        }
    }

    /**
     * 从服务器获取数据
     */
    String str;
    int code;
    String api_token;

    private void gainDataFromService() {
        SharedPreferences shared = getSharedPreferences("Activity", MODE_PRIVATE);
        api_token = shared.getString("api_token", "");
        FormEncodingBuilder formEncoding = new FormEncodingBuilder();
        OkhttpUtils.getInstance().sendPostHttp(ContentUrl.BASE_URL + ContentUrl.FAVORITES, api_token, formEncoding, new Callback() {
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
                Log.i("aaaaaaaa", str);
                Log.i("vvvvvvvv", code + "");
                handler.sendEmptyMessage(100);
            }
        });
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 100:
                    switchView();//切换不同的view界面
                    break;
                case 110:
                    frameLayout.removeView(loadingView);
                    frameLayout.addView(emptyView);
                    break;
                case 130:
                    loadMoreResult();
                    break;
                case 300:
                    parsRefreshNewData();//解析下拉刷新获取的数据
                    break;
                case ONE_PAGE:
                    parsOnePageData();//解析当前也为第一页的时候下拉刷新的数据
                    break;
            }
        }
    };


    /**
     * 解析下拉刷新获取的数据
     */
    private void parsRefreshNewData() {
        if (code == 200) {
            Gson gson = new Gson();
            jobCollectionBean = gson.fromJson(str, JobCollectionBean.class);
            if ("1".equals(jobCollectionBean.getCode())) {
                adapter.setData(jobCollectionBean.getData(),true);
                xrefreshview.stopRefresh();
                xrefreshview.setLoadComplete(false);
            } else {

            }
        } else {

        }
    }

    /**
     * 加载更多的返回结果
     */
    private void loadMoreResult() {
        xrefreshview.stopLoadMore();
        if (code == 200) {
            Gson gson = new Gson();
            jobCollectionBean = gson.fromJson(str, JobCollectionBean.class);
            if (jobCollectionBean.getCode().equals("1")) {

                adapter.setData(jobCollectionBean.getData(), false);
            } else {

            }
        } else {

        }
    }

    /**
     * 切换不同的view界面
     */

    private JobCollectionBean jobCollectionBean;

    private void switchView() {
        if (code == 200) {
            Gson gson = new Gson();
            jobCollectionBean = gson.fromJson(str, JobCollectionBean.class);

            if (jobCollectionBean.getCode().equals("1")) {

                if (jobCollectionBean.getData().size() == 0) {//没有收藏职位的界面
                    frameLayout.removeView(loadingView);
                    frameLayout.addView(noCollectView);
                } else {//有收藏职位的界面
                    frameLayout.removeView(loadingView);
                    frameLayout.addView(successView);
                    if (jobCollectionBean.getData().size() < 15 && jobCollectionBean.getCurrent_page() == 1) {
                        xrefreshview.setPullLoadEnable(false);
                    }
                    adapter.setData(jobCollectionBean.getData(), false);

                }
            } else if (jobCollectionBean.getData().equals("0")) {
                //TODO
            } else if (jobCollectionBean.getData().equals("-1")) {
                Intent intent = new Intent(JobCollectionActivity.this, WXEntryActivity.class);
                intent.putExtra("register", "loginMine");
                startActivity(intent);
                finish();
            }
            if (jobCollectionBean.getMsg() != null && !"".equals(jobCollectionBean.getMsg())) {
                Toast.makeText(JobCollectionActivity.this, jobCollectionBean.getMsg(), Toast.LENGTH_SHORT).show();
            }

        } else {
            frameLayout.removeView(loadingView);
            frameLayout.addView(emptyView);
            Toast.makeText(JobCollectionActivity.this, "联网超时", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 解析收藏职位的简历
     */
    MyJobCollectionAdapter adapter;
    private List<JobCollectionBean.DataBean> listCollection = new ArrayList();

    /**
     * 上垃加载更多
     */
    private void loadMoreData() {
        if (jobCollectionBean.getNext_page_url() == null || "".equals(jobCollectionBean.getNext_page_url())) {
            xrefreshview.setLoadComplete(true);
        } else {
            loadMoreDataAgain();
        }
    }

    /**
     * 下拉刷新数据
     */
    private void pullToRefreshData() {
        if ("1".equals(jobCollectionBean.getCurrent_page())) {
            refreshOnePageData();//当前页为第一页的时候的下拉刷新
        } else {
            if (jobCollectionBean.getPrev_page_url() == null) {
                xrefreshview.stopRefresh();
            } else {
                refreshDataFromService();//下拉刷新获取新数据
            }
        }
    }

    /**
     * 当前页为第一页的时候的下拉刷新
     */
    private static final int ONE_PAGE = 2;

    private void refreshOnePageData() {
        FormEncodingBuilder formEncod = new FormEncodingBuilder();
        formEncod.add("page", "1");
        OkhttpUtils.getInstance().sendPostHttp(ContentUrl.BASE_URL + ContentUrl.FAVORITES, api_token, formEncod, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                str = response.body().string();
                code = response.code();
                Log.i("iiiiooooo", str);
                handler.sendEmptyMessage(ONE_PAGE);
            }
        });

    }

    /**
     * 解析当前也为第一页的时候下拉刷新的数据
     */
    private void parsOnePageData() {
        if (code == 200) {
            Gson gson = new Gson();
            jobCollectionBean = gson.fromJson(str, JobCollectionBean.class);
            if ("1".equals(jobCollectionBean.getCode())) {

                adapter.setData(jobCollectionBean.getData(), true);
                if (jobCollectionBean.getNext_page_url() == null || "".equals(jobCollectionBean.getNext_page_url())) {
                    xrefreshview.setLoadComplete(true);
                    xrefreshview.setPullLoadEnable(false);
                }
                xrefreshview.stopRefresh();

            } else if ("-1".equals(jobCollectionBean.getCode())) {

            }
            if (jobCollectionBean.getMsg() != null && !"".equals(jobCollectionBean.getMsg())) {
                Toast.makeText(JobCollectionActivity.this, jobCollectionBean.getMsg(), Toast.LENGTH_SHORT).show();
            }

        } else {

        }
    }

    /**
     * 下拉刷新获取新数据
     */
    private void refreshDataFromService() {
        SharedPreferences shares = getSharedPreferences("Activity", MODE_PRIVATE);
        api_token = shares.getString("api_token", "");
        FormEncodingBuilder formencoding = new FormEncodingBuilder();
        OkhttpUtils.getInstance().sendPostHttp(jobCollectionBean.getPrev_page_url(), api_token, formencoding, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                str = response.body().string();
                code = response.code();
                Log.i("aaaaaaa", str);
                handler.sendEmptyMessage(300);
            }
        });
    }

    /**
     * 加载更多数据
     */
    private void loadMoreDataAgain() {
        if (jobCollectionBean.getNext_page_url() == null || "".equals(jobCollectionBean.getNext_page_url())) {
            xrefreshview.setLoadComplete(true);
        } else {
            int in = jobCollectionBean.getCurrent_page() + 1;
            FormEncodingBuilder form = new FormEncodingBuilder();
            form.add("page", String.valueOf(in));
            OkhttpUtils.getInstance().sendPostHttp(jobCollectionBean.getNext_page_url(), api_token, form, new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {

                }

                @Override
                public void onResponse(Response response) throws IOException {
                    str = response.body().string();
                    code = response.code();
                    Log.i("11111", str);
                    handler.sendEmptyMessage(130);
                }
            });

        }
    }
}
