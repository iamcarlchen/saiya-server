package com.chinaredstar.cms.api.vo.questionnaire;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ykk on 2017/12/21.
 */
public class AnswerContentVo implements Serializable {

    private Integer questionId;

    private String questionType;

    private Integer questionNo;

    private List<OptionAnswerVo> optionAnswers = new ArrayList<>();

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public Integer getQuestionNo() {
        return questionNo;
    }

    public void setQuestionNo(Integer questionNo) {
        this.questionNo = questionNo;
    }

    public List<OptionAnswerVo> getOptionAnswers() {
        return optionAnswers;
    }

    public void setOptionAnswers(List<OptionAnswerVo> optionAnswers) {
        this.optionAnswers = optionAnswers;
    }
}