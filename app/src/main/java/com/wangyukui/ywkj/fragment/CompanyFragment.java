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
import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XRefreshViewFooter;
import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.wangyukui.R;
import com.wangyukui.ywkj.activity.CompanydetailActivity;
import com.wangyukui.ywkj.activity.CompanydetailActivityTwo;
import com.wangyukui.ywkj.adapter.MyLookMeAdapter;
import com.wangyukui.ywkj.bean.LookMeDataBean;
import com.wangyukui.ywkj.content.ContentUrl;
import com.wangyukui.ywkj.content.OkhttpUtils;
import com.wangyukui.ywkj.view.RecycleViewDivider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/26.
 */

public class CompanyFragment extends Fragment {
    private View loadingView;
    private View successView;
    private View emptyView;
    private FrameLayout framLayout;
    private String api_token;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        SharedPreferences share = getActivity().getSharedPreferences("Activity", Context.MODE_PRIVATE);
        api_token = share.getString("api_token", "");
        framLayout = new FrameLayout(getActivity());
        loadingView = inflater.inflate(R.layout.loading_view, null);
        successView = createSuccessView();
        emptyView = inflater.inflate(R.layout.empty_view_icon, null);
        TextView textview_click = (TextView) emptyView.findViewById(R.id.textview_click);
        textview_click.setText("还没有公司浏览记录");
        framLayout.addView(loadingView);

