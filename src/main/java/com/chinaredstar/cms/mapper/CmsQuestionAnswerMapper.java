package com.chinaredstar.cms.mapper;

import com.chinaredstar.cms.api.model.CmsQuestionAnswer;
import com.greatbee.base.bean.DBException;
import com.greatbee.base.manager.ext.AbstractBasicManager;

import java.util.List;

public class CmsQuestionAnswerMapper extends AbstractBasicManager {
    public CmsQuestionAnswerMapper() {
        super(CmsQuestionAnswer.class);
    }

    public void insertMulti(List<CmsQuestionAnswer> answers ) throws DBException {
        add(answers);
    }
}