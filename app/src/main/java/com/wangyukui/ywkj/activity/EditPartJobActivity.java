package com.wangyukui.ywkj.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.google.gson.Gson;
import com.hss01248.dialog.StyledDialog;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.wangyukui.R;
import com.wangyukui.wxapi.WXEntryActivity;
import com.wangyukui.ywkj.bean.PartTimeBean;
import com.wangyukui.ywkj.content.ContentUrl;
import com.wangyukui.ywkj.bean.PartTimeBean.DataBean;
import com.wangyukui.ywkj.content.OkhttpUtils;
import com.wangyukui.ywkj.tools.UiUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/28.
 */

public class EditPartJobActivity extends BaseActiviyt {
    @Bind(R.id.fram_job_back)
    FrameLayout framJobBack;
    @Bind(R.id.tv_add_save)
    TextView tvAddSave;
    @Bind(R.id.textSelectArea)
    TextView textSelectArea;
    @Bind(R.id.relativeWorkArea)
    RelativeLayout relativeWorkArea;
    @Bind(R.id.textWorkPost)
    TextView textWorkPost;
    @Bind(R.id.relativeWorkPost)
    RelativeLayout relativeWorkPost;
    @Bind(R.id.textIndustryType)
    TextView textIndustryType;
    @Bind(R.id.relativeIndustryCatgory)
    RelativeLayout relativeIndustryCatgory;
    @Bind(R.id.textSex)
    TextView textSex;
    @Bind(R.id.relativeSex)
    RelativeLayout relativeSex;
    @Bind(R.id.textEducation)
    TextView textEducation;
    @Bind(R.id.relativeEducation)
    RelativeLayout relativeEducation;
    @Bind(R.id.textage)
    TextView textage;
    @Bind(R.id.relativeage)
    RelativeLayout relativeage;
    @Bind(R.id.textExp)
    TextView textExp;
    @Bind(R.id.relativeWorkExp)
    RelativeLayout relativeWorkExp;
    @Bind(R.id.textkeyword)
    TextView textkeyword;
    @Bind(R.id.relative_keyWord)
    RelativeLayout relativeKeyWord;
    @Bind(R.id.textkeywordtype)
    TextView textkeywordtype;
    @Bind(R.id.relative_keywork_type)
    RelativeLayout relativeKeyworkType;
    @Bind(R.id.texttime)
    TextView texttime;
    @Bind(R.id.relativeTime)
    RelativeLayout relativeTime;
    @Bind(R.id.textsearcherName)
    TextView textsearcherName;
    @Bind(R.id.ralativeSearcherName)
    RelativeLayout ralativeSearcherName;
    @Bind(R.id.text_shool_start)
    TextView textShoolStart;
    @Bind(R.id.ralative_school_start)
    RelativeLayout ralativeSchoolStart;
    @Bind(R.id.text_shool_end)
    TextView textShoolEnd;
    @Bind(R.id.ralative_school_end)
    RelativeLayout ralativeSchoolEnd;
    @Bind(R.id.text_oneself)
    TextView textOneself;
    @Bind(R.id.ralative_oneself_introduction)
    RelativeLayout ralativeOneselfIntroduction;
    @Bind(R.id.text_phone_number)
    TextView textPhoneNumber;
    @Bind(R.id.ralative_phone_number)
    RelativeLayout ralativePhoneNumber;
    @Bind(R.id.text_qq_number)
    TextView textQqNumber;
    @Bind(R.id.ralative_qq_number)
    RelativeLayout ralativeQqNumber;
    @Bind(R.id.text_wechat_number)
    TextView textWechatNumber;
    @Bind(R.id.ralative_wechat_number)
    RelativeLayout ralativeWechatNumber;

    @Override
    public int getLayoutId() {
        return R.layout.activity_part_time;
    }

    String api_token;
    String part_resume_id;
    String str;
    int code;

