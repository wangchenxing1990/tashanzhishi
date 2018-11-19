package com.wangyukui.ywkj.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/11/30.
 */

public class JobDestoryDataBean implements Serializable {

    /**
     * current_page : 1
     * data : [{"id":"4c5126225","job_title":"2222222","updatetime":"2017-11-24","company_name":"234234234","logo":"","location_name":"高教园区","salary":"2000-2000","education_name":"中专","work_year_name":"1年以上","is_urgent":0},{"id":"3a23ac167","job_title":"IOS开发工程师","updatetime":"2017-11-27","company_name":"一网网络科技有限公司","logo":"companylogo/201710/1509353540zjptw.png","location_name":"松门镇","salary":"3500-7000","education_name":"学历不限","work_year_name":"经验不限","is_urgent":0},{"id":"23e67ec673153b7c38220","job_title":"会计","updatetime":"2017-11-09","company_name":"台州张雨荣公司","logo":"","location_name":"路桥区","salary":"5000-6000","education_name":"大专","work_year_name":"1年以上","is_urgent":0},{"id":"de8c6f619daf488e7e207","job_title":"123","updatetime":"2017-10-26","company_name":"h1602122235","logo":"companylogo/201707/1499145870buk0o.jpg","location_name":"路桥区","salary":"1500-2000","education_name":"学历不限","work_year_name":"经验不限","is_urgent":0},{"id":"b0590b214","job_title":"222222222","updatetime":"2017-10-25","company_name":"123456","logo":"","location_name":"仙居县","salary":"5000-6000","education_name":"学历不限","work_year_name":"经验不限","is_urgent":0},{"id":"23e67ec673153b7c38220","job_title":"会计","updatetime":"2017-11-09","company_name":"台州张雨荣公司","logo":"","location_name":"路桥区","salary":"5000-6000","education_name":"大专","work_year_name":"1年以上","is_urgent":0},{"id":"972e4e8e2d99219","job_title":"测试测试经纪人职位","updatetime":"2017-11-24","company_name":"1231231231","logo":"","location_name":"椒江区","salary":"1000-1500","education_name":"学历不限","work_year_name":"经验不限","is_urgent":0},{"id":"de8c6f619daf488e7e207","job_title":"123","updatetime":"2017-10-26","company_name":"h1602122235","logo":"companylogo/201707/1499145870buk0o.jpg","location_name":"路桥区","salary":"1500-2000","education_name":"学历不限","work_year_name":"经验不限","is_urgent":0},{"id":"13a33b185","job_title":"江健力职位2","updatetime":"2017-10-25","company_name":"江健力大公司","logo":"","location_name":"复兴区","salary":"1000-100万","education_name":"学历不限","work_year_name":"经验不限","is_urgent":0},{"id":"9b8735160","job_title":"Phpkaifa","updatetime":"2017-08-12","company_name":"Bing234","logo":"companylogo/201707/1499736852oxwax.jpg","location_name":"岛石镇","salary":"1000-1000","education_name":"中专","work_year_name":"在校学生","is_urgent":0},{"id":"972e4e8e2d99219","job_title":"测试测试经纪人职位","updatetime":"2017-11-24","company_name":"1231231231","logo":"","location_name":"椒江区","salary":"1000-1500","education_name":"学历不限","work_year_name":"经验不限","is_urgent":0},{"id":"23e67ec673153b7c38220","job_title":"会计","updatetime":"2017-11-09","company_name":"台州张雨荣公司","logo":"","location_name":"路桥区","salary":"5000-6000","education_name":"大专","work_year_name":"1年以上","is_urgent":0},{"id":"de8c6f619daf488e7e207","job_title":"123","updatetime":"2017-10-26","company_name":"h1602122235","logo":"companylogo/201707/1499145870buk0o.jpg","location_name":"路桥区","salary":"1500-2000","education_name":"学历不限","work_year_name":"经验不限","is_urgent":0}]
     * first_page_url : http://192.168.1.115:1024/api/v1/user/browse/com?page=1
     * from : 1
     * next_page_url : http://192.168.1.115:1024/api/v1/user/browse/com?page=2
     * path : http://192.168.1.115:1024/api/v1/user/browse/com
     * per_page : 15
     * prev_page_url : null
     * to : 15
     * code : 1
     * msg :
     */

    private int current_page;
    private String first_page_url;
    private int from;
    private String next_page_url;
    private String path;
    private int per_page;
    private String prev_page_url;
    private int to;
    private String code;
    private String msg;
    private List<DataBean> data;

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public String getFirst_page_url() {
        return first_page_url;
    }

    public void setFirst_page_url(String first_page_url) {
        this.first_page_url = first_page_url;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public String getNext_page_url() {
        return next_page_url;
    }

    public void setNext_page_url(String next_page_url) {
        this.next_page_url = next_page_url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public String getPrev_page_url() {
        return prev_page_url;
    }

    public void setPrev_page_url(String prev_page_url) {
        this.prev_page_url = prev_page_url;
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
         * id : 4c5126225
         * job_title : 2222222
         * updatetime : 2017-11-24
         * company_name : 234234234
         * logo :
         * location_name : 高教园区
         * salary : 2000-2000
         * education_name : 中专
         * work_year_name : 1年以上
         * is_urgent : 0
         */

        private String id;
        private String job_title;
        private String updatetime;
        private String company_name;
        private String logo;
        private String location_name;
        private String salary;
        private String education_name;
        private String work_year_name;
        private int is_urgent;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
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

        public int getIs_urgent() {
            return is_urgent;
        }

        public void setIs_urgent(int is_urgent) {
            this.is_urgent = is_urgent;
        }
    }
}
