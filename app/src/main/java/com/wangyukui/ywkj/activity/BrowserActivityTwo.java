package com.wangyukui.ywkj.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wangyukui.R;
import com.wangyukui.ywkj.fragment.CompanyFragment;
import com.wangyukui.ywkj.fragment.JobFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/15.
 */

public class BrowserActivityTwo extends FragmentActivity implements View.OnClickListener {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browesing_histiry_three);
        // initData();
        // initView();
        initViews();
    }

    private TabLayout tab_layout;
    private ViewPager view_pager;
    private FrameLayout fram_job_back;
    private void initViews() {
        fram_job_back= (FrameLayout) findViewById(R.id.fram_job_back);
        tab_layout = (TabLayout) findViewById(R.id.tab_layout);
        view_pager = (ViewPager) findViewById(R.id.view_pager);
        fram_job_back.setOnClickListener(this);
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
        tab_layout.addTab(tab_layout.newTab().setText("职位浏览记录"));
        tab_layout.addTab(tab_layout.newTab().setText("公司浏览记录"));
        adapter.addFragmetTilte(new JobFragment(), "职位浏览记录");
        adapter.addFragmetTilte(new CompanyFragment(), "公司浏览记录");
        view_pager.setAdapter(adapter);
        tab_layout.setupWithViewPager(view_pager);
    }

    private class MyAdapter extends FragmentPagerAdapter {
        List<String> titles = new ArrayList<>();
        List<Fragment> fragmentList = new ArrayList<>();

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return titles.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }

        public void addFragmetTilte(Fragment fragment, String string) {
            titles.add(string);
            fragmentList.add(fragment);
        }
    }

    String api_token;
    List<Fragment> fragments = new ArrayList<>();

    public void initData() {
        fragments.add(new JobFragment());
        fragments.add(new CompanyFragment());

        SharedPreferences share = getSharedPreferences("Activity", Context.MODE_PRIVATE);
        api_token = share.getString("api_token", "");

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frame_layout, fragments.get(0))
                .show(fragments.get(0)).commit();
    }


    FrameLayout frame_layout;
    LinearLayout linear_job_browse;
    LinearLayout linear_company_browse;
    TextView textjobbrowse;
    TextView textcompanybrowse;
    View view_left;
    View view_right;
    View loadingView;
    View successView;

    public void initView() {

        FrameLayout fram_job_back = (FrameLayout) findViewById(R.id.fram_job_back);
        linear_job_browse = (LinearLayout) findViewById(R.id.linear_job_browse);
        linear_company_browse = (LinearLayout) findViewById(R.id.linear_company_browse);
        textjobbrowse = (TextView) findViewById(R.id.textjobbrowse);
        textcompanybrowse = (TextView) findViewById(R.id.textcompanybrowse);

        view_left = findViewById(R.id.line_left);
        view_right = findViewById(R.id.line_right);

        fram_job_back.setOnClickListener(this);
        linear_job_browse.setOnClickListener(this);
        linear_company_browse.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fram_job_back:
                finish();
                break;
            case R.id.linear_job_browse:
                textjobbrowse.setTextColor(getResources().getColor(R.color.foot_text_color_green));
                textcompanybrowse.setTextColor(getResources().getColor(R.color.home_text_color));
                view_left.setBackgroundColor(getResources().getColor(R.color.foot_text_color_green));
                view_right.setBackgroundColor(getResources().getColor(R.color.view_line_f0));
                switchFragment(0);
                break;
            case R.id.linear_company_browse:
                textjobbrowse.setTextColor(getResources().getColor(R.color.home_text_color));
                textcompanybrowse.setTextColor(getResources().getColor(R.color.foot_text_color_green));
                view_left.setBackgroundColor(getResources().getColor(R.color.view_line_f0));
                view_right.setBackgroundColor(getResources().getColor(R.color.foot_text_color_green));
                switchFragment(1);
                break;
        }
    }

    int oldIndex;

    private void switchFragment(int targIndex) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (fragments.get(targIndex).isAdded()) {
            if (targIndex != oldIndex) {
                transaction.hide(fragments.get(oldIndex)).show(fragments.get(targIndex));
            }
        } else {
            transaction.add(R.id.frame_layout, fragments.get(targIndex)).show(fragments.get(targIndex)).hide(fragments.get(oldIndex));
        }
        transaction.commit();
        oldIndex = targIndex;
    }

}
