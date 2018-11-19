package com.wangyukui.ywkj.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wangyukui.R;
import com.wangyukui.wxapi.WXEntryActivity;
import com.wangyukui.ywkj.activity.BlacknumActivity;
import com.wangyukui.ywkj.activity.EditPartJobActivity;
import com.wangyukui.ywkj.activity.EditResumeActivity;
import com.wangyukui.ywkj.activity.EditResumeTwoActivity;
import com.wangyukui.ywkj.activity.MainActivity;
import com.wangyukui.ywkj.activity.PartJobActivity;
import com.wangyukui.ywkj.activity.PreviewResumeActivityTwo;
import com.wangyukui.ywkj.activity.RegisterActivity;
import com.wangyukui.ywkj.activity.ResumeStateActivity;
import com.wangyukui.ywkj.content.ContentUrl;
import com.wangyukui.ywkj.view.CircleTransform;
import com.wangyukui.ywkj.view.RoundImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import it.sephiroth.android.library.picasso.Picasso;


/**
 * Created by Administrator on 2017/4/1.
 */
@SuppressLint("ValidFragment")
public class ResumeFragent extends Fragment {

    private final Context context;
    @Nullable
    @Bind(R.id.fram_job_back)
    FrameLayout framJobBack;
    @Nullable
    @Bind(R.id.ll_resume_register)
    LinearLayout llResumeRegister;
    @Nullable
    @Bind(R.id.ll_resume_login)
    LinearLayout llResumeLogin;
    @Nullable
    @Bind(R.id.imageviewresume)
    RoundImageView imageviewresume;
    @Nullable
    @Bind(R.id.tv_user_name)
    TextView tvUserName;
    @Nullable
    @Bind(R.id.textdegree)
    TextView textdegree;
    @Nullable
    @Bind(R.id.progressbar)
    ProgressBar progressbar;
    @Nullable
    @Bind(R.id.tv_user_phone)
    TextView tvUserPhone;
    @Nullable
    @Bind(R.id.image_edit_resume)
    ImageView imageEditResume;
    @Nullable
    @Bind(R.id.rl_edit_resume)
    RelativeLayout rlEditResume;
    @Nullable
    @Bind(R.id.image_review_resume)
    ImageView imageReviewResume;
    @Nullable
    @Bind(R.id.rl_preview_resume)
    RelativeLayout rlPreviewResume;
    @Nullable
    @Bind(R.id.image_open_resume)
    ImageView imageOpenResume;
    @Nullable
    @Bind(R.id.iamge_next_open)
    ImageView iamgeNextOpen;
    @Nullable
    @Bind(R.id.textresume_status)
    TextView textresumeStatus;
    @Nullable
    @Bind(R.id.rl_open_resume)
    RelativeLayout rlOpenResume;
    @Nullable
    @Bind(R.id.image_company_blacklist)
    ImageView imageCompanyBlacklist;
    @Nullable
    @Bind(R.id.rl_company_blacklist)
    RelativeLayout rlCompanyBlacklist;
    @Nullable
    @Bind(R.id.image_company_blacklists)
    ImageView imageCompanyBlacklists;
    @Nullable
    @Bind(R.id.rl_part_time_job)
    RelativeLayout rlPartTimeJob;
    @Nullable
    @Bind(R.id.text_create_resume)
    TextView textCreateResume;

    public ResumeFragent(MainActivity context) {
        this.context = context;
    }

    String degree1="";
    String resume_status="";
    String api_token="";
    String part_resume_id="";
    String resume_id="";
    String names="";
    String phonee="";
    String avatar="";

    @Nullable
    @Override
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        SharedPreferences shared = getContext().getSharedPreferences("Activity", Context.MODE_PRIVATE);
        api_token = shared.getString("api_token", "");
        SharedPreferences shares = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        avatar = shares.getString("avatar", "");
        resume_id = shares.getString("resume_id", "");
        degree1 = shares.getString("degree1", "");
        names = shares.getString("name", "");
        phonee = shares.getString("resume_mobile", "");
        Log.i("degree1vdegree1",degree1+names+phonee);

