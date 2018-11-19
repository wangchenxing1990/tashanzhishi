package com.wangyukui.ywkj.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wangyukui.R;
import com.wangyukui.ywkj.bean.PreviewResumeDataBean;
import com.wangyukui.ywkj.bean.SkillBean;
import com.wangyukui.ywkj.jmessage.JGApplication;

import java.util.List;

/**
 * Created by Administrator on 2017/5/8.
 */

public class MyPreSkillAdapter extends DefualtAdapter {
    private List<PreviewResumeDataBean.DataBean.ResumeSkillBean> datas;

    public MyPreSkillAdapter( List<PreviewResumeDataBean.DataBean.ResumeSkillBean> datas) {
        super(datas);
        this.datas = datas;
    }

    public void setData(List<PreviewResumeDataBean.DataBean.ResumeSkillBean> data){
        datas.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder;
        String degree = datas.get(position).getDegree()+"";
        if (convertView == null) {
            holder = new MyViewHolder();
            convertView = View.inflate(JGApplication.context, R.layout.language_list, null);
            holder.textview_content = (TextView) convertView.findViewById(R.id.textview_content);
            holder.language_level = (TextView) convertView.findViewById(R.id.language_level);
            holder.textview_right = (TextView) convertView.findViewById(R.id.textview_right);
            holder.view2 = (View) convertView.findViewById(R.id.view2);

            convertView.setTag(holder);
        } else {
            holder = (MyViewHolder) convertView.getTag();
        }


        holder.textview_content.setText(datas.get(position).getSkillname());

        holder.textview_right.setVisibility(View.GONE);
        holder.view2.setVisibility(View.GONE);


        if (degree.equals("1")) {
            holder.language_level.setText("入门");
        } else if (degree.equals("2")) {
            holder.language_level.setText("熟练");
        } else if (degree.equals("3")) {
            holder.language_level.setText("精通");
        }

        return convertView;
    }

    class MyViewHolder {
        private TextView textview_content, language_level, textview_right;
        private View view2;
    }
}
