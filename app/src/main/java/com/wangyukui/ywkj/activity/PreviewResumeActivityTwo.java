package com.wangyukui.ywkj.activity;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.wangyukui.R;
import com.wangyukui.ywkj.adapter.MyPreBookAdapter;
import com.wangyukui.ywkj.adapter.MyPreEduAdapter;
import com.wangyukui.ywkj.adapter.MyPreLangAdapter;
import com.wangyukui.ywkj.adapter.MyPreOtherAdapter;
import com.wangyukui.ywkj.adapter.MyPreProExpAdapter;
import com.wangyukui.ywkj.adapter.MyPreSkillAdapter;
import com.wangyukui.ywkj.adapter.MyWorkPreViewAdapter;
import com.wangyukui.ywkj.bean.BookBean;
import com.wangyukui.ywkj.bean.EducaExperBean;
import com.wangyukui.ywkj.bean.LangBean;
import com.wangyukui.ywkj.bean.OtherBean;
import com.wangyukui.ywkj.bean.PreviewResumeDataBean;
import com.wangyukui.ywkj.bean.ProgressBean;
import com.wangyukui.ywkj.bean.SkillBean;
import com.wangyukui.ywkj.bean.WorkExperience;
import com.wangyukui.ywkj.content.ContentUrl;
import com.wangyukui.ywkj.content.OkhttpUtils;
import com.wangyukui.ywkj.tools.parsResumeShowData;
import com.wangyukui.ywkj.view.CircleTransform;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by Administrator on 2017/5/8.
 */

public class PreviewResumeActivityTwo extends BaseActiviyt implements View.OnClickListener {
    @Override
    public int getLayoutId() {
        return R.layout.activity_preview_resume_two;
    }

    @Override
    public void initData() {
        //从服务器获取数据

    }

    int code;
    String str;
    /**
     * 从服务器获取数据
     */
    String api_token;
    String resume_id = "";

