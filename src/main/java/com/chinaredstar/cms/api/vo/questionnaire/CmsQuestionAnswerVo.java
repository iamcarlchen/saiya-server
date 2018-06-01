package com.chinaredstar.cms.api.vo.questionnaire;


import java.io.Serializable;

public class CmsQuestionAnswerVo implements Serializable {
    private static final long serialVersionUID = -5020082229609587334L;

    private Integer questionId;

    private String optionId;

    private String optionContent;

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getOptionId() {
        return optionId;
    }

    public void setOptionId(String optionId) {
        this.optionId = optionId;
    }

    public String getOptionContent() {
        return optionContent;
    }

    public void setOptionContent(String optionContent) {
        this.optionContent = optionContent == null ? null : optionContent.trim();
    }
}