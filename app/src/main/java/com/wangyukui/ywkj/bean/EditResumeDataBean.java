package com.wangyukui.ywkj.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/11/27.
 */

public class EditResumeDataBean implements Serializable {

    /**
     * code : 1
     * msg :
     * data : {"id":"2561","resume_name":"我的简历","language":"cn","name":"王玉奎","sex":"0","expectedsalary":2500,"isexpectedsalary":"1","intentionjobs":"大数据开发","introduction":"我嗯呢嗯摸摸去咯6句咯莫high吧魔图咯的陌陌里披肩龙提供太可怜了哦哦哦们几乎龙族李海龙哦哦都说了默默就认识了","mobile":"18317770484","email":"18317770484@163.com","qq":"1064920856","top_time":null,"chkphoto_open":"0","avatar":"personalavatar/201711/1511764731xfllt.jpg","address":"","job_status":"1","resume_status":"1","bkresume_status":"1","review":0,"marital":"0","height":"0","place_name":"","longitude":"","latitude":"","xs_status":0,"birthday_name":"2003-11","work_year_name":"5年以上","homeaddress_name":"临海市","expectedsalary_name":"2500","jobsort_name":"家电/数码产品研发,音响工程师,电器维修,电器工程师","jobarea_name":"下陈街道,葭芷街道,台州汽校","education_name":"硕士","census_name":"","age":"14","resume_workexp":[{"id":187,"company":"阿里巴巴","industry":3,"comkind":5,"scale":"2000","starttime":"2013-11-01","endtime":"2015-11-01","post":"区域经理","content":"特大就是杀毒日本我问问看快乐东西也都","starttime_name":"2013-11","endtime_name":"2015-11","industry_name":"计算机业（硬件、网络设备）","comkind_name":"民营企业","scale_name":"1000人以上"}],"resume_eduexp":[{"id":230,"edu_type":1,"school":"哈弗大学学","starttime":"2003-11-01","endtime":"2009-11-01","speciality":"自动化","education":60,"type":0,"description":"呢饿克拉肯客服他肯thick森女他的","education_name":"硕士","starttime_name":"2003-11","endtime_name":"2009-11"}],"resume_proexp":[{"id":73,"project_name":"海底隧道","starttime":"2013-11-01","endtime":"2015-11-01","post":"总指挥","content":"用么TurkNONO族开始了DJ屠龙记龙图咯哦哦自己控制摸摸啦流量控制","starttime_name":"2013-11","endtime_name":"2015-11"}],"resume_skill":[{"id":103,"skillname":"胸口碎大石","degree":3}],"resume_cer":[{"id":101,"certificate_name":"java二级证书","gettime":"2014-01-01","gettime_name":"2014年"}],"resume_lang":[{"id":120,"language":3,"degree":2,"level":34,"language_name":"英语","level_name":"CET6"}],"resume_other":[{"id":108,"title":"职业目标","content":"我饿的咯唾沫哦哦无尽空虚5门XX门哦哟我的看见了咯YY默默我OK吉拉魔图咯的哦下雨了哦哦哦控制"}]}
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

    public static class DataBean implements Serializable{
        /**
         * id : 2561
         * resume_name : 我的简历
         * language : cn
         * name : 王玉奎
         * sex : 0
         * expectedsalary : 2500
         * isexpectedsalary : 1
         * intentionjobs : 大数据开发
         * introduction : 我嗯呢嗯摸摸去咯6句咯莫high吧魔图咯的陌陌里披肩龙提供太可怜了哦哦哦们几乎龙族李海龙哦哦都说了默默就认识了
         * mobile : 18317770484
         * email : 18317770484@163.com
         * qq : 1064920856
         * top_time : null
         * chkphoto_open : 0
         * avatar : personalavatar/201711/1511764731xfllt.jpg
         * address :
         * job_status : 1
         * resume_status : 1
         * bkresume_status : 1
         * review : 0
         * marital : 0
         * height : 0
         * place_name :
         * longitude :
         * latitude :
         * xs_status : 0
         * birthday_name : 2003-11
         * work_year_name : 5年以上
         * homeaddress_name : 临海市
         * expectedsalary_name : 2500
         * jobsort_name : 家电/数码产品研发,音响工程师,电器维修,电器工程师
         * jobarea_name : 下陈街道,葭芷街道,台州汽校
         * education_name : 硕士
         * census_name :
         * age : 14
         * resume_workexp : [{"id":187,"company":"阿里巴巴","industry":3,"comkind":5,"scale":"2000","starttime":"2013-11-01","endtime":"2015-11-01","post":"区域经理","content":"特大就是杀毒日本我问问看快乐东西也都","starttime_name":"2013-11","endtime_name":"2015-11","industry_name":"计算机业（硬件、网络设备）","comkind_name":"民营企业","scale_name":"1000人以上"}]
         * resume_eduexp : [{"id":230,"edu_type":1,"school":"哈弗大学学","starttime":"2003-11-01","endtime":"2009-11-01","speciality":"自动化","education":60,"type":0,"description":"呢饿克拉肯客服他肯thick森女他的","education_name":"硕士","starttime_name":"2003-11","endtime_name":"2009-11"}]
         * resume_proexp : [{"id":73,"project_name":"海底隧道","starttime":"2013-11-01","endtime":"2015-11-01","post":"总指挥","content":"用么TurkNONO族开始了DJ屠龙记龙图咯哦哦自己控制摸摸啦流量控制","starttime_name":"2013-11","endtime_name":"2015-11"}]
         * resume_skill : [{"id":103,"skillname":"胸口碎大石","degree":3}]
         * resume_cer : [{"id":101,"certificate_name":"java二级证书","gettime":"2014-01-01","gettime_name":"2014年"}]
         * resume_lang : [{"id":120,"language":3,"degree":2,"level":34,"language_name":"英语","level_name":"CET6"}]
         * resume_other : [{"id":108,"title":"职业目标","content":"我饿的咯唾沫哦哦无尽空虚5门XX门哦哟我的看见了咯YY默默我OK吉拉魔图咯的哦下雨了哦哦哦控制"}]
         */

