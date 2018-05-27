package com.chinaredstar.cms.mapper;

import com.chinaredstar.cms.api.model.CmsQuestionnaire;
import com.greatbee.base.bean.DBException;
import com.greatbee.base.manager.ext.AbstractBasicManager;

public class CmsQuestionnaireMapper extends AbstractBasicManager {
    public CmsQuestionnaireMapper() {
        super(CmsQuestionnaire.class);
    }

    public void countUpWillNumByPrimaryKey(int questionnaireId) throws DBException {
        CmsQuestionnaire questionnaire = (CmsQuestionnaire) read(questionnaireId);
        questionnaire.setWillNum(questionnaire.getWillNum() + 1);
        update(questionnaire);
    }
}