    private void gainDatas() {
        FormEncodingBuilder form = new FormEncodingBuilder();
        form.add("id", resume_id);
        OkhttpUtils.getInstance().sendPostHttp(ContentUrl.BASE_URL + ContentUrl.RESUME_SHOW, api_token, form, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                String eMessage = e.getMessage();
                System.out.println("获取的错误的信息" + eMessage);
                handler.sendEmptyMessage(101);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                str = response.body().string();
                code = response.code();
                System.out.println("kkkkkkkkkkkkk" + str);

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
                    if (code == 200) {
                        JSONObject jsons = JSON.parseObject(str);
                        String codes = jsons.getString("code");

                        if (codes.equals("1")) {
                            relative_loading.setVisibility(View.INVISIBLE);
                            scrollview.setVisibility(View.VISIBLE);
                            Gson gson = new Gson();
                            PreviewResumeDataBean previewResumeDataBean = gson.fromJson(str, PreviewResumeDataBean.class);
                            parasenData(previewResumeDataBean);//解析数据
                        } else {
                            Toast.makeText(PreviewResumeActivityTwo.this, "获取数据失败", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(PreviewResumeActivityTwo.this, "获取数据失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };


    /**
     * 解析数据
     *
     * @param jsonData
     */
    List<PreviewResumeDataBean.DataBean.ResumeWorkexpBean> listWorkExp = new ArrayList<>();
    List<PreviewResumeDataBean.DataBean.ResumeEduexpBean> educationList = new ArrayList<>();
    List<PreviewResumeDataBean.DataBean.ResumeProexpBean> projectList = new ArrayList<>();
    List<PreviewResumeDataBean.DataBean.ResumeLangBean> langList = new ArrayList<>();
    List<PreviewResumeDataBean.DataBean.ResumeSkillBean> skillList = new ArrayList<>();
    List<PreviewResumeDataBean.DataBean.ResumeCerBean> bookList = new ArrayList<>();
    List<PreviewResumeDataBean.DataBean.ResumeOtherBean> otherList = new ArrayList<>();

    private void parasenData(PreviewResumeDataBean jsonData) {

        if (jsonData.getData().getAvatar() == null || jsonData.getData().getAvatar().isEmpty()) {
            Picasso.with(PreviewResumeActivityTwo.this).load(R.mipmap.avatar_m2x)
                    .transform(new CircleTransform()).into(imagepreviewresume);
        } else {
            Picasso.with(PreviewResumeActivityTwo.this).load(ContentUrl.BASE_ICON_URL + jsonData.getData().getAvatar())
                    .transform(new CircleTransform()).into(imagepreviewresume);
        }

        if (jsonData.getData().getResume_eduexp().size() == 0 || "".equals(jsonData.getData().getResume_eduexp())) {
            view_height_10.setVisibility(View.GONE);
            view_education_bcak.setVisibility(View.GONE);
            text_education_back.setVisibility(View.GONE);
            text_education_back.setVisibility(View.GONE);
        } else {
            view_height_10.setVisibility(View.VISIBLE);
            view_education_bcak.setVisibility(View.VISIBLE);
            text_education_back.setVisibility(View.VISIBLE);
            text_education_back.setVisibility(View.VISIBLE);
        }

        if (jsonData.getData().getResume_workexp().size() == 0 || "".equals(jsonData.getData().getResume_workexp())) {
            view_height_9.setVisibility(View.GONE);
            view_work_exp.setVisibility(View.GONE);
            view_line_9.setVisibility(View.GONE);
            text_work_exp.setVisibility(View.GONE);
        } else {
            view_height_9.setVisibility(View.VISIBLE);
            view_work_exp.setVisibility(View.VISIBLE);
            view_line_9.setVisibility(View.VISIBLE);
            text_work_exp.setVisibility(View.VISIBLE);
        }

        if (jsonData.getData().getResume_proexp().size() == 0 || "".equals(jsonData.getData().getResume_proexp())) {
            view_height_11.setVisibility(View.GONE);
            view_program_exp.setVisibility(View.GONE);
            view_line_11.setVisibility(View.GONE);
            text_program_exp.setVisibility(View.GONE);
        } else {
            view_height_11.setVisibility(View.VISIBLE);
            view_program_exp.setVisibility(View.VISIBLE);
            view_line_11.setVisibility(View.VISIBLE);
            text_program_exp.setVisibility(View.VISIBLE);
        }

        if (jsonData.getData().getResume_lang().size() == 0 || "".equals(jsonData.getData().getResume_lang())) {
            text_language.setVisibility(View.GONE);
            view_language.setVisibility(View.GONE);
            view_height_12.setVisibility(View.GONE);
            view_line_12.setVisibility(View.GONE);
        } else {
            text_language.setVisibility(View.VISIBLE);
            view_language.setVisibility(View.VISIBLE);
            view_height_12.setVisibility(View.VISIBLE);
            view_line_12.setVisibility(View.VISIBLE);
        }

        if (jsonData.getData().getResume_skill().size() == 0 || "".equals(jsonData.getData().getResume_skill())) {
            text_skill.setVisibility(View.GONE);
            view_skill.setVisibility(View.GONE);
            view_height_13.setVisibility(View.GONE);
            view_line_13.setVisibility(View.GONE);
        } else {
            text_skill.setVisibility(View.VISIBLE);
            view_skill.setVisibility(View.VISIBLE);
            view_height_13.setVisibility(View.VISIBLE);
            view_line_13.setVisibility(View.VISIBLE);
        }

        if (jsonData.getData().getResume_cer().size() == 0 || "".equals(jsonData.getData().getResume_cer())) {
            text_book.setVisibility(View.GONE);
            view_book.setVisibility(View.GONE);
            view_height_14.setVisibility(View.GONE);
            view_line_14.setVisibility(View.GONE);
        } else {
            text_book.setVisibility(View.VISIBLE);
            view_book.setVisibility(View.VISIBLE);
            view_height_14.setVisibility(View.VISIBLE);
            view_line_14.setVisibility(View.VISIBLE);
        }

        if (jsonData.getData().getResume_other().size() == 0 || "".equals(jsonData.getData().getResume_other())) {
            text_other.setVisibility(View.GONE);
            view_other.setVisibility(View.GONE);
            view_height_15.setVisibility(View.GONE);
            view_line_15.setVisibility(View.GONE);
        } else {
            text_other.setVisibility(View.VISIBLE);
            view_other.setVisibility(View.VISIBLE);
            view_height_15.setVisibility(View.VISIBLE);
            view_line_15.setVisibility(View.VISIBLE);
        }

        textname.setText(jsonData.getData().getName());
        textage.setText(jsonData.getData().getAge() + "岁");

        if ("在校学生".equals(jsonData.getData().getWork_year_name())) {
            textworkexp.setText(jsonData.getData().getWork_year_name());
        } else if ("应届毕业生".equals(jsonData.getData().getWork_year_name())) {
            textworkexp.setText(jsonData.getData().getWork_year_name());
        } else {
            textworkexp.setText(jsonData.getData().getWork_year_name() + "工作经验");
        }

        if (jsonData.getData().getHeight() == null || jsonData.getData().getHeight().isEmpty() || "0".equals(jsonData.getData().getHeight())) {
            texthight.setText("未填写");
        } else {
            texthight.setText(jsonData.getData().getHeight() + "cm");
        }
        textloaction.setText(jsonData.getData().getHomeaddress_name());
        text_detail_address.setText(jsonData.getData().getAddress());
        textphone.setText(jsonData.getData().getMobile());
        if (jsonData.getData().getEmail() == null || jsonData.getData().getEmail().isEmpty()) {
            textemail.setVisibility(View.GONE);
            text_emeal.setVisibility(View.GONE);
        } else {
            textemail.setText(jsonData.getData().getEmail());
        }

        if (jsonData.getData().getCensus_name() == null || jsonData.getData().getCensus_name().isEmpty()) {
            text_address_content.setText("未填写");
        } else {
            text_address_content.setText(jsonData.getData().getCensus_name());
        }

        textworkintenson.setText(jsonData.getData().getIntentionjobs());
        text_work_address_content.setText(jsonData.getData().getJobarea_name());
        text_work_category_content.setText(jsonData.getData().getJobsort_name());
        if (jsonData.getData().getQq() == null || jsonData.getData().getQq().isEmpty()) {
            text_qq.setText("未填写");
        } else {
            text_qq.setText(jsonData.getData().getQq());
        }

        if (jsonData.getData().getIntroduction() == null || jsonData.getData().getIntroduction().isEmpty()) {
            textintruduction.setVisibility(View.GONE);
            text_seft.setVisibility(View.GONE);
            view_line_4.setVisibility(View.GONE);
            view_height_3.setVisibility(View.GONE);
            view_height_5.setVisibility(View.GONE);
        } else {
            textintruduction.setText("\t\t" + jsonData.getData().getIntroduction());
        }

        text_education_content.setText(jsonData.getData().getEducation_name());
        if (jsonData.getData().getSex().equals("0")) {
            textsex.setText("保密");
        } else if (jsonData.getData().getSex().equals("1")) {
            textsex.setText("男");
        } else if (jsonData.getData().getSex().equals("2")) {
            textsex.setText("女");
        }

        if (jsonData.getData().getJob_status().equals("1")) {
            textworkexp.setText(jsonData.getData().getEducation_name() + "|" + jsonData.getData().getWork_year_name() + "|" + "不在职，正在找工作");
        } else if (jsonData.getData().getJob_status().equals("2")) {
            textworkexp.setText(jsonData.getData().getEducation_name() + "|" + jsonData.getData().getWork_year_name() + "|" + "在职，打算近期换工作");
        } else if (jsonData.getData().getJob_status().equals("3")) {
            textworkexp.setText(jsonData.getData().getEducation_name() + "|" + jsonData.getData().getWork_year_name() + "|" + "在职，有更好的机会才考虑");
        } else if (jsonData.getData().getJob_status().equals("4")) {
            textworkexp.setText(jsonData.getData().getEducation_name() + "|" + jsonData.getData().getWork_year_name() + "|" + "不考虑换工作");
        }

        if (jsonData.getData().getIsexpectedsalary() == null || "".equals(jsonData.getData().getIsexpectedsalary())) {

        } else {
            if ("0".equals(jsonData.getData().getIsexpectedsalary())) {
                textviewdiscuss.setText(jsonData.getData().getIsexpectedsalary());
            } else {
                textviewdiscuss.setText("面议");
            }

        }

        myWorkPreViewAdapter.setData(jsonData.getData().getResume_workexp());
        myPreEduAdapter.setData(jsonData.getData().getResume_eduexp());
        myPreProExpAdapter.setData(jsonData.getData().getResume_proexp());
        myPreLangAdapter.setData(jsonData.getData().getResume_lang());
        myPreSkillAdapter.setData(jsonData.getData().getResume_skill());
        myPreBookAdapter.setData(jsonData.getData().getResume_cer());
        myPreOtherAdapter.setData(jsonData.getData().getResume_other());

    }


    private ImageView imagepreviewresume;
    private TextView textname;
    private TextView textsex;
    private TextView textage;
    private TextView textworkexp;
    private TextView texthight;
    private TextView textworkstatue;
    private TextView textloaction;
    private TextView textphone;
    private TextView textemail;
    private TextView text_emeal;
    private TextView textqq;
    private TextView textworkintenson;
    private TextView textJobCategory;
    private TextView textviewdiscuss;
    private TextView textplaceadd;
    private TextView textintruduction;
    private TextView text_education_exp;
    private TextView text_detail_address_content;
    private TextView text_program_exp;
    private TextView text_language;
    private TextView text_skill;
    private TextView text_book;
    private TextView text_other;
    private TextView text_seft;
    private TextView text_address_content;
    private TextView text_work_address_content;
    private TextView text_work_category_content;
    private TextView text_qq;
    private TextView text_education_content;
    private FrameLayout fram_decribe_back;
    private ListView listviewworkexperience;
    private ListView listvieweduecationxperience;
    private ListView listviewprojectexperience;
    private ListView listviewlanguage;
    private ListView listviewskill;
    private ListView listviewbook;
    private ListView listviewothermessage;
    private View view_height_9, view_line_9, view_work_exp;
    private View view_height_10, view_education_bcak, view_line_10;
    private View view_height_11, view_program_exp, view_line_11;
    private View view_height_13, view_height_12;
    private View view_line_12, view_line_13, view_skill;
    private View view_line_14, view_height_14;
    private View view_line_15, view_height_15;
    private View view_language;
    private View view_book;
    private View view_other;
    private View view_line_4;
    private View view_height_3;
    private View view_height_5;
    private ScrollView scrollview;
    private RelativeLayout relative_loading;
    private TextView text_detail_address, text_work_exp, text_education_back;
    MyWorkPreViewAdapter myWorkPreViewAdapter;
    MyPreEduAdapter myPreEduAdapter;
    MyPreProExpAdapter myPreProExpAdapter;
    MyPreLangAdapter myPreLangAdapter;
    MyPreSkillAdapter myPreSkillAdapter;
    MyPreBookAdapter myPreBookAdapter;
    MyPreOtherAdapter myPreOtherAdapter;
    @Override
    public void initView() {

        fram_decribe_back = (FrameLayout) findViewById(R.id.fram_decribe_back);

        imagepreviewresume = (ImageView) findViewById(R.id.imagepreviewresume);
        textname = (TextView) findViewById(R.id.textname);
        textworkexp = (TextView) findViewById(R.id.textworkexp);
        textsex = (TextView) findViewById(R.id.text_sex_content);
        textage = (TextView) findViewById(R.id.text_birthday_content);
        text_education_exp = (TextView) findViewById(R.id.text_education_content);
        texthight = (TextView) findViewById(R.id.text_bady_content);
        text_detail_address = (TextView) findViewById(R.id.text_detail_address_content);
        textworkintenson = (TextView) findViewById(R.id.text_want_job_content);
        textplaceadd = (TextView) findViewById(R.id.textplaceadd);
        textviewdiscuss = (TextView) findViewById(R.id.text_salary);
        textphone = (TextView) findViewById(R.id.text_phone_numbers);
        textemail = (TextView) findViewById(R.id.text_emeals);
        text_emeal = (TextView) findViewById(R.id.text_emeal);
        textintruduction = (TextView) findViewById(R.id.text_self_evaluate);
        textloaction = (TextView) findViewById(R.id.text_place_content);
        text_detail_address = (TextView) findViewById(R.id.text_detail_address_content);
        text_seft = (TextView) findViewById(R.id.text_seft);
        text_detail_address_content = (TextView) findViewById(R.id.text_detail_address_content);
        text_address_content = (TextView) findViewById(R.id.text_address_content);
        text_work_address_content = (TextView) findViewById(R.id.text_work_address_content);
        text_work_category_content = (TextView) findViewById(R.id.text_work_category_content);
        text_education_content = (TextView) findViewById(R.id.text_education_content);
        text_qq = (TextView) findViewById(R.id.text_qq_number);
        scrollview = (ScrollView) findViewById(R.id.scrollview);
        relative_loading = (RelativeLayout) findViewById(R.id.relative_loading);

        relative_loading.setVisibility(View.VISIBLE);
        scrollview.setVisibility(View.INVISIBLE);

        view_height_9 = (View) findViewById(R.id.view_height_9);
        view_work_exp = (View) findViewById(R.id.view_work_exp);
        view_line_9 = (View) findViewById(R.id.view_line_9);
        text_work_exp = (TextView) findViewById(R.id.text_work_exp);

        view_height_10 = (View) findViewById(R.id.view_height_10);
        view_education_bcak = (View) findViewById(R.id.view_education_bcak);
        view_line_10 = (View) findViewById(R.id.view_line_10);
        text_education_back = (TextView) findViewById(R.id.text_education_back);

        view_height_11 = (View) findViewById(R.id.view_height_11);
        view_program_exp = (View) findViewById(R.id.view_program_exp);
        view_line_11 = (View) findViewById(R.id.view_line_11);
        text_program_exp = (TextView) findViewById(R.id.text_program_exp);

        view_height_13 = (View) findViewById(R.id.view_height_13);
        view_skill = (View) findViewById(R.id.view_skill);
        view_line_13 = (View) findViewById(R.id.view_line_13);
        text_skill = (TextView) findViewById(R.id.text_skill);

        view_line_12 = (View) findViewById(R.id.view_line_12);
        view_language = (View) findViewById(R.id.view_language);
        view_height_12 = (View) findViewById(R.id.view_height_12);
        text_language = (TextView) findViewById(R.id.text_language);

        view_line_14 = (View) findViewById(R.id.view_height_14);
        view_book = (View) findViewById(R.id.view_book);
        view_height_14 = (View) findViewById(R.id.view_height_14);
        text_book = (TextView) findViewById(R.id.text_book);

        view_line_15 = (View) findViewById(R.id.view_height_15);
        view_other = (View) findViewById(R.id.view_other);
        view_height_15 = (View) findViewById(R.id.view_height_15);
        view_line_4 = (View) findViewById(R.id.view_line_4);
        view_height_3 = (View) findViewById(R.id.view_height_3);
        view_height_5 = (View) findViewById(R.id.view_height_5);
        text_other = (TextView) findViewById(R.id.text_other_msg);

        listviewworkexperience = (ListView) findViewById(R.id.listviewworkexperience);
        listvieweduecationxperience = (ListView) findViewById(R.id.listvieweduecationxperience);
        listviewprojectexperience = (ListView) findViewById(R.id.listviewprojectexperience);
        listviewlanguage = (ListView) findViewById(R.id.listviewlanguage);
        listviewskill = (ListView) findViewById(R.id.listviewskill);
        listviewbook = (ListView) findViewById(R.id.listviewbook);
        listviewothermessage = (ListView) findViewById(R.id.listviewothermessage);
        fram_decribe_back.setOnClickListener(this);
        SharedPreferences share = getSharedPreferences("Activity", MODE_PRIVATE);
        api_token = share.getString("api_token", "");
        SharedPreferences shares = getSharedPreferences("data", MODE_PRIVATE);
        resume_id = shares.getString("resume_id", "");

        myWorkPreViewAdapter = new MyWorkPreViewAdapter(listWorkExp);
        listviewworkexperience.setAdapter(myWorkPreViewAdapter);

        myPreEduAdapter = new MyPreEduAdapter(educationList);
        listvieweduecationxperience.setAdapter(myPreEduAdapter);

        myPreProExpAdapter = new MyPreProExpAdapter(projectList);
        listviewprojectexperience.setAdapter(myPreProExpAdapter);

        myPreLangAdapter = new MyPreLangAdapter(langList);
        listviewlanguage.setAdapter(myPreLangAdapter);


        myPreSkillAdapter = new MyPreSkillAdapter(skillList);
        listviewskill.setAdapter(myPreSkillAdapter);

        myPreBookAdapter = new MyPreBookAdapter(bookList);
        listviewbook.setAdapter(myPreBookAdapter);

        myPreOtherAdapter=new MyPreOtherAdapter(otherList);
        listviewothermessage.setAdapter(myPreOtherAdapter);

        listviewworkexperience.setFocusable(false);
        listvieweduecationxperience.setFocusable(false);
        listviewprojectexperience.setFocusable(false);
        listviewlanguage.setFocusable(false);
        listviewskill.setFocusable(false);
        listviewbook.setFocusable(false);
        listviewothermessage.setFocusable(false);

        gainDatas();
    }


    /**
     * 点击事件的方法集合
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fram_decribe_back://返回上一个界面
                finish();
                break;
        }
    }
}
