package com.wangyukui.ywkj.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XRefreshViewFooter;
import com.google.gson.Gson;
import com.hss01248.dialog.StyledDialog;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.wangyukui.R;
import com.wangyukui.wxapi.WXEntryActivity;
import com.wangyukui.ywkj.adapter.MySubmitAdapter;
import com.wangyukui.ywkj.bean.SubmitBean;
import com.wangyukui.ywkj.bean.SubmitResumeDataBean;
import com.wangyukui.ywkj.content.ContentUrl;
import com.wangyukui.ywkj.content.OkhttpUtils;
import com.wangyukui.ywkj.jmessage.JGApplication;
import com.wangyukui.ywkj.tools.UiUtils;
import com.wangyukui.ywkj.view.LoginOffPopuWindow;
import com.wangyukui.ywkj.view.MyPopuwindown;
import com.wangyukui.ywkj.view.RecycleViewDivider;
import com.wangyukui.ywkj.view.SwipeItemLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/4.
 */
public class SubmitResumeActivity extends BaseActiviyt implements View.OnClickListener {

    public SubmitBean data;

    @Override
    public int getLayoutId() {
        return R.layout.activity_submit_resume;
    }

    @Override
    public void initData() {
        api_token= getSharedPreferences("Activity", Context.MODE_PRIVATE).getString("api_token", "");
        gainDataFromService();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 100:
                    if (code == 200) {
                        parsData();
                    } else {
                        frameLayout.removeView(loadingView);
                        frameLayout.addView(emptyView);
                    }
                    break;
                case 120:
                    frameLayout.removeView(loadingView);
                    frameLayout.addView(emptyView);
                    Toast.makeText(SubmitResumeActivity.this, "联网失败,请稍后重试", Toast.LENGTH_SHORT).show();
                    break;
                case 130:
                    loadMoreResult();
                    break;
                case 300:
                    parsRefreshData();//解析加载更多数据
                    break;
                case 30:
                    parsRepealResume();//撤销简历成功
                    break;
                case ONE_PAGE:
                    parsOnePage();//解析当前页为第一页的时候的下拉刷新
                    break;
            }
        }
    };


    /**
     * 撤销简历成功
     */
    private void parsRepealResume() {
        if (code == 200) {
            JSONObject json = JSON.parseObject(str);
            String codes = json.getString("code");
            String msg = json.getString("msg");
            StyledDialog.dismissLoading();
            if ("1".equals(codes)) {
                adapter.notifyDataSetChanged();
            } else {
            }
            if (msg != null && !"".equals(msg)) {
                Toast.makeText(SubmitResumeActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        } else {
            StyledDialog.dismissLoading();
            Toast.makeText(SubmitResumeActivity.this, "撤销简历失败", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 显示是否退出选项按钮
     */
    LoginOffPopuWindow loginOffWindow;

    private void showPopuwindow(SubmitBean data) {
        this.data = data;
        popupWindow.dismiss();
        loginOffWindow = new LoginOffPopuWindow(SubmitResumeActivity.this, R.layout.login_off_popuwindown);
        View view = loginOffWindow.getView();
        loginOffWindow.showAtLocation(SubmitResumeActivity.this.findViewById(R.id.ll_popuwind), Gravity.CENTER, 0, 0);
        TextView textview = (TextView) view.findViewById(R.id.textview);
        textview.setText("确定撤销简历吗?");

        TextView tv_off_cancel = (TextView) view.findViewById(R.id.tv_off_cancel);
        TextView tv_off_sure = (TextView) view.findViewById(R.id.tv_off_sure);
        tv_off_cancel.setOnClickListener(this);
        tv_off_sure.setOnClickListener(this);

    }

    /**
     * 解析加载更多数据
     */
    private void parsRefreshData() {
        if (code == 200) {
            Gson gson=new Gson();
            submitResumeDataBean=gson.fromJson(str,SubmitResumeDataBean.class);

            if ("1".equals(submitResumeDataBean.getCode())) {
                adapter.setData(submitResumeDataBean.getData(),true);
                xrefreshview.stopRefresh();
                xrefreshview.setLoadComplete(false);
            }

        } else {

        }
    }

    /**
     * 加载更多的返回结果
     */
    SubmitResumeDataBean submitResumeDataBean;
    private void loadMoreResult() {
        xrefreshview.stopLoadMore();
        Gson gson=new Gson();
        submitResumeDataBean=gson.fromJson(str,SubmitResumeDataBean.class);

        if (code == 200) {
            if (submitResumeDataBean.getCode().equals("1")) {
               adapter.setData(submitResumeDataBean.getData(),false);
            } else {

            }

        } else {

        }
    }

    List<SubmitResumeDataBean.DataBean> listResume = new ArrayList<>();
    MySubmitAdapter adapter;
    PopupWindow popupWindow;
    private void parsData() {
        Gson gson=new Gson();
         submitResumeDataBean=gson.fromJson(str, SubmitResumeDataBean.class);
        if (submitResumeDataBean.getCode().equals("1")) {
            frameLayout.removeView(loadingView);
            if (submitResumeDataBean.getData().size() == 0) {
                frameLayout.addView(emptyResumeView);
            } else {
                frameLayout.addView(successView);
                adapter.setData(submitResumeDataBean.getData(),false);
            }

        } else if ("-1".equals(submitResumeDataBean.getCode())) {
            frameLayout.removeView(loadingView);
            frameLayout.addView(emptyView);
            Intent intent = new Intent(SubmitResumeActivity.this, WXEntryActivity.class);
            intent.putExtra("register", "loginMine");
            startActivity(intent);
            finish();
        }

        if (submitResumeDataBean.getMsg() != null && !"".equals(submitResumeDataBean.getMsg())) {
            Toast.makeText(SubmitResumeActivity.this, submitResumeDataBean.getMsg(), Toast.LENGTH_SHORT).show();
        }


    }

    /**
     * 撤销投递的简历
     *
     * @param
     */
    private void reapelSubmitResume() {
        StyledDialog.buildLoading().show();
        FormEncodingBuilder foem = new FormEncodingBuilder();
        foem.add("id", data.getId());
        foem.add("status", "4");
        OkhttpUtils.getInstance().sendPostHttp(ContentUrl.BASE_URL + ContentUrl.RESUME_SEND_UPDATE, api_token, foem, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                str = response.body().string();
                code = response.code();
                Log.i("撤销简历成功的数据", str);
                handler.sendEmptyMessage(30);
                listResume.remove(data);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
            popupWindow = null;
        }
    }

    /**
     * 上拉加载更多
     */
    private void downLoadMoreData() {
        if (submitResumeDataBean.getNext_page_url() == null||submitResumeDataBean.getNext_page_url().equals("")) {//表示完成上垃加载
            xrefreshview.setLoadComplete(true);
        } else {//还有数据还有可以进行加载更多
            int in = submitResumeDataBean.getCurrent_page()+ 1;
            FormEncodingBuilder form = new FormEncodingBuilder();
            form.add("page", String.valueOf(in));
            OkhttpUtils.getInstance().sendPostHttp(submitResumeDataBean.getNext_page_url(), api_token, form, new Callback() {
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

    /**
     * 下拉刷新
     */
    private void refreshMoreData() {
        if (1==submitResumeDataBean.getCurrent_page()) {//刷新第一页
            refreshOnePage();
        } else {//刷新的不是第一页
            if (submitResumeDataBean.getPrev_page_url() == null||submitResumeDataBean.getPrev_page_url().equals("")) {
                xrefreshview.stopRefresh();
            } else {
                refreshData();//下拉刷新数据
            }
        }

    }

    /**
     * 刷新的为第一页
     */
    private static final int ONE_PAGE = 1;

    private void refreshOnePage() {

        FormEncodingBuilder formd = new FormEncodingBuilder();
        formd.add("page", "1");
        OkhttpUtils.getInstance().sendPostHttp(ContentUrl.BASE_URL + ContentUrl.RESUME_SEND, api_token, formd, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                str = response.body().string();
                code = response.code();
                Log.i("ssssaaaa", str);
                handler.sendEmptyMessage(ONE_PAGE);
            }
        });

    }

    /**
     * 解析当前页为第一页的时候的下拉刷新
     */
    private void parsOnePage() {
        if (code == 200) {
            Gson gson=new Gson();
             submitResumeDataBean=gson.fromJson(str,SubmitResumeDataBean.class);
            if ("1".equals(submitResumeDataBean.getCode())) {

                adapter.setData(submitResumeDataBean.getData(),true);

                if (submitResumeDataBean.getFirst_page_url() == null || "".equals(submitResumeDataBean.getFirst_page_url())) {
                    xrefreshview.setLoadComplete(true);
                    xrefreshview.setPullLoadEnable(false);
                }
                xrefreshview.stopRefresh();

            } else {

            }
            if (submitResumeDataBean.getMsg() != null && !"".equals(submitResumeDataBean.getMsg())) {
                Toast.makeText(SubmitResumeActivity.this, submitResumeDataBean.getMsg(), Toast.LENGTH_SHORT).show();
            }
        } else {

        }
    }

    /**
     * 下拉刷新数据
     */
    private void refreshData() {
        FormEncodingBuilder formcom = new FormEncodingBuilder();
        OkhttpUtils.getInstance().sendPostHttp(submitResumeDataBean.getPrev_page_url(), api_token, formcom, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                str = response.body().string();
                code = response.code();
                Log.i("232323232323", str);
                handler.sendEmptyMessage(300);
            }
        });
    }

    String str="";
    int code;
    private void gainDataFromService() {

        FormEncodingBuilder formEncoding = new FormEncodingBuilder();
        OkhttpUtils.getInstance().sendPostHttp(ContentUrl.BASE_URL + ContentUrl.RESUME_SEND, api_token, formEncoding, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                if (e.getMessage() != null) {
                    handler.sendEmptyMessage(120);
                }
            }

            @Override
            public void onResponse(Response response) throws IOException {
                str = response.body().string();
                code = response.code();
                Log.i("sssssssss", str + code);
                handler.sendEmptyMessage(100);
            }
        });

    }

    private FrameLayout fram_job_back;
    private FrameLayout frameLayout;
    private ListView list_view_submit_resume;
    private View emptyView;
    private View loadingView;
    private View successView;
    private View emptyResumeView;
    private String api_token="";

    @Override
    public void initView() {

        fram_job_back = (FrameLayout) findViewById(R.id.fram_job_back);
        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        list_view_submit_resume = (ListView) findViewById(R.id.list_view_submit_resume);

        emptyView = createEmptyView();
        loadingView = createLoadingView();
        successView = createSuccessView();
        emptyResumeView = createemptyResumeView();
        frameLayout.addView(loadingView);
        fram_job_back.setOnClickListener(this);

        adapter = new MySubmitAdapter(listResume, this);
        recycler_view_test_rv.setAdapter(adapter);

        xrefreshview.setAutoLoadMore(false);
        xrefreshview.setPinnedTime(1000);
        xrefreshview.setMoveForHorizontal(true);
        xrefreshview.setPullLoadEnable(true);

        adapter.setCustomLoadMoreView(new XRefreshViewFooter(SubmitResumeActivity.this));
        xrefreshview.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh(boolean isPullDown) {
                super.onRefresh(isPullDown);
                refreshMoreData();//下拉刷新
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                super.onLoadMore(isSilence);
                downLoadMoreData();//上垃加载更多
            }
        });
    }

    /**
     * 创建没有投递简历的空界面
     *
     * @return
     */
    private View createemptyResumeView() {
        View view = View.inflate(SubmitResumeActivity.this, R.layout.empty_resume_view, null);
        return view;
    }

    private XRefreshView xrefreshview;
    private RecyclerView recycler_view_test_rv;

    /**
     * 创建数据加载成功的界面
     */
    private View createSuccessView() {
        View view = View.inflate(SubmitResumeActivity.this, R.layout.fram_listview, null);
        xrefreshview = (XRefreshView) view.findViewById(R.id.xrefreshview);
        recycler_view_test_rv = (RecyclerView) view.findViewById(R.id.recycler_view_test_rv);

        recycler_view_test_rv.setBackgroundColor(0xffffffff);
        //添加分割线
        recycler_view_test_rv.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL,1,0xffdfdfdf));

        recycler_view_test_rv.setLayoutManager(new LinearLayoutManager(this));
        recycler_view_test_rv.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(this));

        return view;
    }

    /**
     * 创建正在加载界面
     *
     * @return
     */
    private View createLoadingView() {
        View view = View.inflate(this, R.layout.loading_view, null);
        return view;
    }

    private TextView textview_click;

    /**
     * 创建空界面
     *
     * @return
     */
    private View createEmptyView() {
        View view = View.inflate(this, R.layout.empty_view, null);
        textview_click = (TextView) view.findViewById(R.id.textview_click);
        textview_click.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.fram_job_back://返回
                finish();
                break;
            case R.id.textview_click://点击获取数据
                frameLayout.removeAllViews();
                frameLayout.addView(loadingView);
                gainDataFromService();
                break;
            case R.id.tv_off_sure:
                reapelSubmitResume();//撤销简历
                loginOffWindow.dismiss();
                break;
            case R.id.tv_off_cancel:
                loginOffWindow.dismiss();
                break;

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        StyledDialog.dismissLoading();
    }
}
