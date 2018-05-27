package com.chinaredstar.cms.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.model.CmsQuestionAnswer;
import com.chinaredstar.cms.api.model.CmsQuestionnaireReport;
import com.chinaredstar.cms.api.model.CmsQuestionnaireUser;
import com.chinaredstar.cms.api.service.CmsQuestionAnswerService;
import com.chinaredstar.cms.api.vo.questionnaire.CmsQuestionAnswerVo;
import com.chinaredstar.cms.api.vo.questionnaire.CmsQuestionnaireAnswerVo;
//import com.chinaredstar.cms.mapper.CmsQuestionAnswerMapper;
//import com.chinaredstar.cms.mapper.CmsQuestionnaireMapper;
//import com.chinaredstar.cms.mapper.CmsQuestionnaireReportMapper;
//import com.chinaredstar.cms.mapper.CmsQuestionnaireUserMapper;
//import com.chinaredstar.perseus.utils.JsonUtil;
import com.greatbee.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by yuxin.zou on 2017/11/8.
 */
@Service("cmsQuestionAnswerService")
public class CmsQuestionAnswerServiceImpl implements CmsQuestionAnswerService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

//    @Autowired
//    private CmsQuestionAnswerMapper cmsQuestionAnswerMapper;
//
//    @Autowired
//    private CmsQuestionnaireMapper cmsQuestionnaireMapper;
//
//    @Autowired
//    private CmsQuestionnaireReportMapper cmsQuestionnaireReportMapper;
//
//    @Autowired
//    private CmsQuestionnaireUserMapper cmsQuestionnaireUserMapper;

    @Override
    public ServiceResult saveQuestionAnswer(CmsQuestionnaireAnswerVo saveVo) {
        ServiceResult serviceResult = new ServiceResult<>(false);
        if (saveVo == null) {
            serviceResult.setMsg("参数不能为空");
            return serviceResult;
        }
        if (saveVo.getQuestionnaireId() == null) {
            serviceResult.setMsg("问卷ID不能为空");
            return serviceResult;
        }
       /* if (StringUtils.isEmpty(saveVo.getUserName())) {
            serviceResult.setMsg("用户名不能为空");
            return serviceResult;
        }*/
        /*if (StringUtils.isEmpty(saveVo.getUserIp())) {
            serviceResult.setMsg("用户IP不能为空");
            return serviceResult;
        }*/
        if (saveVo.getQuestionAnswers() == null) {
            serviceResult.setMsg("答案不能为空");
            return serviceResult;
        }
        for (CmsQuestionAnswerVo answerVo : saveVo.getQuestionAnswers()) {
            if (answerVo.getQuestionId() == null) {
                serviceResult.setMsg("问题ID不能为空");
                return serviceResult;
            }
            if (answerVo.getOptionId() == null) {
                serviceResult.setMsg("选项ID不能为空");
                return serviceResult;
            }
        }
        try {


            // userId:如用手机号存在则用手机号, 否则生成UUID
            String userId = StringUtil.isInvalid(saveVo.getMobile()) ? genUserId() : saveVo.getMobile();
            List<CmsQuestionAnswer> answers = new ArrayList<>();
            for (CmsQuestionAnswerVo answerVo : saveVo.getQuestionAnswers()) {
                CmsQuestionAnswer targetAnswer = new CmsQuestionAnswer();
                BeanUtils.copyProperties(saveVo, targetAnswer);
                BeanUtils.copyProperties(answerVo, targetAnswer);
                targetAnswer.setCreateTime(new Date());
                targetAnswer.setUserId(userId);
                answers.add(targetAnswer);
            }

            //判断来源是否为APP
//            if(StringUtils.isNotBlank(saveVo.getOpenId())){
//                //根据openId去用户中心拿用户信息
//                com.chinaredstar.uc.dubbo.core.api.vo.ServiceResult<UsersVo> userServiceResult=userService.getUserInfoByOpenid(saveVo.getOpenId());
//                if(userServiceResult.isOk() && null!=userServiceResult.getData()){
//                    UsersVo usersVo = userServiceResult.getData();
//                    if(StringUtils.isNotBlank(usersVo.getMobile())){
//                        saveVo.setMobile(usersVo.getMobile());
//                        userId = usersVo.getMobile();
//                    }
//                    if(StringUtils.isNotBlank(usersVo.getUsername())){
//                        saveVo.setUserName(usersVo.getUsername());
//                    }
//                }else{
//                    logger.error("根据openID:{}获取用户信息异常:{}",saveVo.getOpenId(),JsonUtil.toJson(userServiceResult,false));
//                }
//
//            }
            //TODO
//            cmsQuestionAnswerMapper.insertMulti(answers);
//            cmsQuestionnaireMapper.countUpWillNumByPrimaryKey(saveVo.getQuestionnaireId());

            // 添加用户信息, 以用户手机号为依据
            saveUser(saveVo);

            // 添加用户问卷报表记录
            saveReport(saveVo, userId);

            serviceResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("保存调查问卷答案失败, {}", e);
            e.printStackTrace();
            serviceResult.setMsg("保存调查问卷答案失败");
        }

        return serviceResult;
    }

    /**
     * 保存用户信息, 手机号必填
     *
     * @param saveVo
     */
    private void saveUser(CmsQuestionnaireAnswerVo saveVo) {
        if (StringUtil.isValid(saveVo.getMobile())) {
            CmsQuestionnaireUser user = new CmsQuestionnaireUser();
            user.setAddress(saveVo.getAddress());
            user.setArea(saveVo.getArea());
            user.setCity(saveVo.getCity());
            user.setMobile(saveVo.getMobile());
            user.setProvince(saveVo.getProvince());
            user.setQuestionnaireId(saveVo.getQuestionnaireId());
            user.setUserIp(saveVo.getUserIp());
            user.setUserName(saveVo.getUserName());
            user.setCreateTime(new Date());
            //TODO
//            cmsQuestionnaireUserMapper.insertSelective(user);
        }
    }

    private void saveReport(CmsQuestionnaireAnswerVo saveVo, String userId) {
        CmsQuestionnaireReport report = new CmsQuestionnaireReport();
        report.setQuestionnaireId(saveVo.getQuestionnaireId());
        report.setUserId(userId);
        report.setUserIp(saveVo.getUserIp());
        report.setPhone(saveVo.getMobile());
        report.setUserName(saveVo.getUserName());
        report.setCreateTime(new Date());
//        report.setAnswerContent(JsonUtil.toJson(saveVo.getAnswerContents(), false));
        report.setAnswerContent(JSONArray.toJSONString(saveVo.getAnswerContents()));
        //TODO
//        cmsQuestionnaireReportMapper.insert(report);
    }

    /**
     * 生成用户ID(UUID)
     *
     * @return 用户ID
     */
    private static String genUserId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