        if (api_token.isEmpty()) {//没有登录账号显示的界面
            View viewLogin = inflater.inflate(R.layout.resume_fragment, container, false);
            ButterKnife.bind(this, viewLogin);
            framJobBack.setVisibility(View.INVISIBLE);
            return viewLogin;
        } else {//登录后但是没有创建简历的界面
            if (resume_id == null || "".equals(resume_id)) {
                View viewNoResume = inflater.inflate(R.layout.resgister_no_resume, container, false);
                ButterKnife.bind(this, viewNoResume);
                return viewNoResume;
            } else {//登录并也创建了简历的界面
                View viewAll = inflater.inflate(R.layout.user_register_success, container, false);
                ButterKnife.bind(this, viewAll);

                tvUserName.setText(names);
                tvUserPhone.setText(phonee);

                disPlayData();//展示界面的数据

                return viewAll;
            }
        }
    }

    private void disPlayData() {

        if ("1".equals(resume_status)) {
            textresumeStatus.setText("完全公开");
        } else if ("2".equals(resume_status)) {
            textresumeStatus.setText("不公开");
        } else if ("3".equals(resume_status)) {
            textresumeStatus.setText("完全保密");
        }

        if ("0".equals(degree1) || "".equals(degree1)) {
            textdegree.setText("0%");
        } else {
            Log.i("degree1", degree1);
            final Float degree = Float.parseFloat(degree1) * 100;
            textdegree.setText(Math.round(degree) + "%");
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    for (int i = 0; i < degree; i++) {
                        SystemClock.sleep(10);
                        final int finalI = i;
                        progressbar.setProgress(finalI);
                    }
                }
            }.start();
            if (avatar == null || avatar.isEmpty()) {
                Picasso.with(getActivity())
                        .load(R.mipmap.avatar_m2x)
                        .transform(new CircleTransform())
                        .into(imageviewresume);
            } else {
                Picasso.with(getActivity())
                        .load(ContentUrl.BASE_ICON_URL + avatar)
                        .transform(new CircleTransform())
                        .into(imageviewresume);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Nullable
    @OnClick({R.id.ll_resume_register, R.id.ll_resume_login, R.id.rl_edit_resume,
            R.id.rl_preview_resume, R.id.rl_open_resume,
            R.id.rl_company_blacklist, R.id.rl_part_time_job,R.id.text_create_resume})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_resume_register:
                Intent intentRegister = new Intent(getActivity(), RegisterActivity.class);
                intentRegister.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentRegister);
                break;
            case R.id.ll_resume_login:
                Intent intentLogin = new Intent(getActivity(), WXEntryActivity.class);
                intentLogin.putExtra("register", "register");
                intentLogin.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentLogin);
                break;
            case R.id.rl_edit_resume:
                if (resume_id == null || "".equals(resume_id)) {//没有创建简历进入的界面
                    Intent editResumeIntent = new Intent(context, EditResumeActivity.class);
                    startActivity(editResumeIntent);

                } else {//创建简历之后进入的页面
                    Intent editTwoResumeIntent = new Intent(context, EditResumeTwoActivity.class);
                    startActivity(editTwoResumeIntent);
                }
                break;
            case R.id.rl_preview_resume:
                if (resume_id == null || "".equals(resume_id)) {
                    Toast.makeText(context, "您还没有创建简历", Toast.LENGTH_SHORT).show();
                } else {
                    Intent previewIntent = new Intent(context, PreviewResumeActivityTwo.class);
                    startActivity(previewIntent);
                }
                break;
            case R.id.rl_open_resume:
                if (resume_id == null || "".equals(resume_id)) {
                    Toast.makeText(context, "您还没有创建简历", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(context, ResumeStateActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.rl_company_blacklist:
                if (resume_id == null || "".equals(resume_id)) {
                    Toast.makeText(context, "您还没有创建简历", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intentBlack = new Intent(context, BlacknumActivity.class);
                    startActivity(intentBlack);
                }
                break;
            case R.id.rl_part_time_job:
                if (part_resume_id == null || "".equals(part_resume_id)) {
                    Intent intent = new Intent(context, PartJobActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(context, EditPartJobActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.text_create_resume:
                Intent intent = new Intent(getActivity(), EditResumeActivity.class);
                startActivity(intent);
                break;
        }
    }
}
