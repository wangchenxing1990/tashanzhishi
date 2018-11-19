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

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XRefreshViewFooter;
import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.wangyukui.R;
import com.wangyukui.ywkj.activity.WebViewActivity;
import com.wangyukui.ywkj.adapter.MyListFragmentAdapter;
import com.wangyukui.ywkj.bean.ListFragmentBean;
import com.wangyukui.ywkj.content.ContentUrl;
import com.wangyukui.ywkj.content.OkhttpUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/28.
 */

public class NewsListFragment extends Fragment {
    public static Fragment newInstance(int type) {
        Bundle args = new Bundle();
        NewsListFragment fragment = new NewsListFragment();
        args.putInt("type", type);
        fragment.setArguments(args);
        return fragment;
    }

    ListFragmentBean listFragmentBean;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1000:
                    framLayout.removeAllViews();
                    framLayout.removeView(loadingView);
                    framLayout.addView(successView);
                    Gson gson = new Gson();
                    listFragmentBean = gson.fromJson(string, ListFragmentBean.class);
                    adapter.setData(listFragmentBean.getData(),false);
                    break;
                case 110:
                    if (code==200){
                        Gson gsons = new Gson();
                        listFragmentBean = gsons.fromJson(string, ListFragmentBean.class);

                        if (listFragmentBean.getCode().equals("1")){
                            adapter.setData(listFragmentBean.getData(),true);
                            xrefreshview.stopRefresh(true);
                        }else{

                        }

                    }

                    break;
                case 130:
                    if (code==200){
                        Gson gsons = new Gson();
                        listFragmentBean = gsons.fromJson(string, ListFragmentBean.class);
                        if (listFragmentBean.getCode().equals("1")){
                            adapter.setData(listFragmentBean.getData(),false);
                            if (listFragmentBean.getNext_page_url()==null||"".equals(listFragmentBean.getNext_page_url())){
                                xrefreshview.setLoadComplete(true);

                            }else{
                                xrefreshview.setLoadComplete(false);
                            }
                        }else{

                        }

                    }
                    break;
            }
        }
    };
    int mType;
    String api_token = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mType = getArguments().getInt("type");
        SharedPreferences share = getActivity().getSharedPreferences("Activity", Context.MODE_PRIVATE);
        api_token = share.getString("api_token", "");
    }

    private View loadingView;
    private View emptyView;
    private View successView;
    private FrameLayout framLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (framLayout == null) {
            framLayout = new FrameLayout(getActivity());
            loadingView = createLoadingView();
            emptyView = createEmptyView();
            successView = createSuccessView();
            framLayout.removeAllViews();
            framLayout.addView(loadingView);
            gainServiceData();
        }

        return framLayout;
    }

    /**
     * 创建加载成功的界面
     *
     * @return
     */
    private XRefreshView xrefreshview;
    private RecyclerView recycler_view_test_rv;
    private MyListFragmentAdapter adapter;
    private List<ListFragmentBean.DataBean> listData = new ArrayList();

    private View createSuccessView() {
        View view = View.inflate(getActivity(), R.layout.fram_listview, null);
        xrefreshview = (XRefreshView) view.findViewById(R.id.xrefreshview);
        recycler_view_test_rv = (RecyclerView) view.findViewById(R.id.recycler_view_test_rv);
        recycler_view_test_rv.setBackgroundColor(0xffefefef);
        recycler_view_test_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        xrefreshview.setPullLoadEnable(true);
        xrefreshview.setAutoLoadMore(false);
        xrefreshview.setPinnedTime(1000);
        xrefreshview.setMoveForHorizontal(true);

        adapter = new MyListFragmentAdapter(listData, getActivity());
        adapter.setCustomLoadMoreView(new XRefreshViewFooter(getActivity()));
        recycler_view_test_rv.setAdapter(adapter);

        adapter.setOnItemClickListener(new MyListFragmentAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, ListFragmentBean.DataBean data) {
                Intent intent=new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("id",data.getId()+"");
                startActivity(intent);
            }
        });

        xrefreshview.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh(boolean isPullDown) {
                super.onRefresh(isPullDown);
                refreshMoreNewData();//下拉刷新更多数据
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                super.onLoadMore(isSilence);
                loadMoreData();//上垃加载更多数据
            }

        });

        return view;
    }

    /**
     * 上垃加载更多数据
     */
    private void loadMoreData() {

        FormEncodingBuilder formEncoding=new FormEncodingBuilder();
        formEncoding.add("category", mType + "");
        Log.i("上拉获取更多数据",listFragmentBean.getNext_page_url());
        OkhttpUtils.getInstance().sendPostHttp(listFragmentBean.getNext_page_url(), api_token, formEncoding, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                code = response.code();
                string = response.body().string();
                Log.i("thrthrthrthr",string);
                handler.sendEmptyMessage(130);
            }
        });

    }


    /**
     * 下拉刷新更多数据
     */
    private void refreshMoreNewData() {
        if (listFragmentBean.getCurrent_page() == 1) {
            currentOneRefresh();//当前页是第一页的时候的下拉刷新
        } else {
            if (listFragmentBean.getPrev_page_url()==null||"".equals(listFragmentBean.getPrev_page_url())){
                xrefreshview.stopRefresh();
            }else{
                pullRefreshData();//下拉刷新当前页不是第一页
            }

        }

    }

    /**
     * 下拉刷新当前页不是第一页
     */
    private void pullRefreshData() {
        FormEncodingBuilder formEncoding=new FormEncodingBuilder();
        formEncoding.add("category", mType + "");
        OkhttpUtils.getInstance().sendPostHttp(listFragmentBean.getPrev_page_url(), api_token, formEncoding, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                code = response.code();
                string = response.body().string();
                Log.i("twotwotwotwo",string);
                handler.sendEmptyMessage(110);
            }
        });
    }

    /**
     * 当前页是第一页的时候的下拉刷新
     */
    private void currentOneRefresh() {
        FormEncodingBuilder formEncoding = new FormEncodingBuilder();
        formEncoding.add("category", mType + "");
        OkhttpUtils.getInstance().sendPostHttp(ContentUrl.BASE_URL + ContentUrl.NEWS, api_token, formEncoding, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                code = response.code();
                string = response.body().string();
                Log.i("oneoneoneoneone",string);
                handler.sendEmptyMessage(110);
            }
        });
    }

    /**
     * 创建加载为空的界面
     *
     * @return
     */
    private View createEmptyView() {
        View view = View.inflate(getActivity(), R.layout.empty_view, null);
        return view;
    }

    /**
     * 创建正在加载界面
     *
     * @return
     */
    private View createLoadingView() {
        View view = View.inflate(getActivity(), R.layout.loading_view, null);
        return view;
    }


    int code;
    String string = "";
    private void gainServiceData() {
        FormEncodingBuilder formEncoding = new FormEncodingBuilder();
        formEncoding.add("category", mType + "");
        OkhttpUtils.getInstance().sendPostHttp(ContentUrl.BASE_URL + ContentUrl.NEWS, api_token, formEncoding, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                code = response.code();
                string = response.body().string();
                Log.i("获取的资讯类的数据的内容", string);
                handler.sendEmptyMessage(1000);
            }
        });
    }
}
