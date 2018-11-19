package com.wangyukui.ywkj.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wangyukui.R;
import com.wangyukui.ywkj.bean.PreviewResumeDataBean;
import com.wangyukui.ywkj.bean.ProgressBean;
import com.wangyukui.ywkj.jmessage.JGApplication;

import java.util.List;

/**
 * Created by Administrator on 2017/5/8.
 */

public class MyPreProExpAdapter extends DefualtAdapter {
    private List<PreviewResumeDataBean.DataBean.ResumeProexpBean> datas;
    public MyPreProExpAdapter(List<PreviewResumeDataBean.DataBean.ResumeProexpBean> datas) {
        super(datas);
        this.datas=datas;
    }

    public void setData(List<PreviewResumeDataBean.DataBean.ResumeProexpBean> data){
        datas.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder;
        if (convertView==null){
            holder=new MyViewHolder();
            convertView = View.inflate(JGApplication.context, R.layout.pre_education_list, null);
            holder.texttime = (TextView) convertView.findViewById(R.id.texttime);
            holder.texttimeend = (TextView) convertView.findViewById(R.id.texttimeend);
            holder.textworkname = (TextView) convertView.findViewById(R.id.textworkname);
            holder.textcompanyname = (TextView) convertView.findViewById(R.id.textcompanyname);


            convertView.setTag(holder);
        }else {
            holder= (MyViewHolder) convertView.getTag();
        }

        holder.texttime.setText(datas.get(position).getStarttime_name());
        holder.texttimeend.setText(datas.get(position).getEndtime_name());
        holder.textworkname.setText(datas.get(position).getPost()+" | "+datas.get(position).getProject_name());
        holder.textcompanyname.setText(datas.get(position).getContent());


        return convertView;
    }
    class MyViewHolder{
        private TextView texttime,texttimeend,textworkname,textcompanyname;
    }
}
