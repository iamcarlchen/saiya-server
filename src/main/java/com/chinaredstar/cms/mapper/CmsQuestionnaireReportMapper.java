package com.chinaredstar.cms.mapper;

import com.chinaredstar.cms.api.model.CmsQuestionnaireReport;
import com.greatbee.base.bean.DBException;
import com.greatbee.base.manager.ext.AbstractBasicManager;

public class CmsQuestionnaireReportMapper extends AbstractBasicManager {
    public CmsQuestionnaireReportMapper() {
        super(CmsQuestionnaireReport.class);
    }

    public void insert(CmsQuestionnaireReport report) throws DBException {
        add(report);
    }
}