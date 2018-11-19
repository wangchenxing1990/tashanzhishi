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
import com.andview.refreshview.XRefreshView;
import com.bigkoo.pickerview.OptionsPickerView;
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
import com.wangyukui.ywkj.view.MyPopuwindown;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/24.
 */

public class IntensionActivity extends BaseActivity {
    @Bind(R.id.iv_back_basic)
    FrameLayout ivBackBasic;
    @Bind(R.id.tv_next_intension)
    TextView tvNextIntension;
    @Bind(R.id.tv_one_number)
    TextView tvOneNumber;
    @Bind(R.id.tv_two_number)
    TextView tvTwoNumber;
    @Bind(R.id.tv_three_number)
    TextView tvThreeNumber;
    @Bind(R.id.tv_four_number)
    TextView tvFourNumber;
    @Bind(R.id.text_intent_sarly)
    TextView textIntentSarly;
    @Bind(R.id.icon_image_next_basic)
    ImageView iconImageNextBasic;
    @Bind(R.id.tv_select_saraly)
    TextView tvSelectSaraly;
    @Bind(R.id.rl_salary_expection)
    RelativeLayout rlSalaryExpection;
    @Bind(R.id.text_interview)
    TextView textInterview;
    @Bind(R.id.icon_image_next_sex)
    ImageView iconImageNextSex;
    @Bind(R.id.tv_view_disscuess)
    TextView tvViewDisscuess;
    @Bind(R.id.rl_discuss_personally)
    RelativeLayout rlDiscussPersonally;
    @Bind(R.id.text_intent_work)
    TextView textIntentWork;
    @Bind(R.id.icon_image_next_birthday)
    ImageView iconImageNextBirthday;
    @Bind(R.id.textViewIntentsion_work)
    TextView textViewIntentsionWork;
    @Bind(R.id.rl_intension_job)
    RelativeLayout rlIntensionJob;
    @Bind(R.id.text_work_location)
    TextView textWorkLocation;
    @Bind(R.id.icon_image_next_education)
    ImageView iconImageNextEducation;
    @Bind(R.id.tv_select_work)
    TextView tvSelectWork;
    @Bind(R.id.rl_place_job)
    RelativeLayout rlPlaceJob;
    @Bind(R.id.text_work_address)
    TextView textWorkAddress;
    @Bind(R.id.icon_image_next_work)
    ImageView iconImageNextWork;
    @Bind(R.id.tv_select_area_work)
    TextView tvSelectAreaWork;
    @Bind(R.id.rl_work_area)
    RelativeLayout rlWorkArea;
    @Bind(R.id.ll_popuwind)
    LinearLayout llPopuwind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_intension_activity);
        ButterKnife.bind(this);
        showViewPopuwind();
    }


    private String id="";
    String saraly = "";
    String work = "";
    String areaLocation = "";
    String jobsort="";
    String jobarea="";
    String discuss = "1";
    @OnClick({R.id.tv_next_intension, R.id.rl_salary_expection,
            R.id.rl_discuss_personally, R.id.rl_intension_job, R.id.rl_place_job, R.id.rl_work_area})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_next_intension://点击下一步进入下一个界面
                nextView();
                break;
            case R.id.rl_salary_expection://薪资期望
                Intent intentt = new Intent(IntensionActivity.this, EducationActivity.class);
                intentt.putExtra("education", "intensionsaraly");
                intentt.putExtra("company_name", saraly);
                startActivityForResult(intentt, 20);
                break;
            case R.id.rl_discuss_personally://显示面议
               pvOptionWork.show();
                break;
            case R.id.rl_place_job://工作岗位
                Intent intentJob = new Intent(IntensionActivity.this, AllWorkActivity.class);
                //intentJob.putExtra("params", "intension");
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("job_category", (ArrayList<String>) listJob);
                bundle.putStringArrayList("job_categoryId", (ArrayList<String>) listJobId);
                intentJob.putExtras(bundle);
                startActivityForResult(intentJob, 50);
                break;
            case R.id.rl_intension_job://意向工作岗位
                Intent intentIntensionJob = new Intent(IntensionActivity.this, NameActivity.class);
                intentIntensionJob.putExtra("params", "intension");
                intentIntensionJob.putExtra("mobile", work);
                startActivityForResult(intentIntensionJob, 21);
                break;
            case R.id.rl_work_area://工作区域
                Intent locationIntent = new Intent(IntensionActivity.this, LocationActivity.class);
                locationIntent.putExtra("params", "");
