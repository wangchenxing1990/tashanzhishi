package com.wangyukui.ywkj.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.wangyukui.R;
import com.wangyukui.ywkj.adapter.MyGridAdapter;
import com.wangyukui.ywkj.adapter.MyListViewAdapter;
import com.wangyukui.ywkj.bean.AreaCity;
import com.wangyukui.ywkj.bean.AreaCode;
import com.wangyukui.ywkj.bean.AreaCounty;
import com.wangyukui.ywkj.bean.AreaOne;
import com.wangyukui.ywkj.bean.LocationAreaBean;
import com.wangyukui.ywkj.content.ContentUrl;
import com.wangyukui.ywkj.content.OkhttpUtils;
import com.wangyukui.ywkj.tools.ViewUtilss;
import com.wangyukui.ywkj.view.MyGridView;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import co.lujun.androidtagview.TagContainerLayout;
import co.lujun.androidtagview.TagView;

/**
 * Created by Administrator on 2017/4/20.
 */
public class LocationActivityTwo extends BaseActiviyt implements View.OnClickListener {
    @Override
    public int getLayoutId() {
        return R.layout.activity_area_code;
    }

    private String string;
    private LocationAreaBean locationAreaBean;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1000:
                    //解析从网上获取的数据
                    praseJson();
                    //读取本地数据
                    String localArea = ViewUtilss.praseLocal(R.raw.area);
                    //解析本地数据
                    Gson gson=new Gson();
                    locationAreaBean= gson.fromJson(localArea, LocationAreaBean.class);
                    listView.setAdapter(new MyListViewAdapter(locationAreaBean.getData()));
                    break;
            }
        }
    };

    private List<AreaOne> listAreas = new ArrayList<AreaOne>();

    /**
     * 解析数据
     */
    LocationAreaBean.DataBean.NextsBeanX nextsBean;
    private void praseJson() {

        JSONObject jsonObject = JSON.parseObject(string);
        String data = jsonObject.getString("data");
        JSONObject jsonData = JSON.parseObject(data);

        Gson gson=new Gson();
        nextsBean=gson.fromJson(jsonData+"", LocationAreaBean.DataBean.NextsBeanX.class);
        gridView.setAdapter(new MyGridAdapter(nextsBean.getNexts()));

    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        params = intent.getStringExtra("params");
        paramss = intent.getStringExtra("paramss");
        paramsss = intent.getStringExtra("paramsss");
        paramsss = intent.getStringExtra("paramssss");

        FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();
        formEncodingBuilder.add("type","1");
        OkhttpUtils.getInstance().sendPostHttp(ContentUrl.BASE_URL + ContentUrl.AREA_CODE, "", formEncodingBuilder, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                string = response.body().string();
                System.out.println("请求的地域的地理位置" + string);
                handler.sendEmptyMessage(1000);
            }
        });
    }

    private MyGridView gridView;
    private ListView listView;
    private FrameLayout backLocation;
    private TextView tv_city_grid;
    private RelativeLayout relative;
    private String params;
    private String paramss;
    private String paramsss;
    private TextView tv_province;
    private TextView text_save;
    private TagContainerLayout textSelectWork;

    @Override
    public void initView() {

        backLocation = (FrameLayout) findViewById(R.id.iv_back_location);
        tv_province = (TextView) findViewById(R.id.tv_province);
        text_save = (TextView) findViewById(R.id.text_save);
        listView = (ListView) findViewById(R.id.list_view);
        textSelectWork = (TagContainerLayout) findViewById(R.id.textSelectWork);

        /**
         * 工作区域的选择
         */
        textSelectWork.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text) {
                listArea.remove(position);
                listAreaId.remove(position);
                textSelectWork.setTags(listArea);
            }

            @Override
            public void onTagLongClick(int position, String text) {

            }

            @Override
            public void onTagCrossClick(int position) {
                listArea.remove(position);
                listAreaId.remove(position);
                textSelectWork.setTags(listArea);
            }
        });

        View header = LayoutInflater.from(this).inflate(R.layout.header_list_view, null);
        gridView = (MyGridView) header.findViewById(R.id.grid_view);
        tv_city_grid = (TextView) header.findViewById(R.id.tv_city_grid);
        relative = (RelativeLayout) header.findViewById(R.id.relative);
        listView.addHeaderView(header);
        //取消点击GridView时出现的背景颜色
        gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));

        text_save.setOnClickListener(this);
        backLocation.setOnClickListener(this);
        gridView.setOnItemClickListener(myGridViewOnclickListener);//设置GridView的条目点击监听
        listView.setOnItemClickListener(myListOnclickListener);//设置ListView条目的点击监听事件

        if ("area".equals(params)) {
            gridView.setVisibility(View.VISIBLE);
            relative.setVisibility(View.VISIBLE);
            tv_province.setText("期望地点");
            text_save.setVisibility(View.INVISIBLE);
        } else {
            gridView.setVisibility(View.VISIBLE);
            relative.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.iv_back_location:
                finish();//返回上一个界面
                break;
            case R.id.text_save:

                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("listArea", (Serializable) listArea);
                bundle.putSerializable("listAreaId", (Serializable) listAreaId);
                intent.putExtras(bundle);
                LocationActivityTwo.this.setResult(9, intent);

                finish();
                break;
        }

    }

    /**
     * 点击listview的条目的事件
     */
    AdapterView.OnItemClickListener myListOnclickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if ("area".equals(params)) {

                if (locationAreaBean.getData().get(position - 1).getNext()!=0) {

                    Intent intent = new Intent(LocationActivityTwo.this, CityActivityTwo.class);
                    intent.putExtra("params", params);
                    intent.putExtra("paramss", paramss);
                    intent.putExtra("city", (Serializable) locationAreaBean.getData().get(position-1).getNexts());
                    intent.putExtra("next", (Serializable) locationAreaBean.getData().get(position-1).getName());

                    startActivityForResult(intent, 9);
                }
            } else {

                if (locationAreaBean.getData().get(position - 1).getNext()!=0) {

                    if (params.equals("area")) {
                        Intent intent = new Intent(LocationActivityTwo.this, CityActivityTwo.class);
                        intent.putExtra("params", params);
                        intent.putExtra("paramss", paramss);
                        intent.putExtra("next", listAreas.get(position).getNext());
                        intent.putExtra("city", (Serializable) locationAreaBean.getData().get(position-1).getNexts());
                        startActivityForResult(intent, 9);
                    } else {
                        Intent intent = new Intent(LocationActivityTwo.this, CityActivityTwo.class);
                        intent.putExtra("params", params);
                        intent.putExtra("paramss", paramss);
                        intent.putExtra("next", listAreas.get(position).getNext());
                        intent.putExtra("city", (Serializable) locationAreaBean.getData().get(position-1).getNexts());
//                        Bundle bundle = new Bundle();
//                        bundle.putSerializable("city", (Serializable) listAreass.get(position - 1));
//                        intent.putExtra("province", listAreas.get(position - 1).getName());
//                        bundle.putSerializable("county", (Serializable) listAreasss.get(position - 1));
//                        intent.putExtras(bundle);

                        startActivityForResult(intent, 9);
                    }
                }
            }
        }
    };

    /**
     * 点击Gridview返回数据
     */
    private List<String> listArea = new ArrayList<>();
    private List<String> listAreaId = new ArrayList<>();
    AdapterView.OnItemClickListener myGridViewOnclickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent();
            intent.putExtra("location", nextsBean.getNexts().get(position).getName());
            intent.putExtra("locationId", nextsBean.getNexts().get(position).getCid());
            LocationActivityTwo.this.setResult(9, intent);
            finish();
        }
    };

    /**
     * 点击返回上一个界面
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
        } else {
            List<String> listArea = (List<String>) data.getExtras().getSerializable("listArea");
            List<String> listAreaId = (List<String>) data.getExtras().getSerializable("listAreaId");
            String location = data.getExtras().getString("location");
            String locationId = data.getExtras().getString("locationId");
            String params = data.getExtras().getString("params");
            if (params.equals("area")) {
                Intent intent = new Intent();
                intent.putExtra("location", location);
                intent.putExtra("locationId", locationId);
                LocationActivityTwo.this.setResult(9, intent);
                finish();
            } else {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("listArea", (Serializable) listArea);
                bundle.putSerializable("listAreaId", (Serializable) listAreaId);
                intent.putExtra("params", "");
                intent.putExtras(bundle);
//                intent.putExtra("location", location);
//                intent.putExtra("locationId", locationId);
                LocationActivityTwo.this.setResult(9, intent);
                finish();
            }
        }
    }
}
