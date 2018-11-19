package com.wangyukui.ywkj.activity;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
//import com.baidu.platform.comapi.map.F;
import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.wangyukui.R;
import com.wangyukui.ywkj.bean.CarerBean;
import com.wangyukui.ywkj.bean.InformationBean;
import com.wangyukui.ywkj.content.ContentUrl;
import com.wangyukui.ywkj.content.OkhttpUtils;
import com.wangyukui.ywkj.fragment.NewsListFragment;
import com.wangyukui.ywkj.subPageView.BasePager;
import com.wangyukui.ywkj.subPageView.CarerGossipPage;
import com.wangyukui.ywkj.subPageView.CarerProjectPage;
import com.wangyukui.ywkj.subPageView.EducationTrainPage;
import com.wangyukui.ywkj.subPageView.InterviewSkillPage;
import com.wangyukui.ywkj.subPageView.LaborLawsPage;
import com.wangyukui.ywkj.subPageView.PublicInstitutionPage;
import com.wangyukui.ywkj.subPageView.ResumeGuidePage;
import com.wangyukui.ywkj.subPageView.SalaryWelfarePage;
import com.wangyukui.ywkj.view.ViewPagerIndicator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/7/7.
 */

public class FindActivity extends BaseActiviyt {
    @Bind(R.id.image_input_name_back)
    FrameLayout imageInputNameBack;
    @Bind(R.id.tv_input_title)
    TextView tvInputTitle;
    @Bind(R.id.tab_layout)
    TabLayout tabLayout;
    @Bind(R.id.view_pager)
    ViewPager viewPager;

    @Override
    public int getLayoutId() {
        return R.layout.find_activity_two;
    }

    @Override
    public void initData() {

    }

    String str;
    int code;

    @Override
    public void initView() {
        ButterKnife.bind(this);

        gainDataFromService();//从服务器获取数据
    }

    /**
     * 从服务器获取数据
     */
    private void gainDataFromService() {
        FormEncodingBuilder form = new FormEncodingBuilder();
        OkhttpUtils.getInstance().sendPostHttp(ContentUrl.BASE_URL + ContentUrl.CAREER_INFORMATION, "", form, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                str = response.body().string();
                code = response.code();
                Log.i("请求职场资讯的数据成功", str);
                handler.sendEmptyMessage(1000);
            }
        });

    }

    @OnClick(R.id.image_input_name_back)
    public void onViewClicked() {
        finish();//关闭当前界面,返回上一个界面
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1000:
                    parsGainDataFromService();//解析数据
                    break;
            }
        }
    };

    /**
     * 解析数据
     */
    private void parsGainDataFromService() {
        if (code == 200) {
            Gson gson = new Gson();
            InformationBean informationBean = gson.fromJson(str, InformationBean.class);
            MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
            for (int i=0;i<informationBean.getData().size();i++){
                tabLayout.addTab(tabLayout.newTab().setText(informationBean.getData().get(i).getName()));
                adapter.addFragment(NewsListFragment.newInstance(informationBean.getData().get(i).getCategory()),informationBean.getData().get(i).getName());
            }
            viewPager.setAdapter(adapter);
            tabLayout.setupWithViewPager(viewPager);
        } else {

        }
    }


    class MyAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragmentLists=new ArrayList();
        private List<String> fragmentTitles=new ArrayList();
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentLists.get(position);
        }

        @Override
        public int getCount() {
            return fragmentLists.size();
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitles.get(position);
        }
        public void addFragment(Fragment fragment, String title) {
            fragmentLists.add(fragment);
            fragmentTitles.add(title);
        }

    }
}
