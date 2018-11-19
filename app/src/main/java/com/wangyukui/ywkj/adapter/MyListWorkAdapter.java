package com.wangyukui.ywkj.adapter;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wangyukui.R;
import com.wangyukui.ywkj.bean.EditResumeDataBean;
import com.wangyukui.ywkj.bean.WorkExperience;
import com.wangyukui.ywkj.jmessage.JGApplication;

import java.util.List;

/**
 * Created by Administrator on 2017/5/2.
 */

public class MyListWorkAdapter extends DefualtAdapter {
    private List<EditResumeDataBean.DataBean.ResumeWorkexpBean> datas;

    public MyListWorkAdapter(List<EditResumeDataBean.DataBean.ResumeWorkexpBean> datas) {
        super(datas);
        this.datas = datas;
    }

    public void setData(List<EditResumeDataBean.DataBean.ResumeWorkexpBean> data){
        Log.i("wwwwwwwww",data.get(0).getCompany());
        datas.clear();
        datas.addAll(data);
        notifyDataSetChanged();
       // notifyDataSetInvalidated();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(JGApplication.context, R.layout.list_view_work_exper, null);
            holder.texttime = (TextView) convertView.findViewById(R.id.texttime);
            holder.texttimeend = (TextView) convertView.findViewById(R.id.texttimeend);
            holder.textworkname = (TextView) convertView.findViewById(R.id.textworkname);
            holder.textcompanyname = (TextView) convertView.findViewById(R.id.textcompanyname);
            holder.textworkarea = (TextView) convertView.findViewById(R.id.textworkarea);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textcompanyname.setText(datas.get(position).getCompany());
        holder.texttime.setText(datas.get(position).getStarttime_name());
        holder.texttimeend.setText(datas.get(position).getEndtime_name());
        holder.textworkname.setText(datas.get(position).getPost());
        holder.textworkarea.setText(datas.get(position).getIndustry_name());
        Log.i("sssssssss0",datas.get(position).getIndustry_name());
        Log.i("sssssssss1",datas.get(position).getEndtime_name());
        Log.i("sssssssss2",datas.get(position).getPost());
        return convertView;
    }

    class ViewHolder {
        private TextView texttime, texttimeend, textworkname, textcompanyname, textworkarea;
    }
}
