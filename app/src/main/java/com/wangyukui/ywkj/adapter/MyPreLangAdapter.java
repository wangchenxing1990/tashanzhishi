package com.wangyukui.ywkj.adapter;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wangyukui.R;
import com.wangyukui.ywkj.bean.LangBean;
import com.wangyukui.ywkj.bean.PreviewResumeDataBean;
import com.wangyukui.ywkj.jmessage.JGApplication;

import java.util.List;

/**
 * Created by Administrator on 2017/5/8.
 */

public class MyPreLangAdapter extends DefualtAdapter {
    private List<PreviewResumeDataBean.DataBean.ResumeLangBean> datas;
    public MyPreLangAdapter(List<PreviewResumeDataBean.DataBean.ResumeLangBean> datas) {
        super(datas);
        this.datas=datas;
    }


    public void setData(List<PreviewResumeDataBean.DataBean.ResumeLangBean> data){
        datas.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder;
        String degree=datas.get(position).getDegree()+"";
        if (convertView==null){
            holder=new MyViewHolder();
            convertView = View.inflate(JGApplication.context, R.layout.language_list, null);
            holder.textview_content = (TextView) convertView.findViewById(R.id.textview_content);
            holder.language_level = (TextView) convertView.findViewById(R.id.language_level);
            holder.textview_right = (TextView) convertView.findViewById(R.id.textview_right);
            holder.view2 = (View) convertView.findViewById(R.id.view2);

            convertView.setTag(holder);
        }else {
            holder= (MyViewHolder) convertView.getTag();
        }

        holder.textview_content.setText(datas.get(position).getLanguage_name());
        holder.language_level.setVisibility(View.VISIBLE);
        holder.view2.setVisibility(View.VISIBLE);
        Log.i("aaaaaaaaaa",datas.get(0).getLevel_name());
        if (datas.get(position).getLevel_name()==null||datas.get(position).getLevel_name().equals("")){
            Log.i("1113333333",datas.get(position).getLevel_name());
            holder.view2.setVisibility(View.GONE);
            holder.language_level.setVisibility(View.GONE);
        }else{
            holder.language_level.setText(datas.get(position).getLevel_name());
        }

        if (degree.equals("1")){
            holder.textview_right.setText("入门");
        }else if(degree.equals("2")){
            holder.textview_right.setText("熟练");
        }else if(degree.equals("3")){
            holder.textview_right.setText("精通");
        }

        return convertView;
    }
    class MyViewHolder{
        private TextView textview_content,language_level,textview_right,textcompanyname,textzhiyu;
        private View view2;
    }

}