        private String id;
        private String resume_name;
        private String language;
        private String name;
        private String sex;
        private int expectedsalary;
        private String isexpectedsalary;
        private String intentionjobs;
        private String introduction;
        private String mobile;
        private String email;
        private String qq;
        private Object top_time;
        private String chkphoto_open;
        private String avatar;
        private String address;
        private String job_status;
        private String resume_status;
        private String bkresume_status;
        private int review;
        private String marital;
        private String height;
        private String place_name;
        private String longitude;
        private String latitude;
        private int xs_status;
        private String birthday_name;
        private String work_year_name;
        private String homeaddress_name;
        private String expectedsalary_name;
        private String jobsort_name;
        private String jobarea_name;
        private String education_name;
        private String census_name;
        private String age;
        private List<ResumeWorkexpBean> resume_workexp;
        private List<ResumeEduexpBean> resume_eduexp;
        private List<ResumeProexpBean> resume_proexp;
        private List<ResumeSkillBean> resume_skill;
        private List<ResumeCerBean> resume_cer;
        private List<ResumeLangBean> resume_lang;
        private List<ResumeOtherBean> resume_other;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getResume_name() {
            return resume_name;
        }

        public void setResume_name(String resume_name) {
            this.resume_name = resume_name;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public int getExpectedsalary() {
            return expectedsalary;
        }

        public void setExpectedsalary(int expectedsalary) {
            this.expectedsalary = expectedsalary;
        }

        public String getIsexpectedsalary() {
            return isexpectedsalary;
        }

        public void setIsexpectedsalary(String isexpectedsalary) {
            this.isexpectedsalary = isexpectedsalary;
        }

        public String getIntentionjobs() {
            return intentionjobs;
        }

        public void setIntentionjobs(String intentionjobs) {
            this.intentionjobs = intentionjobs;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public Object getTop_time() {
            return top_time;
        }

        public void setTop_time(Object top_time) {
            this.top_time = top_time;
        }

        public String getChkphoto_open() {
            return chkphoto_open;
        }

        public void setChkphoto_open(String chkphoto_open) {
            this.chkphoto_open = chkphoto_open;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getJob_status() {
            return job_status;
        }

        public void setJob_status(String job_status) {
            this.job_status = job_status;
        }

        public String getResume_status() {
            return resume_status;
        }

        public void setResume_status(String resume_status) {
            this.resume_status = resume_status;
        }

        public String getBkresume_status() {
            return bkresume_status;
        }

        public void setBkresume_status(String bkresume_status) {
            this.bkresume_status = bkresume_status;
        }

        public int getReview() {
            return review;
        }

        public void setReview(int review) {
            this.review = review;
        }

        public String getMarital() {
            return marital;
        }

        public void setMarital(String marital) {
            this.marital = marital;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getPlace_name() {
            return place_name;
        }

        public void setPlace_name(String place_name) {
            this.place_name = place_name;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public int getXs_status() {
            return xs_status;
        }

        public void setXs_status(int xs_status) {
            this.xs_status = xs_status;
        }

        public String getBirthday_name() {
            return birthday_name;
        }

        public void setBirthday_name(String birthday_name) {
            this.birthday_name = birthday_name;
        }

        public String getWork_year_name() {
            return work_year_name;
        }

        public void setWork_year_name(String work_year_name) {
            this.work_year_name = work_year_name;
        }

        public String getHomeaddress_name() {
            return homeaddress_name;
        }

        public void setHomeaddress_name(String homeaddress_name) {
            this.homeaddress_name = homeaddress_name;
        }

        public String getExpectedsalary_name() {
            return expectedsalary_name;
        }

        public void setExpectedsalary_name(String expectedsalary_name) {
            this.expectedsalary_name = expectedsalary_name;
        }

        public String getJobsort_name() {
            return jobsort_name;
        }

        public void setJobsort_name(String jobsort_name) {
            this.jobsort_name = jobsort_name;
        }

        public String getJobarea_name() {
            return jobarea_name;
        }

        public void setJobarea_name(String jobarea_name) {
            this.jobarea_name = jobarea_name;
        }

        public String getEducation_name() {
            return education_name;
        }

        public void setEducation_name(String education_name) {
            this.education_name = education_name;
        }

        public String getCensus_name() {
            return census_name;
        }

        public void setCensus_name(String census_name) {
            this.census_name = census_name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public List<ResumeWorkexpBean> getResume_workexp() {
            return resume_workexp;
        }

        public void setResume_workexp(List<ResumeWorkexpBean> resume_workexp) {
            this.resume_workexp = resume_workexp;
        }

        public List<ResumeEduexpBean> getResume_eduexp() {
            return resume_eduexp;
        }

        public void setResume_eduexp(List<ResumeEduexpBean> resume_eduexp) {
            this.resume_eduexp = resume_eduexp;
        }

        public List<ResumeProexpBean> getResume_proexp() {
            return resume_proexp;
        }

        public void setResume_proexp(List<ResumeProexpBean> resume_proexp) {
            this.resume_proexp = resume_proexp;
        }

        public List<ResumeSkillBean> getResume_skill() {
            return resume_skill;
        }

        public void setResume_skill(List<ResumeSkillBean> resume_skill) {
            this.resume_skill = resume_skill;
        }

        public List<ResumeCerBean> getResume_cer() {
            return resume_cer;
        }

        public void setResume_cer(List<ResumeCerBean> resume_cer) {
            this.resume_cer = resume_cer;
        }

        public List<ResumeLangBean> getResume_lang() {
            return resume_lang;
        }

        public void setResume_lang(List<ResumeLangBean> resume_lang) {
            this.resume_lang = resume_lang;
        }

        public List<ResumeOtherBean> getResume_other() {
            return resume_other;
        }

        public void setResume_other(List<ResumeOtherBean> resume_other) {
            this.resume_other = resume_other;
        }

        public static class ResumeWorkexpBean implements Serializable{
            /**
             * id : 187
             * company : 阿里巴巴
             * industry : 3
             * comkind : 5
             * scale : 2000
             * starttime : 2013-11-01
             * endtime : 2015-11-01
             * post : 区域经理
             * content : 特大就是杀毒日本我问问看快乐东西也都
             * starttime_name : 2013-11
             * endtime_name : 2015-11
             * industry_name : 计算机业（硬件、网络设备）
             * comkind_name : 民营企业
             * scale_name : 1000人以上
             */

            private int id;
            private String company;
            private int industry;
            private int comkind;
            private String scale;
            private String starttime;
            private String endtime;
            private String post;
            private String content;
            private String starttime_name;
            private String endtime_name;
            private String industry_name;
            private String comkind_name;
            private String scale_name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCompany() {
                return company;
            }

            public void setCompany(String company) {
                this.company = company;
            }

            public int getIndustry() {
                return industry;
            }

            public void setIndustry(int industry) {
                this.industry = industry;
            }

            public int getComkind() {
                return comkind;
            }

            public void setComkind(int comkind) {
                this.comkind = comkind;
            }

            public String getScale() {
                return scale;
            }

            public void setScale(String scale) {
                this.scale = scale;
            }

            public String getStarttime() {
                return starttime;
            }

            public void setStarttime(String starttime) {
                this.starttime = starttime;
            }

            public String getEndtime() {
                return endtime;
            }

            public void setEndtime(String endtime) {
                this.endtime = endtime;
            }

            public String getPost() {
                return post;
            }

            public void setPost(String post) {
                this.post = post;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getStarttime_name() {
                return starttime_name;
            }

            public void setStarttime_name(String starttime_name) {
                this.starttime_name = starttime_name;
            }

            public String getEndtime_name() {
                return endtime_name;
            }

            public void setEndtime_name(String endtime_name) {
                this.endtime_name = endtime_name;
            }

            public String getIndustry_name() {
                return industry_name;
            }

            public void setIndustry_name(String industry_name) {
                this.industry_name = industry_name;
            }

            public String getComkind_name() {
                return comkind_name;
            }

            public void setComkind_name(String comkind_name) {
                this.comkind_name = comkind_name;
            }

            public String getScale_name() {
                return scale_name;
            }

            public void setScale_name(String scale_name) {
                this.scale_name = scale_name;
            }
        }

        public static class ResumeEduexpBean implements Serializable{
            /**
             * id : 230
             * edu_type : 1
             * school : 哈弗大学学
             * starttime : 2003-11-01
             * endtime : 2009-11-01
             * speciality : 自动化
             * education : 60
             * type : 0
             * description : 呢饿克拉肯客服他肯thick森女他的
             * education_name : 硕士
             * starttime_name : 2003-11
             * endtime_name : 2009-11
             */

            private int id;
            private int edu_type;
            private String school;
            private String starttime;
            private String endtime;
            private String speciality;
            private int education;
            private int type;
            private String description;
            private String education_name;
            private String starttime_name;
            private String endtime_name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getEdu_type() {
                return edu_type;
            }

            public void setEdu_type(int edu_type) {
                this.edu_type = edu_type;
            }

            public String getSchool() {
                return school;
            }

            public void setSchool(String school) {
                this.school = school;
            }

            public String getStarttime() {
                return starttime;
            }

            public void setStarttime(String starttime) {
                this.starttime = starttime;
            }

            public String getEndtime() {
                return endtime;
            }

            public void setEndtime(String endtime) {
                this.endtime = endtime;
            }

            public String getSpeciality() {
                return speciality;
            }

            public void setSpeciality(String speciality) {
                this.speciality = speciality;
            }

            public int getEducation() {
                return education;
            }

            public void setEducation(int education) {
                this.education = education;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getEducation_name() {
                return education_name;
            }

            public void setEducation_name(String education_name) {
                this.education_name = education_name;
            }

            public String getStarttime_name() {
                return starttime_name;
            }

            public void setStarttime_name(String starttime_name) {
                this.starttime_name = starttime_name;
            }

            public String getEndtime_name() {
                return endtime_name;
            }

            public void setEndtime_name(String endtime_name) {
                this.endtime_name = endtime_name;
            }
        }

        public static class ResumeProexpBean implements Serializable{
            /**
             * id : 73
             * project_name : 海底隧道
             * starttime : 2013-11-01
             * endtime : 2015-11-01
             * post : 总指挥
             * content : 用么TurkNONO族开始了DJ屠龙记龙图咯哦哦自己控制摸摸啦流量控制
             * starttime_name : 2013-11
             * endtime_name : 2015-11
             */

            private int id;
            private String project_name;
            private String starttime;
            private String endtime;
            private String post;
            private String content;
            private String starttime_name;
            private String endtime_name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getProject_name() {
                return project_name;
            }

            public void setProject_name(String project_name) {
                this.project_name = project_name;
            }

            public String getStarttime() {
                return starttime;
            }

            public void setStarttime(String starttime) {
                this.starttime = starttime;
            }

            public String getEndtime() {
                return endtime;
            }

            public void setEndtime(String endtime) {
                this.endtime = endtime;
            }

            public String getPost() {
                return post;
            }

            public void setPost(String post) {
                this.post = post;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getStarttime_name() {
                return starttime_name;
            }

            public void setStarttime_name(String starttime_name) {
                this.starttime_name = starttime_name;
            }

            public String getEndtime_name() {
                return endtime_name;
            }

            public void setEndtime_name(String endtime_name) {
                this.endtime_name = endtime_name;
            }
        }

        public static class ResumeSkillBean implements Serializable{
            /**
             * id : 103
             * skillname : 胸口碎大石
             * degree : 3
             */

            private int id;
            private String skillname;
            private int degree;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getSkillname() {
                return skillname;
            }

            public void setSkillname(String skillname) {
                this.skillname = skillname;
            }

            public int getDegree() {
                return degree;
            }

            public void setDegree(int degree) {
                this.degree = degree;
            }
        }

        public static class ResumeCerBean implements Serializable{
            /**
             * id : 101
             * certificate_name : java二级证书
             * gettime : 2014-01-01
             * gettime_name : 2014年
             */

            private int id;
            private String certificate_name;
            private String gettime;
            private String gettime_name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCertificate_name() {
                return certificate_name;
            }

            public void setCertificate_name(String certificate_name) {
                this.certificate_name = certificate_name;
            }

            public String getGettime() {
                return gettime;
            }

            public void setGettime(String gettime) {
                this.gettime = gettime;
            }

            public String getGettime_name() {
                return gettime_name;
            }

            public void setGettime_name(String gettime_name) {
                this.gettime_name = gettime_name;
            }
        }

        public static class ResumeLangBean implements Serializable{
            /**
             * id : 120
             * language : 3
             * degree : 2
             * level : 34
             * language_name : 英语
             * level_name : CET6
             */

            private int id;
            private int language;
            private int degree;
            private int level;
            private String language_name;
            private String level_name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getLanguage() {
                return language;
            }

            public void setLanguage(int language) {
                this.language = language;
            }

            public int getDegree() {
                return degree;
            }

            public void setDegree(int degree) {
                this.degree = degree;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getLanguage_name() {
                return language_name;
            }

            public void setLanguage_name(String language_name) {
                this.language_name = language_name;
            }

            public String getLevel_name() {
                return level_name;
            }

            public void setLevel_name(String level_name) {
                this.level_name = level_name;
            }
        }

        public static class ResumeOtherBean implements Serializable{
            /**
             * id : 108
             * title : 职业目标
             * content : 我饿的咯唾沫哦哦无尽空虚5门XX门哦哟我的看见了咯YY默默我OK吉拉魔图咯的哦下雨了哦哦哦控制
             */

            private int id;
            private String title;
            private String content;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }
    }
}
