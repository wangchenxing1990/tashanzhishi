package com.wangyukui.ywkj.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wangyukui.R;
import com.wangyukui.ywkj.fragment.CompanyBriefFragment;
import com.wangyukui.ywkj.fragment.FindingWorkFragment;

import java.util.ArrayList;
import java.util.List;

//import com.baidu.platform.comapi.map.F;
//import com.squareup.okhttp.Address;
//import com.tencent.mm.opensdk.modelbiz.AddCardToWXCardPackage;

/**
 * Created by Administrator on 2017/5/10.
 */
public class CompanydetailActivityTwo extends FragmentActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browesing_histiry_three);
       // initData();
//        initView();
        initViews();
    }

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FrameLayout fram_job_back;
    private TextView title_skill;
    private void initViews() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        com_id = intent.getStringExtra("com_id");
        SharedPreferences sharedPreferences=getSharedPreferences("data",MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString("id",id);
        editor.putString("com_id",com_id);
        editor.commit();
        tabLayout= (TabLayout) findViewById(R.id.tab_layout);
        viewPager= (ViewPager) findViewById(R.id.view_pager);
        fram_job_back= (FrameLayout) findViewById(R.id.fram_job_back);
        title_skill= (TextView) findViewById(R.id.title_skill);
        fram_job_back.setOnClickListener(this);
        title_skill.setText("企业详情");
        tabLayout.addTab(tabLayout.newTab().setText("企业简介"));
        tabLayout.addTab(tabLayout.newTab().setText("在招职位"));

        MyDetailAdapter adapter=new MyDetailAdapter(getSupportFragmentManager());
        adapter.addFragment(new CompanyBriefFragment() ,"企业简介");
        adapter.addFragment(new FindingWorkFragment() ,"在招职位");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }


    class MyDetailAdapter extends FragmentPagerAdapter{

        private List<Fragment> listFragment=new ArrayList();
        private List<String> listTitle=new ArrayList();
        public MyDetailAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return listFragment.get(position);
        }

        @Override
        public int getCount() {
            return listFragment.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return listTitle.get(position);
        }

        public void addFragment(Fragment fragment,String title){
            listFragment.add(fragment);
            listTitle.add(title);
        }
    }


    String company_name;
    String api_token;
    int code;
    private List<Fragment> fragments=new ArrayList();
    String com_id;
    String id;
    public void initData() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        com_id = intent.getStringExtra("com_id");
        SharedPreferences sharedPreferences=getSharedPreferences("data",MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString("id",id);
        editor.putString("com_id",com_id);
        editor.commit();
        Log.i("com_idcom_id",com_id+"com_id");
        fragments.add(new CompanyBriefFragment());
        fragments.add(new FindingWorkFragment());
        getSupportFragmentManager().beginTransaction().add(R.id.frame_layout,fragments.get(0)).show(fragments.get(0)).commit();

    }

    FrameLayout iv_back_basic;
    FrameLayout frame_layout;
    LinearLayout linear_company_brief;
    LinearLayout linear_job_invite;
    TextView textbfief;
    TextView textcompanyjob;
    View view_left;
    View view_right;

    public void initView() {
        iv_back_basic = (FrameLayout) findViewById(R.id.iv_back_basic);
        linear_company_brief = (LinearLayout) findViewById(R.id.linear_company_brief);
        linear_job_invite = (LinearLayout) findViewById(R.id.linear_job_invite);

        view_left =  findViewById(R.id.view_left);
        view_right =  findViewById(R.id.view_right);

        textbfief = (TextView) findViewById(R.id.textbfief);
        textcompanyjob = (TextView) findViewById(R.id.textcompanyjob);
        frame_layout = (FrameLayout) findViewById(R.id.frame_layout);

        iv_back_basic.setOnClickListener(this);
        linear_company_brief.setOnClickListener(this);
        linear_job_invite.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_basic://返回
                finish();
                break;
            case R.id.fram_job_back:
                finish();
                break;
            case R.id.linear_company_brief://企业简介
                // Toast.makeText(this, "点击了企业简介", Toast.LENGTH_SHORT).show();
                textbfief.setTextColor(getResources().getColor(R.color.foot_text_color_green));
                textcompanyjob.setTextColor(getResources().getColor(R.color.color_text));

                view_left.setBackgroundColor(getResources().getColor(R.color.foot_text_color_green));
//                view_right.setBackgroundColor(getResources().getColor(R.color.color_text));
                view_right.setVisibility(View.INVISIBLE);
                view_left.setVisibility(View.VISIBLE);

                swithFragment(0);
                break;
            case R.id.linear_job_invite://在招职位

                textbfief.setTextColor(getResources().getColor(R.color.color_text));
                textcompanyjob.setTextColor(getResources().getColor(R.color.foot_text_color_green));
                view_left.setVisibility(View.INVISIBLE);
                view_right.setVisibility(View.VISIBLE);
                view_right.setBackgroundColor(getResources().getColor(R.color.foot_text_color_green));
                swithFragment(1);
                break;


        }
    }

    int oldIndex;
    private void swithFragment(int targIndex){

        FragmentTransaction transactioln=getSupportFragmentManager().beginTransaction();
        if (fragments.get(targIndex).isAdded()){

            if (targIndex==oldIndex){
                transactioln.show(fragments.get(oldIndex));
            }else{
                transactioln.show(fragments.get(targIndex)).hide(fragments.get(oldIndex));
            }
        }else{
            transactioln.add(R.id.frame_layout,fragments.get(targIndex)).show(fragments.get(targIndex)).hide(fragments.get(oldIndex));
        }

        transactioln.commit();
        oldIndex=targIndex;
    }

}
