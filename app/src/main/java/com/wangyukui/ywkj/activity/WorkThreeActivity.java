package com.wangyukui.ywkj.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wangyukui.R;
import com.wangyukui.ywkj.adapter.MyWorkThreeAdapter;
import com.wangyukui.ywkj.bean.AreaOne;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import co.lujun.androidtagview.TagContainerLayout;
import co.lujun.androidtagview.TagView;

/**
 * Created by Administrator on 2017/4/25.
 */
public class WorkThreeActivity extends BaseActiviyt implements View.OnClickListener {
    @Override
    public int getLayoutId() {
        return R.layout.activity_area_code;
    }

    @Override
    public void initData() {

    }

    private List<AreaOne> listThree=new ArrayList<>();
    private TextView tv_province;
    private TextView text_save;

    private FrameLayout iv_back_location;
    private ListView list_view;
    private TagContainerLayout textSelectWork;

    @Override
    public void initView() {

        Intent intent = getIntent();
        listThree = (List<AreaOne>) intent.getSerializableExtra("workTwoo");

        tv_province = (TextView) findViewById(R.id.tv_province);
        iv_back_location = (FrameLayout) findViewById(R.id.iv_back_location);
        list_view = (ListView) findViewById(R.id.list_view);
        text_save= (TextView) findViewById(R.id.text_save);
        textSelectWork= (TagContainerLayout) findViewById(R.id.textSelectWork);
        iv_back_location.setOnClickListener(this);
        text_save.setOnClickListener(this);

        list_view.setOnItemClickListener(myOnItemClickListener);
        list_view.setAdapter(new MyWorkThreeAdapter(listThree));

        textSelectWork.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text) {
                listJob.remove(position);
                textSelectWork.setTags(listJob);
            }

            @Override
            public void onTagLongClick(int position, String text) {

            }

            @Override
            public void onTagCrossClick(int position) {
                listJob.remove(position);
                textSelectWork.setTags(listJob);
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_location:
                finish();
                break;
            case R.id.text_save:
                saveData();//保存数据
                break;
        }
    }

    /**
     * 点击保存数据
     */
    private void saveData() {
        Intent intent=new Intent();
        Bundle bundle=new Bundle();
        bundle.putSerializable("listItem", (Serializable) listJob);
        bundle.putSerializable("cid", (Serializable) listJobId);
        intent.putExtras(bundle);
        WorkThreeActivity.this.setResult(50,intent);
        finish();
    }

    /**
     * 点击条目进行工作岗位的选择
     */
    private List<String> listJob=new ArrayList<>();
    private List<String> listJobId=new ArrayList<>();
    AdapterView.OnItemClickListener myOnItemClickListener=new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            if (listJob.contains(listThree.get(position))){

            }
            for (int i=0;i<listJob.size();i++){
                if (listJob.get(i).equals(listThree.get(position).getName())){
                    Toast.makeText(WorkThreeActivity.this,"不能重复添加",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            if (listJob.size()<5){
                listJob.add(listThree.get(position).getName());
                listJobId.add(listThree.get(position).getCid());
            }else{
                Toast.makeText(WorkThreeActivity.this,"只能选择5个",Toast.LENGTH_SHORT).show();
            }

            textSelectWork.setTags(listJob);

        }
    };

}
