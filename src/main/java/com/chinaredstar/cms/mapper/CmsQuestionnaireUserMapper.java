package com.chinaredstar.cms.mapper;

import com.chinaredstar.cms.api.model.CmsQuestionnaireUser;
import com.greatbee.base.bean.DBException;
import com.greatbee.base.manager.ext.AbstractBasicManager;

public class  CmsQuestionnaireUserMapper extends AbstractBasicManager {
    public CmsQuestionnaireUserMapper() {
        super(CmsQuestionnaireUser.class);
    }

    public void insertSelective(CmsQuestionnaireUser user) throws DBException {
        add(user);
    }
}