//                locationIntent.putExtra("jobareaaId", listAreaId);
//                locationIntent.putExtra("jobareaaName", listArea);
                Bundle bundleName = new Bundle();
                bundleName.putStringArrayList("jobareaaId", (ArrayList<String>) listAreaId);
                bundleName.putStringArrayList("jobareaaName", (ArrayList<String>) listArea);
                locationIntent.putExtras(bundleName);
                locationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(locationIntent, 9);
                break;
        }
    }

    /**
     * 显示是否面议
     */
    OptionsPickerView pvOptionWork;
    private void showViewPopuwind() {
        final List<String> cardState = new ArrayList<>();
        cardState.add("不显示面议");
        cardState.add("显示面议");

        pvOptionWork = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String tx = cardState.get(options1);
                tvViewDisscuess.setText(tx);
                discuss = options1 + "";

                System.out.print("asdonosanfmoasmalk" + tx);

            }
        })
                .setSubmitText("确定")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
                .setTitleText("显示面议")//标题
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

        pvOptionWork.setPicker(cardState);//添加数据源

    }



    private void nextView() {

        if (saralyId == null || "".equals(saralyId)) {
            Toast.makeText(IntensionActivity.this, "请输入期望薪资", Toast.LENGTH_SHORT).show();
            return;
        }

        if (discuss == null || "".equals(discuss)) {
            Toast.makeText(IntensionActivity.this, "请选择是否面议", Toast.LENGTH_SHORT).show();
            return;
        }

        if (work == null || "".equals(work)) {
            Toast.makeText(IntensionActivity.this, "请输入意向工作岗位", Toast.LENGTH_SHORT).show();
            return;
        }

        if (jobsort == null || "".equals(jobsort)) {
            Toast.makeText(IntensionActivity.this, "请选择工作岗位", Toast.LENGTH_SHORT).show();
            return;
        }

        if (jobarea == null || "".equals(jobarea)) {
            Toast.makeText(IntensionActivity.this, "请选择工作地区", Toast.LENGTH_SHORT).show();
            return;
        }

        uploadingData();
    }

    private String str="";
    private void uploadingData() {
        SharedPreferences share = getSharedPreferences("Activity", MODE_PRIVATE);
        String api_token = share.getString("api_token", "");
        SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        id = sharedPreferences.getString("resume_id", "");

        StyledDialog.buildLoading().show();

        FormEncodingBuilder formEncoding = new FormEncodingBuilder();
        formEncoding.add("expectedsalary", saralyId);
        formEncoding.add("id", id);
        formEncoding.add("isexpectedsalary", discuss);
        formEncoding.add("intentionjobs", work);
        formEncoding.add("jobsort", jobsort);
        formEncoding.add("jobarea", jobarea);

        OkhttpUtils.getInstance().sendPostHttp(ContentUrl.BASE_URL + ContentUrl.RESUME_INTENT, api_token, formEncoding, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                if (e.getMessage() != null) {
                    handler.sendEmptyMessage(1200);
                }
            }

            @Override
            public void onResponse(Response response) throws IOException {
                code = response.code();
                str = response.body().string();
                System.out.println("11111111222222" + str);
                handler.sendEmptyMessage(1000);
            }
        });
    }

    String text = "";
    String textLocation;
    List<String> listArea = new ArrayList<>();
    String saralyId;
    List<String> listJob = new ArrayList<>();
    List<String> listJobId = new ArrayList<>();
    List<String> listAreaId = new ArrayList<>();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
        } else {
            switch (requestCode) {
                case 20:
                    saraly = data.getExtras().getString("intensionsaraly");
                    saralyId = data.getExtras().getString("intensionsaralyId");
                    tvSelectSaraly.setText(saraly);

                    break;
                case 21:

                    work = data.getExtras().getString("intension");
                    Log.i("work", work);
                    if (!work.isEmpty()) {
                        textViewIntentsionWork.setText(work);
                    } else {
                        textViewIntentsionWork.setText("请输入意向工作岗位");
                    }


                    break;
                case 9:

                    listArea = (List<String>) data.getExtras().getSerializable("listArea");
                    listAreaId = (List<String>) data.getExtras().getSerializable("listAreaId");
                    areaLocation = "";
                    jobarea = "";
                    for (int i = 0; i < listArea.size(); i++) {
                        Log.i("aaaaaa", listArea.get(i));
                        if (i < listArea.size()) {
                            areaLocation += listArea.get(i) + ",";
                            jobarea += listAreaId.get(i) + ",";
                        } else {
                            areaLocation += listArea.get(i);
                            jobarea += listAreaId.get(i);
                        }
                    }

                    if (!areaLocation.isEmpty()) {
                        tvSelectAreaWork.setText(areaLocation);
                    } else {
                        tvSelectAreaWork.setText("请选择工作地区");
                    }


                    break;
                case 50://工作岗位
                    listJob = (List<String>) data.getExtras().getSerializable("listItem");
                    listJobId = (List<String>) data.getExtras().getSerializable("cid");
                    text = "";
                    jobsort = "";
                    for (int i = 0; i < listJob.size(); i++) {
                        if (listJob.size() == 1 || i == listJob.size() - 1) {
                            text += listJob.get(i);
                            jobsort += listJobId.get(i);
                        } else {
                            text += listJob.get(i) + ",";
                            jobsort += listJobId.get(i) + ",";
                        }

                    }
                    if (!text.isEmpty()) {
                        tvSelectWork.setText(text);
                    } else {
                        tvSelectWork.setText("请选择工作岗位");
                    }

                    break;

            }
        }
    }

    private int code;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1000:
                    if (code == 200) {
                        JSONObject jsonObject = JSON.parseObject(str);
                        String codes = jsonObject.getString("code");
                        if ("1".equals(codes)) {
                            StyledDialog.dismissLoading();
                            Intent intent = new Intent(IntensionActivity.this, EduBackGroundActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            StyledDialog.dismissLoading();
                            Toast.makeText(IntensionActivity.this, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        StyledDialog.dismissLoading();
                        Toast.makeText(IntensionActivity.this, "联网超时,请稍后重试", Toast.LENGTH_SHORT).show();
                    }

                    break;
                case 1200:

                    break;
            }
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(IntensionActivity.this, MainActivity.class);
        intent.putExtra("register", "register");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
