package com.wangyukui.ywkj.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/11/30.
 */

public class JobInfoDataBean implements Serializable {
    /**
     * code : 1
     * msg :
     * data : {"id":"205829","com_id":"3d4ab6128668","uid":138871,"job_title":"财务","department":0,"recruiting_num":"1","description":"懂电脑，熟练操作，具体面议","gender":"0","welfare":"五险,公积金,年终奖","contact_info":0,"job_status":"1","part_status":"1","pageviews":1,"xs_status":1,"lang_type":"cn","send":0,"fav":0,"industry_name":"机械制造、机电设备、重工业","job_category_name":"财务/会计","location_name":"温岭市","education_name":"学历不限","work_year_name":"经验不限","language_name":"语言不限","salary":"3000-4000","age":"年龄不限","reply":{"rates":0,"average_time":"0小时"},"is_urgent":0,"company_user":{"last_login_time":"2017-11-28"},"company_basic":{"id":"128668","company_name":"金翼机电有限公司","address":"泥土工业区金翼机电有限公司","latitude":"","longitude":"","brands":"KP","logo":"","coordinate_address":"","cert_status":0,"established":"2008-08-00","contacts":"郑巧红","comkind_name":"行政机关","industry_name":"机械制造、机电设备、重工业","employee_num_name":"50-200人"}}
     */

    private String code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 205829
         * com_id : 3d4ab6128668
         * uid : 138871
         * job_title : 财务
         * department : 0
         * recruiting_num : 1
         * description : 懂电脑，熟练操作，具体面议
         * gender : 0
         * welfare : 五险,公积金,年终奖
         * contact_info : 0
         * job_status : 1
         * part_status : 1
         * pageviews : 1
         * xs_status : 1
         * lang_type : cn
         * send : 0
         * fav : 0
         * industry_name : 机械制造、机电设备、重工业
         * job_category_name : 财务/会计
         * location_name : 温岭市
         * education_name : 学历不限
         * work_year_name : 经验不限
         * language_name : 语言不限
         * salary : 3000-4000
         * age : 年龄不限
         * reply : {"rates":0,"average_time":"0小时"}
         * is_urgent : 0
         * company_user : {"last_login_time":"2017-11-28"}
         * company_basic : {"id":"128668","company_name":"金翼机电有限公司","address":"泥土工业区金翼机电有限公司","latitude":"","longitude":"","brands":"KP","logo":"","coordinate_address":"","cert_status":0,"established":"2008-08-00","contacts":"郑巧红","comkind_name":"行政机关","industry_name":"机械制造、机电设备、重工业","employee_num_name":"50-200人"}
         */

        private String id;
        private String com_id;
        private int uid;
        private String job_title;
        private int department;
        private String recruiting_num;
        private String description;
        private String gender;
        private String welfare;
        private int contact_info;
        private String job_status;
        private String part_status;
        private int pageviews;
        private int xs_status;
        private String lang_type;
        private int send;
        private int fav;
        private String industry_name;
        private String job_category_name;
        private String location_name;
        private String education_name;
        private String work_year_name;
        private String language_name;
        private String salary;
        private String age;
        private ReplyBean reply;
        private int is_urgent;
        private CompanyUserBean company_user;
        private CompanyBasicBean company_basic;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCom_id() {
            return com_id;
        }

