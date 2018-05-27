package com.chinaredstar.cms.api.vo.questionnaire;


import java.io.Serializable;

//具体答案选项
public class OptionAnswerVo implements Serializable {

    private String optionId;

    private String optionContent;

    private String optionTag;

    private String optionDescription;

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
        this.optionContent = optionContent;
    }

    public String getOptionTag() {
        return optionTag;
    }

    public void setOptionTag(String optionTag) {
        this.optionTag = optionTag;
    }

    public String getOptionDescription() {
        return optionDescription;
    }

    public void setOptionDescription(String optionDescription) {
        this.optionDescription = optionDescription;
    }
}
