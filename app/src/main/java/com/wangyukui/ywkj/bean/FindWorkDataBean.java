package com.wangyukui.ywkj.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/11/30.
 */

public class FindWorkDataBean implements Serializable {
    /**
     * per_page : 15
     * current_page : 1
     * next_page_url : null
     * prev_page_url : null
     * from : 1
     * to : 5
     * data : [{"id":"89acdc199993","com_id":81855,"job_title":"家装渠道业务员","updatetime":"2017-11-30","job_status":"1","recruiting_num":"2","location_name":"路桥区,椒江区,黄岩区","salary":"4000-1万","education_name":"大专","work_year_name":"经验不限","company_name":"台州涌丰建材有限公司","job_status_name":"审核通过","is_urgent":0},{"id":"99f71b199992","com_id":81855,"job_title":"储备业务员","updatetime":"2017-11-30","job_status":"1","recruiting_num":"5","location_name":"玉环县","salary":"3500-5000","education_name":"高中","work_year_name":"经验不限","company_name":"台州涌丰建材有限公司","job_status_name":"审核通过","is_urgent":0},{"id":"92b579192733","com_id":81855,"job_title":"分销渠道业务员","updatetime":"2017-11-30","job_status":"1","recruiting_num":"5","location_name":"台州市","salary":"3500-5000","education_name":"大专","work_year_name":"2年以上","company_name":"台州涌丰建材有限公司","job_status_name":"审核通过","is_urgent":0},{"id":"f40ac4192642","com_id":81855,"job_title":"售后维护员","updatetime":"2017-11-30","job_status":"1","recruiting_num":"3","location_name":"玉环县","salary":"4000-6000","education_name":"高中","work_year_name":"经验不限","company_name":"台州涌丰建材有限公司","job_status_name":"审核通过","is_urgent":0},{"id":"b05acd192640","com_id":81855,"job_title":"销售内勤","updatetime":"2017-11-30","job_status":"1","recruiting_num":"2","location_name":"椒江区","salary":"3000-5000","education_name":"中专","work_year_name":"经验不限","company_name":"台州涌丰建材有限公司","job_status_name":"审核通过","is_urgent":0}]
     * code : 1
     * msg :
     */

    private int per_page;
    private int current_page;
    private Object next_page_url;
    private Object prev_page_url;
    private int from;
    private int to;
    private String code;
    private String msg;
    private List<DataBean> data;

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public Object getNext_page_url() {
        return next_page_url;
    }

    public void setNext_page_url(Object next_page_url) {
        this.next_page_url = next_page_url;
    }

    public Object getPrev_page_url() {
        return prev_page_url;
    }

    public void setPrev_page_url(Object prev_page_url) {
        this.prev_page_url = prev_page_url;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 89acdc199993
         * com_id : 81855
         * job_title : 家装渠道业务员
         * updatetime : 2017-11-30
         * job_status : 1
         * recruiting_num : 2
         * location_name : 路桥区,椒江区,黄岩区
         * salary : 4000-1万
         * education_name : 大专
         * work_year_name : 经验不限
         * company_name : 台州涌丰建材有限公司
         * job_status_name : 审核通过
         * is_urgent : 0
         */

        private String id;
        private int com_id;
        private String job_title;
        private String updatetime;
        private String job_status;
        private String recruiting_num;
        private String location_name;
        private String salary;
        private String education_name;
        private String work_year_name;
        private String company_name;
        private String job_status_name;
        private int is_urgent;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getCom_id() {
            return com_id;
        }

        public void setCom_id(int com_id) {
            this.com_id = com_id;
        }

        public String getJob_title() {
            return job_title;
        }

        public void setJob_title(String job_title) {
            this.job_title = job_title;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        public String getJob_status() {
            return job_status;
        }

        public void setJob_status(String job_status) {
            this.job_status = job_status;
        }

        public String getRecruiting_num() {
            return recruiting_num;
        }

        public void setRecruiting_num(String recruiting_num) {
            this.recruiting_num = recruiting_num;
        }

        public String getLocation_name() {
            return location_name;
        }

        public void setLocation_name(String location_name) {
            this.location_name = location_name;
        }

        public String getSalary() {
            return salary;
        }

        public void setSalary(String salary) {
            this.salary = salary;
        }

        public String getEducation_name() {
            return education_name;
        }

        public void setEducation_name(String education_name) {
            this.education_name = education_name;
        }

        public String getWork_year_name() {
            return work_year_name;
        }

        public void setWork_year_name(String work_year_name) {
            this.work_year_name = work_year_name;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public String getJob_status_name() {
            return job_status_name;
        }

        public void setJob_status_name(String job_status_name) {
            this.job_status_name = job_status_name;
        }

        public int getIs_urgent() {
            return is_urgent;
        }

        public void setIs_urgent(int is_urgent) {
            this.is_urgent = is_urgent;
        }
    }
}