        initData();
        return framLayout;
    }


    private void initData() {
        FormEncodingBuilder formEncoding = new FormEncodingBuilder();
        OkhttpUtils.getInstance().sendPostHttp(ContentUrl.BASE_URL + ContentUrl.USER_BROWSE_COM, api_token, formEncoding, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                string = response.body().string();
                code = response.code();
                handler.sendEmptyMessage(200);
                Log.i("3433333332222", string);
            }
        });
    }

    /**
     * 创建成功界面
     *
     * @return
     */
    XRefreshView recycler_view_rightt;
    RecyclerView recycler_view_test_rv_right;
    MyLookMeAdapter companyadapter;
    private View createSuccessView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fram_listview, null);
        recycler_view_rightt = (XRefreshView) view.findViewById(R.id.xrefreshview);
        recycler_view_test_rv_right = (RecyclerView) view.findViewById(R.id.recycler_view_test_rv);

        recycler_view_test_rv_right.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler_view_test_rv_right.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayoutManager.HORIZONTAL, 1, 0xffdfdfdf));
        recycler_view_rightt.setPullLoadEnable(true);
        recycler_view_rightt.setAutoLoadMore(false);
        recycler_view_rightt.setPinnedTime(1000);
        recycler_view_rightt.setMoveForHorizontal(true);

        companyadapter = new MyLookMeAdapter(listCompany);
        recycler_view_test_rv_right.setAdapter(companyadapter);
        companyadapter.setCustomLoadMoreView(new XRefreshViewFooter(getActivity()));


        recycler_view_rightt.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh(boolean isPullDown) {//下拉刷新
                super.onRefresh(isPullDown);
                pullToRefreshCompanyData();//下拉刷新公司浏览记录的数据
            }

            @Override
            public void onLoadMore(boolean isSilence) {//上垃加载
                super.onLoadMore(isSilence);
                loadMoreCompanyData();//上垃加载更多公司浏览数据
            }
        });

        companyadapter.setmOnItemClickListener(new MyLookMeAdapter.OnRecyclerViewOnItemClickListener() {
            @Override
            public void onItenClick(View view, LookMeDataBean.DataBean data) {
                Intent intent = new Intent(getActivity(), CompanydetailActivityTwo.class);
                intent.putExtra("com_id", data.getEcom_id());
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
                case 200:
                    parsDataCompany();//解析获取公司浏览的数据
                    break;
                case 400:
                    parsCompanyDataUp();//解析上啦加载公司浏览的数据
                    break;
                case 410:
                    parsRefreshNewDataCompany();//解析下拉刷新的公司浏览记录的数据
                    break;
                case ONE_COMPANY_PAGE:
                    parsOneDataNewCompany();//解析当前是第一页的时候的数据
                    break;
            }
        }
    };
    /**
     * 解析公司浏览记录
     */
    private List<LookMeDataBean.DataBean> listCompany = new ArrayList<>();
    private LookMeDataBean lookMeDataBean;
    private void parsDataCompany() {
        if (code == 200) {
            Gson gson = new Gson();
            lookMeDataBean = gson.fromJson(string, LookMeDataBean.class);
            if ("1".equals(lookMeDataBean.getCode())) {
                framLayout.removeView(loadingView);
                if (lookMeDataBean.getData().size() == 0) {
                    framLayout.addView(emptyView);
                } else {
                    framLayout.addView(successView);
                    companyadapter.setData(lookMeDataBean.getData(), false);
                }


            }
        } else {

        }
    }




    /**
     * 下拉刷新公司浏览记录的数据
     */
    private void pullToRefreshCompanyData() {
        if ("1".equals(lookMeDataBean.getCurrent_page())) {
            refreshOnePageData();//下拉刷新当前公司的页是第一页的时候的数据
        } else {
            if (lookMeDataBean.getPrev_page_url() == null||"".equals(lookMeDataBean.getPrev_page_url())) {
                recycler_view_rightt.stopRefresh();
            } else {
                FormEncodingBuilder formencoding = new FormEncodingBuilder();
                OkhttpUtils.getInstance().sendPostHttp(lookMeDataBean.getPrev_page_url(), api_token, formencoding, new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {

                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                        string = response.body().string();
                        code = response.code();
                        Log.i("11122124214", string);
                        handler.sendEmptyMessage(410);
                    }
                });
            }
        }
    }


    /**
     * 上垃加载更多公司浏览的数据
     */
    private void loadMoreCompanyData() {
        if (lookMeDataBean.getNext_page_url() == null||"".equals(lookMeDataBean.getNext_page_url())) {
            recycler_view_rightt.setLoadComplete(true);
        } else {
            FormEncodingBuilder formEncoding = new FormEncodingBuilder();
            OkhttpUtils.getInstance().sendPostHttp(lookMeDataBean.getNext_page_url(), api_token, formEncoding, new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {

                }

                @Override
                public void onResponse(Response response) throws IOException {
                    string = response.body().string();
                    code = response.code();
                    Log.i("12233432523554", string);
                    handler.sendEmptyMessage(400);
                }
            });
        }
    }

    /**
     * 解析上啦加载公司浏览的数据
     */
    private void parsCompanyDataUp() {
        if (code == 200) {
           Gson gson=new Gson();
            lookMeDataBean=gson.fromJson(string,LookMeDataBean.class);
            if ("1".equals(lookMeDataBean.getCode())) {

                companyadapter.setData(lookMeDataBean.getData(),false);

            } else {

            }
        } else {

        }
    }

    /**
     * 解析下拉刷新的公司浏览记录的数据
     */
    private void parsRefreshNewDataCompany() {
        if (code == 200) {
          Gson gson=new Gson();
            lookMeDataBean=gson.fromJson(string,LookMeDataBean.class);
            if ("1".equals(lookMeDataBean.getCode())) {
                companyadapter.setData(lookMeDataBean.getData(),true);
                recycler_view_rightt.stopRefresh();
                recycler_view_rightt.setLoadComplete(false);

            } else {

            }
        } else {

        }
    }

    /**
     * 刷新公司当前页是第一页的时候的数据
     */
    String string;
    int code;
    private static final int ONE_COMPANY_PAGE = 3;

    private void refreshOnePageData() {
        FormEncodingBuilder formEncoding = new FormEncodingBuilder();
        formEncoding.add("page", "1");
        OkhttpUtils.getInstance().sendPostHttp(ContentUrl.BASE_URL + ContentUrl.USER_BROWSE_COM, api_token, formEncoding, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                string = response.body().string();
                code = response.code();
                Log.i("aaddssadfs", string);
                handler.sendEmptyMessage(ONE_COMPANY_PAGE);
            }
        });
    }

    /**
     * 解析当前是第一页的时候的数据
     */
    private void parsOneDataNewCompany() {
        if (code == 200) {
           Gson gson= new Gson();
            lookMeDataBean=gson.fromJson(string,LookMeDataBean.class);

            if ("1".equals(lookMeDataBean.getCode())) {

                if (lookMeDataBean.getNext_page_url() == null || "".equals(lookMeDataBean.getNext_page_url())) {
                    recycler_view_rightt.setPullLoadEnable(false);
                    recycler_view_rightt.setLoadComplete(true);
                }
                companyadapter.setData(lookMeDataBean.getData(),true);
                recycler_view_rightt.stopRefresh();
                recycler_view_rightt.setLoadComplete(false);
            } else if ("-1".equals(lookMeDataBean.getCode())) {

            }
            if (lookMeDataBean.getMsg() != null && !"".equals(lookMeDataBean.getMsg())) {
                Toast.makeText(getActivity(), lookMeDataBean.getMsg(), Toast.LENGTH_SHORT).show();
            }
        } else {

        }
    }
}
