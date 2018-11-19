package com.wangyukui.ywkj.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/12/1.
 */

public class SearchResultDataBean implements Serializable {
    /**
     * per_page : 15
     * current_page : 1
     * next_page_url : https://www.15hr.com/api/v1/company_job/search?page=2
     * prev_page_url : null
     * from : 1
     * to : 15
     * data : [{"id":"dd001f166401","job_title":"财务","updatetime":"2017-12-01","part_status":"1","logo":"companylogo/201707/1500886344exr4z.jpg","location_name":"椒江区","salary":"3500-1万","education_name":"高中","work_year_name":"经验不限","company_name":"协和电子科技公司","is_urgent":1},{"id":"592015204521","job_title":" 预结算（通信行业）","updatetime":"2017-12-01","part_status":"1","logo":"companylogo/201709/1505718033n7kzi.jpg","location_name":"临海市","salary":"7000-1万","education_name":"大专","work_year_name":"2年以上","company_name":"台州中建通信建设有限公司","is_urgent":0},{"id":"e809c4204520","job_title":"市政二级建造师","updatetime":"2017-12-01","part_status":"1","logo":"companylogo/201709/1505718033n7kzi.jpg","location_name":"临海市","salary":"6000-8000","education_name":"大专","work_year_name":"2年以上","company_name":"台州中建通信建设有限公司","is_urgent":0},{"id":"89a2ed205798","job_title":"技术工/普工","updatetime":"2017-12-01","part_status":"1","logo":"companylogo/1304/20131107161238.gif","location_name":"椒江区","salary":"3500-5000","education_name":"高中","work_year_name":"经验不限","company_name":"浙江艾玛特环境设备科技有限公司","is_urgent":0},{"id":"985211205767","job_title":"销售/业务员/内勤助理","updatetime":"2017-12-01","part_status":"1","logo":"companylogo/1304/20131107161238.gif","location_name":"椒江区","salary":"3000-6000","education_name":"大专","work_year_name":"2年以上","company_name":"浙江艾玛特环境设备科技有限公司","is_urgent":0},{"id":"782bd0182521","job_title":"仓管","updatetime":"2017-12-01","part_status":"0","logo":"companylogo/1304/20131107161238.gif","location_name":"椒江区","salary":"3000-5000","education_name":"高中","work_year_name":"2年以上","company_name":"浙江艾玛特环境设备科技有限公司","is_urgent":0},{"id":"21dcfe181849","job_title":"品质检验、品质主管","updatetime":"2017-12-01","part_status":"0","logo":"companylogo/1304/20131107161238.gif","location_name":"椒江区","salary":"3000-6000","education_name":"学历不限","work_year_name":"经验不限","company_name":"浙江艾玛特环境设备科技有限公司","is_urgent":0},{"id":"32cd7c181536","job_title":"文案策划 专员","updatetime":"2017-12-01","part_status":"0","logo":"companylogo/1304/20131107161238.gif","location_name":"椒江区","salary":"3000-4000","education_name":"大专","work_year_name":"1年以上","company_name":"浙江艾玛特环境设备科技有限公司","is_urgent":0},{"id":"7964d8180855","job_title":"CAD制图员（暖通设计）","updatetime":"2017-12-01","part_status":"0","logo":"companylogo/1304/20131107161238.gif","location_name":"椒江区","salary":"3000-4000","education_name":"中专","work_year_name":"1年以上","company_name":"浙江艾玛特环境设备科技有限公司","is_urgent":0},{"id":"7decb6175357","job_title":"机械制图设计","updatetime":"2017-12-01","part_status":"0","logo":"companylogo/1304/20131107161238.gif","location_name":"椒江区","salary":"3000-5000","education_name":"大专","work_year_name":"1年以上","company_name":"浙江艾玛特环境设备科技有限公司","is_urgent":0},{"id":"2f2a31205839","job_title":"淘宝童鞋男女工打包","updatetime":"2017-12-01","part_status":"1","logo":"","location_name":"温岭市","salary":"4000-5000","education_name":"学历不限","work_year_name":"经验不限","company_name":"台州梵卡豪商贸有限公司","is_urgent":0},{"id":"69e149205806","job_title":"物业综合主管","updatetime":"2017-12-01","part_status":"1","logo":"","location_name":"路桥区","salary":"5000-8000","education_name":"大专","work_year_name":"2年以上","company_name":"景瑞物业","is_urgent":0},{"id":"a7357f197969","job_title":"高薪诚聘 电焊工","updatetime":"2017-12-01","part_status":"0","logo":"companylogo/201708/15020684947onza.jpg","location_name":"北仑区","salary":"8000-1万","education_name":"学历不限","work_year_name":"经验不限","company_name":"宁波海威蓝船务有限公司","is_urgent":0},{"id":"fa95df197968","job_title":"高薪招聘 厨师","updatetime":"2017-12-01","part_status":"0","logo":"companylogo/201708/15020684947onza.jpg","location_name":"北仑区","salary":"8000-1万","education_name":"学历不限","work_year_name":"经验不限","company_name":"宁波海威蓝船务有限公司","is_urgent":0},{"id":"65970f197366","job_title":"公司直招 普工","updatetime":"2017-12-01","part_status":"0","logo":"companylogo/201708/15020684947onza.jpg","location_name":"路桥区,温岭市,北仑区","salary":"8000-1.5万","education_name":"学历不限","work_year_name":"经验不限","company_name":"宁波海威蓝船务有限公司","is_urgent":0}]
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
         * id : dd001f166401
         * job_title : 财务
         * updatetime : 2017-12-01
         * part_status : 1
         * logo : companylogo/201707/1500886344exr4z.jpg
         * location_name : 椒江区
         * salary : 3500-1万
         * education_name : 高中
         * work_year_name : 经验不限
         * company_name : 协和电子科技公司
         * is_urgent : 1
         */

        private String id;
        private String job_title;
        private String updatetime;
        private String part_status;
        private String logo;
        private String location_name;
        private String salary;
        private String education_name;
        private String work_year_name;
        private String company_name;
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

        public String getPart_status() {
            return part_status;
        }

        public void setPart_status(String part_status) {
            this.part_status = part_status;
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
    }
}
