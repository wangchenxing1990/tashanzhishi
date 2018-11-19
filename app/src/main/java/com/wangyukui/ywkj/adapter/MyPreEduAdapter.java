package com.wangyukui.ywkj.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wangyukui.R;
import com.wangyukui.ywkj.bean.EducaExperBean;
import com.wangyukui.ywkj.bean.PreviewResumeDataBean;
import com.wangyukui.ywkj.jmessage.JGApplication;

import java.util.List;

/**
 * Created by Administrator on 2017/5/8.
 */

public class MyPreEduAdapter extends DefualtAdapter {
    private List<PreviewResumeDataBean.DataBean.ResumeEduexpBean> datas;

    public MyPreEduAdapter(List<PreviewResumeDataBean.DataBean.ResumeEduexpBean> datas) {
        super(datas);
        this.datas = datas;
    }

    public void setData(List<PreviewResumeDataBean.DataBean.ResumeEduexpBean> data){
        datas.addAll( data);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder;
        if (convertView == null) {
            holder = new MyViewHolder();
            convertView = View.inflate(JGApplication.context, R.layout.pre_education_list, null);
            holder.texttime = (TextView) convertView.findViewById(R.id.texttime);
            holder.texttimeend = (TextView) convertView.findViewById(R.id.texttimeend);
            holder.textworkname = (TextView) convertView.findViewById(R.id.textworkname);
            holder.textcompanyname = (TextView) convertView.findViewById(R.id.textcompanyname);

            convertView.setTag(holder);
        } else {
            holder = (MyViewHolder) convertView.getTag();
        }

        holder.texttime.setText(datas.get(position).getStarttime_name());
        holder.texttimeend.setText(datas.get(position).getEndtime_name());
        if (datas.get(position).getEducation_name()==null||datas.get(position).getEducation_name().isEmpty()){
            holder.textworkname.setText(datas.get(position).getSchool() + " | " + datas.get(position).getSpeciality());
        }else{
            holder.textworkname.setText(datas.get(position).getSchool() + " | "
                    + datas.get(position).getEducation_name() + " | " + datas.get(position).getSpeciality());
        }

        holder.textcompanyname.setText(datas.get(position).getDescription());
//        holder.textworkarea.setText(datas.get(position).getSchool());
//        holder.textdescription.setText(datas.get(position).getDescription());

        return convertView;
    }

    class MyViewHolder {
        private TextView texttime, texttimeend, textworkname, textcompanyname;
    }
}