        public void setCom_id(String com_id) {
            this.com_id = com_id;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getJob_title() {
            return job_title;
        }

        public void setJob_title(String job_title) {
            this.job_title = job_title;
        }

        public int getDepartment() {
            return department;
        }

        public void setDepartment(int department) {
            this.department = department;
        }

        public String getRecruiting_num() {
            return recruiting_num;
        }

        public void setRecruiting_num(String recruiting_num) {
            this.recruiting_num = recruiting_num;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getWelfare() {
            return welfare;
        }

        public void setWelfare(String welfare) {
            this.welfare = welfare;
        }

        public int getContact_info() {
            return contact_info;
        }

        public void setContact_info(int contact_info) {
            this.contact_info = contact_info;
        }

        public String getJob_status() {
            return job_status;
        }

        public void setJob_status(String job_status) {
            this.job_status = job_status;
        }

        public String getPart_status() {
            return part_status;
        }

        public void setPart_status(String part_status) {
            this.part_status = part_status;
        }

        public int getPageviews() {
            return pageviews;
        }

        public void setPageviews(int pageviews) {
            this.pageviews = pageviews;
        }

        public int getXs_status() {
            return xs_status;
        }

        public void setXs_status(int xs_status) {
            this.xs_status = xs_status;
        }

        public String getLang_type() {
            return lang_type;
        }

        public void setLang_type(String lang_type) {
            this.lang_type = lang_type;
        }

        public int getSend() {
            return send;
        }

        public void setSend(int send) {
            this.send = send;
        }

        public int getFav() {
            return fav;
        }

        public void setFav(int fav) {
            this.fav = fav;
        }

        public String getIndustry_name() {
            return industry_name;
        }

        public void setIndustry_name(String industry_name) {
            this.industry_name = industry_name;
        }

        public String getJob_category_name() {
            return job_category_name;
        }

        public void setJob_category_name(String job_category_name) {
            this.job_category_name = job_category_name;
        }

        public String getLocation_name() {
            return location_name;
        }

        public void setLocation_name(String location_name) {
            this.location_name = location_name;
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

        public String getLanguage_name() {
            return language_name;
        }

        public void setLanguage_name(String language_name) {
            this.language_name = language_name;
        }

        public String getSalary() {
            return salary;
        }

        public void setSalary(String salary) {
            this.salary = salary;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public ReplyBean getReply() {
            return reply;
        }

        public void setReply(ReplyBean reply) {
            this.reply = reply;
        }

        public int getIs_urgent() {
            return is_urgent;
        }

        public void setIs_urgent(int is_urgent) {
            this.is_urgent = is_urgent;
        }

        public CompanyUserBean getCompany_user() {
            return company_user;
        }

        public void setCompany_user(CompanyUserBean company_user) {
            this.company_user = company_user;
        }

        public CompanyBasicBean getCompany_basic() {
            return company_basic;
        }

        public void setCompany_basic(CompanyBasicBean company_basic) {
            this.company_basic = company_basic;
        }

        public static class ReplyBean {
            /**
             * rates : 0
             * average_time : 0小时
             */

            private int rates;
            private String average_time;

            public int getRates() {
                return rates;
            }

            public void setRates(int rates) {
                this.rates = rates;
            }

            public String getAverage_time() {
                return average_time;
            }

            public void setAverage_time(String average_time) {
                this.average_time = average_time;
            }
        }

        public static class CompanyUserBean {
            /**
             * last_login_time : 2017-11-28
             */

            private String last_login_time;

            public String getLast_login_time() {
                return last_login_time;
            }

            public void setLast_login_time(String last_login_time) {
                this.last_login_time = last_login_time;
            }
        }

        public static class CompanyBasicBean {
            /**
             * id : 128668
             * company_name : 金翼机电有限公司
             * address : 泥土工业区金翼机电有限公司
             * latitude :
             * longitude :
             * brands : KP
             * logo :
             * coordinate_address :
             * cert_status : 0
             * established : 2008-08-00
             * contacts : 郑巧红
             * comkind_name : 行政机关
             * industry_name : 机械制造、机电设备、重工业
             * employee_num_name : 50-200人
             */

            private String id;
            private String company_name;
            private String address;
            private String latitude;
            private String longitude;
            private String brands;
            private String logo;
            private String coordinate_address;
            private int cert_status;
            private String established;
            private String contacts;
            private String comkind_name;
            private String industry_name;
            private String employee_num_name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCompany_name() {
                return company_name;
            }

            public void setCompany_name(String company_name) {
                this.company_name = company_name;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getLatitude() {
                return latitude;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }

            public String getLongitude() {
                return longitude;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude;
            }

            public String getBrands() {
                return brands;
            }

            public void setBrands(String brands) {
                this.brands = brands;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getCoordinate_address() {
                return coordinate_address;
            }

            public void setCoordinate_address(String coordinate_address) {
                this.coordinate_address = coordinate_address;
            }

            public int getCert_status() {
                return cert_status;
            }

            public void setCert_status(int cert_status) {
                this.cert_status = cert_status;
            }

            public String getEstablished() {
                return established;
            }

            public void setEstablished(String established) {
                this.established = established;
            }

            public String getContacts() {
                return contacts;
            }

            public void setContacts(String contacts) {
                this.contacts = contacts;
            }

            public String getComkind_name() {
                return comkind_name;
            }

            public void setComkind_name(String comkind_name) {
                this.comkind_name = comkind_name;
            }

            public String getIndustry_name() {
                return industry_name;
            }

            public void setIndustry_name(String industry_name) {
                this.industry_name = industry_name;
            }

            public String getEmployee_num_name() {
                return employee_num_name;
            }

            public void setEmployee_num_name(String employee_num_name) {
                this.employee_num_name = employee_num_name;
            }
        }
    }
}
