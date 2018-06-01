package com.chinaredstar.cms.api.vo.questionnaire;


import java.io.Serializable;
import java.util.List;

public class CmsQuestionnaireAnswerVo implements Serializable {
    private static final long serialVersionUID = 6624019097311027261L;

    private Integer questionnaireId;

    private String userIp;

    private String userName;

    private String province;

    private String city;

    private String area;

    private String address;

    private String mobile;

    private List<CmsQuestionAnswerVo> questionAnswers;

    private List<AnswerContentVo> answerContents;

    private String openId;

    private String source;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Integer questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp == null ? null : userIp.trim();
    }

    /*public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }*/

    public List<CmsQuestionAnswerVo> getQuestionAnswers() {
        return questionAnswers;
    }

    public void setQuestionAnswers(List<CmsQuestionAnswerVo> questionAnswers) {
        this.questionAnswers = questionAnswers;
    }

    public List<AnswerContentVo> getAnswerContents() {
        return answerContents;
    }

    public void setAnswerContents(List<AnswerContentVo> answerContents) {
        this.answerContents = answerContents;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}