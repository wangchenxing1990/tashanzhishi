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
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.deserializer.JavaObjectDeserializer;
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
import com.wangyukui.ywkj.adapter.MyJobBrowseAdapter;
import com.wangyukui.ywkj.bean.CompanyJobBean;
import com.wangyukui.ywkj.bean.JobCollectionBean;
import com.wangyukui.ywkj.bean.JobDestoryDataBean;
import com.wangyukui.ywkj.content.ContentUrl;
import com.wangyukui.ywkj.content.OkhttpUtils;
import com.wangyukui.ywkj.view.RecycleViewDivider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/26.
 */

public class JobFragment extends Fragment {
    private FrameLayout frameLayout;
    private View successView;
    private View loadingView;
    private View emptyView;
    private String api_token="";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        SharedPreferences share = getActivity().getSharedPreferences("Activity", Context.MODE_PRIVATE);
        api_token = share.getString("api_token", "");
        frameLayout = new FrameLayout(getActivity());
        successView = createSuccessView();
        loadingView = inflater.inflate(R.layout.loading_view, null);
        emptyView = inflater.inflate(R.layout.empty_view_icon, null);
        TextView textview_click = (TextView) emptyView.findViewById(R.id.textview_click);
        textview_click.setText("还没有职位浏览记录");
        frameLayout.addView(loadingView);
        initData();
        return frameLayout;
    }

    String str="";
    int code=0;
    private void initData() {
        FormEncodingBuilder formding = new FormEncodingBuilder();
        OkhttpUtils.getInstance().sendPostHttp(ContentUrl.BASE_URL + ContentUrl.USER_BROWSE_JOB, api_token, formding, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                String eM = e.getMessage().toString();
                if (eM != null) {
                    handler.sendEmptyMessage(110);

                }
            }

            @Override
            public void onResponse(Response response) throws IOException {
                str = response.body().string();
                code = response.code();
                Log.i("--------1", str);
                handler.sendEmptyMessage(100);
            }
        });

    }

    /**
     * 创建成功界面
     *
     * @return
     */
    XRefreshView xrefreshview;
    RecyclerView recycler_view_test_rv;
    MyJobBrowseAdapter adapter;
    List<JobDestoryDataBean.DataBean> listJob = new ArrayList<>();
    private View createSuccessView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fram_listview, null);
        xrefreshview = (XRefreshView) view.findViewById(R.id.xrefreshview);
        recycler_view_test_rv = (RecyclerView) view.findViewById(R.id.recycler_view_test_rv);
        recycler_view_test_rv.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayoutManager.HORIZONTAL, 1, 0xffdfdfdf));
        recycler_view_test_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        xrefreshview.setPullLoadEnable(true);

        adapter = new MyJobBrowseAdapter(listJob, getActivity());
        recycler_view_test_rv.setAdapter(adapter);
        xrefreshview.setAutoLoadMore(false);
        xrefreshview.setPinnedTime(1000);
        xrefreshview.setMoveForHorizontal(true);
        adapter.setCustomLoadMoreView(new XRefreshViewFooter(getActivity()));

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

        adapter.setOnItemClickListener(new MyJobBrowseAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, JobDestoryDataBean.DataBean data) {
                Intent intent = new Intent(getActivity(), JobInfoActivity.class);
                intent.putExtra("id", data.getId());
                startActivity(intent);
            }
        });

        return view;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 100:
                    switchView();//根据请求的结果切换不同的界面
                    break;
                case 300:
                    parsDataRefresh();//解析刷新的数据
                    break;
                case 301:
                    parsPullDataMore();//解析上拉加载更多数据
                    break;

                case ONE_PAGE:
                    parsOneDataNew();//解析当前是第一页的时候的数据
                    break;

            }
        }
    };
    /**
     * 根据不同的结果切换不同的界面
     */

    private JobDestoryDataBean jobDestoryDataBean;
    private void switchView() {
        if (code == 200) {
            Gson gson = new Gson();
            jobDestoryDataBean = gson.fromJson(str, JobDestoryDataBean.class);
            if ("1".equals(jobDestoryDataBean.getCode())) {
                frameLayout.removeView(loadingView);
                if (jobDestoryDataBean.getData().size() == 0) {
                    frameLayout.addView(emptyView);
                } else {
                    frameLayout.addView(successView);
                    if ("1".equals(jobDestoryDataBean.getCurrent_page()) && jobDestoryDataBean.getData().size() < 15) {
                        xrefreshview.setPullLoadEnable(false);
                    }

                    adapter.setData(jobDestoryDataBean.getData(), false);
                }
            } else if ("0".equals(jobDestoryDataBean.getCode())) {

            } else if ("-1".equals(jobDestoryDataBean.getCode())) {
                frameLayout.removeView(loadingView);
                frameLayout.removeView(emptyView);
            }
        } else {
            Toast.makeText(getActivity(), "联网失败", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 下拉刷新
     */
    private void pullToRefreshData() {
        if ("1".equals(jobDestoryDataBean.getCurrent_page())) {
            refreshOnePage();//下拉刷新当前页面是第一页的时候的界面

        } else {
            if (jobDestoryDataBean.getPrev_page_url() == null || "".equals(jobDestoryDataBean.getPrev_page_url())) {
                xrefreshview.stopRefresh();//停止刷新

            } else {
                refreshData();//下拉刷新
            }
        }
    }

    /**
     * 上垃加载更多
     */
    private void loadMoreData() {
        if (jobDestoryDataBean.getNext_page_url() == null || "".equals(jobDestoryDataBean.getNext_page_url())) {
            xrefreshview.setLoadComplete(true);
        } else {
            pullLoadMoreData();//上垃加载更多数据
        }
    }

    /**
     * 下拉刷新当前页面是第一页的时候的界面
     */
    private static final int ONE_PAGE = 1;

    private void refreshOnePage() {
        FormEncodingBuilder formEncoding = new FormEncodingBuilder();
        formEncoding.add("page", "1");
        OkhttpUtils.getInstance().sendPostHttp(ContentUrl.BASE_URL + ContentUrl.USER_BROWSE_JOB, api_token, formEncoding, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                str = response.body().string();
                code = response.code();
                Log.i("aaaaaaaa", str);
                handler.sendEmptyMessage(ONE_PAGE);
            }
        });

    }

    /**
     * 下拉刷新
     */
    private void refreshData() {
        FormEncodingBuilder fromd = new FormEncodingBuilder();
        OkhttpUtils.getInstance().sendPostHttp(jobDestoryDataBean.getPrev_page_url(), api_token, fromd, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                str = response.body().string();
                code = response.code();
                Log.i("qqqqqqqq", str);
                handler.sendEmptyMessage(300);
            }
        });
    }

    /**
     * 上垃加载更多数据
     */
    private void pullLoadMoreData() {
        FormEncodingBuilder formEncoding = new FormEncodingBuilder();
        OkhttpUtils.getInstance().sendPostHttp(jobDestoryDataBean.getNext_page_url(), api_token, formEncoding, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                str = response.body().string();
                code = response.code();
                Log.i("12222222", str);
                handler.sendEmptyMessage(301);
            }
        });
    }

    /**
     * 解析下拉刷新的数据
     */
    private void parsDataRefresh() {
        if (code == 200) {
            Gson gson = new Gson();
            jobDestoryDataBean = gson.fromJson(str, JobDestoryDataBean.class);
            if ("1".equals(jobDestoryDataBean.getCode())) {
                adapter.setData(jobDestoryDataBean.getData(), true);
                xrefreshview.stopRefresh();
                xrefreshview.setLoadComplete(false);
            } else {
                Toast.makeText(getActivity(), "刷新失败", Toast.LENGTH_SHORT).show();
            }
        } else {

        }
    }

    /**
     * 解析上啦加载更多数据
     */
    private void parsPullDataMore() {
        xrefreshview.stopLoadMore();
        if (code == 200) {
            Gson gson = new Gson();
            jobDestoryDataBean = gson.fromJson(str, JobDestoryDataBean.class);
            if ("1".equals(jobDestoryDataBean.getCode())) {
                if ("1".equals(jobDestoryDataBean.getCurrent_page()) && jobDestoryDataBean.getData().size() < 15) {
                    xrefreshview.setLoadComplete(true);
                }
                adapter.setData(jobDestoryDataBean.getData(), false);
            } else {
                Toast.makeText(getActivity(), "刷新失败", Toast.LENGTH_SHORT).show();
            }
        } else {

        }
    }

    /**
     * 解析当前页是第一页的时候的数据
     */
    private void parsOneDataNew() {
        if (code == 200) {
            Gson gson = new Gson();
            jobDestoryDataBean = gson.fromJson(str, JobDestoryDataBean.class);
            if ("1".equals(jobDestoryDataBean.getCode())) {
                adapter.setData(jobDestoryDataBean.getData(), true);
                if (jobDestoryDataBean.getNext_page_url() == null || "".equals(jobDestoryDataBean.getNext_page_url())) {
                    xrefreshview.setLoadComplete(true);
                    xrefreshview.setPullLoadEnable(false);
                }
            }
            xrefreshview.stopRefresh();
        } else if ("-1".equals(jobDestoryDataBean.getCode())) {

        }

        if (jobDestoryDataBean.getMsg() != null && !"".equals(jobDestoryDataBean.getMsg())) {
            Toast.makeText(getActivity(), jobDestoryDataBean.getMsg(), Toast.LENGTH_SHORT).show();
        }

    }
}
