package com.chinaredstar.cms.api.model;

import java.io.Serializable;

/**
 * CMS Questionnaire
 *
 */
public class CmsQuestionnaire implements Serializable {
    private Integer id;
    private Integer willNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWillNum() {
        return willNum;
    }

    public void setWillNum(Integer willNum) {
        this.willNum = willNum;
    }
}