package com.wangyukui.ywkj.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/11/29.
 */

public class JobCollectionBean implements Serializable {
    /**
     * per_page : 15
     * current_page : 1
     * next_page_url : null
     * prev_page_url : null
     * from : 1
     * to : 2
     * data : [{"id":700557,"job_id":"5de8a3204588","logo":"","job_title":"业务员","location_name":"椒江区","work_year_name":"经验不限","education_name":"学历不限","salary":"4000-8000","company_name":"台州快速投资咨询有限公司","is_urgent":0,"updatetime":"2017-11-25","part_status":"1"},{"id":699729,"job_id":"fdba83203315","logo":"companylogo/201708/1502355617skiws.jpg","job_title":"汽车内外饰注塑模具钳工组长","location_name":"宁海县","work_year_name":"3年以上","education_name":"学历不限","salary":"8000-1万","company_name":"台州市黄岩挚诚企业管理咨询有限公司","is_urgent":1,"updatetime":"2017-11-29","part_status":"1"}]
     * code : 1
     * msg :
     */

    private int per_page;
    private int current_page;
    private String next_page_url;
    private String prev_page_url;
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

    public String getNext_page_url() {
        return next_page_url;
    }

    public void setNext_page_url(String next_page_url) {
        this.next_page_url = next_page_url;
    }

    public String getPrev_page_url() {
        return prev_page_url;
    }

    public void setPrev_page_url(String prev_page_url) {
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
         * id : 700557
         * job_id : 5de8a3204588
         * logo :
         * job_title : 业务员
         * location_name : 椒江区
         * work_year_name : 经验不限
         * education_name : 学历不限
         * salary : 4000-8000
         * company_name : 台州快速投资咨询有限公司
         * is_urgent : 0
         * updatetime : 2017-11-25
         * part_status : 1
         */

        private int id;
        private String job_id;
        private String logo;
        private String job_title;
        private String location_name;
        private String work_year_name;
        private String education_name;
        private String salary;
        private String company_name;
        private int is_urgent;
        private String updatetime;
        private String part_status;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getJob_id() {
            return job_id;
        }

        public void setJob_id(String job_id) {
            this.job_id = job_id;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getJob_title() {
            return job_title;
        }

        public void setJob_title(String job_title) {
            this.job_title = job_title;
        }

        public String getLocation_name() {
            return location_name;
        }

        public void setLocation_name(String location_name) {
            this.location_name = location_name;
        }

        public String getWork_year_name() {
            return work_year_name;
        }

        public void setWork_year_name(String work_year_name) {
            this.work_year_name = work_year_name;
        }

        public String getEducation_name() {
            return education_name;
        }

        public void setEducation_name(String education_name) {
            this.education_name = education_name;
        }

        public String getSalary() {
            return salary;
        }

        public void setSalary(String salary) {
            this.salary = salary;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public int getIs_urgent() {
            return is_urgent;
        }

        public void setIs_urgent(int is_urgent) {
            this.is_urgent = is_urgent;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        public String getPart_status() {
            return part_status;
        }

        public void setPart_status(String part_status) {
            this.part_status = part_status;
        }
    }
}
