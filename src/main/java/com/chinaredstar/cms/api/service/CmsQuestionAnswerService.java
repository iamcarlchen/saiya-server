package com.chinaredstar.cms.api.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.vo.questionnaire.CmsQuestionnaireAnswerVo;

/**
 * Created by yuxin.zou on 2017/11/8.
 */
public interface CmsQuestionAnswerService {
    /**
     * 保存问卷调查答案
     *
     * @param saveVo
     * @return
     */
    ServiceResult saveQuestionAnswer(CmsQuestionnaireAnswerVo saveVo);
}
