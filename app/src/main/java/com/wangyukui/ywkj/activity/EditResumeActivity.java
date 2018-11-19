package com.wangyukui.ywkj.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.hss01248.dialog.StyledDialog;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.wangyukui.R;
import com.wangyukui.ywkj.content.ContentUrl;
import com.wangyukui.ywkj.content.OkhttpUtils;
import com.wangyukui.ywkj.jmessage.BaseActivity;
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
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;

/**
 * Created by Administrator on 2017/4/19.
 */

public class EditResumeActivity extends BaseActivity{

    @Bind(R.id.iv_back_basic)
    FrameLayout ivBackBasic;
    @Bind(R.id.tv_next_basic)
    TextView tvNextBasic;
    @Bind(R.id.tv_one_number)
    TextView tvOneNumber;
    @Bind(R.id.tv_two_number)
    TextView tvTwoNumber;
    @Bind(R.id.tv_three_number)
    TextView tvThreeNumber;
    @Bind(R.id.tv_four_number)
    TextView tvFourNumber;
    @Bind(R.id.text_name)
    TextView textName;
    @Bind(R.id.icon_image_next_basic)
    ImageView iconImageNextBasic;
    @Bind(R.id.tv_input_name)
    TextView tvInputName;
    @Bind(R.id.rl_name)
    RelativeLayout rlName;
    @Bind(R.id.text_sex)
    TextView textSex;
    @Bind(R.id.icon_image_next_sex)
    ImageView iconImageNextSex;
    @Bind(R.id.tv_sex_input)
    TextView tvSexInput;
    @Bind(R.id.rl_sex)
    RelativeLayout rlSex;
    @Bind(R.id.text_birthday)
    TextView textBirthday;
    @Bind(R.id.icon_image_next_birthday)
    ImageView iconImageNextBirthday;
    @Bind(R.id.tv_select_birthday)
    TextView tvSelectBirthday;
    @Bind(R.id.rl_birthday)
    RelativeLayout rlBirthday;
    @Bind(R.id.text_education)
    TextView textEducation;
    @Bind(R.id.icon_image_next_education)
    ImageView iconImageNextEducation;
    @Bind(R.id.tv_input_education)
    TextView tvInputEducation;
    @Bind(R.id.rl_education)
    RelativeLayout rlEducation;
    @Bind(R.id.text_work)
    TextView textWork;
    @Bind(R.id.icon_image_next_work)
    ImageView iconImageNextWork;
    @Bind(R.id.tv_input_work_suffer)
    TextView tvInputWorkSuffer;
    @Bind(R.id.rl_work_suffer)
    RelativeLayout rlWorkSuffer;
    @Bind(R.id.text_address)
    TextView textAddress;
    @Bind(R.id.icon_image_next_location)
    ImageView iconImageNextLocation;
    @Bind(R.id.tv_select_location)
    TextView tvSelectLocation;
    @Bind(R.id.rl_location)
    RelativeLayout rlLocation;
    @Bind(R.id.text_state)
    TextView textState;
    @Bind(R.id.icon_image_next_static)
    ImageView iconImageNextStatic;
    @Bind(R.id.tv_work_state_basic)
    TextView tvWorkStateBasic;
    @Bind(R.id.rl_work_state)
    RelativeLayout rlWorkState;
    @Bind(R.id.text_phone_number)
    TextView textPhoneNumber;
    @Bind(R.id.icon_image_next_phone)
    ImageView iconImageNextPhone;
    @Bind(R.id.tv_input_phone)
    TextView tvInputPhone;
    @Bind(R.id.rl_phone_number)
    RelativeLayout rlPhoneNumber;
    @Bind(R.id.text_email)
    TextView textEmail;
    @Bind(R.id.icon_image_next_email)
    ImageView iconImageNextEmail;
    @Bind(R.id.tv_input_email)
    TextView tvInputEmail;
    @Bind(R.id.rl_email)
    RelativeLayout rlEmail;
    @Bind(R.id.icon_image_next_qq)
    ImageView iconImageNextQq;
    @Bind(R.id.tv_input_qq)
    TextView tvInputQq;
    @Bind(R.id.rl_qq)
    RelativeLayout rlQq;
    @Bind(R.id.ll_popuwind)
    LinearLayout llPopuwind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic_information_activity);
        ButterKnife.bind(this);
        initTimePicker();//选择生日的日期
        initSexPicker();//选择性别
    }

    /**
     * 选择性别的弹出框
     */
    OptionsPickerView pvOptions;

    private void initSexPicker() {
        final List<String> cardItem = new ArrayList<>();
        cardItem.add("保密");
        cardItem.add("男");
        cardItem.add("女");
        pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String tx = cardItem.get(options1);
                tvSexInput.setText(tx);
                sex = options1 + "";
            }
        })
                .setSubmitText("确定")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
                .setTitleText("性别")//标题
                .setSubCalSize(18)//确定和取消文字大小
                .setTitleSize(20)//标题文字大小
                .setTitleBgColor(0xFFffffff)//标题背景颜色 Night mode
                .setBgColor(0xFFffffff)//滚轮背景颜色 Night mode
                .setContentTextSize(18)//滚轮文字大小
                .setLinkage(false)//设置是否联动，默认true
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setCyclic(false, false, false)//循环与否
                .setSelectOptions(1, 1, 1)  //设置默认选中项
                .setOutSideCancelable(false)//点击外部dismiss default true
                .isDialog(false)//是否显示为对话框样式
                .build();
        pvOptions.setPicker(cardItem);//添加数据源
    }


    private String id;
    private String resume_eid;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1000:
                    if (code == 200) {
                        StyledDialog.dismissLoading();
                        JSONObject json = JSON.parseObject(str);
                        String code = json.getString("code");
                        if (code.equals("1")) {
                            Toast.makeText(EditResumeActivity.this, "提交数据成功", Toast.LENGTH_SHORT).show();
                            String data = json.getString("data");
                            JSONObject jsonObject = JSON.parseObject(data);

                            id = jsonObject.getString("id");
                            String degree = jsonObject.getString("degree");
                            String status = jsonObject.getString("resume_status");
                            resume_eid = jsonObject.getString("resume_eid");

                            SharedPreferences share = getSharedPreferences("data", MODE_PRIVATE);
                            SharedPreferences.Editor editor = share.edit();

                            editor.putString("resume_id", id);
                            editor.putString("degree1", degree);
                            editor.putString("status", status);
                            editor.putString("resume_eid", resume_eid);
                            editor.commit();

                            System.out.println("id===========" + id);
                            System.out.println("degree1===========" + degree);
                            System.out.println("status===========" + status);
                            System.out.println("resume_eid===========" + resume_eid);

                            registerPersoneralUser();

                            Intent JobIntension = new Intent(EditResumeActivity.this, IntensionActivity.class);
                            startActivity(JobIntension);
                            finish();
                        }
                    } else {
                        StyledDialog.dismissLoading();
                        Toast.makeText(EditResumeActivity.this, "数据保存失败", Toast.LENGTH_SHORT).show();
                    }

                    break;
                case 1200:
                    StyledDialog.dismissLoading();
                    Toast.makeText(EditResumeActivity.this, "保存数据失败请重试", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    /**
     * 登录聊天的求职者的用户账号
     */
    private void registerPersoneralUser() {
        SharedPreferences shared = getSharedPreferences("data", MODE_PRIVATE);
        String uid = shared.getString("uid", "");
        String chat_pwd = shared.getString("chat_pwd", "");
        if (chat_pwd != null && chat_pwd.length() > 0) {
            JMessageClient.login("personal_" + uid, chat_pwd, new BasicCallback() {
                @Override
                public void gotResult(int i, String s) {
                    Log.i("职位详情的登陆成功", i + "字符串" + s);
                    updataJGData();
                }
            });
        }
    }

    private void updataJGData() {
        UserInfo userInfo = JMessageClient.getMyInfo();
        Log.i("从本地获取当前登录用户的信息", "1111111" + userInfo);
        if (userInfo == null) {

        } else {
            userInfo.setRegion(resume_eid);

            JMessageClient.updateMyInfo(UserInfo.Field.region, userInfo, new BasicCallback() {

                @Override
                public void gotResult(int i, String s) {
                    Log.i("更新头像", "i：" + i + "  s;" + s);
                }
            });

        }
    }


    @OnClick({R.id.iv_back_basic, R.id.tv_next_basic,
            R.id.rl_name, R.id.rl_sex, R.id.rl_birthday,
            R.id.rl_education, R.id.rl_work_suffer, R.id.rl_location,
            R.id.rl_work_state, R.id.rl_phone_number, R.id.rl_email, R.id.rl_qq})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.iv_back_basic://返回上一个界面
                finish();
                break;
            case R.id.tv_next_basic://点击下一步进入下一个界面
                submitData();
                break;
            case R.id.rl_name://姓名
                Intent inputNameIntent = new Intent(EditResumeActivity.this, NameActivity.class);
                inputNameIntent.putExtra("params", "name");
                inputNameIntent.putExtra("mobile", name);
                startActivityForResult(inputNameIntent, 10);
                break;
            case R.id.rl_sex://性别

                pvOptions.show();

                break;
            case R.id.rl_birthday://生日
                pvTime.show();
                break;
            case R.id.rl_education://学历背景
                Intent intentEdcation = new Intent(EditResumeActivity.this, EducationActivity.class);
                intentEdcation.putExtra("education", "education");
                intentEdcation.putExtra("company_name", edcation);
                startActivityForResult(intentEdcation, 15);
                break;
            case R.id.rl_work_suffer://工作经验
                Intent intentSuffer = new Intent(EditResumeActivity.this, EducationActivity.class);
                intentSuffer.putExtra("education", "suffer");
                intentSuffer.putExtra("company_name", suffer);
                startActivityForResult(intentSuffer, 16);
                break;
            case R.id.rl_location://现居住地
                Intent locationIntent = new Intent(EditResumeActivity.this, LocationActivity.class);
                locationIntent.putExtra("params", "area");
                locationIntent.putExtra("paramss", "areas");
                locationIntent.putExtra("paramss_name", "cesus");
                startActivityForResult(locationIntent, 9);
                break;
            case R.id.rl_work_state://求职状态
                final List<String> cardState = new ArrayList<>();
                cardState.add("不在职，正在找工作");
                cardState.add("在职，打算近期换工作");
                cardState.add("在职，有更好的机会才考虑");
                cardState.add("不考虑换工作");
                OptionsPickerView pvOptionWork = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        String tx = cardState.get(options1);
                        tvWorkStateBasic.setText(tx);
                        workstate = options1 + 1 + "";
                        System.out.print("asdonosanfmoasmalk" + tx);
                    }
                })
                        .setSubmitText("确定")//确定按钮文字
                        .setCancelText("取消")//取消按钮文字
                        .setTitleText("求职状态")//标题
                        .setSubCalSize(18)//确定和取消文字大小
                        .setTitleSize(20)//标题文字大小
                        .setTitleBgColor(0xFFffffff)//标题背景颜色 Night mode
                        .setBgColor(0xFFffffff)//滚轮背景颜色 Night mode
                        .setContentTextSize(18)//滚轮文字大小
                        .setLinkage(false)//设置是否联动，默认true
                        .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                        .setCyclic(false, false, false)//循环与否
                        .setSelectOptions(1, 1, 1)  //设置默认选中项
                        .setOutSideCancelable(false)//点击外部dismiss default true
                        .isDialog(false)//是否显示为对话框样式
                        .build();
                pvOptionWork.show();
                pvOptionWork.setPicker(cardState);//添加数据源

                break;
            case R.id.rl_phone_number://电话号码
                Intent intentPhone = new Intent(EditResumeActivity.this, NameActivity.class);
                intentPhone.putExtra("params", "phone");
                intentPhone.putExtra("mobile", phone);
                startActivityForResult(intentPhone, 12);
                break;
            case R.id.rl_email://邮箱
                Intent intentEmail = new Intent(EditResumeActivity.this, NameActivity.class);
                intentEmail.putExtra("params", "email");
                intentEmail.putExtra("mobile", email);
                startActivityForResult(intentEmail, 13);
                break;
            case R.id.rl_qq://QQ号码
                Intent intentQQ = new Intent(EditResumeActivity.this, NameActivity.class);
                intentQQ.putExtra("params", "qq");
                intentQQ.putExtra("mobile", qq);
                startActivityForResult(intentQQ, 14);
                break;

        }
    }

    private String name = "";
    private String phone = "";
    private String email = "";
    private String suffer = "";
    private String sufferId;
    private String edcation = "";
    private String educationId = "";
    private String location = "";
    private String locationId = "";
    private String birthday = "";
    private String sex = "0";
    private String workstate = "1";
    private String qq = "";

    /**
     * 提交数据
     */
    private void submitData() {
        SharedPreferences share = getSharedPreferences("data", MODE_PRIVATE);

        if (name == null || name.isEmpty()) {
            Toast.makeText(EditResumeActivity.this, "请输入姓名", Toast.LENGTH_SHORT).show();
            return;
        }
        if (sex == null || name.isEmpty()) {
            Toast.makeText(EditResumeActivity.this, "请选择性别", Toast.LENGTH_SHORT).show();
            return;
        }
        if (birthday == null || birthday.isEmpty()) {
            Toast.makeText(this, "请选择生日", Toast.LENGTH_SHORT).show();
            return;
        }
        if (edcation == null || edcation.isEmpty()) {
            Toast.makeText(EditResumeActivity.this, "请选择学历", Toast.LENGTH_SHORT).show();
            return;
        }

        if (suffer == null || suffer.isEmpty()) {
            Toast.makeText(EditResumeActivity.this, "请选择相关工作经验", Toast.LENGTH_SHORT).show();
            return;
        }
        if (location == null || location.isEmpty()) {
            Toast.makeText(EditResumeActivity.this, "请选择现居住地", Toast.LENGTH_SHORT).show();
            return;
        }
        if (workstate == null || workstate.isEmpty()) {
            Toast.makeText(EditResumeActivity.this, "请选择求职状态", Toast.LENGTH_SHORT).show();
            return;
        }
        if (phone == null || phone.isEmpty()) {
            Toast.makeText(EditResumeActivity.this, "请输入手机号码", Toast.LENGTH_SHORT).show();
            return;
        }

        Pattern pattern = Pattern.compile(ContentUrl.PHONE_PARTTEN);
        Matcher matcher = pattern.matcher(phone);

        if (!matcher.matches()) {
            Toast.makeText(EditResumeActivity.this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
            return;
        }

        uploadingData();
    }

    private String str;
    private int code;

    /**
     * 向服务器提交数据
     */
    private void uploadingData() {
        StyledDialog.buildLoading().show();
        SharedPreferences share = getSharedPreferences("Activity", MODE_PRIVATE);
        String api_token = share.getString("api_token", "");
        FormEncodingBuilder formEncoding = new FormEncodingBuilder();
        formEncoding.add("name", name);
        formEncoding.add("sex", sex);
        formEncoding.add("birthday", birthday + "-01");
        formEncoding.add("education", educationId);
        formEncoding.add("work_year", sufferId);
        formEncoding.add("homeaddress", locationId);
        formEncoding.add("job_status", workstate);
        formEncoding.add("mobile", phone);
        if (email == null || email.isEmpty()) {
            formEncoding.add("email", "");
        } else {
            formEncoding.add("email", email);
        }

        if (qq == null || qq.isEmpty()) {
            formEncoding.add("qq", "");
        } else {
            formEncoding.add("qq", qq);
        }


        OkhttpUtils.getInstance().sendPostHttp(ContentUrl.BASE_URL + ContentUrl.RESUME_INFO, api_token, formEncoding, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                if (e.getMessage() != null) {
                    handler.sendEmptyMessage(1200);
                }
            }

            @Override
            public void onResponse(Response response) throws IOException {
                str = response.body().string();
                code = response.code();
                System.out.println("============++++++++------" + str);
                handler.sendEmptyMessage(1000);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {

        } else {

            switch (requestCode) {
                case 10:
                    name = data.getExtras().getString("name");
                    if (!name.isEmpty()) {
                        tvInputName.setText(name);
                        SharedPreferences shared = getSharedPreferences("data", MODE_PRIVATE);
                        SharedPreferences.Editor nameEditor = shared.edit();
                        nameEditor.putString("name", name);
                        nameEditor.commit();
                    } else {
                        tvInputName.setText("请输入姓名");
                    }
                    break;
                case 12:
                    phone = data.getExtras().getString("phone");
                    if (!phone.isEmpty()) {
                        tvInputPhone.setText(phone);
                        SharedPreferences sharedphone = getSharedPreferences("data", MODE_PRIVATE);
                        SharedPreferences.Editor editorphone = sharedphone.edit();
                        editorphone.putString("phone", phone);
                        editorphone.commit();
                    } else {
                        tvInputPhone.setText("请输入手机号码");
                    }

                    break;
                case 13:
                    email = data.getExtras().getString("email");
                    if (!email.isEmpty()) {
                        tvInputEmail.setText(email);
                        SharedPreferences sharedemail = getSharedPreferences("data", MODE_PRIVATE);
                        SharedPreferences.Editor editoremail = sharedemail.edit();
                        editoremail.putString("email", email);
                        editoremail.commit();
                    } else {
                        tvInputEmail.setText("请输入邮箱");
                    }

                    break;
                case 14:
                    qq = data.getExtras().getString("qq");
                    if (!qq.isEmpty()) {
                        tvInputQq.setText(qq);
                        SharedPreferences sharedqq = getSharedPreferences("data", MODE_PRIVATE);
                        SharedPreferences.Editor editorqq = sharedqq.edit();
                        editorqq.putString("qq", qq);
                        editorqq.commit();
                    } else {
                        tvInputQq.setText("请输入qq");
                    }

                    break;
                case 15:
                    edcation = data.getExtras().getString("education");
                    educationId = data.getExtras().getString("educationId");
                    tvInputEducation.setText(edcation);
                    break;
                case 16:
                    suffer = data.getExtras().getString("suffer");
                    sufferId = data.getExtras().getString("sufferId");

                    tvInputWorkSuffer.setText(suffer);
                    break;
                case 9:
                    location = data.getExtras().getString("location");
                    locationId = data.getExtras().getString("locationId");

                    System.out.println("-----------ssss" + locationId);
                    tvSelectLocation.setText(location);
                    break;
            }
        }
    }

    /**
     * 选择时间
     */
    private TimePickerView pvTime;

    private void initTimePicker() {
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(1900, 1, 1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2020, 1, 1);
        pvTime = new TimePickerView.Builder(EditResumeActivity.this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                birthday = UiUtils.getTime(date.toString());
                tvSelectBirthday.setText(birthday);
            }
        }).setType(TimePickerView.Type.YEAR_MONTH)
                .setTitleText("选择时间")
                .setRangDate(startDate, endDate)//起始终止年月日设定
                .build();
        pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。

    }

    @Override
    protected void onPause() {
        super.onPause();
        StyledDialog.dismissLoading();
    }
}