    @Override
    public void initData() {

        SharedPreferences shares = getSharedPreferences("Activity", MODE_PRIVATE);
        api_token = shares.getString("api_token", "");
        SharedPreferences share = getSharedPreferences("data", MODE_PRIVATE);
        part_resume_id = share.getString("part_resume_id", "");
        Log.i("part_resume_id", part_resume_id);
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        gainDataFromService();//获取兼职简历的数据
    }

    /**
     * 获取兼职简历的数据
     */
    private void gainDataFromService() {
        FormEncodingBuilder form = new FormEncodingBuilder();
        form.add("id", part_resume_id);
        OkhttpUtils.getInstance().sendPostHttp(ContentUrl.BASE_URL + ContentUrl.PART_RESUME_EDIT, api_token, form, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                if (e.getMessage() != null) {
                    handler.sendEmptyMessage(GAIN_DATSA_FAILURE);
                }
            }

            @Override
            public void onResponse(Response response) throws IOException {
                code = response.code();
                str = response.body().string();
                Log.i("获取的兼职简历的信息数据", str);
                handler.sendEmptyMessage(GAIN_DATSA_SUCCESS);
            }
        });

    }
    /**
     * 解析从服务器获取的数据
     */
    private void parsFromServiceData() {
        if (code == 200) {
            JSONObject json = JSON.parseObject(str);
            String codes = json.getString("code");
            String message = json.getString("msg");
            if ("1".equals(codes)) {
                Gson gson = new Gson();
                PartTimeBean partTimeBean = gson.fromJson(str, PartTimeBean.class);
                DataBean dataBean = partTimeBean.getData();
                Log.i("id", partTimeBean.getData().getId());
                showPartTimeData(dataBean);//展示数据
            } else if ("-1".equals(codes)) {
                Intent intent = new Intent(EditPartJobActivity.this, WXEntryActivity.class);
                startActivity(intent);
            }

            if (message != null && !"".equals(message)) {
                Toast.makeText(EditPartJobActivity.this, message, Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(EditPartJobActivity.this, "获取数据失败", Toast.LENGTH_SHORT).show();
        }
    }
    /**
     *
     */
    private static final int GAIN_DATSA_FAILURE = 0;
    private static final int GAIN_DATSA_SUCCESS = 1;
    private static final int UPDATA_DATA_SUCCESS = 2;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case GAIN_DATSA_FAILURE://获取数据失败
                    break;
                case GAIN_DATSA_SUCCESS://获取数据成功
                   parsFromServiceData();//解析从服务器获取的数据
                    break;
                case UPDATA_DATA_SUCCESS:
                    parsUpdataToService();//解析更新兼职简历返回的数据
                    break;
            }
        }
    };

    /**
     * 解析更新兼职简历返回的数据
     */
    private void parsUpdataToService() {
        if(code==200){
            JSONObject json=JSON.parseObject(str);
            String codes=json.getString("code");
            String msg=json.getString("msg");
            if ("1".equals(codes)){
                Intent intent=new Intent(EditPartJobActivity.this,MainActivity.class);
                intent.putExtra("register","register");
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }else if("-1".equals(codes)){
                Intent intent=new Intent(EditPartJobActivity.this,WXEntryActivity.class);
               // intent.putExtra("register","register");
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }else if ("0".equals(codes)){

            }
            StyledDialog.dismissLoading();
            Toast.makeText(EditPartJobActivity.this,msg,Toast.LENGTH_SHORT).show();
        }else{
            StyledDialog.dismissLoading();
        }
    }




    private static final int PART_TIME_JOB = 62;
    private static final int PART_TIME_NAME = 52;
    private static final int PART_TIME_HEIGHT = 101;
    private static final int PART_TIME_EDUCATION = 15;
    private static final int PART_TIME_SCHOOL = 30;
    private static final int PART_TIME_DESCRIBE = 39;
    private static final int PART_TIME_PHONE = 12;
    private static final int PART_TIME_QQ = 14;
    private static final int PART_TIME_WECHAT = 24;
    private static final int PART_TIME_LOCATION = 9;
    private static final int PART_TIEM_FREE = 88;
    private String titleTime = "";
    private String locationFlag;
    private String flag;
    private String stateId;

    @OnClick({R.id.fram_job_back, R.id.tv_add_save, R.id.relativeWorkArea, R.id.relativeWorkPost,
            R.id.relativeIndustryCatgory, R.id.relativeSex, R.id.relativeEducation, R.id.relativeage,
            R.id.relativeWorkExp, R.id.relative_keyWord, R.id.relative_keywork_type, R.id.relativeTime, R.id.ralativeSearcherName,
            R.id.ralative_school_start, R.id.ralative_school_end, R.id.ralative_oneself_introduction, R.id.ralative_phone_number,
            R.id.ralative_qq_number, R.id.ralative_wechat_number})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fram_job_back://返回上一个界面
                finish();
                break;
            case R.id.tv_add_save://更新数据
                saveUpdateData();//保存更新的数据
                break;
            case R.id.relativeWorkArea://空闲时间
                Intent intentFree = new Intent(EditPartJobActivity.this, ChoosePtTimeActivity.class);
                intentFree.putExtra("free", free_time);
                startActivityForResult(intentFree, PART_TIEM_FREE);
                break;
            case R.id.relativeWorkPost://兼职意向
                Intent intentIntension = new Intent(EditPartJobActivity.this, EducationActivity.class);
                intentIntension.putExtra("education", "partJob");
                intentIntension.putExtra("partJobId", intention);
                intentIntension.putExtra("partJob", intention_name);
                startActivityForResult(intentIntension, PART_TIME_JOB);
                break;
            case R.id.relativeIndustryCatgory://期望地点
                locationFlag = "intension";
                Intent intentLocation = new Intent(EditPartJobActivity.this, LocationActivityTwo.class);
                intentLocation.putExtra("params", "area");
                startActivityForResult(intentLocation, PART_TIME_LOCATION);
                break;
            case R.id.relativeSex://姓名
                Intent intentName = new Intent(EditPartJobActivity.this, NameActivity.class);
                intentName.putExtra("params", "partTime");
                intentName.putExtra("mobile", name);
                startActivityForResult(intentName, PART_TIME_NAME);
                break;
            case R.id.relativeEducation://性别
                sexList.clear();
                // sexList.add("保密");
                sexList.add("男");
                sexList.add("女");
                titleTime = "选择性别";
                flag = "sex";
                stateId = sex;
                showSelectSex();//显示选择性别
                break;
            case R.id.relativeage://生日
                titleTime = "选择生日";
                flag = "birthday";
                showSelectBirthday();//显示选择生日
                break;
            case R.id.relativeWorkExp://居住地
                locationFlag = "address";
                Intent intentAddress = new Intent(EditPartJobActivity.this, LocationActivityTwo.class);
                intentAddress.putExtra("params", "area");
                startActivityForResult(intentAddress, PART_TIME_LOCATION);
                break;
            case R.id.relative_keyWord://当前状态
                sexList.clear();
                sexList.add("在校学生");
                sexList.add("已经毕业");
                titleTime = "当前状态";
                flag = "state";
                stateId = student;
                showSelectSex();//显示当前状态
                break;
            case R.id.relative_keywork_type://身高
                Intent intentHigh = new Intent(EditPartJobActivity.this, NameActivity.class);
                intentHigh.putExtra("params", "height");
                intentHigh.putExtra("mobile", height);
                startActivityForResult(intentHigh, PART_TIME_HEIGHT);
                break;
            case R.id.relativeTime://学校
                Intent intentSchool = new Intent(EditPartJobActivity.this, NameActivity.class);
                intentSchool.putExtra("params", "schoolname");
                intentSchool.putExtra("mobile", school);
                startActivityForResult(intentSchool, PART_TIME_SCHOOL);
                break;
            case R.id.ralativeSearcherName://学历
                Intent intentEducaton = new Intent(EditPartJobActivity.this, EducationActivity.class);
                intentEducaton.putExtra("education", "education");
                intentEducaton.putExtra("company_name", education_name);
                startActivityForResult(intentEducaton, PART_TIME_EDUCATION);
                break;
            case R.id.ralative_school_start://在校开始时间
                titleTime = "在校开始时间";
                flag = "schoolStart";
                showSelectBirthday();//显示在校开始时间
                break;
            case R.id.ralative_school_end://在校结束时间
                titleTime = "在校结束时间";
                flag = "schoolEnd";
                showSelectBirthday();//显示在校结束时间
                break;
            case R.id.ralative_oneself_introduction://自我介绍
                Intent intentIntroduction = new Intent(EditPartJobActivity.this, DescribeActivity.class);
                intentIntroduction.putExtra("params", "oneselfDescr");
                intentIntroduction.putExtra("introduction", content);
                startActivityForResult(intentIntroduction, PART_TIME_DESCRIBE);
                break;
            case R.id.ralative_phone_number://手机号
                Intent intentPhone = new Intent(EditPartJobActivity.this, NameActivity.class);
                intentPhone.putExtra("params", "phone");
                intentPhone.putExtra("mobile", mobile);
                startActivityForResult(intentPhone, PART_TIME_PHONE);
                break;
            case R.id.ralative_qq_number://qq
                Intent intentQQ = new Intent(EditPartJobActivity.this, NameActivity.class);
                intentQQ.putExtra("params", "qq");
                intentQQ.putExtra("mobile", qq);
                startActivityForResult(intentQQ, PART_TIME_QQ);
                break;
            case R.id.ralative_wechat_number://微信号
                Intent intentWechat = new Intent(EditPartJobActivity.this, NameActivity.class);
                intentWechat.putExtra("params", "wechat");
                intentWechat.putExtra("mobile", wechat);
                startActivityForResult(intentWechat, PART_TIME_WECHAT);
                break;
        }
    }

    /**
     * 保存更新的数据
     */
    private String PHONE="^1\\d{10}$";
    private void saveUpdateData() {

        if (free_time==null||"".equals(free_time)){
            Toast.makeText(EditPartJobActivity.this, "请填写空闲时间", Toast.LENGTH_SHORT).show();
            return;
        }
        if (intention==null||"".equals(intention)){
            Toast.makeText(EditPartJobActivity.this, "请选择兼职意向", Toast.LENGTH_SHORT).show();
            return;
        }
        if (address==null||"".equals(address)){
            Toast.makeText(EditPartJobActivity.this, "请输入姓名", Toast.LENGTH_SHORT).show();
            return;
        }
        if (name == null || "".equals(name)) {
            Toast.makeText(EditPartJobActivity.this, "请输入姓名", Toast.LENGTH_SHORT).show();
            return;
        }
        if (sex == null || "".equals(sex)) {
            Toast.makeText(EditPartJobActivity.this, "请选择性别", Toast.LENGTH_SHORT).show();
            return;
        }
        if (birthday == null || "".equals(birthday)) {
            Toast.makeText(EditPartJobActivity.this, "请选择生日", Toast.LENGTH_SHORT).show();
            return;
        }
        if (address == null || "".equals(address)) {
            Toast.makeText(EditPartJobActivity.this, "请选择居住地", Toast.LENGTH_SHORT).show();
            return;
        }
        if (student == null || "".equals(student)) {
            Toast.makeText(EditPartJobActivity.this, "请选择是否是学生", Toast.LENGTH_SHORT).show();
            return;
        }
        if (content == null || "".equals(content)) {
            Toast.makeText(EditPartJobActivity.this, "请填写自我介绍", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mobile == null || "".equals(mobile)) {
            Toast.makeText(EditPartJobActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        Pattern pattern = Pattern.compile(PHONE);
        Matcher matcher = pattern.matcher(mobile);

        if (!matcher.matches()) {
            Toast.makeText(EditPartJobActivity.this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
            return;
        }
        saveSendDataToservice();//向服务器发送数据

    }

    /**
     * 向服务器发送数据
     */
    private void saveSendDataToservice() {
        StyledDialog.buildLoading().show();
        FormEncodingBuilder form=new FormEncodingBuilder();
        form.add("id",part_resume_id);
        form.add("free_time", free_time);
        form.add("name", name);
        form.add("sex", sex);
        form.add("birthday", birthday);
        form.add("address", address);
        form.add("student", student);
        if (height == null || "".equals(height)) {
            form.add("height", "");
        } else {
            form.add("height", height);
        }

        if (school == null || "".equals(school)) {
            form.add("school", "");
        } else {
            form.add("school", school);
        }

        if (time_start == null || "".equals(time_start)) {
            form.add("time_start", "");
        } else {
            form.add("time_start", time_start);
        }

        if (time_end == null || "".equals(time_end)) {
            form.add("time_end", "");
        } else {
            form.add("time_end", time_end);
        }

        if (education == null || "".equals(education)) {
            form.add("education", "");
        } else {
            form.add("education", education);
        }
        form.add("mobile", mobile);

        if (qq == null || "".equals(qq)) {
            form.add("qq", "");
        } else {
            form.add("qq", qq);
        }

        if (wechat == null || "".equals(wechat)) {
            form.add("wechat", "");
        } else {
            form.add("wechat", wechat);
        }

        form.add("content", content);
        if (intention == null || "".equals(intention)) {
            form.add("intention", "");
        } else {
            form.add("intention", intention);
        }
        if (place == null || "".equals(place)) {
            form.add("place", "");
        } else {
            form.add("place", place);
        }

        //向服务器保存数据
        OkhttpUtils.getInstance().sendPostHttp(ContentUrl.BASE_URL + ContentUrl.PART_RESUME_UPDATE, api_token, form, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                code=response.code();
                str=response.body().string();
                Log.i("更新兼职简历数据",str);
                handler.sendEmptyMessage(UPDATA_DATA_SUCCESS);
            }
        });
    }

    /**
     * 开启一个界面返回的数据
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
            switch (requestCode) {
                case PART_TIEM_FREE://空闲时间
//                    free_time = "";
                    free_time = data.getExtras().getString("free");
                    Log.i("freefreefree", free_time);
                    if (free_time == null || "".equals(free_time)) {

                    } else {
                        textSelectArea.setText("已填");
                    }
                    break;
                case PART_TIME_JOB://兼职意向
                    List<String> listPart = (List<String>) data.getExtras().getSerializable("list");
                    List<String> listPartId = (List<String>) data.getExtras().getSerializable("listId");
                    if (listPart.size() == 0) {
                        textWorkPost.setText("请选择兼职意向");
                        intention_name = "";
                        intention = "";
                    } else {
                        Log.i("listPart", listPart.toString());
                        intention_name = "";
                        intention = "";

                        for (int i = 0; i < listPart.size(); i++) {
                            if (i == listPart.size() - 1) {
                                intention_name += listPart.get(i);
                                intention += listPartId.get(i);
                            } else {
                                intention_name += listPart.get(i) + ",";
                                intention += listPartId.get(i) + ",";
                            }
                        }

                        textWorkPost.setText(intention_name);
                    }

                    break;
                case PART_TIME_LOCATION:
                    String location = data.getExtras().getString("location");
                    String locationId = data.getExtras().getString("locationId");
                    if (location == null || "".equals(location)) {

                    } else {
                        if (locationFlag == "intension") {
                            place_name = location;
                            textIndustryType.setText(place_name);
                            place = locationId;
                        } else if (locationFlag == "address") {
                            address_name = location;
                            textExp.setText(address_name);
                            address = locationId;
                        }
                    }
                    break;
                case PART_TIME_NAME://姓名
                    name = data.getExtras().getString("partTime");
                    if (name == null || "".equals(name)) {
                        textSex.setText("请输入姓名");
                    } else {
                        textSex.setText(name);
                    }
                    break;
                case PART_TIME_HEIGHT://身高
                    height = data.getExtras().getString("height");
                    if (height == null || "".equals(height)) {
                        textkeywordtype.setText("请输入身高");
                    } else {
                        textkeywordtype.setText(height);
                    }
                    break;
                case PART_TIME_SCHOOL://学校
                    school = data.getExtras().getString("schoolname");
                    if (school == null || "".equals(school)) {
                        texttime.setText("请输入学校名称");
                    } else {
                        texttime.setText(school);
                    }
                    break;
                case PART_TIME_EDUCATION://学历
                    education_name = data.getExtras().getString("education");
                    education = data.getExtras().getString("educationId");
                    //  Log.i("edcationIdedcationId",edcationId);
                    if (education == null || "".equals(education)) {
                        textsearcherName.setText("请选择学历");
                    } else {
                        textsearcherName.setText(education_name);
                    }
                    break;
                case PART_TIME_DESCRIBE://自我介绍
                    content = data.getExtras().getString("oneselfDescr");
                    if (content == null || "".equals(content)) {
                        textOneself.setText("请输入自我评价");
                    } else {
                        textOneself.setText(content);
                    }
                    break;
                case PART_TIME_PHONE://手机号
                    mobile = data.getExtras().getString("phone");
                    if (mobile == null || "".equals(mobile)) {
                        textPhoneNumber.setText("请输入手机号");
                    } else {
                        textPhoneNumber.setText(mobile);
                    }
                    break;
                case PART_TIME_QQ://qq
                    qq = data.getExtras().getString("qq");
                    if (qq == null || "".equals(qq)) {
                        textQqNumber.setText("请输入qq号");
                    } else {
                        textQqNumber.setText(qq);
                    }
                    break;
                case PART_TIME_WECHAT://微信
                    wechat = data.getExtras().getString("wechat");
                    if (wechat == null || "".equals(wechat)) {
                        textWechatNumber.setText("请输入微信号");
                    } else {
                        textWechatNumber.setText(wechat);
                    }
                    break;
            }
        }
    }

    /**
     * 展示数据
     */
    String name="", sex="", birthday="", address="", student="", height="",
            school="", time_start="", time_end="", education="", mobile="", qq="", wechat="", content="", intention="", place="",
            address_name="", time_start_name="", time_end_name="", education_name="", place_name="", free_time="", birthday_name="", intention_name="";

    private void showPartTimeData(DataBean dataBean) {
        name = dataBean.getName();
        sex = dataBean.getSex();
        birthday = dataBean.getBirthday();
        address = dataBean.getAddress();
        student = dataBean.getStudent();
        height = dataBean.getHeight();
        school = dataBean.getSchool();
        time_start = dataBean.getTime_start();
        time_end = dataBean.getTime_end();
        education = dataBean.getEducation();
        mobile = dataBean.getMobile();
        qq = dataBean.getQq();
        wechat = dataBean.getWechat();
        content = dataBean.getContent();
        intention = dataBean.getIntention();
        place = dataBean.getPlace();
        address_name = dataBean.getAddress_name();
        time_start_name = dataBean.getTime_start_name();
        time_end_name = dataBean.getTime_end_name();
        education_name = dataBean.getEducation_name();
        place_name = dataBean.getPlace_name();
        free_time = dataBean.getFree_time();
        birthday_name = dataBean.getBirthday_name();
        intention_name = dataBean.getIntention_name();

        if (free_time != null && !"".equals(free_time)) {
            textSelectArea.setText("已填");
        }

        if (intention_name != null && !"".equals(intention_name)) {
            textWorkPost.setText(intention_name);
        }
        if (place_name != null && !"".equals(place_name)) {
            textIndustryType.setText(place_name);
        }
        if (name != null && !"".equals(name)) {
            textSex.setText(name);
        }

        if (sex.equals("1")) {
            textEducation.setText("男");
        } else if (sex.equals("2")) {
            textEducation.setText("女");
        }
        if (birthday_name != null && !"".equals(birthday_name)) {
            textage.setText(birthday_name);
        }
        if (address_name != null && !"".equals(address_name)) {
            textExp.setText(address_name);
        }
        if ("0".equals(student)) {
            textkeyword.setText("在校学生");
        } else if ("1".equals(student)) {
            textkeyword.setText("现已毕业");
        }
        if (height != null && !"".equals(height)) {
            textkeywordtype.setText(height);
        }
        if (school != null && !"".equals(school)) {
            texttime.setText(school);
        }
        if (education_name != null && !"".equals(education_name)) {
            textsearcherName.setText(education_name);
        }
        if (time_start_name != null && !"".equals(time_start_name)) {
            textShoolStart.setText(time_start_name);
        }
        if (time_end_name != null && !"".equals(time_end_name)) {
            textShoolEnd.setText(time_end_name);
        }
        if (content != null && !"".equals(content)) {
            textOneself.setText(content);
        }
        if (mobile != null && !"".equals(mobile)) {
            textPhoneNumber.setText(mobile);
        }
        if (qq != null && !"".equals(qq)) {
            textQqNumber.setText(qq);
        }
        if (wechat != null && !"".equals(wechat)) {
            textWechatNumber.setText(wechat);
        }
    }

    /**
     * 显示选择性别
     */
    private List<String> sexList = new ArrayList<>();

    private void showSelectSex() {
        int sexId;
        if (stateId == null || "".equals(stateId)) {
            sexId = 0;
        } else {
            if (flag.equals("sex")) {
                sexId = Integer.parseInt(stateId) - 1;
            } else {
                sexId = Integer.parseInt(stateId);
            }

        }

        OptionsPickerView pvSwex = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                String tx = sexList.get(options1);
                if (flag.equals("sex")) {
                    textEducation.setText(tx);
                    sex = options1 + 1 + "";
                } else if (flag.equals("state")) {
                    textkeyword.setText(tx);
                    student = options1 + "";
                }
            }
        })
                .setSubmitText("确定")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
                .setTitleText(titleTime)//标题
                .setSubCalSize(18)//确定和取消文字大小
                .setTitleSize(20)//标题文字大小
                .setTitleColor(Color.BLACK)//标题文字颜色
                .setSubmitColor(Color.BLUE)//确定按钮文字颜色
                .setCancelColor(Color.BLUE)//取消按钮文字颜色
                .setTitleBgColor(0xFFffffff)//标题背景颜色 Night mode
                .setBgColor(0xFFffffff)//滚轮背景颜色 Night mode
                .setContentTextSize(18)//滚轮文字大小
                .setLinkage(false)//设置是否联动，默认true
                //.setLabels("省", "市", "区")//设置选择的三级单位
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setCyclic(false, false, false)//循环与否
                .setSelectOptions(sexId, 1, 1)  //设置默认选中项
                .setOutSideCancelable(false)//点击外部dismiss default true
                .isDialog(false)//是否显示为对话框样式
                .build();

        pvSwex.setPicker(sexList);//添加数据源
        pvSwex.show();
    }

    /**
     * 显示选择生日
     */
    private void showSelectBirthday() {
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(1950, 1, 1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2020, 1, 1);
        final TimePickerView birthdayTime = new TimePickerView.Builder(EditPartJobActivity.this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                String data = UiUtils.getTime(date + "");
                if (flag.equals("birthday")) {
                    textage.setText(data);
                    birthday = data + "-01";
                } else if (flag.equals("schoolStart")) {
                    textShoolStart.setText(data);
                    time_start = data + "-01";
                } else if (flag.equals("schoolEnd")) {
                    textShoolEnd.setText(data);
                    time_end = data + "-01";
                }
            }
        }).setType(TimePickerView.Type.YEAR_MONTH)
                .setTitleText(titleTime)
                .setRangDate(startDate, endDate)//起始终止年月日设定
                .build();
        birthdayTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        birthdayTime.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        StyledDialog.dismissLoading();
    }
}
